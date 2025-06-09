package vectorwing.farmersdelight.common.mixin.refabricated;

import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipePropertySet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.registry.ModRecipePropertySets;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @Shadow @Final private static Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> RECIPE_PROPERTY_SETS;

    @Accessor("RECIPE_PROPERTY_SETS") @Mutable
    private static void fdrf$setRECIPE_PROPERTY_SETS(Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> value) {
        throw new RuntimeException("");
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void fdrf$modifyRecipePropertySets(CallbackInfo ci) {
        Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> map = new HashMap<>(RECIPE_PROPERTY_SETS);
        map.put(ModRecipePropertySets.COOKING_POT_INPUT_ONE, recipe -> recipe instanceof CookingPotRecipe cookingPotRecipe ?
                Optional.ofNullable(cookingPotRecipe.input().get(0)) :
                Optional.empty());
        map.put(ModRecipePropertySets.COOKING_POT_INPUT_TWO, recipe -> recipe instanceof CookingPotRecipe cookingPotRecipe && cookingPotRecipe.input().size() >= 2 ?
                Optional.ofNullable(cookingPotRecipe.input().get(1)) :
                Optional.empty());
        map.put(ModRecipePropertySets.COOKING_POT_INPUT_THREE, recipe -> recipe instanceof CookingPotRecipe cookingPotRecipe && cookingPotRecipe.input().size() >= 3  ?
                Optional.ofNullable(cookingPotRecipe.input().get(2)) :
                Optional.empty());
        map.put(ModRecipePropertySets.COOKING_POT_INPUT_FOUR, recipe -> recipe instanceof CookingPotRecipe cookingPotRecipe && cookingPotRecipe.input().size() >= 4 ?
                Optional.ofNullable(cookingPotRecipe.input().get(3)) :
                Optional.empty());
        map.put(ModRecipePropertySets.COOKING_POT_INPUT_FIVE, recipe -> recipe instanceof CookingPotRecipe cookingPotRecipe && cookingPotRecipe.input().size() >= 5 ?
                Optional.ofNullable(cookingPotRecipe.input().get(4)) :
                Optional.empty());
        map.put(ModRecipePropertySets.COOKING_POT_INPUT_SIX, recipe -> recipe instanceof CookingPotRecipe cookingPotRecipe  && cookingPotRecipe.input().size() >= 6 ?
                Optional.ofNullable(cookingPotRecipe.input().get(5)) :
                Optional.empty());
        map.put(ModRecipePropertySets.COOKING_POT_CONTAINER, recipe -> recipe instanceof CookingPotRecipe cookingPotRecipe && !cookingPotRecipe.container().isEmpty() ?
                Optional.of(Ingredient.of(cookingPotRecipe.container().getItem())) :
                Optional.empty());

        map.put(ModRecipePropertySets.CUTTING_BOARD_INPUT, recipe -> recipe instanceof CuttingBoardRecipe cuttingBoardRecipe ?
                Optional.of(cuttingBoardRecipe.getInput()) : Optional.empty());
        map.put(ModRecipePropertySets.CUTTING_BOARD_TOOL, recipe -> recipe instanceof CuttingBoardRecipe cuttingBoardRecipe ?
                Optional.of(cuttingBoardRecipe.getTool()) : Optional.empty());
        fdrf$setRECIPE_PROPERTY_SETS(ImmutableMap.copyOf(map));
    }
}
