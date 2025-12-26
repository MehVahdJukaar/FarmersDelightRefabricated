package vectorwing.farmersdelight.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecyclei.FMLClientSetupEvent;
import vectorwing.farmersdelight.client.event.ClientSetupEvents;
import vectorwing.farmersdelight.client.event.TooltipEvents;
import vectorwing.farmersdelight.client.gui.ComfortHealthOverlay;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.client.gui.NourishmentHungerOverlay;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;

public class FarmersDelightClient implements ClientModInitializer
{
	public static void setup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> MenuScreens.register(ModMenuTypes.COOKING_POT.get(), CookingPotScreen::new));

		NourishmentHungerOverlay.init();
		ComfortHealthOverlay.init();
	}

	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register(TooltipEvents::addTooltipToVanillaSoups);
		TooltipComponentCallback.EVENT.register(ClientSetupEvents::registerCustomTooltipRenderers);
		ModelLoadingPlugin.register(ClientSetupEvents::onModelRegister);
		ClientSetupEvents.onEntityRendererRegister();
		ClientSetupEvents.onRegisterRenderers();
		ClientSetupEvents.registerParticles();
	}
}
