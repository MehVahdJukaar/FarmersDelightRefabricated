package vectorwing.farmersdelight.refabricated.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SlotItemHandler extends Slot {
    private static final Container EMPTY_INVENTORY = new SimpleContainer(0);
    private final ItemStackHandler itemHandler;

    // These two fields are required to allow any item from `getItem` to be modified provided you use setChanged() after the modification.
    private ItemStack refStack;
    private ItemStack getStack;

    public SlotItemHandler(ItemStackHandler inventoryIn, int index, int xPosition, int yPosition) {
        super(EMPTY_INVENTORY, index, xPosition, yPosition);
        this.itemHandler = inventoryIn;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return !stack.isEmpty() && itemHandler.isItemValid(getContainerSlot(), stack);
    }

    @Override
    public ItemStack getItem() {
        getStack = itemHandler.getStackInSlot(getContainerSlot());
        refStack = getStack.copy();
        return getStack;
    }

    @Override
    public void set(ItemStack stack) {
        itemHandler.setStackInSlot(getContainerSlot(), stack);
        setChanged();
    }

    @Override
    public ItemStack remove(int amount) {
        ItemStack stack = itemHandler.removeItem(getContainerSlot(), amount, false);
        setChanged();
        return stack;
    }

    @Override
    public int getMaxStackSize() {
        return itemHandler.getSlotLimit(getContainerSlot());
    }

    public ItemHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public void setChanged() {
        if (refStack != null && getStack != null && !ItemStack.matches(refStack, getStack))
            itemHandler.setStackInSlot(getContainerSlot(), getStack);
        refStack = null;
        getStack = null;
    }
}
