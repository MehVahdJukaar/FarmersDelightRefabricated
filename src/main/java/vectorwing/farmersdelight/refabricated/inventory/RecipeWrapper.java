package vectorwing.farmersdelight.refabricated.inventory;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * Refabricated: Wrapper for ItemStackHandler.
 */
public record RecipeWrapper(ItemStackHandler handler) implements RecipeInput {

    @Override
    public ItemStack getItem(int slot) {
        return handler.getStackInSlot(slot);
    }

    @Override
    public int size() {
        return handler.getSlotCount();
    }
}
