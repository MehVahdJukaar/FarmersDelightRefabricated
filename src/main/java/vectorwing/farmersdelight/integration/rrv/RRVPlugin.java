package vectorwing.farmersdelight.integration.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.client.recipe.ClientRecipeManager;
import cc.cassian.rrv.common.builtin.crafting.CraftingClientRecipe;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.DoughRecipe;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.integration.rrv.cooking_pot.CookingPotClientRecipe;
import vectorwing.farmersdelight.integration.rrv.cutting.CuttingClientRecipe;
import vectorwing.farmersdelight.integration.rrv.decomposition.DecompositionClientRecipe;

import java.util.List;

public class RRVPlugin implements ReliableRecipeViewerClientPlugin {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addClientRecipeProvider(recipeList -> {
            // Cooking Pot
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.COOKING.get()).forEach(recipeHolder -> {
				var recipe = recipeHolder.value();
                recipeList.add(new CookingPotClientRecipe(recipeHolder.id().identifier(), recipe.input(), recipe.result(), recipe.container(), recipe.getExperience(), recipe.getCookTime()));
            });
            // Cutting Board - serverside
			ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.CUTTING.get()).forEach(recipeHolder -> {
				var  recipe = recipeHolder.value();
                recipeList.add(new CuttingClientRecipe(recipeHolder.id().identifier(), recipe.getInput(), recipe.getResults(), recipe.getTool(), recipe.getRollableResults()));
            });
            recipeList.add(new DecompositionClientRecipe(ModItems.ORGANIC_COMPOST.get().getDefaultInstance(), ModItems.RICH_SOIL.get().getDefaultInstance()));
			ClientRecipeManager.INSTANCE.getRecipesForType(RecipeType.CRAFTING).forEach(recipeHolder -> {
				if (recipeHolder.value() instanceof DoughRecipe) {
					recipeList.add(new CraftingClientRecipe.Builder(recipeHolder.id().identifier(), List.of(SlotContent.of(Items.WHEAT), SlotContent.of(ConventionalItemTags.WATER_BUCKETS))).setResult(new ItemStackTemplate(ModItems.WHEAT_DOUGH.get())).build());
				}
			});
        });
    }
}
