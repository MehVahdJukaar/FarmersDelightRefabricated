package vectorwing.farmersdelight.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;
import vectorwing.farmersdelight.common.tag.ModTags;

/**
 * Fabric should <b>really</b> have an event for this...
 * This is the bare minimum to keep parity with Forge.
 */
@Mixin(CropBlock.class)
public class CropBlockMixin {
    @ModifyVariable(method = "getGrowthSpeed", at = @At(value = "LOAD", ordinal = 1), ordinal = 1)
    private static float farmersdelightrefabricated$modifyGrowthSpeedForNonFarmland(float original, Block block, BlockGetter level, BlockPos pos) {
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.getBlock() instanceof RichSoilBlock && !block.builtInRegistryHolder().is(ModTags.DOES_NOT_SURVIVE_RICH_SOIL) && original < 0.00001F)
            return 1.0F;

        if (belowState.getBlock() instanceof RichSoilFarmlandBlock && block.builtInRegistryHolder().is(ModTags.SURVIVES_RICH_SOIL_FARMLAND) && original < 0.00001F) {
            if (belowState.hasProperty(RichSoilFarmlandBlock.MOISTURE) && belowState.getValue(RichSoilFarmlandBlock.MOISTURE) > 0)
                return 3.0F;
            return 1.0F;
        }

        return original;
    }
}
