package vectorwing.farmersdelight.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.fabric.api.client.rendering.v1.SpecialGuiElementRegistry;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.item.ItemModels;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.client.event.ClientSetupEvents;
import vectorwing.farmersdelight.client.event.TooltipEvents;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.client.gui.renderer.GuiCanvasSignRenderer;
import vectorwing.farmersdelight.client.model.SkilletCookingConditionalItemModelProperty;
import vectorwing.farmersdelight.client.renderer.SkilletItemRenderer;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.network.ModNetworking;
import vectorwing.farmersdelight.common.network.payload.FlipSkilletPayload;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;

public class FarmersDelightClient implements ClientModInitializer {
    public static boolean naturalRegenerationEnabled = false;

    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register(TooltipEvents::addTooltipToVanillaSoups);
        TooltipComponentCallback.EVENT.register(ClientSetupEvents::registerCustomTooltipRenderers);
        ClientSetupEvents.onRegisterRenderers();
        ClientSetupEvents.registerParticles();
		ClientSetupEvents.registerMenuScreens();

		SpecialModelRenderers.ID_MAPPER.put(SkilletItemRenderer.ID, SkilletItemRenderer.Unbaked.CODEC);
		ModNetworking.initClient();

        // Obscure Fabric event to the rescue!
        ClientPreAttackCallback.EVENT.register((client, player, clickCount) -> {
            if (!player.isSpectator() && player.isUsingItem() && player.getUseItem().getItem() instanceof SkilletItem && clickCount != 0 && !player.getUseItem().has(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get())) {
                ClientPlayNetworking.send(FlipSkilletPayload.INSTANCE);
            }
            return false;
        });

		// render type stuff
		BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT,
			ModBlocks.TOMATO_CROP_ON_ROPE.get(),
			ModBlocks.BROWN_MUSHROOM_COLONY.get(), ModBlocks.RED_MUSHROOM_COLONY.get(), ModBlocks.BUDDING_TOMATO_CROP.get(),
			ModBlocks.CABBAGE_CROP.get(), ModBlocks.CUTTING_BOARD.get(), ModBlocks.ONION_CROP.get(),
			ModBlocks.WILD_CABBAGES.get(), ModBlocks.WILD_BEETROOTS.get(), ModBlocks.WILD_CARROTS.get(),
			ModBlocks.WILD_ONIONS.get(), ModBlocks.WILD_POTATOES.get(), ModBlocks.WILD_RICE.get(), ModBlocks.WILD_TOMATOES.get(),
			ModBlocks.RICE_CROP.get(), ModBlocks.TOMATO_CROP.get(), ModBlocks.RICE_CROP_PANICLES.get(),
			ModBlocks.ROAST_CHICKEN_BLOCK.get(), ModBlocks.SANDY_SHRUB.get(), ModBlocks.ROPE.get(),
			ModBlocks.CANVAS_RUG.get(), ModBlocks.COOKING_POT.get(), ModBlocks.SAFETY_NET.get());

		// rendering stuff
		SpecialGuiElementRegistry.register(ctx -> new GuiCanvasSignRenderer(ctx.vertexConsumers(), ctx.client().getAtlasManager()));

        ConditionalItemModelProperties.ID_MAPPER.put(FarmersDelight.id("skillet/is_cooking"), SkilletCookingConditionalItemModelProperty.MAP_CODEC);
    }
}
