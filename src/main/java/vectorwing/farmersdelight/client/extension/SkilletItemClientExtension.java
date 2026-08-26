package vectorwing.farmersdelight.client.extension;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.EnumParameters;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

public class SkilletItemClientExtension
{

	public static HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity living, InteractionHand hand, ItemStack stack) {
		return stack.has(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get()) ? HumanoidModel.ArmPose.FARMERSDELIGHT_SKILLET_FLIP : null;
	}
}
