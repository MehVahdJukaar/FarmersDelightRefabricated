package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;

public class ModRecipeSerializers
{
	public static final LazyRegistrar<RecipeSerializer<?>> RECIPE_SERIALIZERS = LazyRegistrar.create(BuiltInRegistries.RECIPE_SERIALIZER, FarmersDelight.MODID);

	public static final RegistryObject<RecipeSerializer<?>> COOKING = RECIPE_SERIALIZERS.register("cooking", CookingPotRecipe.Serializer::new);
	public static final RegistryObject<RecipeSerializer<?>> CUTTING = RECIPE_SERIALIZERS.register("cutting", CuttingBoardRecipe.Serializer::new);

	public static final RegistryObject<SimpleCraftingRecipeSerializer<?>> FOOD_SERVING =
			RECIPE_SERIALIZERS.register("food_serving", () -> new SimpleCraftingRecipeSerializer<>(FoodServingRecipe::new));
}
