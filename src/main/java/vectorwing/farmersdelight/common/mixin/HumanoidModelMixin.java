package vectorwing.farmersdelight.common.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

// TODO: Handle differently if the Skillet changes get merged.
@Mixin(HumanoidModel.class)
public class HumanoidModelMixin {
    @Shadow @Final public ModelPart leftArm;

    @Shadow @Final public ModelPart rightArm;

    @Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getUsedItemHand()Lnet/minecraft/world/InteractionHand;"))
    private <T extends LivingEntity> void farmersdelightrefabricated$setupSkilletThirdPersonAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        ItemStack stack = entity.getUseItem();
        if (stack.getItem() instanceof SkilletItem && stack.has(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get())) {
            long time = stack.get(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get());
            float partialTicks = Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(false);
            float animation = ((entity.level().getGameTime() - time) + partialTicks) / SkilletItem.FLIP_TIME;
            animation = Mth.clamp(animation, 0, 1);

            if (entity.getMainArm() == HumanoidArm.LEFT) {
                leftArm.xRot = (-Mth.sin(animation * Mth.TWO_PI) * 15 - 20) * (float) (Math.PI / 180.0);
            } else {
                rightArm.xRot = (-Mth.sin(animation * Mth.TWO_PI) * 15 - 20) * (float) (Math.PI / 180.0);
            }
        }
    }
}
