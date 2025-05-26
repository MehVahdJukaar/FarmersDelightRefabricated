package vectorwing.farmersdelight.common.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandler;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

/**
 * Util for handling ItemStacks and inventories containing them.
 */
public class ItemUtils
{
	public static void dropItems(Level level, BlockPos pos, ItemStackHandler inventory) {
		for (int slot = 0; slot < inventory.getSlotCount(); slot++)
			Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(),
					inventory.removeItem(slot));
		inventory.commitModifiedStacks();
	}

	public static boolean isInventoryEmpty(ItemHandler inventory) {
		for (int i = 0; i < inventory.getSlotCount(); ++i) {
			if (!inventory.getStackInSlot(i).isEmpty())
				return false;
		}
		return true;
	}

	public static void spawnItemEntity(Level level, ItemStack stack, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
		ItemEntity entity = new ItemEntity(level, x, y, z, stack);
		entity.setDeltaMovement(xMotion, yMotion, zMotion);
		level.addFreshEntity(entity);
	}
}
