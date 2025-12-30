package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;

public class ModRecipeTypes
{
	public static final LazyRegistrar<RecipeType<?>> RECIPE_TYPES = LazyRegistrar.create(BuiltInRegistries.RECIPE_TYPE, FarmersDelight.MODID);

	public static final RegistryObject<RecipeType<CookingPotRecipe>> COOKING = RECIPE_TYPES.register("cooking", () -> registerRecipeType("cooking"));
	public static final RegistryObject<RecipeType<CuttingBoardRecipe>> CUTTING = RECIPE_TYPES.register("cutting", () -> registerRecipeType("cutting"));

	public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
		return new RecipeType<>()
		{
			public String toString() {
				return FarmersDelight.MODID + ":" + identifier;
			}
		};
	}
}
