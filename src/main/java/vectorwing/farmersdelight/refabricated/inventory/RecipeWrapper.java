package vectorwing.farmersdelight.refabricated.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;

/**
 * Refabricated: Wrapper for ItemStackHandler.
 */
public class RecipeWrapper implements Container {

	private final ItemHandler handler;
	private final StackedContents stackedContents;
	private final int ingredientAmount;

	public RecipeWrapper(ItemHandler handler) {
		this.handler = handler;
		this.stackedContents = new StackedContents();
		int ingredientAmount = 0;
		for (int value : handler.getInputSlotIndexes()) {
			ItemStack itemstack = handler.getStackInSlot(value);
			if (!itemstack.isEmpty()) {
				++ingredientAmount;
				stackedContents.accountStack(itemstack, 1);
			}
		}
		this.ingredientAmount = ingredientAmount;
	}

	public StackedContents stackedContents() {
		return stackedContents;
	}

	public int ingredientAmount() {
		return ingredientAmount;
	}

	@Override
	public int getContainerSize() {
		return handler.getSlotCount();
	}

	@Override
	public boolean isEmpty() {
		return handler.getSlots().isEmpty();
	}

	@Override
	public ItemStack getItem(int slot) {
		return handler.getStackInSlot(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		return handler.removeItem(slot, amount);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return handler.extractItem(slot, Integer.MAX_VALUE, false);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		handler.setStackInSlot(slot, stack);
	}

	@Override
	public void setChanged() {

	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public void clearContent() {

	}
}