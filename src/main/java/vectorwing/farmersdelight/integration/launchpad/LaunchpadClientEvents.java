package vectorwing.farmersdelight.integration.launchpad;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.client.gui.HUDOverlays;

@EventBusSubscriber(modid = FarmersDelight.MODID, value =  Dist.CLIENT)
public class LaunchpadClientEvents {
	@SubscribeEvent
	public static void register(RegisterGuiLayersEvent event) {
		event.registerAbove(VanillaGuiLayers.PLAYER_HEALTH, HUDOverlays.ComfortOverlay.ID, HUDOverlays.ComfortOverlay.INSTANCE::render);
		event.registerAbove(VanillaGuiLayers.FOOD_LEVEL, HUDOverlays.NourishmentOverlay.ID, HUDOverlays.NourishmentOverlay.INSTANCE::render);
	}

}
