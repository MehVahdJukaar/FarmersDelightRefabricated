package vectorwing.farmersdelight.integration.eiv;

import de.crafty.eiv.common.api.IExtendedItemViewIntegration;
import de.crafty.eiv.common.api.recipe.ItemView;
import de.crafty.eiv.common.recipe.ServerRecipeManager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.eiv.cooking_pot.CookingPotServerRecipe;
import vectorwing.farmersdelight.integration.eiv.cooking_pot.CookingPotViewRecipe;
import vectorwing.farmersdelight.integration.eiv.cutting.CuttingServerRecipe;
import vectorwing.farmersdelight.integration.eiv.cutting.CuttingViewRecipe;
import vectorwing.farmersdelight.integration.eiv.info.InfoServerRecipe;
import vectorwing.farmersdelight.integration.eiv.info.InfoViewRecipe;
import vectorwing.farmersdelight.integration.eiv.info.InfoViewType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EIVPlugin implements IExtendedItemViewIntegration {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addRecipeProvider(recipeList -> {
            // Info Recipe - serverside
            recipeList.add(new InfoServerRecipe());
            // Cooking Pot - serverside
            ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.COOKING.get()).forEach(recipe -> {
                recipeList.add(new CookingPotServerRecipe(recipe.input(), recipe.result(), recipe.container(), recipe.getExperience(), recipe.getCookTime()));
            });
            // Cutting Board - serverside
            ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.CUTTING.get()).forEach(recipe -> {
                recipeList.add(new CuttingServerRecipe(recipe.getInput(), recipe.getResults(), recipe.getTool()));
            });
        });

        // Cooking Pot - clientside
        ItemView.registerRecipeWrapper(CookingPotServerRecipe.TYPE, modRecipe -> {
            return Collections.singletonList(new CookingPotViewRecipe(modRecipe));
        });

        // Info Recipe - Clientside
        ItemView.registerRecipeWrapper(InfoServerRecipe.TYPE, modRecipe -> {
            ArrayList<InfoViewRecipe> infoRecipes = new ArrayList<>();
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.WHEAT_DOUGH.get())), "farmersdelight.jei.info.dough"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.STRAW.get())), "farmersdelight.jei.info.straw"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.HAM.get())), "farmersdelight.jei.info.ham"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.SMOKED_HAM.get())), "farmersdelight.jei.info.ham"));
            infoRecipes.add(new InfoViewRecipe((new ItemStack(ModItems.FLINT_KNIFE.get())), "farmersdelight.jei.info.knife"));
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

        // Cooking Pot - clientside
        ItemView.registerRecipeWrapper(CuttingServerRecipe.TYPE, modRecipe -> {
            return Collections.singletonList(new CuttingViewRecipe(modRecipe));
        });
    }
}
