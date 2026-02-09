package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.DoughRecipe;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regRecipeSerializer;

public class ModRecipeSerializers {
    public static final Supplier<RecipeSerializer<CookingPotRecipe>> COOKING = regRecipeSerializer("cooking",()-> new RecipeSerializer<>(CookingPotRecipe.Serializer.codec(), CookingPotRecipe.Serializer.streamCodec()));
    public static final Supplier<RecipeSerializer<CuttingBoardRecipe>> CUTTING = regRecipeSerializer("cutting", ()-> new RecipeSerializer<>(CuttingBoardRecipe.Serializer.codec(), CuttingBoardRecipe.Serializer.streamCodec()));

	public static final Supplier<RecipeSerializer<FoodServingRecipe>> FOOD_SERVING =
            regRecipeSerializer("food_serving", () -> FoodServingRecipe.SERIALIZER);
	public static final Supplier<RecipeSerializer<DoughRecipe>> DOUGH =
            regRecipeSerializer("dough", () -> DoughRecipe.SERIALIZER);

	public static void touch() {

	}
}
