package com.nhoryzon.mc.farmersdelight.registry;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModSounds;

import java.util.function.Supplier;

// Dont use!
@Deprecated(forRemoval = true)
public enum SoundsRegistry {
    BLOCK_STOVE_CRACKLE(ModSounds.BLOCK_STOVE_CRACKLE),
    BLOCK_CUTTING_BOARD_KNIFE(ModSounds.BLOCK_CUTTING_BOARD_KNIFE),
    STOVE(ModSounds.BLOCK_STOVE_CRACKLE);

    private final Supplier<SoundEvent> supplier;

    SoundsRegistry(Supplier<SoundEvent> rottenTomato) {
        this.supplier = rottenTomato;
    }

    public SoundEvent get() {
        return supplier.get();
    }
}
