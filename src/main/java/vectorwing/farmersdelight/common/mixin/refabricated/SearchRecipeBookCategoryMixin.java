package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vectorwing.farmersdelight.common.registry.ModRecipeBookCategories;

@Mixin(SearchRecipeBookCategory.class)
public enum SearchRecipeBookCategoryMixin {
	FARMERSDELIGHT_COOKING(ModRecipeBookCategories.COOKING_MEALS.get(), ModRecipeBookCategories.COOKING_DRINKS.get(), ModRecipeBookCategories.COOKING_MISC.get());

	@Shadow
	SearchRecipeBookCategoryMixin(RecipeBookCategory... includedCategories) {
	}
}
