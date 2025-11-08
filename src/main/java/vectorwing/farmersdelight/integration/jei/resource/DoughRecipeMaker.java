package vectorwing.farmersdelight.integration.jei.resource;

import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;

public class DoughRecipeMaker
{
	public static List<RecipeHolder<CraftingRecipe>> createRecipe() {
		NonNullList<Ingredient> inputs = NonNullList.of(
                // This was an empty stack, but JEI complained, and it isn't visible anyway..
				Ingredient.of(Items.WHEAT),
				Ingredient.of(Items.WHEAT),
				Ingredient.of(Items.WATER_BUCKET)
		);

		ItemStack output = new ItemStack(ModItems.WHEAT_DOUGH.get());
		String path = FarmersDelight.MODID + ".dough";

		ResourceLocation id = FarmersDelight.res("wheat_dough_from_water");
		return List.of(new RecipeHolder<>(ResourceKey.create(Registries.RECIPE, id), new ShapelessRecipe(path, CraftingBookCategory.MISC, output, inputs)));
	}
}
