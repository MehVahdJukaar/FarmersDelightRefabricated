package vectorwing.farmersdelight.integration.jei;

import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;

import java.util.List;

public class FDRecipes
{

    private final SynchronizedRecipes synchronizedRecipes;

    public FDRecipes() {
        Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;

		if (level != null) {
            synchronizedRecipes = level.recipeAccess().getSynchronizedRecipes();
		} else {
			throw new NullPointerException("minecraft world must not be null.");
		}

	}

	public List<RecipeHolder<CookingPotRecipe>> getCookingPotRecipes() {
        System.out.println("GET COOKING POT RECIPES");
        for (RecipeHolder<CookingPotRecipe> cookingPotRecipeRecipeHolder : synchronizedRecipes.getAllOfType(ModRecipeTypes.COOKING.get())) {
            System.out.println(cookingPotRecipeRecipeHolder.id());
        }

        return List.copyOf(synchronizedRecipes.getAllOfType(ModRecipeTypes.COOKING.get()));
	}

	public List<RecipeHolder<CuttingBoardRecipe>> getCuttingBoardRecipes() {
		return List.copyOf(synchronizedRecipes.getAllOfType(ModRecipeTypes.CUTTING.get()));
	}
}
