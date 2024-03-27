package vectorwing.farmersdelight.integration.rei;

import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import vectorwing.farmersdelight.integration.rei.display.CookingPotDisplay;
import vectorwing.farmersdelight.integration.rei.display.CuttingDisplay;

public class ServerREIPlugin implements REIServerPlugin {
    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(REICategoryIdentifiers.COOKING, CookingPotDisplay.serializer());
        registry.register(REICategoryIdentifiers.CUTTING, CuttingDisplay.serializer());
    }
}
