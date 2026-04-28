package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;
import vectorwing.farmersdelight.refabricated.client.FDRecipeCategories;

import java.util.List;

@Mixin(RecipeBookCategories.class)
public enum RecipeBookCategoriesMixin {
	FARMERSDELIGHT_COOKING_SEARCH(new ItemStack(Items.COMPASS)),
	FARMERSDELIGHT_COOKING_MEALS(new ItemStack(ModItems.VEGETABLE_NOODLES.get())),
	FARMERSDELIGHT_COOKING_DRINKS(new ItemStack(ModItems.APPLE_CIDER.get())),
	FARMERSDELIGHT_COOKING_MISC(new ItemStack(ModItems.DUMPLINGS.get()), new ItemStack(ModItems.TOMATO_SAUCE.get()));

	@Shadow
	RecipeBookCategoriesMixin(ItemStack... itemIcons) {
	}

	@Inject(method = "getCategories", at = @At("HEAD"), cancellable = true)
	private static void fdrf$getCustomCategories(RecipeBookType recipeBookType, CallbackInfoReturnable<List<RecipeBookCategories>> cir) {
		if (recipeBookType == FDRecipeBookTypes.COOKING)
			cir.setReturnValue(List.of(FDRecipeCategories.COOKING_SEARCH, FDRecipeCategories.COOKING_MEALS, FDRecipeCategories.COOKING_DRINKS, FDRecipeCategories.COOKING_MISC));
	}
}
