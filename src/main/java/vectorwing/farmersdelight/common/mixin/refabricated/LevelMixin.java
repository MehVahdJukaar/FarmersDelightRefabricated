package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.refabricated.block.ReplaceOnRemoval;

@Mixin(Level.class)
public abstract class LevelMixin {
	@Shadow
	public abstract BlockState getBlockState(BlockPos pos);

	@WrapOperation(method = "removeBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
	public boolean fdrf$setBlock(Level instance,
								 BlockPos pos,
								 BlockState blockState,
								 int updateFlags,
								 Operation<Boolean> original) {
		BlockState oldState = getBlockState(pos);
		boolean returnValue = original.call(instance, pos, blockState, updateFlags);
		if (oldState.getBlock() instanceof ReplaceOnRemoval replaceOnRemoval && replaceOnRemoval.onRemove(oldState, blockState, instance, pos, updateFlags)) {
			return true;
		}
		return returnValue;
	}
}
