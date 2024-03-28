package vectorwing.farmersdelight.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.PitcherCropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.registry.ModBlocks;

@Mixin(PitcherCropBlock.class)
public class PitcherCropBlockMixin {
    @ModifyReturnValue(method = "mayPlaceOn", at = @At("RETURN"))
    private boolean allowPitcherCropOnRichSoil(boolean original, BlockState state) {
        return original || state.is(ModBlocks.RICH_SOIL_FARMLAND.get());
    }
}
