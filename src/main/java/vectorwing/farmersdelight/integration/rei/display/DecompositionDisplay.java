package vectorwing.farmersdelight.integration.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;

import java.util.List;

public class DecompositionDisplay implements Display {

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(EntryIngredients.of(ModItems.ORGANIC_COMPOST.get()));
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(EntryIngredients.of(ModItems.RICH_SOIL.get()));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICategoryIdentifiers.DECOMPOSITION;
    }

}
