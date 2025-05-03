package vectorwing.farmersdelight.refabricated.inventory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

// Made into a Fabric Storage Wrapper so we can have minimal changes compared to the NeoForge branch.
public class ItemStackHandler implements ItemHandler {
    private final List<ItemStackStorage> slots;
    // Required to allow ItemStacks obtained from getStackInSlot to be directly modified.
    private final Cache<Integer, StackReference> stackRefs = CacheBuilder.newBuilder()
            .expireAfterWrite(15, TimeUnit.SECONDS) // 15s is overkill for most use cases, but it's to be safe.
            .build();

    public ItemStackHandler() {
        this(1);
    }

    public ItemStackHandler(int size) {
        this.slots = new ObjectArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            slots.add(new ItemStackStorage(i, this));
        }
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        var slotRef = slots.get(slot);
        try {
            StackReference ref = stackRefs.get(slot, () -> {
                var stack = slotRef.getResource().toStack((int) slotRef.getAmount());
                return new StackReference(stack.copy(), stack);
            });
            return ref.current();

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commitModifiedStacks() {
        var stackMap = stackRefs.asMap();
        if (stackMap.isEmpty())
            return;
        for (Map.Entry<Integer, StackReference> stackRef : stackMap.entrySet()) {
            if (!ItemStack.matches(stackRef.getValue().original(), stackRef.getValue().current())) {
                setStackInSlotInternal(stackRef.getKey(), stackRef.getValue().current());
            }
            stackRefs.invalidate(stackRef.getKey());
        }
        stackRefs.cleanUp();
    }

    public void setStackInSlot(int slot, ItemStack stack) {
        setStackInSlotInternal(slot, stack);

        commitModifiedStacks();
    }

    //TODO: this is horrible
    private void setStackInSlotInternal(int slot, ItemStack stack) {
        if (!getSlot(slot).isResourceBlank())
            removeItem(slot, getSlotLimit(slot), false);
        insertItem(slot, stack, false);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return removeItem(slot, amount, false);
    }

    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        stackRefs.invalidate(slot);
        if (stack.isEmpty())
            return stack;
        try (Transaction transaction = Transaction.openOuter()) {
            stack = stack.copyWithCount(stack.getCount() - (int) insertSlot(slot, ItemVariant.of(stack), stack.getCount(), transaction));
            if (!simulate)
                transaction.commit();
        }
        onContentsChanged(slot);
        return stack;
    }

    public @NotNull ItemStack removeItem(int slot, int amount, boolean simulate) {
        stackRefs.invalidate(slot);
        ItemStack stack;
        try (Transaction transaction = Transaction.openOuter()) {
            stack = getStackInSlot(slot).copyWithCount((int) extractSlot(slot, ItemVariant.of(getStackInSlot(slot)), amount, transaction));
            if (!simulate)
                transaction.commit();
        }
        onContentsChanged(slot);
        return stack;
    }

    public int getSlotLimit(int slot) {
        return 64;
    }

    public int getStackLimit(int slot, ItemVariant resource) {
        return Math.min(getSlotLimit(slot), resource.getItem().getDefaultMaxStackSize());
    }

    public int getSlotCount() {
        return slots.size();
    }

    @Override
    public SingleSlotStorage<ItemVariant> getSlot(int slot) {
        return slots.get(slot);
    }

    @Override
    public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
        StoragePreconditions.notBlankNotNegative(resource, maxAmount);
        long inserted = 0;
        for (Iterator<ItemStackStorage> it = getInsertableSlotsFor(resource); it.hasNext(); ) {
            ItemStackStorage storage = it.next();
            long thisInsert = storage.insert(resource, maxAmount, transaction);
            if (thisInsert > 0) {
                onContentsChanged(storage.index);
                inserted += thisInsert;
            }
            if (inserted >= maxAmount)
                break;
        }
        return inserted;
    }

    @Override
    public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
        StoragePreconditions.notBlankNotNegative(resource, maxAmount);
        SortedSet<ItemStackStorage> slots = getSlotsContaining(resource);
        if (slots.isEmpty())
            return 0;
        long extracted = 0;
        for (ItemStackStorage storage : slots) {
            long thisExtract = storage.extract(resource, maxAmount, transaction);
            if (thisExtract > 0) {
                onContentsChanged(storage.index);
                extracted += thisExtract;
            }
            if (extracted >= maxAmount)
                break;
        }
        return extracted;
    }

    @Override
    public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
        if (resource.isBlank())
            return 0;
        long amount = slots.get(slot).insert(resource, maxAmount, transaction);
        onContentsChanged(slot);
        return amount;
    }

    @Override
    public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
        if (resource.isBlank())
            return 0;
        long amount = slots.get(slot).extract(resource, maxAmount, transaction);
        onContentsChanged(slot);
        return amount;
    }

    public SortedSet<ItemStackStorage> getSlotsContaining(ItemVariant resource) {
        return slots.stream().filter(storageViews -> storageViews.getResource().equals(resource)).collect(Collectors.toCollection(ObjectLinkedOpenHashSet::new));
    }

    public Iterator<ItemStackStorage> getInsertableSlotsFor(ItemVariant resource) {
        return slots.stream()
                .filter(views -> views.isResourceBlank() || views.getResource().equals(resource))
                .iterator();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Iterator<StorageView<ItemVariant>> iterator() {
        return (Iterator) getSlots().iterator();
    }


    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        ListTag listTag = new ListTag();

        for (int i = 0; i < slots.size(); ++i) {
            if (!slots.get(i).isResourceBlank()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                slots.get(i).writeNbt(itemTag, provider);
                listTag.add(itemTag);
            }
        }

        CompoundTag tag = new CompoundTag();
        tag.put("Items", listTag);
        return tag;
    }

    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        for (int i = 0; i < slots.size(); ++i) {
            setStackInSlot(i, ItemStack.EMPTY);
        }
        ListTag listTag = tag.getList("Items", Tag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); ++i) {
            CompoundTag compound = listTag.getCompound(i);
            slots.get(compound.getInt("Slot")).readNbt(compound, provider);
        }
    }

    protected void onContentsChanged(int slot) {

    }

    protected record StackReference(ItemStack original, ItemStack current) {

    }
}
