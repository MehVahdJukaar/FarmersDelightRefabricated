package vectorwing.farmersdelight.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import vectorwing.farmersdelight.client.event.ClientSetupEvents;
import vectorwing.farmersdelight.client.event.TooltipEvents;
import vectorwing.farmersdelight.client.gui.ComfortHealthOverlay;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.client.gui.NourishmentHungerOverlay;
import vectorwing.farmersdelight.client.renderer.SkilletItemRenderer;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.networking.ModNetworking;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;

public class FarmersDelightClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register(TooltipEvents::addTooltipToVanillaSoups);
        TooltipComponentCallback.EVENT.register(ClientSetupEvents::registerCustomTooltipRenderers);
        ModelLoadingPlugin.register(ClientSetupEvents::onModelRegister);
        ClientSetupEvents.onRegisterRecipeBookCategories();
        ClientSetupEvents.onEntityRendererRegister();
        ClientSetupEvents.onRegisterRenderers();
        ClientSetupEvents.registerParticles();

        MenuScreens.register(ModMenuTypes.COOKING_POT.get(), CookingPotScreen::new);

        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SKILLET.get(), new SkilletItemRenderer());
        // could have been done with item renderer but this way we can have easier control over item positioning using the model json
        ItemProperties.register(ModItems.SKILLET.get(), new ResourceLocation("cooking"),
                (stack, world, entity, s) -> stack.getTagElement("Cooking") != null ? 1 : 0);

        // Obscure Fabric event to the rescue!
        ClientPreAttackCallback.EVENT.register((client, player, clickCount) -> {
            if (player != null && !player.isSpectator() && player.isUsingItem() && player.getUseItem().getItem() instanceof SkilletItem && clickCount != 0) {
                ModNetworking.CHANNEL.sendToServer(new ModNetworking.FlipSkilletMessage());
            }
            return false;
        });

        // render type stuff
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ModBlocks.BROWN_MUSHROOM_COLONY.get(), ModBlocks.RED_MUSHROOM_COLONY.get(), ModBlocks.BUDDING_TOMATO_CROP.get(),
                ModBlocks.CABBAGE_CROP.get(), ModBlocks.CUTTING_BOARD.get(), ModBlocks.ONION_CROP.get(),
                ModBlocks.WILD_CABBAGES.get(), ModBlocks.WILD_BEETROOTS.get(), ModBlocks.WILD_CARROTS.get(),
                ModBlocks.WILD_ONIONS.get(), ModBlocks.WILD_POTATOES.get(), ModBlocks.WILD_RICE.get(), ModBlocks.WILD_TOMATOES.get(),
                ModBlocks.RICE_CROP.get(), ModBlocks.TOMATO_CROP.get(), ModBlocks.RICE_CROP_PANICLES.get(),
                ModBlocks.ROAST_CHICKEN_BLOCK.get(), ModBlocks.SANDY_SHRUB.get(), ModBlocks.ROPE.get(),
                ModBlocks.CANVAS_RUG.get(), ModBlocks.COOKING_POT.get(), ModBlocks.SAFETY_NET.get());
    }
}
