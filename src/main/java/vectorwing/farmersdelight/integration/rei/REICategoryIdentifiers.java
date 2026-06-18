
package vectorwing.farmersdelight.integration.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.integration.rei.client.display.ClientsidedCookingPotDisplay;
import vectorwing.farmersdelight.integration.rei.display.CookingPotDisplay;
import vectorwing.farmersdelight.integration.rei.display.CuttingDisplay;
import vectorwing.farmersdelight.integration.rei.display.DecompositionDisplay;

public class REICategoryIdentifiers {
    public static final CategoryIdentifier<CookingPotDisplay> COOKING = CategoryIdentifier.of(FarmersDelight.id("plugin/cooking"));
    public static final CategoryIdentifier<CuttingDisplay> CUTTING = CategoryIdentifier.of(FarmersDelight.id("plugin/cutting"));
    public static final CategoryIdentifier<DecompositionDisplay> DECOMPOSITION = CategoryIdentifier.of(FarmersDelight.id("plugin/decomposition"));
}
