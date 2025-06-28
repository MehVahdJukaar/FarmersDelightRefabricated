package vectorwing.farmersdelight.refabricated.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.minecraft.world.item.ItemStack;

public class ItemHandlerStackWrapper extends SingleStackStorage {
    private final ItemHandler handler;
    private final int slot;

    public ItemHandlerStackWrapper(ItemHandler handler, int slot) {
        this.handler = handler;
        this.slot = slot;
    }

    @Override
    protected ItemStack getStack() {
        return handler.getStackInSlot(slot);
    }

    @Override
    protected void setStack(ItemStack stack) {
        handler.setStackInSlot(slot, stack);
    }

    @Override
    protected int getCapacity(ItemVariant itemVariant) {
        if (itemVariant.isBlank())
            return handler.getSlotLimit(slot);

        return Math.min(handler.getSlotLimit(slot), itemVariant.toStack().getMaxStackSize());
    }
}
