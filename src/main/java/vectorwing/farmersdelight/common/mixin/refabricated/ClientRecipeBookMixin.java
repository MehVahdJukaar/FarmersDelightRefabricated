package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.refabricated.client.FDRecipeCategories;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
    @Inject(method = "setupCollections", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;copyOf(Ljava/util/Map;)Lcom/google/common/collect/ImmutableMap;"))
    private void fdrf$setupAggregateCategories(Iterable<RecipeHolder<?>> iterable, RegistryAccess registryAccess, CallbackInfo ci, @Local(ordinal = 1) Map<RecipeBookCategories, List<RecipeCollection>> aggregateCategories) {
        aggregateCategories.put(FDRecipeCategories.COOKING_SEARCH, Stream.of(FDRecipeCategories.COOKING_MEALS, FDRecipeCategories.COOKING_DRINKS, FDRecipeCategories.COOKING_MISC)
                .flatMap(categories -> aggregateCategories.getOrDefault(categories, List.of()).stream())
                .toList()
        );
    }

    @Inject(method = "getCategory", at = @At(value = "INVOKE", target = "Lcom/mojang/logging/LogUtils;defer(Ljava/util/function/Supplier;)Ljava/lang/Object;", ordinal = 0), cancellable = true)
    private static void fdrf$getCustomRecipeCategory(RecipeHolder<?> recipe, CallbackInfoReturnable<RecipeBookCategories> cir) {
        if (recipe.value() instanceof CookingPotRecipe cookingRecipe) {
            CookingPotRecipeBookTab tab = cookingRecipe.getRecipeBookTab();
            if (tab != null) {
                cir.setReturnValue(switch (tab) {
                    case MEALS -> FDRecipeCategories.COOKING_MEALS;
                    case DRINKS -> FDRecipeCategories.COOKING_DRINKS;
                    case MISC -> FDRecipeCategories.COOKING_MISC;
                });
            }
        }
    }
}
