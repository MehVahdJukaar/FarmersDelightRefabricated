package com.nhoryzon.mc.farmersdelight.registry;

import net.minecraft.world.effect.MobEffect;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.function.Supplier;

// Dont use!
@Deprecated(forRemoval = true)
public enum EffectsRegistry {
    NOURISHMENT(ModEffects.NOURISHMENT),
    COMFORT(ModEffects.COMFORT);

    private final Supplier<MobEffect> effect;

    EffectsRegistry(Supplier<MobEffect> effect) {
        this.effect = effect;
    }

    public MobEffect get() {
        return effect.get();
    }
}
