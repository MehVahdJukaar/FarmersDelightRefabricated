package vectorwing.farmersdelight.common.item;

import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import org.jspecify.annotations.Nullable;

public class FuelItem extends Item
{
	public final int burnTime;

	public FuelItem(Properties properties) {
		this(properties, 100);
	}

	public FuelItem(Properties properties, int burnTime) {
		super(properties);
		this.burnTime = burnTime;
        FuelValueEvents.BUILD.register((builder, context) -> {
			builder.add(this, burnTime);
		});
	}

	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		return this.burnTime;
	}
}
