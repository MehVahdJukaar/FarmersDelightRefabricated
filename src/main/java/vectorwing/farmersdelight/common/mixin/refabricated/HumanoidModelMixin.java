package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.client.renderer.SkilletItemRenderer;
import vectorwing.farmersdelight.refabricated.duck.SkilletEntityRenderState;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin {
	@Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;useItemHand:Lnet/minecraft/world/InteractionHand;"))
	private <T extends HumanoidRenderState> void farmersdelightrefabricated$setupSkilletThirdPersonAnim(T humanoidRenderState, CallbackInfo ci) {
		boolean mainArmRight = humanoidRenderState.mainArm == HumanoidArm.RIGHT;
		boolean useItemMainhand = humanoidRenderState.useItemHand == InteractionHand.MAIN_HAND;
		HumanoidArm arm = mainArmRight == useItemMainhand ? HumanoidArm.RIGHT : HumanoidArm.LEFT;
		SkilletItemRenderer.applyTransform((HumanoidModel)(Object)this, (SkilletEntityRenderState)humanoidRenderState, humanoidRenderState.mainArm);
	}
}
