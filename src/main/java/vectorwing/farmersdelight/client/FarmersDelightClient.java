package vectorwing.farmersdelight.client;

import io.github.fabricators_of_create.porting_lib.event.client.MouseInputEvents;
import io.github.fabricators_of_create.porting_lib.registries.mixin.NetworkedRegistryDataAccessor;
import io.github.fabricators_of_create.porting_lib.util.NetworkDirection;
import io.github.fabricators_of_create.porting_lib.util.NetworkHooks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;
import vectorwing.farmersdelight.client.event.ClientSetupEvents;
import vectorwing.farmersdelight.client.event.TooltipEvents;
import vectorwing.farmersdelight.client.gui.ComfortHealthOverlay;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.client.gui.NourishmentHungerOverlay;
import vectorwing.farmersdelight.client.renderer.SkilletItemRenderer;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.networking.ModNetworking;
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

        NourishmentHungerOverlay.init();
        ComfortHealthOverlay.init();

        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SKILLET.get(), new SkilletItemRenderer());
        // could have been done with item renderer but this way we can have easier control over item positioning using the model json
        ItemProperties.register(ModItems.SKILLET.get(), new ResourceLocation("cooking"),
                (stack, world, entity, s) -> stack.getTagElement("Cooking") != null ? 1 : 0);

        // skillet flip stuff. could be put in a better place...
        MouseInputEvents.AFTER_BUTTON.register((button,modifier, action) -> {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1 && action == MouseInputEvents.Action.PRESS) {
                var player = Minecraft.getInstance().player;
                if (player != null && player.isUsingItem()) {
                    if (player.getUseItem().getItem() instanceof SkilletItem) {
                        ModNetworking.CHANNEL.sendToServer(new ModNetworking.FlipSkilletMessage());
                    }
                }
            }
        });
    }
}
