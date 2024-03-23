package vectorwing.farmersdelight.common.block.entity.inventory;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.utility.ItemUtils;;

public class CookingPotItemHandler extends ItemStackHandler {
	private static final int SLOTS_INPUT = 6;
	private static final int SLOT_CONTAINER_INPUT = 7;
	private static final int SLOT_MEAL_OUTPUT = 8;
	private final ItemStackHandlerContainer itemHandler;
	private final Direction side;

	public CookingPotItemHandler(ItemStackHandlerContainer itemHandler, @Nullable Direction side) {
		this.itemHandler = itemHandler;
		this.side = side;
	}

	// By default, ItemStackHandler on Forge will return true, so it's probably safe to
	// return it here.
	public boolean isItemValid(int slot, @NotNull ItemStack stack) {
		return true;
	}

	@Override
	public int getSlotCount() {
		return itemHandler.getSlotCount();
	}

	@Override
	@NotNull
	public ItemStack getStackInSlot(int slot) {
		return itemHandler.getStackInSlot(slot);
	}

	@NotNull
	public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT ? ItemUtils.insertItem(itemHandler, slot, stack, simulate) : stack;
		} else {
			return slot == SLOT_CONTAINER_INPUT ? ItemUtils.insertItem(itemHandler, slot, stack, simulate) : stack;
		}
	}

	@NotNull
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT ? itemHandler.removeItem(slot, amount) : ItemStack.EMPTY;
		} else {
			return slot == SLOT_MEAL_OUTPUT ? itemHandler.removeItem(slot, amount) : ItemStack.EMPTY;
		}
	}

	@Override
	public int getSlotLimit(int slot) {
		return itemHandler.getSlotLimit(slot);
	}
}
