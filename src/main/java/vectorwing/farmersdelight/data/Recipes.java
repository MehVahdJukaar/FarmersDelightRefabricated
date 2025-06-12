package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;
import vectorwing.farmersdelight.data.recipe.CraftingRecipes;
import vectorwing.farmersdelight.data.recipe.CuttingRecipes;
import vectorwing.farmersdelight.data.recipe.SmeltingRecipes;

import java.util.concurrent.CompletableFuture;

@MethodsReturnNonnullByDefault
public class Recipes extends FabricRecipeProvider
{
	public Recipes(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
		return new RecipeProvider(registryLookup, exporter) {
			@Override
			public void buildRecipes() {
				CraftingRecipes.register(registryLookup, output);
				SmeltingRecipes.register(registryLookup, output);
				CookingRecipes.register(registryLookup, output);
				CuttingRecipes.register(registryLookup, output);
			}
		};
	}

	@Override
	public String getName() {
		return "Farmer's Delight Recipes";
	}
}
