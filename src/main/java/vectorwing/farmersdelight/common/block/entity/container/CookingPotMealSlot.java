package vectorwing.farmersdelight.common.block.entity.container;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandlerContainer;
import vectorwing.farmersdelight.refabricated.inventory.SlotItemHandler;

public class CookingPotMealSlot extends SlotItemHandler
{
	public CookingPotMealSlot(ItemStackHandlerContainer inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	@Override
	public boolean mayPickup(Player playerIn) {
		return false;
	}
}
