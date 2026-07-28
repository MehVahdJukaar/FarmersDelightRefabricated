package vectorwing.farmersdelight.common.utility;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemHandlerHelper;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.refabricated.ItemAbility;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

/**
 * Util for handling ItemStacks and inventories containing them.
 */
public class ItemUtils
{
	/**
	 * Shorthand method for checking if the given stack either has a required ToolAction, or is otherwise part of a given tag.
	 * @param toolAction The ToolAction to check for
	 * @param fallbackTag An item tag to check for, if the given ToolAction is absent
	 * @return true if either condition matches
	 */
	public static boolean isValidTool(ItemStack stack, ItemAbility toolAction, TagKey<Item> fallbackTag) {
		return toolAction.canPerformAction(stack) || stack.is(fallbackTag);	}

	public static boolean isKnife(ItemStack stack) {
		return isValidTool(stack, KnifeItem.KNIFE_HARVEST, ModTags.Items.KNIVES);
	}

	public static void dropItems(Level level, BlockPos pos, ItemStackHandler inventory) {
		for (int slot = 0; slot < inventory.getSlotCount(); slot++)
			Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
	}

	@Deprecated(forRemoval = true)
	public static void dropItems(Level level, BlockPos pos, io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler inventory) {
		for(int slot = 0; slot < inventory.getSlotCount(); ++slot) {
			Containers.dropItemStack(level, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), inventory.getStackInSlot(slot));
		}
	}

	@Deprecated(forRemoval = true)
	public static boolean isInventoryEmpty(Container inventory) {
		return inventory.isEmpty();
	}

	public static void clearItems(ItemStackHandler inventory) {
		for (int i = 0; i < inventory.getSlotCount(); i++) {
			inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
	}

	public static boolean doesInventoryHaveItems(ItemStackHandler inventory) {
		for (int i = 0; i < inventory.getSlotCount(); i++) {
			if (!inventory.getStackInSlot(i).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Shorthand method for spawning an item entity: creation, setting delta movement, and adding to the given level.
	 */
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
