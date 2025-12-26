package vectorwing.farmersdelight.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.DoughRecipe;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regRecipeSerializer;

public class ModRecipeSerializers
{
    public static final Supplier<RecipeSerializer<CookingPotRecipe>> COOKING = regRecipeSerializer("cooking", CookingPotRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<CuttingBoardRecipe>> CUTTING = regRecipeSerializer("cutting", CuttingBoardRecipe.Serializer::new);

    public static final Supplier<RecipeSerializer<FoodServingRecipe>> FOOD_SERVING =
            regRecipeSerializer("food_serving", () -> new SimpleCraftingRecipeSerializer<>(FoodServingRecipe::new));
    public static final Supplier<RecipeSerializer<DoughRecipe>> DOUGH =
            regRecipeSerializer("dough", () -> new SimpleCraftingRecipeSerializer<>(DoughRecipe::new));

    public static void touch() {

    }
}
