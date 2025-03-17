package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.FabricUtils.regRecipeSerializer;

public class ModRecipeSerializers {
    public static final Supplier<RecipeSerializer<?>> COOKING = regRecipeSerializer("cooking", CookingPotRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> CUTTING = regRecipeSerializer("cutting", CuttingBoardRecipe.Serializer::new);

    public static final Supplier<SimpleCraftingRecipeSerializer<?>> FOOD_SERVING =
            regRecipeSerializer("food_serving", () -> new SimpleCraftingRecipeSerializer<>(FoodServingRecipe::new));

	public static void touch() {

	}
}
