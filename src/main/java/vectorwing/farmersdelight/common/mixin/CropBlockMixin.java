package vectorwing.farmersdelight.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.registry.ModBlocks;

@Mixin(CropBlock.class)
public class CropBlockMixin {
    @ModifyReturnValue(method = "mayPlaceOn", at = @At("RETURN"))
    private boolean allowSaplingOnRichSoil(boolean original, BlockState state) {
        return original || state.is(ModBlocks.RICH_SOIL_FARMLAND.get());
    }
}
