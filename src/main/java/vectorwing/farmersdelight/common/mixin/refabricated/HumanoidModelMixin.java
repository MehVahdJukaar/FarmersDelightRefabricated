package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.client.extension.pose.ModArmPoses;

// TODO: Handle differently if the Skillet changes get merged.
@Mixin(HumanoidModel.class)
public class HumanoidModelMixin {

	@Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getUsedItemHand()Lnet/minecraft/world/InteractionHand;"))
    private <T extends LivingEntity> void farmersdelightrefabricated$setupSkilletThirdPersonAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		HumanoidModel<?> model = (HumanoidModel<?>) (Object) this;
		ModArmPoses.SkilletArmPose.applyTransform(model, entity, entity.getMainArm());
		ModArmPoses.HandCookingArmPose.applyTransform(model, entity, entity.getMainArm());
    }
}
