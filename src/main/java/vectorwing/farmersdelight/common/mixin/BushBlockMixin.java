package vectorwing.farmersdelight.common.mixin;

import io.github.fabricators_of_create.porting_lib.common.util.IPlantable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BushBlock.class)
public abstract class BushBlockMixin extends Block {
    public BushBlockMixin(Properties properties) {
        super(properties);
    }

    // this is something PortingLib should do...
    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    private void allowSaplingOnRichSoil(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockPos blockpos = pos.below();
        if (state.getBlock() == this)
            cir.setReturnValue(level.getBlockState(blockpos)
                    .canSustainPlant(level, blockpos, Direction.UP, (IPlantable) this));
    }
}
