package vectorwing.farmersdelight.client.recipebook;

import com.google.common.collect.ImmutableList;
import io.github.fabricators_of_create.porting_lib.recipe_book_categories.RecipeBookRegistry;
import net.minecraft.client.RecipeBookCategories;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;

import java.util.function.Supplier;

public class RecipeCategories
{
	// Moved initalizers to RecipeBookCategoriesMixin.
	public static Supplier<RecipeBookCategories> COOKING_SEARCH;
	public static Supplier<RecipeBookCategories> COOKING_MEALS;
	public static Supplier<RecipeBookCategories> COOKING_DRINKS;
	public static Supplier<RecipeBookCategories> COOKING_MISC;

	public static void init() {
		RecipeBookRegistry.registerBookCategories(FarmersDelight.RECIPE_TYPE_COOKING, ImmutableList.of(COOKING_SEARCH.get(), COOKING_MEALS.get(), COOKING_DRINKS.get(), COOKING_MISC.get()));
		RecipeBookRegistry.registerAggregateCategory(COOKING_SEARCH.get(), ImmutableList.of(COOKING_MEALS.get(), COOKING_DRINKS.get(), COOKING_MISC.get()));
		RecipeBookRegistry.registerRecipeCategoryFinder(ModRecipeTypes.COOKING.get(), recipe ->
		{
			if (recipe instanceof CookingPotRecipe cookingRecipe) {
				CookingPotRecipeBookTab tab = cookingRecipe.getRecipeBookTab();
				if (tab != null) {
					return switch (tab) {
						case MEALS -> COOKING_MEALS.get();
						case DRINKS -> COOKING_DRINKS.get();
						case MISC -> COOKING_MISC.get();
					};
				}
			}
			return COOKING_MISC.get();
		});
	}
}
