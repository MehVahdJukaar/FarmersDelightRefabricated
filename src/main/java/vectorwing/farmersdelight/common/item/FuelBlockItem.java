package vectorwing.farmersdelight.common.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import org.jetbrains.annotations.Nullable;;

/**
 * Deprecated - Fuel is now defined in a NeoForge datamap: {@link NeoForgeDataMaps#FURNACE_FUELS}.
 */
@Deprecated(forRemoval = true)
public class FuelBlockItem extends BlockItem
{
	public final int burnTime;

	public FuelBlockItem(Block block, Properties properties) {
		this(block, properties, 100);
	}

	public FuelBlockItem(Block block, Properties properties, int burnTime) {
		super(block, properties);
		this.burnTime = burnTime;
        FuelRegistry.INSTANCE.add(this, burnTime);
	}

	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		return this.burnTime;
	}
}
