package vectorwing.farmersdelight.client.event;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.gui.CookingPotTooltip;
import vectorwing.farmersdelight.client.particle.SparkleParticle;
import vectorwing.farmersdelight.client.particle.StarParticle;
import vectorwing.farmersdelight.client.particle.SteamParticle;
import vectorwing.farmersdelight.client.renderer.*;
import vectorwing.farmersdelight.common.EnumParameters;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.*;

public class ClientSetupEvents
{
	public static void init() {
		ItemProperties.register(ModItems.SKILLET.get(), ResourceLocation.withDefaultNamespace("cooking"),
			(stack, world, entity, s) -> stack.getOrDefault(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY).getStack().isEmpty() ? 0 : 1);
	}

//	public static void registerClientExtensions() {
//		event.registerItem(new IClientItemExtensions()
//		{
//			BlockEntityWithoutLevelRenderer renderer = ;
//
//			@Override
//			public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
//				return renderer;
//			}
//		}, ModItems.SKILLET.get());
//	}
//
//	@SubscribeEvent
//	public static void registerRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
//		RecipeCategories.init(event);
//	}

    public static ClientTooltipComponent registerCustomTooltipRenderers(TooltipComponent data) {
        if (CookingPotTooltip.CookingPotTooltipComponent.class.isAssignableFrom(data.getClass())) {
            return new CookingPotTooltip((CookingPotTooltip.CookingPotTooltipComponent) data);
        }
        return null;
    }

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new IClientItemExtensions()
		{
			BlockEntityWithoutLevelRenderer renderer = new SkilletItemRenderer();

			@Override
			public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return renderer;
			}

			@Override
			public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity living, InteractionHand hand, ItemStack stack) {
				return stack.has(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get()) ? EnumParameters.PROXY_SKILLET_FLIP.getValue() : null;
			}
		}, ModItems.SKILLET.get());
	}



//    @SubscribeEvent(priority = EventPriority.LOW)
//    public static void registerGuiLayers(RegisterGuiLayersEvent event) {
//		HUDOverlays.register(event);
//    }

    public static void onRegisterRenderers() {
        EntityRendererRegistry.register(ModEntityTypes.ROTTEN_TOMATO.get(), ThrownItemRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.STOVE.get(), DefaultStoveRenderer<StoveBlockEntity>::new);
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
		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.SPARKLE.get(), SparkleParticle.Factory::new);
    }

//	@SubscribeEvent
//	public static void onModelRegister(ModelEvent.RegisterAdditional event) {
//		event.register(new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "skillet_cooking"), "inventory"));
//	}

	// TODO: I hate the skillet model so much...
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
