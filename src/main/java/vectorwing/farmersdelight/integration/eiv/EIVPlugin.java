package vectorwing.farmersdelight.integration.eiv;

import de.crafty.eiv.common.api.IExtendedItemViewIntegration;
import de.crafty.eiv.common.api.recipe.ItemView;
import de.crafty.eiv.common.recipe.ServerRecipeManager;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.integration.eiv.cooking_pot.CookingPotServerRecipe;
import vectorwing.farmersdelight.integration.eiv.cooking_pot.CookingPotViewRecipe;

import java.util.Collections;

public class EIVPlugin implements IExtendedItemViewIntegration {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addRecipeProvider(recipeList -> {
            ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.COOKING.get()).forEach(recipe -> {
                recipeList.add(new CookingPotServerRecipe(recipe.input(), recipe.result(), recipe.container(), recipe.getExperience(), recipe.getCookTime()));
            });
            //For the client
            ItemView.registerRecipeWrapper(CookingPotServerRecipe.TYPE,  modRecipe -> {

                //Here you tell EIV how to process incoming server recipes
                //Requires you to return a list of client-side view-recipes (IEivViewRecipe)

                return Collections.singletonList(new CookingPotViewRecipe(modRecipe));
            });
        });
    }
}
