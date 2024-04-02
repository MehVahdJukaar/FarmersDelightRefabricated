package com.nhoryzon.mc.farmersdelight.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
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
        this(FabricBlockSettings.copyOf(Blocks.TALL_GRASS).sounds(SoundType.CROP).offset(offsetType));
    }

}
