package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.client.extension.HandCookedItemClientExtension;
import vectorwing.farmersdelight.client.extension.SkilletItemClientExtension;
import vectorwing.farmersdelight.common.item.HandCookedItem;
import vectorwing.farmersdelight.common.registry.ModItems;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {
	@WrapOperation(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;applyItemArmTransform(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/entity/HumanoidArm;F)V", ordinal = 2))
	private static void farmersdelightrefabricated$setupHandCookedFirstPersonAnim(ItemInHandRenderer instance, PoseStack poseStack, HumanoidArm hand, float equippedProg, Operation<Void> original, AbstractClientPlayer player, float partialTicks, float pitch, InteractionHand hand2, float swingProgress, ItemStack stack, float equippedProgress, PoseStack poseStack2, MultiBufferSource buffer, int combinedLight, @Local HumanoidArm arm) {
		if (!(stack.getItem() instanceof HandCookedItem) || !HandCookedItemClientExtension.applyForgeHandTransform(poseStack, player, arm, stack, partialTicks, equippedProgress, swingProgress)) {
			original.call(instance, poseStack, hand, equippedProg);
		}
	}
}
