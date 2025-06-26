package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.refabricated.duck.SkilletEntityRenderState;

@Mixin(HumanoidMobRenderer.class)
public class HumanoidMobRendererMixin {
    @Inject(method = "extractHumanoidRenderState", at = @At("TAIL"))
    private static void fdrf$extractSkilletEntityRenderState(LivingEntity entity, HumanoidRenderState reusedState, float partialTick, ItemModelResolver itemModelResolver, CallbackInfo ci) {
        ((SkilletEntityRenderState)reusedState).fdrf$setSkilletFlipTimestamp(entity.getUseItem().getOrDefault(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get(), -1L));
    }
}
