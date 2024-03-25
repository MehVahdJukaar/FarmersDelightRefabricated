package vectorwing.farmersdelight.common.block.entity.container;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlotItemHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CookingPotMealSlot extends SlotItemHandler
{
	public CookingPotMealSlot(ItemStackHandler inventoryIn, int index, int xPosition, int yPosition) {
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
