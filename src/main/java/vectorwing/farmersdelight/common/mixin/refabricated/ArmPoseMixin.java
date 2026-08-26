package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.model.HumanoidModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HumanoidModel.ArmPose.class)
public enum ArmPoseMixin {
	FARMERSDELIGHT_SKILLET_FLIP(false),
	FARMERSDELIGHT_HAND_COOKING(false);

	@Shadow
	ArmPoseMixin(boolean twoHanded) {

	}
}
