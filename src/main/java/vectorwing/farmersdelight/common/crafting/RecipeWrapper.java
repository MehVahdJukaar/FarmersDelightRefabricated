package vectorwing.farmersdelight.common.crafting;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * Refabricated: Wrapper for ItemStackHandler.
 */
public class RecipeWrapper implements RecipeInput {

    private final ItemStackHandler handler;

    public RecipeWrapper(ItemStackHandler handler) {
        this.handler = handler;
    }

    public ItemStackHandler getHandler() {
        return handler;
    }

    @Override
    public ItemStack getItem(int slot) {
        return handler.getStackInSlot(slot);
    }

    @Override
    public int size() {
        return handler.getSlotCount();
    }
}
