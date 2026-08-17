package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.state.CanvasSign;

@Mixin(SignBlockEntity.class)
public abstract class SignBlockEntityMixin extends BlockEntity {
	public SignBlockEntityMixin(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
		super(type, worldPosition, blockState);
	}

	@ModifyReturnValue(method = "createDefaultSignText", at = @At("TAIL"))
	private SignText fdrf$fixSignTextColor(SignText signText) {
		if (getBlockState().getBlock() instanceof CanvasSign canvasSignBlock) {
			if (canvasSignBlock.isDarkBackground()) {
				return signText.setColor(DyeColor.WHITE);
			}
		}
		return signText;
	}
}
