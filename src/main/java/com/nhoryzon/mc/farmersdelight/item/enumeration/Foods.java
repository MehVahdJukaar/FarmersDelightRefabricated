package com.nhoryzon.mc.farmersdelight.item.enumeration;

import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.FoodValues;

// Dont use!
@Deprecated(forRemoval = true)
public enum Foods {
    PIE_SLICE(FoodValues.PIE_SLICE);

    final FoodProperties supplier;

    Foods(FoodProperties supplier) {
        this.supplier = supplier;
    }

    public FoodProperties get() {
        return this.supplier;
    }
}
