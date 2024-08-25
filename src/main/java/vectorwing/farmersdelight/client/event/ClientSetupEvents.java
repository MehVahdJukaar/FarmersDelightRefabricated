package vectorwing.farmersdelight.client.event;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.client.gui.CookingPotTooltip;
import vectorwing.farmersdelight.client.particle.StarParticle;
import vectorwing.farmersdelight.client.particle.SteamParticle;
import vectorwing.farmersdelight.client.recipebook.RecipeCategories;
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
			BlockEntityWithoutLevelRenderer renderer = new SkilletItemRenderer();
			@Override
			public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return renderer;
			}
		}, ModItems.SKILLET.get());
	}
	 */

	public static void registerRecipeBookCategories() {
		RecipeCategories.init();
	}

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
		BlockEntityRenderers.register(ModBlockEntityTypes.STOVE.get(), StoveRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.CUTTING_BOARD.get(), CuttingBoardRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.CANVAS_SIGN.get(), CanvasSignRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.HANGING_CANVAS_SIGN.get(), HangingCanvasSignRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.SKILLET.get(), SkilletRenderer::new);
	}

	/*
	public static void registerMenuScreens(RegisterMenuScreensEvent event) {
		event.register(ModMenuTypes.COOKING_POT.get(), CookingPotScreen::new);
	}
	 */

	public static void registerParticles() {
		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.STAR.get(), StarParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.STEAM.get(), SteamParticle.Factory::new);
	}

	public static void onModelRegister(ModelLoadingPlugin.Context event) {
		event.addModels(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "skillet_cooking"));
	}

//	@SubscribeEvent
//	public static void onModelBake(ModelEvent.ModifyBakingResult event) {
//		Map<ModelResourceLocation, BakedModel> modelRegistry = event.getModels();
//
//		ModelResourceLocation skilletLocation = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "skillet"), "inventory");
//		BakedModel skilletModel = modelRegistry.get(skilletLocation);
//		ModelResourceLocation skilletCookingLocation = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "skillet_cooking"), "inventory");
//		BakedModel skilletCookingModel = modelRegistry.get(skilletCookingLocation);
//		modelRegistry.put(skilletLocation, new SkilletModel(event.getModelBakery(), skilletModel, skilletCookingModel));
//	}
}
