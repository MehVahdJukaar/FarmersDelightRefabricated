package vectorwing.farmersdelight.common.utility;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import vectorwing.farmersdelight.common.tag.ModTags;

public class SoilUtils {
    public static boolean isAbleToPlaceRichSoil(Block block) {
        if (block.builtInRegistryHolder().is(ModTags.DOES_NOT_SURVIVE_RICH_SOIL))
            return false;

        if (block.builtInRegistryHolder().is(ModTags.SURVIVES_RICH_SOIL_FARMLAND))
            return true;

        return !(block instanceof CropBlock || block instanceof NetherWartBlock || block instanceof WaterlilyBlock);
    }

    public static boolean isAbleToPlaceRichSoilFarmland(Block block) {
        if (block.builtInRegistryHolder().is(ModTags.DOES_NOT_SURVIVE_RICH_SOIL_FARMLAND))
            return false;

        if (block.builtInRegistryHolder().is(ModTags.SURVIVES_RICH_SOIL_FARMLAND))
            return true;

        return !(block == Blocks.DEAD_BUSH || block == Blocks.LILY_PAD || block == Blocks.RED_MUSHROOM || block == Blocks.BROWN_MUSHROOM || block == Blocks.NETHER_WART);
    }
}
