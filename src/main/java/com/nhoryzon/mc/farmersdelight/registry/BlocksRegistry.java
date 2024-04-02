package com.nhoryzon.mc.farmersdelight.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

// Dont use!
@Deprecated(forRemoval = true)
public enum BlocksRegistry {
    SKILLET(ModBlocks.SKILLET),
    COOKING_POT(ModBlocks.COOKING_POT),
    STOVE(ModBlocks.STOVE);

    private final Supplier<Block> supplier;

    BlocksRegistry(Supplier<Block> rottenTomato) {
        this.supplier = rottenTomato;
    }

    public Block get() {
        return supplier.get();
    }
}
