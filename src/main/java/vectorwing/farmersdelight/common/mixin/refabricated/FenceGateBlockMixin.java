package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.FenceGateBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.RopeFenceGateBlock;
import vectorwing.farmersdelight.common.registry.ModSounds;

@Mixin(FenceGateBlock.class)
public class FenceGateBlockMixin {
	@ModifyExpressionValue(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/properties/WoodType;fenceGateOpen()Lnet/minecraft/sounds/SoundEvent;"))
	private SoundEvent fdrf$modifyUseWithoutItemOpenSound(SoundEvent original) {
		if ((FenceGateBlock) (Object) this instanceof RopeFenceGateBlock) {
			return ModSounds.BLOCK_ROPE_FENCE_GATE_OPEN.get();
		}
		return original;
	}

	@ModifyExpressionValue(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/properties/WoodType;fenceGateClose()Lnet/minecraft/sounds/SoundEvent;"))
	private SoundEvent fdrf$modifyUseWithoutItemCloseSound(SoundEvent original) {
		if ((FenceGateBlock) (Object) this instanceof RopeFenceGateBlock) {
			return ModSounds.BLOCK_ROPE_FENCE_GATE_CLOSE.get();
		}
		return original;
	}

	@ModifyExpressionValue(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/properties/WoodType;fenceGateClose()Lnet/minecraft/sounds/SoundEvent;"))
	private SoundEvent fdrf$modifyOnExplosionHitCloseSound(SoundEvent original) {
		if ((FenceGateBlock) (Object) this instanceof RopeFenceGateBlock) {
			return ModSounds.BLOCK_ROPE_FENCE_GATE_CLOSE.get();
		}
		return original;
	}

	@ModifyExpressionValue(method = "neighborChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/properties/WoodType;fenceGateOpen()Lnet/minecraft/sounds/SoundEvent;"))
	private SoundEvent fdrf$modifyNeighborChangedOpenSound(SoundEvent original) {
		if ((FenceGateBlock) (Object) this instanceof RopeFenceGateBlock) {
			return ModSounds.BLOCK_ROPE_FENCE_GATE_OPEN.get();
		}
		return original;
	}

	@ModifyExpressionValue(method = "neighborChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/properties/WoodType;fenceGateClose()Lnet/minecraft/sounds/SoundEvent;"))
	private SoundEvent fdrf$modifyNeighborChangedCloseSound(SoundEvent original) {
		if ((FenceGateBlock) (Object) this instanceof RopeFenceGateBlock) {
			return ModSounds.BLOCK_ROPE_FENCE_GATE_CLOSE.get();
		}
		return original;
	}
}