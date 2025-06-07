package vectorwing.farmersdelight.refabricated.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.CrashReport;
import net.minecraft.ReportedException;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ItemStackHandler implements ItemHandler {
    private final NonNullList<ItemStack> stacks;
    private final List<ItemHandlerStackWrapper> fabricWrappers;

    public ItemStackHandler() {
        this(1);
    }

    public ItemStackHandler(int size) {
        this.stacks = NonNullList.withSize(size, ItemStack.EMPTY);
        ItemHandlerStackWrapper[] wrappers = new ItemHandlerStackWrapper[size];
        for (int i = 0; i < size; ++i) {
            wrappers[i] = new ItemHandlerStackWrapper(this, i);
        }
        fabricWrappers = Arrays.asList(wrappers);
    }

    public int getSlotCount() {
        return stacks.size();
    }

    public int getSlotLimit(int slot) {
        return 99;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return stacks.get(slot);
    }

    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (stack.isEmpty())
            return ItemStack.EMPTY;
        if (!isItemValid(slot, stack))
            return stack;
        ItemStack existing = stacks.get(slot);
        int limit = getSlotLimit(slot);
        if (!existing.isEmpty()) {
            if (!ItemStack.isSameItemSameComponents(stack, existing)) {
                return stack;
            }
            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;
        if (!simulate) {
            if (existing.isEmpty()) {
                stacks.set(slot, reachedLimit ? stack.copyWithCount(limit) : stack);
            } else {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            onContentsChanged(slot);
        }
        return reachedLimit ? stack.copyWithCount(stack.getCount() - limit) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0)
            return ItemStack.EMPTY;
        ItemStack existing = stacks.get(slot);
        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract) {
            if (!simulate) {
                stacks.set(slot, ItemStack.EMPTY);
                onContentsChanged(slot);
                return existing;
            }
            return existing.copy();
        }

        if (!simulate) {
            stacks.set(slot, existing.copyWithCount(existing.getCount() - toExtract));
            onContentsChanged(slot);
        }
        return existing.copyWithCount(toExtract);
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        stacks.set(slot, stack);
        onContentsChanged(slot);
    }

    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        ListTag listTag = new ListTag();

        for (int i = 0; i < stacks.size(); ++i) {
            if (!stacks.get(i).isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                listTag.add(stacks.get(i).save(provider, itemTag));
            }
        }

        CompoundTag tag = new CompoundTag();
        tag.put("Items", listTag);
        return tag;
    }

    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        stacks.clear();
        ListTag listTag = tag.getList("Items", Tag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); ++i) {
            CompoundTag itemTag = listTag.getCompound(i);
            int slot = itemTag.getInt("Slot");
            if (slot >= 0 && slot < stacks.size()) {
                ItemStack.parse(provider, itemTag).ifPresent(stack -> stacks.set(slot, stack));
            }
        }
    }

    protected void onContentsChanged(int slot) {}

    @Deprecated(forRemoval = true, since = "3.1.0")
    public ItemStack removeItem(int slot, int amount, boolean simulate) {
        return extractItem(slot, amount, simulate);
    }

    @Override
    public SingleSlotStorage<ItemVariant> getSlot(int slot) {
        return fabricWrappers.get(slot);
    }

    @Override
    public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
        long amount = 0;
        try {
            for (ItemHandlerStackWrapper wrapper : fabricWrappers) {
                amount += wrapper.insert(resource, maxAmount - amount, transaction);
                if (amount == maxAmount)
                    return maxAmount;
            }
        } catch (Exception ex) {
            CrashReport report = CrashReport.forThrowable(ex, "Inserting resources into slots");
            report.addCategory("Slotted insertion details")
                    .setDetail("Slots", () -> Objects.toString(fabricWrappers, null))
                    .setDetail("Resource", () -> Objects.toString(resource, null))
                    .setDetail("Max amount", maxAmount)
                    .setDetail("Transaction", transaction);
            throw new ReportedException(report);
        }
        return amount;
    }

    @Override
    public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
        long amount = 0;
        try {
            for (ItemHandlerStackWrapper wrapper : fabricWrappers) {
                amount += wrapper.extract(resource, maxAmount - amount, transaction);
                if (amount == maxAmount)
                    return maxAmount;
            }
        } catch (Exception ex) {
            CrashReport report = CrashReport.forThrowable(ex, "Inserting resources into slots");
            report.addCategory("Slotted insertion details")
                    .setDetail("Slots", () -> Objects.toString(fabricWrappers, null))
                    .setDetail("Resource", () -> Objects.toString(resource, null))
                    .setDetail("Max amount", maxAmount)
                    .setDetail("Transaction", transaction);
            throw new ReportedException(report);
        }
        return amount;
    }

    @Override
    public @NotNull Iterator<StorageView<ItemVariant>> iterator() {
        return getSlots().stream()
                .map(storageViews -> (StorageView<ItemVariant>)storageViews)
                .iterator();
    }
}
