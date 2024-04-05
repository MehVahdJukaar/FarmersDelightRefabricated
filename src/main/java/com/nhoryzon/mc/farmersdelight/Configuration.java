package com.nhoryzon.mc.farmersdelight;

import java.util.List;

@Deprecated(forRemoval = true)
@SuppressWarnings("unused")
public class Configuration {

    /* Settings */
    private boolean isEnableVanillaCropCrates() {
        return vectorwing.farmersdelight.common.Configuration.ENABLE_VANILLA_CROP_CRATES.get();
    }
    private boolean isFarmersBuyFDCrops() {
        return vectorwing.farmersdelight.common.Configuration.FARMERS_BUY_FD_CROPS.get();
    }
    private boolean isWanderingTraderSellsFDItems() {
        return vectorwing.farmersdelight.common.Configuration.WANDERING_TRADER_SELLS_FD_ITEMS.get();
    }
    private double getRichSoilBoostChance() {
        return vectorwing.farmersdelight.common.Configuration.RICH_SOIL_BOOST_CHANCE.get();
    }
    private double getCuttingBoardFortuneBonus() {
        return vectorwing.farmersdelight.common.Configuration.CUTTING_BOARD_FORTUNE_BONUS.get();
    }
    private boolean isEnableRopeReeling() {
        return vectorwing.farmersdelight.common.Configuration.ENABLE_ROPE_REELING.get();
    }
    private List<? extends String> getCanvasSignDarkBackgroundList() {
        return vectorwing.farmersdelight.common.Configuration.CANVAS_SIGN_DARK_BACKGROUND_LIST.get();
    }

    /* Farming */
    private String getDefaultTomatoVineRope() {
        return vectorwing.farmersdelight.common.Configuration.DEFAULT_TOMATO_VINE_ROPE.get();
    }
    private boolean isEnableTomatoVineClimbingTaggedRopes() {
        return vectorwing.farmersdelight.common.Configuration.ENABLE_TOMATO_VINE_CLIMBING_TAGGED_ROPES.get();
    }

    /* Overrides */
    private boolean isVanillaSoupExtraEffects() {
        return vectorwing.farmersdelight.common.Configuration.VANILLA_SOUP_EXTRA_EFFECTS.get();
    }
    private boolean isRabbitStewJumpBoost() {
        return vectorwing.farmersdelight.common.Configuration.RABBIT_STEW_JUMP_BOOST.get();
    }
    private boolean isDispenserToolsCuttingBoard() {
        return vectorwing.farmersdelight.common.Configuration.DISPENSER_TOOLS_CUTTING_BOARD.get();
    }

    /* Stack size */

    private boolean isEnableStackableSoupSize() {
        return vectorwing.farmersdelight.common.Configuration.ENABLE_STACKABLE_SOUP_ITEMS.get();
    }
    private List<? extends String> getSoupItemList() {
        return vectorwing.farmersdelight.common.Configuration.SOUP_ITEM_LIST.get();
    }
    private boolean isOverrideAllSoupItems() {
        return vectorwing.farmersdelight.common.Configuration.ENABLE_STACKABLE_SOUP_ITEMS.get();
    }

    /* World */
    private boolean isGenerateFDChestLoot() {
        return vectorwing.farmersdelight.common.Configuration.GENERATE_FD_CHEST_LOOT.get();
    }
    private boolean isGenerateVillageCompostHeaps() {
        return vectorwing.farmersdelight.common.Configuration.GENERATE_VILLAGE_COMPOST_HEAPS.get();
    }
    private boolean isGenerateWildCabbages() {
        return vectorwing.farmersdelight.common.Configuration.GENERATE_WILD_CABBAGES.get();
    }
    private int getChanceWildCabbages() {
        return vectorwing.farmersdelight.common.Configuration.CHANCE_WILD_CABBAGES.get();
    }
    private boolean isGenerateWildBeetroots() {
        return vectorwing.farmersdelight.common.Configuration.GENERATE_WILD_BEETROOTS.get();
    }
    private int getChanceWildBeetroots() {
        return vectorwing.farmersdelight.common.Configuration.CHANCE_WILD_BEETROOTS.get();
    }
    private boolean isGenerateWildPotatoes() {
        return vectorwing.farmersdelight.common.Configuration.GENERATE_WILD_POTATOES.get();
    }
    private int getChanceWildPotatoes() {
        return vectorwing.farmersdelight.common.Configuration.CHANCE_WILD_POTATOES.get();
    }
    private boolean isGenerateWildOnions() {
        return vectorwing.farmersdelight.common.Configuration.GENERATE_WILD_ONIONS.get();
    }
    private int getChanceWildOnions() {
        return vectorwing.farmersdelight.common.Configuration.CHANCE_WILD_ONIONS.get();
    }
    private boolean isGenerateWildCarrots() {
        return vectorwing.farmersdelight.common.Configuration.GENERATE_WILD_CARROTS.get();
    }
    private int getChanceWildCarrots() {
        return vectorwing.farmersdelight.common.Configuration.CHANCE_WILD_CARROTS.get();
    }
    private boolean isGenerateWildTomatoes() {
        return vectorwing.farmersdelight.common.Configuration.GENERATE_WILD_TOMATOES.get();
    }
    private int getChanceWildTomatoes() {
        return vectorwing.farmersdelight.common.Configuration.CHANCE_WILD_TOMATOES.get();
    }
    private boolean isGenerateWildRice() {
        return vectorwing.farmersdelight.common.Configuration.GENERATE_WILD_RICE.get();
    }
    private int getChanceWildRice() {
        return vectorwing.farmersdelight.common.Configuration.CHANCE_WILD_RICE.get();
    }

    /* Client */
    public boolean isNourishedHungerOverlay() {
        return vectorwing.farmersdelight.common.Configuration.NOURISHED_HUNGER_OVERLAY.get();
    }
    public boolean isComfortHealthOverlay() {
        return vectorwing.farmersdelight.common.Configuration.COMFORT_HEALTH_OVERLAY.get();
    }
    public boolean isFoodEffectTooltip() {
        return vectorwing.farmersdelight.common.Configuration.FOOD_EFFECT_TOOLTIP.get();
    }
}
