package vectorwing.farmersdelight.integration.rei;

import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import vectorwing.farmersdelight.integration.rei.display.CookingPotDisplay;
import vectorwing.farmersdelight.integration.rei.display.CuttingDisplay;

public class ServerREIPlugin implements REICommonPlugin {
    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(REICategoryIdentifiers.COOKING.getIdentifier(), CookingPotDisplay.SERIALIZER);
        registry.register(REICategoryIdentifiers.CUTTING.getIdentifier(), CuttingDisplay.SERIALIZER);
    }
}
