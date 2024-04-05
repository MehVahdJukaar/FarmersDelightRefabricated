package vectorwing.farmersdelight.common.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

;

public class FuelBlockItem extends BlockItem
{
	public final int burnTime;

	public FuelBlockItem(Block block, Properties properties) {
		this(block, properties, 100);
	}

	public FuelBlockItem(Block block, Properties properties, int burnTime) {
		super(block, properties);
		this.burnTime = burnTime;
		FuelRegistry.INSTANCE.add(this, this.burnTime);
	}

	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		return this.burnTime;
	}
}
