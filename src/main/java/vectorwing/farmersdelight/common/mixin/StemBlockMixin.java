package vectorwing.farmersdelight.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;
import vectorwing.farmersdelight.common.tag.ModTags;

/**
 * Fabric should <b>really</b> have an event for this...
 * This is the bare minimum to keep parity with Forge.
 */
@Mixin(StemBlock.class)
public class StemBlockMixin {
    @ModifyExpressionValue(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean mynethersdelightrefabricated$modifyRandomTickCallForStems(boolean original, @Local(ordinal = 1) BlockState below) {
        if (below.getBlock() instanceof RichSoilBlock)
            return !((Block)(Object)this).builtInRegistryHolder().is(ModTags.DOES_NOT_SURVIVE_RICH_SOIL);

        if (below.getBlock() instanceof RichSoilFarmlandBlock)
            return ((Block)(Object)this).builtInRegistryHolder().is(ModTags.SURVIVES_RICH_SOIL_FARMLAND);

        return original;
    }
}
