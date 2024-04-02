package com.nhoryzon.mc.farmersdelight.registry;

import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

// Dont use!
@Deprecated(forRemoval = true)
public enum ItemsRegistry {
    ROTTEN_TOMATO(ModItems.ROTTEN_TOMATO),
    FRIED_EGG(ModItems.FRIED_EGG),
    ROPE(ModItems.ROPE),
    STRAW(ModItems.STRAW),
    STUFFED_POTATO(ModItems.STUFFED_POTATO),
    TOMATO(ModItems.TOMATO),
    TOMATO_SEEDS(ModItems.TOMATO_SEEDS),
    TOMATO_SAUCE(ModItems.TOMATO_SAUCE),
    CARROT_CRATE(ModItems.CARROT_CRATE),
    POTATO_CRATE(ModItems.POTATO_CRATE),
    BEETROOT_CRATE(ModItems.BEETROOT_CRATE),
    ONION_CRATE(ModItems.ONION_CRATE),
    TOMATO_CRATE(ModItems.TOMATO_CRATE),
    CABBAGE_CRATE(ModItems.CABBAGE_CRATE),
    RICE_BALE(ModItems.RICE_BALE),
    FLINT_KNIFE(ModItems.FLINT_KNIFE),
    IRON_KNIFE(ModItems.IRON_KNIFE),
    DIAMOND_KNIFE(ModItems.DIAMOND_KNIFE),
    GOLDEN_KNIFE(ModItems.GOLDEN_KNIFE),
    CABBAGE(ModItems.CABBAGE),
    CABBAGE_LEAF(ModItems.CABBAGE_LEAF),
    CABBAGE_SEEDS(ModItems.CABBAGE_SEEDS),
    ONION(ModItems.ONION),
    HAMBURGER(ModItems.HAMBURGER),
    HAM(ModItems.HAM),
    CHICKEN_SANDWICH(ModItems.CHICKEN_SANDWICH),
    BACON_SANDWICH(ModItems.BACON_SANDWICH),
    MUTTON_CHOPS(ModItems.MUTTON_CHOPS),
    MUTTON_WRAP(ModItems.MUTTON_WRAP),
    BARBECUE_STICK(ModItems.BARBECUE_STICK),
    MELON_JUICE(ModItems.MELON_JUICE),
    APPLE_PIE(ModItems.APPLE_PIE),
    APPLE_CIDER(ModItems.APPLE_CIDER),
    EGG_SANDWICH(ModItems.EGG_SANDWICH),
    HONEY_GLAZED_HAM_BLOCK(ModItems.HONEY_GLAZED_HAM_BLOCK),
    ROAST_CHICKEN_BLOCK(ModItems.ROAST_CHICKEN_BLOCK),
    ROASTED_MUTTON_CHOPS(ModItems.ROASTED_MUTTON_CHOPS),
    PASTA_WITH_MUTTON_CHOP(ModItems.PASTA_WITH_MUTTON_CHOP),
    STEAK_AND_POTATOES(ModItems.STEAK_AND_POTATOES),
    BEEF_STEW(ModItems.BEEF_STEW),
    COOKED_RICE(ModItems.COOKED_RICE),
    BACON_AND_EGGS(ModItems.BACON_AND_EGGS),
    MIXED_SALAD(ModItems.MIXED_SALAD),
    MILK_BOTTLE(ModItems.MILK_BOTTLE),
    FRUIT_SALAD(ModItems.FRUIT_SALAD),
    CHOCOLATE_PIE_SLICE(ModItems.CHOCOLATE_PIE_SLICE),
    APPLE_PIE_SLICE(ModItems.APPLE_PIE_SLICE);


    private final Supplier<Item> supplier;

    ItemsRegistry(Supplier<Item> rottenTomato) {
        this.supplier = rottenTomato;
    }

    public Item get() {
        return supplier.get();
    }
}
