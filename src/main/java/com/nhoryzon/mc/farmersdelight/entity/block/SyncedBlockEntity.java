package com.nhoryzon.mc.farmersdelight.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@Deprecated(forRemoval = true)
public class SyncedBlockEntity extends vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity {
    public SyncedBlockEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }
}
