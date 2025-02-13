package vectorwing.farmersdelight.common.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.client.gui.ComfortHealthOverlay;
import vectorwing.farmersdelight.client.gui.NourishmentHungerOverlay;


@Mixin(value = Gui.class, priority = 999) // Before AppleSkin
public abstract class GuiMixin {
    @Shadow public abstract void render(GuiGraphics guiGraphics, float partialTick);

    @Unique
    private float farmersdelightrefabricated$partialTick;

    @Inject(method = "render", at = @At("HEAD"))
    private void farmersdelightrefabricated$captureDeltaTracker(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        farmersdelightrefabricated$partialTick = partialTick;
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void farmersdelightrefabricated$clearDeltaTracker(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        farmersdelightrefabricated$partialTick = 0.0F;
    }

    @Inject(method = "renderHearts", at = @At("TAIL"))
    private void farmersdelightrefabricated$renderHearts(GuiGraphics guiGraphics, Player player, int x, int y, int height, int offsetHeartIndex, float maxHealth, int currentHealth, int displayHealth, int absorptionAmount, boolean renderHighlight, CallbackInfo ci) {
        ComfortHealthOverlay.onRenderGuiOverlayPost(guiGraphics, farmersdelightrefabricated$partialTick);
    }

    @Inject(method = "renderPlayerHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V", ordinal = 2))
    private void farmersdelightrefabricated$renderNourishment(GuiGraphics guiGraphics, CallbackInfo ci) {
        NourishmentHungerOverlay.onRenderGuiOverlayPost(guiGraphics, farmersdelightrefabricated$partialTick);
    }
}
