package vectorwing.farmersdelight.common.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.storage.loot.providers.number.ResolvableNumber;
import org.jspecify.annotations.Nullable;

public class FuelBlockItem extends BlockItem
{
	public final int burnTime;

	public FuelBlockItem(Block block, Properties properties) {
		this(block, properties, 100);
	}

	public FuelBlockItem(Block block, Properties properties, int burnTime) {
		super(block, properties.component(DataComponents.COOKING_FUEL, new CookingFuel(new ResolvableNumber.Constant(burnTime), new ResolvableNumber.Constant(1)))); //FIXME
		this.burnTime = burnTime;
	}

	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		return this.burnTime;
	}
}
