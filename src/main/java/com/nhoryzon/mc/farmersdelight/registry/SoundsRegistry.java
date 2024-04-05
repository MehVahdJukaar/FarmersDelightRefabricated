package com.nhoryzon.mc.farmersdelight.registry;

import net.minecraft.sounds.SoundEvent;
import vectorwing.farmersdelight.common.registry.ModSounds;

import java.util.function.Supplier;

@Deprecated(forRemoval = true)
public enum SoundsRegistry {
    BLOCK_STOVE_CRACKLE(ModSounds.BLOCK_STOVE_CRACKLE),

    BLOCK_COOKING_POT_BOIL_SOUP(ModSounds.BLOCK_COOKING_POT_BOIL_SOUP),
    BLOCK_COOKING_POT_BOIL(ModSounds.BLOCK_COOKING_POT_BOIL),

    BLOCK_CUTTING_BOARD_KNIFE(ModSounds.BLOCK_CUTTING_BOARD_KNIFE),

    BLOCK_CABINET_OPEN(ModSounds.BLOCK_CABINET_OPEN),
    BLOCK_CABINET_CLOSE(ModSounds.BLOCK_CABINET_CLOSE),

    BLOCK_SKILLET_SIZZLE(ModSounds.BLOCK_SKILLET_SIZZLE),
    BLOCK_SKILLET_ADD_FOOD(ModSounds.BLOCK_SKILLET_ADD_FOOD),
    ITEM_SKILLET_ATTACK_STRONG(ModSounds.ITEM_SKILLET_ATTACK_STRONG),
    ITEM_SKILLET_ATTACK_WEAK(ModSounds.ITEM_SKILLET_ATTACK_WEAK),

    BLOCK_TOMATO_PICK_FROM_BUSH(ModSounds.ITEM_TOMATO_PICK_FROM_BUSH),

    ENTITY_ROTTEN_TOMATO_THROW(ModSounds.ENTITY_ROTTEN_TOMATO_THROW),
    ENTITY_ROTTEN_TOMATO_HIT(ModSounds.ENTITY_ROTTEN_TOMATO_HIT);


    private final Supplier<SoundEvent> soundEvent;

    SoundsRegistry(Supplier<SoundEvent> soundEvent) {
        this.soundEvent = soundEvent;
    }

    public SoundEvent get() {
        return soundEvent.get();
    }
}
