package com.nhoryzon.mc.farmersdelight.item.enumeration;

import com.google.common.base.Supplier;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.FoodValues;

@Deprecated(forRemoval = true)
public enum Foods {
    CABBAGE(FoodValues.CABBAGE),
    TOMATO(FoodValues.TOMATO),
    ONION(FoodValues.ONION),

    APPLE_CIDER(FoodValues.APPLE_CIDER),

    FRIED_EGG(FoodValues.FRIED_EGG),
    TOMATO_SAUCE(FoodValues.TOMATO_SAUCE),
    WHEAT_DOUGH(FoodValues.WHEAT_DOUGH),
    RAW_PASTA(FoodValues.RAW_PASTA),
    PIE_CRUST(FoodValues.PIE_CRUST),
    PUMPKIN_SLICE(FoodValues.PUMPKIN_SLICE),
    CABBAGE_LEAF(FoodValues.CABBAGE_LEAF),
    MINCED_BEEF(FoodValues.MINCED_BEEF),
    BEEF_PATTY(FoodValues.BEEF_PATTY),
    CHICKEN_CUTS(FoodValues.CHICKEN_CUTS),
    COOKED_CHICKEN_CUTS(FoodValues.COOKED_CHICKEN_CUTS),
    BACON(FoodValues.BACON),
    COOKED_BACON(FoodValues.COOKED_BACON),
    COD_SLICE(FoodValues.COD_SLICE),
    COOKED_COD_SLICE(FoodValues.COOKED_COD_SLICE),
    SALMON_SLICE(FoodValues.SALMON_SLICE),
    COOKED_SALMON_SLICE(FoodValues.COOKED_SALMON_SLICE),
    MUTTON_CHOP(FoodValues.MUTTON_CHOP),
    COOKED_MUTTON_CHOP(FoodValues.COOKED_MUTTON_CHOP),
    HAM(FoodValues.HAM),
    SMOKED_HAM(FoodValues.SMOKED_HAM),

    POPSICLE(FoodValues.POPSICLE),
    COOKIES(FoodValues.COOKIES),
    CAKE_SLICE(FoodValues.CAKE_SLICE),
    PIE_SLICE(FoodValues.PIE_SLICE),
    FRUIT_SALAD(FoodValues.FRUIT_SALAD),
    GLOW_BERRY_CUSTARD(FoodValues.GLOW_BERRY_CUSTARD),

    MIXED_SALAD(FoodValues.MIXED_SALAD),
    NETHER_SALAD(FoodValues.NETHER_SALAD),
    BARBECUE_STICK(FoodValues.BARBECUE_STICK),
    EGG_SANDWICH(FoodValues.EGG_SANDWICH),
    CHICKEN_SANDWICH(FoodValues.CHICKEN_SANDWICH),
    HAMBURGER(FoodValues.HAMBURGER),
    BACON_SANDWICH(FoodValues.BACON_SANDWICH),
    MUTTON_WRAP(FoodValues.MUTTON_WRAP),
    DUMPLINGS(FoodValues.DUMPLINGS),
    STUFFED_POTATO(FoodValues.STUFFED_POTATO),
    CABBAGE_ROLLS(FoodValues.CABBAGE_ROLLS),
    SALMON_ROLL(FoodValues.SALMON_ROLL),
    COD_ROLL(FoodValues.COD_ROLL),
    KELP_ROLL(FoodValues.KELP_ROLL),
    KELP_ROLL_SLICE(FoodValues.KELP_ROLL_SLICE),

    COOKED_RICE(FoodValues.COOKED_RICE),
    BONE_BROTH(FoodValues.BONE_BROTH),
    BEEF_STEW(FoodValues.BEEF_STEW),
    VEGETABLE_SOUP(FoodValues.VEGETABLE_SOUP),
    FISH_STEW(FoodValues.FISH_STEW),
    CHICKEN_SOUP(FoodValues.CHICKEN_SOUP),
    FRIED_RICE(FoodValues.FRIED_RICE),
    PUMPKIN_SOUP(FoodValues.PUMPKIN_SOUP),
    BAKED_COD_STEW(FoodValues.BAKED_COD_STEW),
    NOODLE_SOUP(FoodValues.NOODLE_SOUP),

    BACON_AND_EGGS(FoodValues.BACON_AND_EGGS),
    RATATOUILLE(FoodValues.RATATOUILLE),
    STEAK_AND_POTATOES(FoodValues.STEAK_AND_POTATOES),
    PASTA_WITH_MEATBALLS(FoodValues.PASTA_WITH_MEATBALLS),
    PASTA_WITH_MUTTON_CHOP(FoodValues.PASTA_WITH_MUTTON_CHOP),
    MUSHROOM_RICE(FoodValues.MUSHROOM_RICE),
    ROASTED_MUTTON_CHOPS(FoodValues.ROASTED_MUTTON_CHOPS),
    VEGETABLE_NOODLES(FoodValues.VEGETABLE_NOODLES),
    SQUID_INK_PASTA(FoodValues.SQUID_INK_PASTA),
    GRILLED_SALMON(FoodValues.GRILLED_SALMON),


    ROAST_CHICKEN(FoodValues.ROAST_CHICKEN),
    STUFFED_PUMPKIN(FoodValues.STUFFED_PUMPKIN),
    HONEY_GLAZED_HAM(FoodValues.HONEY_GLAZED_HAM),
    SHEPHERDS_PIE(FoodValues.SHEPHERDS_PIE),

    DOG_FOOD(FoodValues.DOG_FOOD);


    private final Supplier<FoodProperties> foodProperties;

    Foods(FoodProperties foodProperties) {
        this.foodProperties = () -> foodProperties;
    }

    public FoodProperties get() {
        return foodProperties.get();
    }
}
