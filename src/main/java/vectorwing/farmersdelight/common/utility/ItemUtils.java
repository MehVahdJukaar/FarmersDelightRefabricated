package vectorwing.farmersdelight.common.utility;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemHandlerHelper;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * Util for handling ItemStacks and inventories containing them.
 */
public class ItemUtils
{
	public static void dropItems(Level level, BlockPos pos, ItemStackHandler inventory) {
		for (int slot = 0; slot < inventory.getSlotCount(); slot++)
			Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
	}

	public static boolean isInventoryEmpty(Container inventory) {
		return inventory.isEmpty();
	}

	public static void spawnItemEntity(Level level, ItemStack stack, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
		ItemEntity entity = new ItemEntity(level, x, y, z, stack);
		entity.setDeltaMovement(xMotion, yMotion, zMotion);
		level.addFreshEntity(entity);
	}

	// Equivalent of Forge's IItemHandler#insertItem.
	public static ItemStack insertItem(ItemStackHandlerContainer container, int slot, @NotNull ItemStack stack, boolean simulate) {
		if (container.indexInvalid(slot))
			return stack;

		ItemStack existing = container.getItem(slot);
		int limit = Math.min(container.getSlotLimit(slot), stack.getMaxStackSize());

		if (!existing.isEmpty()) {
			if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
				return stack;

			limit -= existing.getCount();
		}

		if (limit <= 0)
			return stack;

		boolean reachedLimit = stack.getCount() > limit;

		ItemStack setStack;
		if (existing.isEmpty()) {
			setStack = stack;
		} else {
			// TODO: Figure out if the game will freeze upon setting the item to the already existing item.
			setStack = existing;
			setStack.grow(reachedLimit ? limit : stack.getCount());
		}
		// It is required to set the stack for syncing purposes.
		container.setItem(slot, setStack);

		return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
	}
}
