/**
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 * <href>https://github.com/neoforged/NeoForge/blob/1.21.1/src/main/java/net/neoforged/neoforge/items/ItemStackHandler.java</href>
 * <br>
 * This class uses exact logic for insertItem and extractItem from NeoForge's impl but with minor cleanup for readability.
 */
package vectorwing.farmersdelight.refabricated.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;


public class InvWrapper extends FabricWrappedInventory {
    private final Container inv;

    public InvWrapper(Container inv) {
        super(inv.getContainerSize());
        this.inv = inv;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof InvWrapper wrapper)
            return getInv().equals(wrapper.getInv());

        return false;
    }

    @NotNull
    public Container getInv() {
        return this.inv;
    }

    @Override
    public int getSlotCount() {
        return inv.getContainerSize();
    }

    @Override
    public int getSlotLimit(int slot) {
        return inv.getMaxStackSize();
    }

    @Override
    public int getStackLimit(int slot, ItemStack stack) {
        return inv.getMaxStackSize(stack);
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inv.getItem(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        ItemStack stackInSlot = inv.getItem(slot);
        if (!stackInSlot.isEmpty()) {
            if (stackInSlot.getCount() >= Math.min(stackInSlot.getMaxStackSize(), getSlotLimit(slot)) ||
                    ItemStack.isSameItemSameComponents(stack, stackInSlot) ||
                    !inv.canPlaceItem(slot, stack))
                return stack;

            int min = Math.min(stack.getMaxStackSize(), getSlotLimit(slot)) - stackInSlot.getCount();
            if (stack.getCount() <= min) {
                if (!simulate) {
                    ItemStack copy = stack.copy();
                    copy.grow(stackInSlot.getCount());
                    inv.setItem(slot, copy);
                    inv.setChanged();
                }

                return ItemStack.EMPTY;
            }

            ItemStack copy = stack.copy();
            if (!simulate) {
                ItemStack split = copy.split(min);
                split.grow(stackInSlot.getCount());
                inv.setItem(slot, split);
                inv.setChanged();
                return stack;
            }

            copy.shrink(min);
            return copy;
        }

        if (!inv.canPlaceItem(slot, stack))
            return stack;

        int min = Math.min(stack.getMaxStackSize(), getSlotLimit(slot));
        if (min < stack.getCount()) {
            ItemStack copy = stack.copy();
            if (!simulate) {
                inv.setItem(slot, copy.split(min));
                inv.setChanged();
                return stack;
            }
            copy.shrink(min);
            return copy;
        }

        if (!simulate) {
            inv.setItem(slot, stack);
            inv.setChanged();
        }

        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0)
            return ItemStack.EMPTY;
        ItemStack stackInSlot = inv.getItem(slot);
        if (stackInSlot.isEmpty())
            return ItemStack.EMPTY;

        if (simulate) {
            if (stackInSlot.getCount() < amount)
                return stackInSlot.copy();

            ItemStack copy = stackInSlot.copy();
            copy.setCount(amount);
            return copy;
        }

        int min = Math.min(stackInSlot.getCount(), amount);
        ItemStack decreased = inv.removeItem(slot, min);
        inv.setChanged();
        return decreased;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        inv.setItem(slot, stack);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return inv.canPlaceItem(slot, stack);
    }
}
