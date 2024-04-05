package com.nhoryzon.mc.farmersdelight.block;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.WildCropBlock;

// Dont use!
@Deprecated(forRemoval = true)
public class WildPatchBlock extends WildCropBlock {
    public WildPatchBlock( Properties properties) {
        super(MobEffects.CONFUSION, 8, properties);
    }

    public WildPatchBlock() {
        this(OffsetType.XZ);
    }

    public WildPatchBlock(OffsetType offsetType) {
        this(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).sound(SoundType.CROP).offsetType(offsetType));
    }

}
