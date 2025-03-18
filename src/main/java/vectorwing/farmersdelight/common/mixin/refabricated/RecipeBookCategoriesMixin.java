package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;
import vectorwing.farmersdelight.refabricated.client.FDRecipeCategories;

import java.util.List;

@Mixin(RecipeBookCategories.class)
public class RecipeBookCategoriesMixin {
	@Inject(method = "getCategories", at = @At("HEAD"), cancellable = true)
	private static void fdrf$getCustomCategories(RecipeBookType recipeBookType, CallbackInfoReturnable<List<RecipeBookCategories>> cir) {
		if (recipeBookType == FDRecipeBookTypes.COOKING)
			cir.setReturnValue(List.of(FDRecipeCategories.COOKING_SEARCH, FDRecipeCategories.COOKING_MEALS, FDRecipeCategories.COOKING_DRINKS, FDRecipeCategories.COOKING_MISC));
	}
}
