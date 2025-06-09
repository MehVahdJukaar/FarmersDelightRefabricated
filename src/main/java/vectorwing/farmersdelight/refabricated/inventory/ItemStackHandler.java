/**
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 * <href>https://github.com/neoforged/NeoForge/blob/1.21.1/src/main/java/net/neoforged/neoforge/items/wrapper/InvWrapper.java</href>
 * <br>
 * This class uses exact logic from NeoForge's InvWrapper class but with minor cleanup for readability.
 */
package vectorwing.farmersdelight.refabricated.inventory;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;

public class ItemStackHandler extends FabricWrappedInventory {
    private final NonNullList<ItemStack> stacks;

    public ItemStackHandler() {
        this(1);
    }

    public ItemStackHandler(int size) {
        super(size);
        this.stacks = NonNullList.withSize(size, ItemStack.EMPTY);
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
        ListTag listTag = tag.getListOrEmpty("Items");
        for (int i = 0; i < listTag.size(); ++i) {
            CompoundTag itemTag = listTag.getCompoundOrEmpty(i);
            if (itemTag.getInt("Slot").isPresent()) {
                int slot = itemTag.getInt("Slot").get();
                if (slot >= 0 && slot < stacks.size()) {
                    ItemStack.parse(provider, itemTag).ifPresent(stack -> stacks.set(slot, stack));
                }
            }
        }
    }

    protected void onContentsChanged(int slot) {}

    @Deprecated(forRemoval = true, since = "3.1.0")
    public ItemStack removeItem(int slot, int amount, boolean simulate) {
        return extractItem(slot, amount, simulate);
    }

}
