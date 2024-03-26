package vectorwing.farmersdelight.client.event;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.client.gui.CookingPotTooltip;
import vectorwing.farmersdelight.client.particle.StarParticle;
import vectorwing.farmersdelight.client.particle.SteamParticle;
import vectorwing.farmersdelight.client.recipebook.RecipeCategories;
import vectorwing.farmersdelight.client.renderer.*;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModEntityTypes;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;

public class ClientSetupEvents {
    public static void onRegisterRecipeBookCategories() {
        RecipeCategories.init();
    }

    public static ClientTooltipComponent registerCustomTooltipRenderers(TooltipComponent data) {
        if (CookingPotTooltip.CookingPotTooltipComponent.class.isAssignableFrom(data.getClass())) {
            return new CookingPotTooltip((CookingPotTooltip.CookingPotTooltipComponent) data);
        }
        return null;
    }

    public static void onModelRegister(ModelLoadingPlugin.Context event) {
        event.addModels(new ModelResourceLocation(new ResourceLocation(FarmersDelight.MODID, "skillet_cooking"), "inventory"));
    }

    public static void onEntityRendererRegister() {
        EntityRendererRegistry.register(ModEntityTypes.ROTTEN_TOMATO.get(), ThrownItemRenderer::new);
    }

    //TODO:
/*
    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult event) {
        Map<ResourceLocation, BakedModel> modelRegistry = event.getModels();

        ModelResourceLocation skilletLocation = new ModelResourceLocation(new ResourceLocation(FarmersDelight.MODID, "skillet"), "inventory");
        BakedModel skilletModel = modelRegistry.get(skilletLocation);
        ModelResourceLocation skilletCookingLocation = new ModelResourceLocation(new ResourceLocation(FarmersDelight.MODID, "skillet_cooking"), "inventory");
        BakedModel skilletCookingModel = modelRegistry.get(skilletCookingLocation);
        modelRegistry.put(skilletLocation, new SkilletModel(event.getModelBakery(), skilletModel, skilletCookingModel));
    }
*/
    public static void onRegisterRenderers() {
        BlockEntityRenderers.register(ModBlockEntityTypes.STOVE.get(), StoveRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.CUTTING_BOARD.get(), CuttingBoardRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.CANVAS_SIGN.get(), CanvasSignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.HANGING_CANVAS_SIGN.get(), HangingCanvasSignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.SKILLET.get(), SkilletRenderer::new);
    }

    public static void registerParticles() {
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.STAR.get(), StarParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.STEAM.get(), SteamParticle.Factory::new);
    }


}
