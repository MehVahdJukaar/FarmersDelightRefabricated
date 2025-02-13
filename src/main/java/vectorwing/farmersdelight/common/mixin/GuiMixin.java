package vectorwing.farmersdelight.common.mixin;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.client.gui.HUDOverlays;


@Mixin(value = Gui.class, priority = 999) // Before AppleSkin
public class GuiMixin {
    @Unique
    private DeltaTracker farmersdelightrefabricated$deltaTracker;

    @Inject(method = "renderHotbarAndDecorations", at = @At("HEAD"))
    private void farmersdelightrefabricated$captureDeltaTracker(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        farmersdelightrefabricated$deltaTracker = deltaTracker;
    }

    @Inject(method = "renderHotbarAndDecorations", at = @At("TAIL"))
    private void farmersdelightrefabricated$clearDeltaTracker(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        farmersdelightrefabricated$deltaTracker = null;
    }

    @Inject(method = "renderHearts", at = @At("TAIL"))
    private void farmersdelightrefabricated$renderHearts(GuiGraphics guiGraphics, Player player, int x, int y, int height, int offsetHeartIndex, float maxHealth, int currentHealth, int displayHealth, int absorptionAmount, boolean renderHighlight, CallbackInfo ci) {
        HUDOverlays.ComfortOverlay.INSTANCE.render(guiGraphics, farmersdelightrefabricated$deltaTracker);
    }

    @Inject(method = "renderFood", at = @At("TAIL"))
    private void farmersdelightrefabricated$renderNourishment(GuiGraphics guiGraphics, Player player, int y, int x, CallbackInfo ci) {
        HUDOverlays.NourishmentOverlay.INSTANCE.render(guiGraphics, farmersdelightrefabricated$deltaTracker);
    }
}
