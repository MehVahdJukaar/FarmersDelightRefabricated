package com.nhoryzon.mc.farmersdelight.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

// Dont use!
@Deprecated(forRemoval = true)
public class PieBlock extends vectorwing.farmersdelight.common.block.PieBlock {

    public PieBlock( Item pieSlice) {
        super(FabricBlockSettings.copyOf(Blocks.CAKE),()-> pieSlice);
    }
}
