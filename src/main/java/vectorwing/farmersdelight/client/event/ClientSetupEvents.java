package vectorwing.farmersdelight.client.event;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.client.gui.CookingPotTooltip;
import vectorwing.farmersdelight.client.particle.SparkleParticle;
import vectorwing.farmersdelight.client.particle.StarParticle;
import vectorwing.farmersdelight.client.particle.SteamParticle;
import vectorwing.farmersdelight.client.renderer.*;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;
import vectorwing.farmersdelight.common.registry.*;

public class ClientSetupEvents
{
	public static void init() {
		ItemProperties.register(ModItems.SKILLET.get(), new ResourceLocation("cooking"),
			(stack, world, entity, s) -> stack.getTagElement("Cooking") != null ? 1 : 0);
	}

	public static ClientTooltipComponent registerCustomTooltipRenderers(TooltipComponent data) {
		if (CookingPotTooltip.CookingPotTooltipComponent.class.isAssignableFrom(data.getClass())) {
			return new CookingPotTooltip((CookingPotTooltip.CookingPotTooltipComponent) data);
		}
		return null;
	}

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

	public static void onEntityRendererRegister() {
		EntityRendererRegistry.register(ModEntityTypes.ROTTEN_TOMATO.get(), ThrownItemRenderer::new);
	}
	
	public static void onRegisterRenderers() {
		BlockEntityRenderers.register(ModBlockEntityTypes.STOVE.get(), DefaultStoveRenderer<StoveBlockEntity>::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.CUTTING_BOARD.get(), CuttingBoardRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.CANVAS_SIGN.get(), CanvasSignRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.HANGING_CANVAS_SIGN.get(), HangingCanvasSignRenderer::new);
		BlockEntityRenderers.register(ModBlockEntityTypes.SKILLET.get(), SkilletRenderer::new);
	}

	public static void registerParticles() {
		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.STAR.get(), StarParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.STEAM.get(), SteamParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.SPARKLE.get(), SparkleParticle.Factory::new);
	}
}
