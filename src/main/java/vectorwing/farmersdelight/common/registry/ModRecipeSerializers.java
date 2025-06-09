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
    public static final Supplier<RecipeSerializer<CookingPotRecipe>> COOKING = regRecipeSerializer("cooking", CookingPotRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<CuttingBoardRecipe>> CUTTING = regRecipeSerializer("cutting", CuttingBoardRecipe.Serializer::new);

	public static final Supplier<CustomRecipe.Serializer<FoodServingRecipe>> FOOD_SERVING =
            regRecipeSerializer("food_serving", () -> new CustomRecipe.Serializer<>(FoodServingRecipe::new));
	public static final Supplier<CustomRecipe.Serializer<DoughRecipe>> DOUGH =
            regRecipeSerializer("dough", () -> new CustomRecipe.Serializer<>(DoughRecipe::new));

	public static void touch() {

	}
}
