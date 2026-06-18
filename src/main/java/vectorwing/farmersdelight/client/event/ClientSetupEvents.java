package vectorwing.farmersdelight.client.event;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.client.gui.CookingPotTooltip;
import vectorwing.farmersdelight.client.particle.SparkleParticle;
import vectorwing.farmersdelight.client.particle.StarParticle;
import vectorwing.farmersdelight.client.particle.SteamParticle;
import vectorwing.farmersdelight.client.renderer.CuttingBoardRenderer;
import vectorwing.farmersdelight.client.renderer.DefaultStoveRenderer;
import vectorwing.farmersdelight.client.renderer.SkilletRenderer;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModEntityTypes;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;

public class ClientSetupEvents
{
	public static void registerRecipeBookCategories() {
		// No-op until Fabric Recipe API V1 gets a Recipe Book API.
	}

	public static ClientTooltipComponent registerCustomTooltipRenderers(TooltipComponent data) {
		if (CookingPotTooltip.CookingPotTooltipComponent.class.isAssignableFrom(data.getClass())) {
			return new CookingPotTooltip((CookingPotTooltip.CookingPotTooltipComponent) data);
		}
		return null;
	}

	public static void registerGuiLayers() {
//		HUDOverlays.register();
	}

	public static void onRegisterRenderers() {
		EntityRenderers.register(ModEntityTypes.ROTTEN_TOMATO.get(), ThrownItemRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.STOVE.get(), DefaultStoveRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.CUTTING_BOARD.get(), CuttingBoardRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.SKILLET.get(), SkilletRenderer::new);
	}

	public static void registerMenuScreens() {
		MenuScreens.register(ModMenuTypes.COOKING_POT.get(), CookingPotScreen::new);
	}

	public static void registerParticles() {
        ParticleProviderRegistry.getInstance().register(ModParticleTypes.STAR.get(), StarParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(ModParticleTypes.STEAM.get(), SteamParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(ModParticleTypes.SPARKLE.get(), SparkleParticle.Factory::new);
	}
}
