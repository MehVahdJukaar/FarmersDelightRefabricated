package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.client.gui.HUDOverlays;


@Mixin(value = Hud.class, priority = 999) // Before AppleSkin
public class HudMixin {
    @Unique
    private DeltaTracker farmersdelightrefabricated$deltaTracker;

    @Inject(method = "extractHotbarAndDecorations", at = @At("HEAD"))
    private void farmersdelightrefabricated$captureDeltaTracker(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        farmersdelightrefabricated$deltaTracker = deltaTracker;
    }

    @Inject(method = "extractHotbarAndDecorations", at = @At("TAIL"))
    private void farmersdelightrefabricated$clearDeltaTracker(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        farmersdelightrefabricated$deltaTracker = null;
    }

    @Inject(method = "extractHearts", at = @At("TAIL"))
    private void farmersdelightrefabricated$renderHearts(GuiGraphicsExtractor guiGraphics, Player player, int x, int y, int height, int offsetHeartIndex, float maxHealth, int currentHealth, int displayHealth, int absorptionAmount, boolean renderHighlight, CallbackInfo ci) {
        HUDOverlays.ComfortOverlay.INSTANCE.render(guiGraphics, farmersdelightrefabricated$deltaTracker);
    }

    @Inject(method = "extractFood", at = @At("TAIL"))
    private void farmersdelightrefabricated$renderNourishment(GuiGraphicsExtractor guiGraphics, Player player, int y, int x, CallbackInfo ci) {
        HUDOverlays.NourishmentOverlay.INSTANCE.render(guiGraphics, farmersdelightrefabricated$deltaTracker);
    }
}
