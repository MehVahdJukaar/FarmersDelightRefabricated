package vectorwing.farmersdelight.common.mixin;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vectorwing.farmersdelight.common.registry.ModBlocks;

@Mixin(LevelRenderer.class)
public abstract class HideBlockBreakProgressMixin
{
	@ModifyArgs(method = "extractBlockDestroyAnimation", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/state/level/BlockBreakingRenderState;<init>(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)V"))
	private void hideBlockDamage(Args args) {
		BlockState state = args.get(1);
		int progress = args.get(2);
		if (state.getBlock() == ModBlocks.CANVAS_RUG.get()) {
			args.set(2, 0);
		}
	}
}
