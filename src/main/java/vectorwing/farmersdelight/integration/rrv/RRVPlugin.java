package vectorwing.farmersdelight.integration.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.common.builtin.shapeless.ShapelessServerRecipe;
import cc.cassian.rrv.common.recipe.ServerRecipeManager;
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
import vectorwing.farmersdelight.integration.rrv.info.InfoServerRecipe;
import vectorwing.farmersdelight.integration.rrv.info.InfoViewRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RRVPlugin implements ReliableRecipeViewerPlugin {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addServerRecipeProvider(recipeList -> {
            // Info Recipe - serverside
            recipeList.add(new InfoServerRecipe());
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

        // Info Recipe - Clientside
        ItemView.addClientRecipeWrapper(InfoServerRecipe.TYPE, modRecipe -> {
            ArrayList<InfoViewRecipe> infoRecipes = new ArrayList<>();
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.WHEAT_DOUGH.get())), "farmersdelight.jei.info.dough"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.STRAW.get())), "farmersdelight.jei.info.straw"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.HAM.get())), "farmersdelight.jei.info.ham"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.SMOKED_HAM.get())), "farmersdelight.jei.info.ham"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.FLINT_KNIFE.get())), "farmersdelight.jei.info.knife"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.COPPER_KNIFE.get())), "farmersdelight.jei.info.knife"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.IRON_KNIFE.get())), "farmersdelight.jei.info.knife"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.DIAMOND_KNIFE.get())), "farmersdelight.jei.info.knife"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.NETHERITE_KNIFE.get())), "farmersdelight.jei.info.knife"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.GOLDEN_KNIFE.get())), "farmersdelight.jei.info.knife"));

            infoRecipes.add(new InfoViewRecipe((List.of(new ItemStack(ModItems.WILD_CABBAGES.get()), new ItemStack(ModItems.CABBAGE.get()), new ItemStack(ModItems.CABBAGE_LEAF.get()))), "farmersdelight.jei.info.wild_cabbages"));
            infoRecipes.add(new InfoViewRecipe((List.of(new ItemStack(ModItems.WILD_BEETROOTS.get()), new ItemStack(Items.BEETROOT))), "farmersdelight.jei.info.wild_beetroots"));
            infoRecipes.add(new InfoViewRecipe((List.of(new ItemStack(ModItems.WILD_CARROTS.get()), new ItemStack(Items.CARROT))), "farmersdelight.jei.info.wild_carrots"));
            infoRecipes.add(new InfoViewRecipe((List.of(new ItemStack(ModItems.WILD_ONIONS.get()), new ItemStack(ModItems.ONION.get()))), "farmersdelight.jei.info.wild_onions"));
            infoRecipes.add(new InfoViewRecipe((List.of(new ItemStack(ModItems.WILD_POTATOES.get()), new ItemStack(Items.POTATO))), "farmersdelight.jei.info.wild_potatoes"));
            infoRecipes.add(new InfoViewRecipe((List.of(new ItemStack(ModItems.WILD_TOMATOES.get()), new ItemStack(ModItems.TOMATO.get()))), "farmersdelight.jei.info.wild_tomatoes"));
            infoRecipes.add(new InfoViewRecipe((List.of(new ItemStack(ModItems.WILD_RICE.get()), new ItemStack(ModItems.RICE.get()), new ItemStack(ModItems.RICE_PANICLE.get()))), "farmersdelight.jei.info.wild_rice"));
            return infoRecipes;
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
