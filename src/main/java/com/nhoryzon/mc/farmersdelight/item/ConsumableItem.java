package com.nhoryzon.mc.farmersdelight.item;

import net.minecraft.world.item.Tier;

// Dont use!
@Deprecated(forRemoval = true)
public class ConsumableItem extends vectorwing.farmersdelight.common.item.ConsumableItem {

    public ConsumableItem(Properties properties) {
        super(properties);
    }

    public ConsumableItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public ConsumableItem(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }
}
