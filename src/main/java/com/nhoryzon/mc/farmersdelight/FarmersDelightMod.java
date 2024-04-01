package com.nhoryzon.mc.farmersdelight;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import vectorwing.farmersdelight.FarmersDelight;

// Dont use!
@Deprecated(forRemoval = true)
public class FarmersDelightMod {

    public static final ResourceKey<CreativeModeTab> ITEM_GROUP =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB,
            FarmersDelight.res("farmersdelight"));
}
