package vectorwing.farmersdelight.refabricated.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SlotItemHandler extends Slot {
    private static final Container EMPTY_INVENTORY = new SimpleContainer(0);
    private final ItemHandler itemHandler;
    protected final int index;

    public SlotItemHandler(ItemStackHandler inventoryIn, int index, int xPosition, int yPosition) {
        super(EMPTY_INVENTORY, index, xPosition, yPosition);
        this.itemHandler = inventoryIn;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public ItemHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return !stack.isEmpty() && itemHandler.isItemValid(index, stack);
    }

    @Override
    public ItemStack getItem() {
        return itemHandler.getStackInSlot(index);
    }

    @Override
    public void set(ItemStack stack) {
        itemHandler.setStackInSlot(index, stack);
        setChanged();
    }

    @Override
    public int getMaxStackSize() {
        return itemHandler.getSlotLimit(index);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        int maxInput = stack.getMaxStackSize();
        int remainder;
        try (Transaction transaction = Transaction.openOuter()) {
            remainder = (int) itemHandler.insertSlot(index, ItemVariant.of(stack), maxInput, transaction);
        }
        return maxInput - remainder;
    }
}
