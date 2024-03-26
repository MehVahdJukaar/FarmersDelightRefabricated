package vectorwing.farmersdelight.integration.rei;

import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import vectorwing.farmersdelight.integration.rei.display.CookingPotDisplay;

public class ServerREIPlugin implements REIServerPlugin {
    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(REICategoryIdentifiers.COOKING, CookingPotDisplay.serializer());
    }
}
