package vectorwing.farmersdelight.refabricated.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SlotItemHandler extends Slot {
    private static final Container EMPTY_INVENTORY = new SimpleContainer(0);
    private final ItemStackHandler itemHandler;

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
        return itemHandler.getStackInSlot(getContainerSlot());
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

    @Override
    public int getMaxStackSize(ItemStack stack) {
        int maxInput = stack.getMaxStackSize();
        int remainder;
        try (Transaction transaction = Transaction.openOuter()) {
            remainder = (int) itemHandler.insertSlot(getContainerSlot(), ItemVariant.of(stack), maxInput, transaction);
        }
        return remainder;
    }

    public ItemHandler getItemHandler() {
        return itemHandler;
    }

}
