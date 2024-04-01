package com.nhoryzon.mc.farmersdelight.registry;

import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

// Dont use!
@Deprecated(forRemoval = true)
public enum ItemsRegistry {
    ROTTEN_TOMATO(ModItems.ROTTEN_TOMATO);

    private final Supplier<Item> supplier;

    ItemsRegistry(Supplier<Item> rottenTomato) {
        this.supplier = rottenTomato;
    }

    public Item get() {
        return supplier.get();
    }
}
