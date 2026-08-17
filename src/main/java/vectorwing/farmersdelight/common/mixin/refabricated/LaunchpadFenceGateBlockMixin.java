package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.registry.ModSounds;

@Mixin(FenceGateBlock.class)
public class LaunchpadFenceGateBlockMixin {
	@ModifyExpressionValue(method = "<init>(Lnet/minecraft/world/level/block/state/properties/WoodType;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/properties/WoodType;fenceGateOpen()Lnet/minecraft/sounds/SoundEvent;"))
	private static SoundEvent fdrf$modifyOpenSound(SoundEvent original, @Local BlockBehaviour.Properties properties) {
		if (properties.blockIdOrThrow().identifier().getPath().equals("rope_fence_gate")) {
			return ModSounds.BLOCK_ROPE_FENCE_GATE_OPEN.get();
		}
		return original;
	}

	@ModifyExpressionValue(method = "<init>(Lnet/minecraft/world/level/block/state/properties/WoodType;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/properties/WoodType;fenceGateClose()Lnet/minecraft/sounds/SoundEvent;"))
	private static SoundEvent fdrf$modifyCloseSound(SoundEvent original, @Local BlockBehaviour.Properties properties) {
		if (properties.blockIdOrThrow().identifier().getPath().equals("rope_fence_gate")) {
			return ModSounds.BLOCK_ROPE_FENCE_GATE_CLOSE.get();
		}
		return original;
	}
}
