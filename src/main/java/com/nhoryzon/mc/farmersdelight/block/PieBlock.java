package com.nhoryzon.mc.farmersdelight.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

@Deprecated(forRemoval = true)
public class PieBlock extends vectorwing.farmersdelight.common.block.PieBlock {
    public final Item pieSlice;
    public PieBlock(Item pieSlice) {
        super(Properties.copy(Blocks.CAKE), () -> pieSlice);
        this.pieSlice = pieSlice;
    }
}
