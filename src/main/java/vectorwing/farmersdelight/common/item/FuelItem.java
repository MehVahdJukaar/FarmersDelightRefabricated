package vectorwing.farmersdelight.common.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.item.crafting.RecipeType;

import net.minecraft.world.level.storage.loot.providers.number.ResolvableNumber;
import org.jspecify.annotations.Nullable;

public class FuelItem extends Item
{
	public final int burnTime;

	public FuelItem(Properties properties) {
		this(properties, 100);
	}

	public FuelItem(Properties properties, int burnTime) {
		super(properties.component(DataComponents.COOKING_FUEL, new CookingFuel(new ResolvableNumber.Constant(burnTime), new ResolvableNumber.Constant(1)))); //FIXME
		this.burnTime = burnTime;
	}

	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		return this.burnTime;
	}
}
