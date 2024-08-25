package vectorwing.farmersdelight.client.recipebook;

import com.google.common.collect.ImmutableList;
import io.github.fabricators_of_create.porting_lib.recipe_book_categories.RecipeBookRegistry;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;

public class RecipeCategories
{
	public static RecipeBookCategories COOKING_SEARCH = RecipeBookCategories.valueOf("FARMERSDELIGHT_COOKING_SEARCH");
	public static RecipeBookCategories COOKING_MEALS = RecipeBookCategories.valueOf("FARMERSDELIGHT_COOKING_MEALS");
	public static RecipeBookCategories COOKING_DRINKS = RecipeBookCategories.valueOf("FARMERSDELIGHT_COOKING_DRINKS");
	public static RecipeBookCategories COOKING_MISC = RecipeBookCategories.valueOf("FARMERSDELIGHT_COOKING_MISC");

	public static void init() {
		RecipeBookRegistry.registerBookCategories(RecipeBookType.valueOf("FARMERSDELIGHT_COOKING"), ImmutableList.of(COOKING_SEARCH, COOKING_MEALS, COOKING_DRINKS, COOKING_MISC));
		RecipeBookRegistry.registerAggregateCategory(COOKING_SEARCH, ImmutableList.of(COOKING_MEALS, COOKING_DRINKS, COOKING_MISC));
		RecipeBookRegistry.registerRecipeCategoryFinder(ModRecipeTypes.COOKING.get(), recipe ->
		{
			if (recipe.value() instanceof CookingPotRecipe cookingRecipe) {
				CookingPotRecipeBookTab tab = cookingRecipe.getRecipeBookTab();
				if (tab != null) {
					return switch (tab) {
						case MEALS -> COOKING_MEALS;
						case DRINKS -> COOKING_DRINKS;
						case MISC -> COOKING_MISC;
					};
				}
			}
			return COOKING_MISC;
		});
	}
}
