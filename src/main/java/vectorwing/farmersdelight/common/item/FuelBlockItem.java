package vectorwing.farmersdelight.common.item;

import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;

public class FuelBlockItem extends BlockItem
{
	public final int burnTime;

	public FuelBlockItem(Block block, Properties properties) {
		this(block, properties, 100);
	}

	public FuelBlockItem(Block block, Properties properties, int burnTime) {
		super(block, properties);
		this.burnTime = burnTime;
        FuelValueEvents.BUILD.register((builder, context) -> {
			builder.add(this, burnTime);
		});
	}

	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		return this.burnTime;
	}
}
