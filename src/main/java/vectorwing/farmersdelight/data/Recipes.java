package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;
import vectorwing.farmersdelight.data.recipe.CraftingRecipes;
import vectorwing.farmersdelight.data.recipe.CuttingRecipes;
import vectorwing.farmersdelight.data.recipe.SmeltingRecipes;

import java.util.concurrent.CompletableFuture;

public class Recipes extends FabricRecipeProvider
{

	public static BootstrapContext<Recipe<?>> recipeContext; //FIXME this is terrible

    public Recipes(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
    }

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, BootstrapContext<Recipe<?>> recipeContext, BootstrapContext<Advancement> advancementContext) {
		return new RecipeProvider(recipeContext, advancementContext) {


			@Override
			public void buildRecipes() {
				Recipes.recipeContext = recipeContext;
				CraftingRecipes.register(registryLookup, output, (out, resourceCondition) -> withConditions(out, resourceCondition));
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
