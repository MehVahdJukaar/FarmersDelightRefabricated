package vectorwing.farmersdelight.client.event;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import vectorwing.farmersdelight.client.gui.CookingPotTooltip;
import vectorwing.farmersdelight.client.particle.SparkleParticle;
import vectorwing.farmersdelight.client.particle.StarParticle;
import vectorwing.farmersdelight.client.particle.SteamParticle;
import vectorwing.farmersdelight.client.renderer.*;
import vectorwing.farmersdelight.common.registry.*;

public class ClientSetupEvents
{

	/*
	public static void init(final FMLClientSetupEvent event) {
	}

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new IClientItemExtensions() {
			BlockEntityWithoutLevelRenderer renderer = new SkilletFlipItemModelWrapper();
			@Override
			public @NonNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return renderer;
			}
		}, ModItems.SKILLET.get());
	}
	 */

	/*
	public static void registerRecipeBookCategories() {
		FDRecipeCategories.init();
	}
	 */

	public static ClientTooltipComponent registerCustomTooltipRenderers(TooltipComponent data) {
		if (CookingPotTooltip.CookingPotTooltipComponent.class.isAssignableFrom(data.getClass())) {
			return new CookingPotTooltip((CookingPotTooltip.CookingPotTooltipComponent) data);
		}
		return null;
	}

	/*
	@SubscribeEvent
	public static void registerGuiLayers(RegisterGuiLayersEvent event) {
		HUDOverlays.register(event);
	}
	 */

	public static void onRegisterRenderers() {
		EntityRendererRegistry.register(ModEntityTypes.ROTTEN_TOMATO.get(), ThrownItemRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.STOVE.get(), DefaultStoveRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.CUTTING_BOARD.get(), CuttingBoardRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.CANVAS_SIGN.get(), StandingCanvasSignRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.HANGING_CANVAS_SIGN.get(), HangingCanvasSignRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.SKILLET.get(), SkilletRenderer::new);
	}

	/*
	public static void registerMenuScreens(RegisterMenuScreensEvent event) {
		event.register(ModMenuTypes.COOKING_POT.get(), CookingPotScreen::new);
	}
	 */

	public static void registerParticles() {
        ParticleProviderRegistry.getInstance().register(ModParticleTypes.STAR.get(), StarParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(ModParticleTypes.STEAM.get(), SteamParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(ModParticleTypes.SPARKLE.get(), SparkleParticle.Factory::new);
	}

//	@SubscribeEvent
//	public static void onModelRegister(ModelEvent.RegisterAdditional event) {
//		event.register(new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "skillet_cooking"), "inventory"));
//	}
}
