package vectorwing.farmersdelight.integration.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.common.builtin.info.InfoClientRecipe;
import cc.cassian.rrv.common.builtin.info.InfoServerRecipe;
import cc.cassian.rrv.common.builtin.shapeless.ShapelessServerRecipe;
import cc.cassian.rrv.common.recipe.ServerRecipeManager;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.integration.rrv.cooking_pot.CookingPotServerRecipe;
import vectorwing.farmersdelight.integration.rrv.cooking_pot.CookingPotViewRecipe;
import vectorwing.farmersdelight.integration.rrv.cutting.CuttingServerRecipe;
import vectorwing.farmersdelight.integration.rrv.cutting.CuttingViewRecipe;
import vectorwing.farmersdelight.integration.rrv.decomposition.DecompositionServerRecipe;
import vectorwing.farmersdelight.integration.rrv.decomposition.DecompositionViewRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RRVPlugin implements ReliableRecipeViewerPlugin {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addServerRecipeProvider(recipeList -> {
            // Cooking Pot - serverside
            ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.COOKING.get()).forEach(recipe -> {
                recipeList.add(new CookingPotServerRecipe(recipe.input(), recipe.result(), recipe.container(), recipe.getExperience(), recipe.getCookTime()));
            });
            // Cutting Board - serverside
            ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.CUTTING.get()).forEach(recipe -> {
                recipeList.add(new CuttingServerRecipe(recipe.getInput(), recipe.getResults(), recipe.getTool(), recipe.getRollableResults()));
            });
            recipeList.add(new DecompositionServerRecipe());
            recipeList.add(new ShapelessServerRecipe(List.of(Ingredient.of(Items.WHEAT), Ingredient.of(Items.WATER_BUCKET)), new ItemStack(ModItems.WHEAT_DOUGH.get())));
        });

        // Cooking Pot - clientside
        ItemView.addClientRecipeWrapper(CookingPotServerRecipe.TYPE, modRecipe -> {
            return Collections.singletonList(new CookingPotViewRecipe(modRecipe));
        });

        // Cutting Board - clientside
        ItemView.addClientRecipeWrapper(CuttingServerRecipe.TYPE, modRecipe -> {
            return Collections.singletonList(new CuttingViewRecipe(modRecipe));
        });

        // Decomposition - Clientside
        ItemView.addClientRecipeWrapper(DecompositionServerRecipe.TYPE, modRecipe -> {
           return List.of(new DecompositionViewRecipe(ModItems.ORGANIC_COMPOST.get().getDefaultInstance(), ModItems.RICH_SOIL.get().getDefaultInstance()));
        });
    }
}
