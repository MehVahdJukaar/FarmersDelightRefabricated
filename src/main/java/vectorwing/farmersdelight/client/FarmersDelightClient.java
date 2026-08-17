package vectorwing.farmersdelight.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.minecraft.client.renderer.item.ItemModels;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.client.event.ClientSetupEvents;
import vectorwing.farmersdelight.client.event.TooltipEvents;
import vectorwing.farmersdelight.client.model.SkilletCookingConditionalItemModelProperty;
import vectorwing.farmersdelight.client.renderer.SkilletFlipItemRenderer;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.network.ModNetworking;
import vectorwing.farmersdelight.common.network.payload.FlipSkilletPayload;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

public class FarmersDelightClient implements ClientModInitializer {
    public static boolean naturalRegenerationEnabled = false;

    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register(TooltipEvents::addTooltipToVanillaSoups);
        ClientTooltipComponentCallback.EVENT.register(ClientSetupEvents::registerCustomTooltipRenderers);
        ClientSetupEvents.onRegisterRenderers();
        ClientSetupEvents.registerParticles();
		ClientSetupEvents.registerMenuScreens();

        ItemModels.ID_MAPPER.put(SkilletFlipItemRenderer.ID, SkilletFlipItemRenderer.Unbaked.CODEC);
        ModNetworking.initClient();

        // Obscure Fabric event to the rescue!
        ClientPreAttackCallback.EVENT.register((client, player, clickCount) -> {
            if (!player.isSpectator() && player.isUsingItem() && player.getUseItem().getItem() instanceof SkilletItem && clickCount != 0 && !player.getUseItem().has(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get())) {
                ClientPlayNetworking.send(FlipSkilletPayload.INSTANCE);
            }
            return false;
        });

        // rendering stuff
        PictureInPictureRendererRegistry.register(ctx -> new GuiCanvasSignRenderer(ctx.bufferSource()));

        ConditionalItemModelProperties.ID_MAPPER.put(FarmersDelight.id("skillet/is_cooking"), SkilletCookingConditionalItemModelProperty.MAP_CODEC);
    }
}
