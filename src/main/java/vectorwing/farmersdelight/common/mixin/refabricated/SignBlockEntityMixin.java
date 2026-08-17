package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.block.state.CanvasSign;

@Mixin(SignBlockEntity.class)
public abstract class SignBlockEntityMixin extends BlockEntity {
	@Shadow
	public SignText frontText;

	@Shadow
	public SignText backText;

	public SignBlockEntityMixin(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
		super(type, worldPosition, blockState);
	}

	@Inject(method = "<init>(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V", at = @At("RETURN"))
	private void fdrf$fixSignTextColor(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState, CallbackInfo ci) {
		if (getBlockState().getBlock() instanceof CanvasSign canvasSignBlock) {
			if (canvasSignBlock.isDarkBackground()) {
				frontText = frontText.asMutable().setColor(DyeColor.WHITE).asImmutable();
				backText = backText.asMutable().setColor(DyeColor.WHITE).asImmutable();
			}
		}
	}
}
