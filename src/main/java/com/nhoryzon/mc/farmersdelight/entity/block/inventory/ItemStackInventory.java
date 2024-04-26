package com.nhoryzon.mc.farmersdelight.entity.block.inventory;

import com.nhoryzon.mc.farmersdelight.exception.SlotInvalidRangeException;
import com.nhoryzon.mc.farmersdelight.util.CompoundTagUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Deprecated(forRemoval = true)
@FunctionalInterface
public interface ItemStackInventory extends Container {
    NonNullList<ItemStack> getItems();

    static ItemStackInventory of(Inventory inventory) {
        return of(1);
    }

    static ItemStackInventory of(int inventorySize) {
        return () -> NonNullList.withSize(inventorySize, ItemStack.EMPTY);
    }

    static boolean canBeStacked(ItemStack left, ItemStack right) {
        if (left.isEmpty() || !ItemStack.isSameItem(left, right) || left.hasTag() != right.hasTag()) {
            return false;
        }
        return (!left.hasTag() || left.getTag().equals(right.getTag()));
    }

    static ItemStack copyStackWithNewSize(ItemStack itemStack, int newSize) {
        if (newSize == 0) {
            return ItemStack.EMPTY;
        }
        ItemStack copy = itemStack.copy();
        copy.setCount(newSize);

        return copy;
    }

    @Override
    default int getContainerSize() {
        return getItems().size();
    }

    @Override
    default boolean isEmpty() {
        return getItems().stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    default ItemStack getItem(int slot) {
        validateSlotIndex(slot);
        return getItems().get(slot);
    }

    @Override
    default ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(getItems(), slot, amount);
        if (!result.isEmpty()) {
            setChanged();
            onContentsChanged(slot);
        }
        return result;
    }

    @Override
    default ItemStack removeItemNoUpdate(int slot) {
        ItemStack result = ContainerHelper.takeItem(getItems(), slot);
        if (!result.isEmpty()) {
            setChanged();
            onContentsChanged(slot);
        }
        return result;
    }

    @Override
    default void setItem(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getMaxCountForSlot(slot)) {
            stack.setCount(stack.getMaxStackSize());
        }
        onContentsChanged(slot);
    }

    @Override
    default void clearContent() {
        getItems().clear();
    }

    @Override
    default void setChanged() {
        // No-op
    }

    @Override
    default boolean stillValid(Player player){
        return true;
    }

    default void onContentsChanged(int slot) {

    }

    default void validateSlotIndex(int slot) {
        if (slot < 0 || slot >= getContainerSize())
            throw new SlotInvalidRangeException(slot, getContainerSize());
    }

    default ItemStack insertStack(int slot, ItemStack stack, boolean simulate) {
        if (stack.isEmpty() || !canPlaceItem(slot, stack)) {
            return stack;
        }

        ItemStack invItemStack = getItem(slot);
        int limit = getStackLimit(slot, invItemStack);

        if (!invItemStack.isEmpty()) {
            if (!canBeStacked(stack, invItemStack)) {
                return stack;
            }

            limit -= invItemStack.getCount();
        }

        if (limit <= 0) {
            return stack;
        }

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate) {
            if (invItemStack.isEmpty()) {
                setItem(slot, reachedLimit ? copyStackWithNewSize(stack, limit) : stack);
            } else {
                invItemStack.grow(reachedLimit ? limit : stack.getCount());
            }

            onContentsChanged(slot);
            setChanged();
        }

        return reachedLimit ? copyStackWithNewSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
    }

    default int getMaxCountForSlot(int slot) {
        return 64;
    }

    default int getStackLimit(int slot, ItemStack stack) {
        return Math.min(getMaxCountForSlot(slot), stack.getMaxStackSize());
    }

    default void readInventoryNbt(CompoundTag tag) {
        ContainerHelper.loadAllItems(tag.getCompound(CompoundTagUtils.TAG_KEY_INVENTORY), getItems());
    }

    default void writeInventoryNbt(CompoundTag tag) {
        tag.put(CompoundTagUtils.TAG_KEY_INVENTORY, ContainerHelper.saveAllItems(new CompoundTag(), getItems()));
    }
}
