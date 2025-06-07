package vectorwing.farmersdelight.integration.jei.resource;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;

public class DoughRecipeMaker
{
	public static List<CraftingRecipe> createRecipe() {
		NonNullList<Ingredient> inputs = NonNullList.of(
				Ingredient.EMPTY,
				Ingredient.of(Items.WHEAT),
				Ingredient.of(Items.WATER_BUCKET)
		);

		ItemStack output = new ItemStack(ModItems.WHEAT_DOUGH.get());
		String path = FarmersDelight.MODID + ".dough";

		ResourceLocation id = new ResourceLocation(path);
		return List.of(new ShapelessRecipe(id, path, CraftingBookCategory.MISC, output, inputs));
	}
}
