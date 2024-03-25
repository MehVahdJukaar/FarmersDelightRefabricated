package vectorwing.farmersdelight.common.utility;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;;
import java.util.Random;

/**
 * Util for providing and calculating math-related objects across the mod.
 */
public class MathUtils
{
	public static final Random RAND = new Random();

	/**
	 * Calculates a comparator signal using an ItemHandler inventory, instead of IInventory.
	 * Employing a RecipeWrapper would have caused a divide-by-zero, hence why this method was made.
	 *
	 * @param handler The inventory to compare.
	 * @return The redstone signal strength.
	 */
	public static int calcRedstoneFromItemHandler(@Nullable ItemStackHandler handler) {
		if (handler == null) {
			return 0;
		} else {
			int i = 0;
			float f = 0.0F;

			for (int j = 0; j < handler.getSlotCount(); ++j) {
				ItemStack itemstack = handler.getStackInSlot(j);
				if (!itemstack.isEmpty()) {
					f += (float) itemstack.getCount() / (float) Math.min(handler.getSlotLimit(j), itemstack.getMaxStackSize());
					++i;
				}
			}

			f = f / (float) handler.getSlotCount();
			return net.minecraft.util.Mth.floor(f * 14.0F) + (i > 0 ? 1 : 0);
		}
	}
}
