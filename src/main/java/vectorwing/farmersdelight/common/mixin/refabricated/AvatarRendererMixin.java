package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.client.extension.HandCookedItemClientExtension;
import vectorwing.farmersdelight.client.extension.SkilletItemClientExtension;
import vectorwing.farmersdelight.client.extension.pose.ModArmPoses;

// TODO: Handle differently if the Skillet changes get merged.
@Mixin(PlayerRenderer.class)
public class AvatarRendererMixin {

	@ModifyReturnValue(method = "getArmPose", at = @At(value = "RETURN"))
    private static HumanoidModel.ArmPose farmersdelightrefabricated$setupSkilletFirstPersonAnim(HumanoidModel.ArmPose original, AbstractClientPlayer player, InteractionHand hand) {
		var skilletPose = SkilletItemClientExtension.getArmPose(player, hand, player.getItemInHand(hand));
		if (skilletPose != null) {
			return skilletPose;
		}
		var skewerPose = HandCookedItemClientExtension.getArmPose(player, hand, player.getItemInHand(hand));
		if (skewerPose != null) {
			return skewerPose;
		}
		return original;
	}
}
