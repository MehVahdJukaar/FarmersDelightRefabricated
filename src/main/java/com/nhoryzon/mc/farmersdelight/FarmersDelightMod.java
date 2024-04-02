package com.nhoryzon.mc.farmersdelight;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import vectorwing.farmersdelight.FarmersDelight;

// Dont use!
@Deprecated(forRemoval = true)
public class FarmersDelightMod {

    public static final ResourceKey<CreativeModeTab> ITEM_GROUP =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB,
                    FarmersDelight.res("farmersdelight"));

    public static final Configuration CONFIG = new Configuration();

    public static MutableComponent i18n(String key, Object... args) {
        return Component.translatable("farmersdelight" + "." + key, args);
    }
}
