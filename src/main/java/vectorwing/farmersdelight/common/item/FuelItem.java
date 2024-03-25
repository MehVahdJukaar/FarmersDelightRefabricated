package vectorwing.farmersdelight.common.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.crafting.RecipeType;

import org.jetbrains.annotations.Nullable;;

import net.minecraft.world.item.Item.Properties;

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
