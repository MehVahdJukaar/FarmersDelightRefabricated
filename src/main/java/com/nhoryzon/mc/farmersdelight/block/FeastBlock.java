package com.nhoryzon.mc.farmersdelight.block;

import net.minecraft.world.item.Item;

// Dont use!
@Deprecated(forRemoval = true)
public class FeastBlock extends vectorwing.farmersdelight.common.block.FeastBlock {

    public FeastBlock(Properties properties, Item servingItem, boolean hasLeftovers) {
        super(properties, () -> servingItem, hasLeftovers);
    }
}
