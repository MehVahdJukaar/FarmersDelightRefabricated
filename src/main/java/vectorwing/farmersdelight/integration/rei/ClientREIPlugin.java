package vectorwing.farmersdelight.integration.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.integration.rei.categories.CookingPotCategory;
import vectorwing.farmersdelight.integration.rei.display.CookingPotDisplay;

public class ClientREIPlugin implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CookingPotRecipe.class, ModRecipeTypes.COOKING.get(), CookingPotDisplay::new);
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CookingPotCategory());

        registry.addWorkstations(REICategoryIdentifiers.COOKING, EntryStacks.of(ModItems.COOKING_POT.get()));
    }

    public void registerTransferHandlers(TransferHandlerRegistry registry) {

    }
}
