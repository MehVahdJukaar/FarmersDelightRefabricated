package vectorwing.farmersdelight.common.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import org.jetbrains.annotations.Nullable;;

/**
 * Deprecated - Fuel is now defined in a NeoForge datamap: {@link NeoForgeDataMaps#FURNACE_FUELS}.
 */
@Deprecated(forRemoval = true)
public class FuelItem extends Item
{
	public final int burnTime;

	public FuelItem(Properties properties) {
		this(properties, 100);
	}

	public FuelItem(Properties properties, int burnTime) {
		super(properties);
		this.burnTime = burnTime;
		FuelRegistry.INSTANCE.add(this, this.burnTime);
	}

	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		return this.burnTime;
	}
}
