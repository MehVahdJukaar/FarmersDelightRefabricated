package vectorwing.farmersdelight.common;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.refabricated.mlconfigs.ConfigBuilder;
import vectorwing.farmersdelight.refabricated.mlconfigs.ConfigType;
import vectorwing.farmersdelight.refabricated.mlconfigs.ModConfigHolder;

import java.util.List;
import java.util.function.Supplier;

public class Configuration {
    public static ModConfigHolder COMMON_CONFIG;
    public static ModConfigHolder CLIENT_CONFIG;

    // COMMON
    public static final String CATEGORY_SETTINGS = "settings";
    public static Supplier<Boolean> ENABLE_VANILLA_CROP_CRATES;
    public static Supplier<Boolean> FARMERS_BUY_FD_CROPS;
    public static Supplier<Boolean> WANDERING_TRADER_SELLS_FD_ITEMS;
    public static Supplier<Double> RICH_SOIL_BOOST_CHANCE;
    public static Supplier<Double> CUTTING_BOARD_FORTUNE_BONUS;
    public static Supplier<Boolean> ENABLE_ROPE_REELING;
    public static Supplier<List<String>> CANVAS_SIGN_DARK_BACKGROUND_LIST;

    public static final String CATEGORY_FARMING = "farming";
    public static Supplier<String> DEFAULT_TOMATO_VINE_ROPE;
    public static Supplier<Boolean> ENABLE_TOMATO_VINE_CLIMBING_TAGGED_ROPES;

    public static final String CATEGORY_RECIPE_BOOK = "recipe_book";
    public static Supplier<Boolean> ENABLE_RECIPE_BOOK_COOKING_POT;

    public static final String CATEGORY_OVERRIDES = "overrides";
    public static Supplier<Boolean> VANILLA_SOUP_EXTRA_EFFECTS;
    public static Supplier<Boolean> RABBIT_STEW_BUFF;
    public static Supplier<Boolean> DISPENSER_TOOLS_CUTTING_BOARD;

    public static final String CATEGORY_OVERRIDES_STACK_SIZE = "stack_size";
    public static Supplier<Boolean> ENABLE_STACKABLE_SOUP_ITEMS;
    public static Supplier<List<String>> SOUP_ITEM_LIST;

    public static final String CATEGORY_WORLD = "world";
    public static Supplier<Boolean> GENERATE_FD_CHEST_LOOT;
    public static Supplier<Boolean> GENERATE_VILLAGE_COMPOST_HEAPS;
    public static Supplier<Boolean> GENERATE_VILLAGE_FARM_FD_CROPS;
    public static Supplier<Boolean> GENERATE_WILD_CABBAGES;
    public static Supplier<Integer> CHANCE_WILD_CABBAGES;
    public static Supplier<Boolean> GENERATE_WILD_BEETROOTS;
    public static Supplier<Integer> CHANCE_WILD_BEETROOTS;
    public static Supplier<Boolean> GENERATE_WILD_POTATOES;
    public static Supplier<Integer> CHANCE_WILD_POTATOES;
    public static Supplier<Boolean> GENERATE_WILD_ONIONS;
    public static Supplier<Integer> CHANCE_WILD_ONIONS;
    public static Supplier<Boolean> GENERATE_WILD_CARROTS;
    public static Supplier<Integer> CHANCE_WILD_CARROTS;
    public static Supplier<Boolean> GENERATE_WILD_TOMATOES;
    public static Supplier<Integer> CHANCE_WILD_TOMATOES;
    public static Supplier<Boolean> GENERATE_WILD_RICE;
    public static Supplier<Integer> CHANCE_WILD_RICE;
    public static Supplier<Boolean> GENERATE_BROWN_MUSHROOM_COLONIES;
    public static Supplier<Integer> CHANCE_BROWN_MUSHROOM_COLONIES;
    public static Supplier<Boolean> GENERATE_RED_MUSHROOM_COLONIES;
    public static Supplier<Integer> CHANCE_RED_MUSHROOM_COLONIES;

    // CLIENT
    public static final String CATEGORY_CLIENT = "client";

    public static Supplier<Boolean> NOURISHED_HUNGER_OVERLAY;
    public static Supplier<Boolean> COMFORT_HEALTH_OVERLAY;
    public static Supplier<Boolean> FOOD_EFFECT_TOOLTIP;

    static {
        ConfigBuilder builder = ConfigBuilder.create(FarmersDelight.MODID, ConfigType.COMMON);

        builder.comment("Game settings").push(CATEGORY_SETTINGS);
        ENABLE_VANILLA_CROP_CRATES = builder.comment("Farmer's Delight adds crates (3x3) for vanilla crops, similar to Quark and Thermal Cultivation. Should they be craftable?")
                .define("enableVanillaCropCrates", true);
        FARMERS_BUY_FD_CROPS = builder.comment("Should Novice and Apprentice Farmers buy this mod's crops? (May reduce chances of other trades appearing)")
                .define("farmersBuyFDCrops", true);
        WANDERING_TRADER_SELLS_FD_ITEMS = builder.comment("Should the Wandering Trader sell some of this mod's items? (Currently includes crop seeds and onions)")
                .define("wanderingTraderSellsFDItems", true);
        RICH_SOIL_BOOST_CHANCE = builder.comment("How often (in percentage) should Rich Soil succeed in boosting a plant's growth at each random tick? Set it to 0.0 to disable this.")
                .define("richSoilBoostChance", 0.2, 0.0, 1.0);
        CUTTING_BOARD_FORTUNE_BONUS = builder.comment("How much of a bonus (in percentage) should each level of Fortune grant to Cutting Board chances? Set it to 0.0 to disable this.")
                .define("cuttingBoardFortuneBonus", 0.1, 0.0, 1.0);
        ENABLE_ROPE_REELING = builder.comment("Should players be able to reel back rope, bottom to top, when sneak-using with an empty hand on them?")
                .define("enableRopeReeling", true);
        CANVAS_SIGN_DARK_BACKGROUND_LIST = builder.comment("A list of dye colors that, when used as the background of a Canvas Sign, should default to white text when placed.\n"+
                        "Dyes: [\"white\", \"orange\", \"magenta\", \"light_blue\", \"yellow\", \"lime\", \"pink\", \"gray\", \"light_gray\", \"cyan\", \"purple\", \"blue\", \"brown\", \"green\", \"red\", \"black\"]")
                .define("canvasSignDarkBackgroundList", ImmutableList.of("gray", "purple", "blue", "brown", "green", "red", "black"), obj -> true);
        builder.pop();

        builder.comment("Farming").push(CATEGORY_FARMING);
        DEFAULT_TOMATO_VINE_ROPE = builder.comment("Which rope should Tomato Vines leave behind when mined by hand?")
                .define("defaultTomatoVineRope", "farmersdelight:rope");
        ENABLE_TOMATO_VINE_CLIMBING_TAGGED_ROPES = builder.comment("Should tomato vines be able to climb any rope tagged as farmersdelight:ropes?\n"+
                        "Beware: this will convert these blocks into the block specified in defaultTomatoVineRope.")
                .define("enableTomatoVineClimbingTaggedRopes", true);
        builder.pop();

        builder.comment("Recipe book").push(CATEGORY_RECIPE_BOOK);
        ENABLE_RECIPE_BOOK_COOKING_POT = builder.comment("Should the Cooking Pot have a Recipe Book available on its interface?")
                .define("enableRecipeBookCookingPot", true);
        builder.pop();

        builder.comment("Vanilla item overrides").push(CATEGORY_OVERRIDES);
        VANILLA_SOUP_EXTRA_EFFECTS = builder.comment("Should soups and stews from vanilla Minecraft grant additional effects, like meals from this mod?")
                .define("vanillaSoupExtraEffects", true);
        RABBIT_STEW_BUFF = builder.comment("Should Rabbit Stew be buffed with improved food stats?")
                .define("rabbitStewBuff", true);
        DISPENSER_TOOLS_CUTTING_BOARD = builder.comment("Should the Dispenser be able to operate a Cutting Board in front of it?")
                .define("dispenserUsesToolsOnCuttingBoard", true);

        builder.comment("Stack size overrides").push(CATEGORY_OVERRIDES_STACK_SIZE);
        ENABLE_STACKABLE_SOUP_ITEMS = builder.comment("Should BowlFoodItems in the following list become stackable to 16, much like Farmer's Delight's meals?")
                .define("enableStackableSoupItems", true);
        SOUP_ITEM_LIST = builder.comment("List of BowlFoodItems. They must extend this class to be affected. Default: vanilla soups and stews.")
                .define("soupItemList", List.of("minecraft:mushroom_stew", "minecraft:beetroot_soup", "minecraft:rabbit_stew"), obj -> true);
        builder.pop();

        builder.pop();

        builder.comment("World generation").push(CATEGORY_WORLD);
        GENERATE_FD_CHEST_LOOT = builder.comment("Should this mod add some of its items (ropes, seeds, knives, meals etc.) as extra chest loot across Minecraft?")
                .define("generateFDChestLoot", true);
        GENERATE_VILLAGE_COMPOST_HEAPS = builder.comment("Should FD generate Compost Heaps across all village biomes?")
                .define("genVillageCompostHeaps", true);
        GENERATE_VILLAGE_FARM_FD_CROPS = builder.comment("Should FD crops show up planted randomly in various village farms?")
                .define("genFDCropsOnVillageFarms", true);

        builder.comment("Wild Cabbage generation").push("wild_cabbages");
        CHANCE_WILD_CABBAGES = builder.comment("Chance of generating clusters. Smaller value = more frequent.")
                .define("chance", 30, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Sea Beet generation").push("wild_beetroots");
        CHANCE_WILD_BEETROOTS = builder.comment("Chance of generating clusters. Smaller value = more frequent.")
                .define("chance", 30, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Wild Potato generation").push("wild_potatoes");
        CHANCE_WILD_POTATOES = builder.comment("Chance of generating clusters. Smaller value = more frequent.")
                .define("chance", 100, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Wild Carrot generation").push("wild_carrots");
        CHANCE_WILD_CARROTS = builder.comment("Chance of generating clusters. Smaller value = more frequent.")
                .define("chance", 120, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Wild Onion generation").push("wild_onions");
        CHANCE_WILD_ONIONS = builder.comment("Chance of generating clusters. Smaller value = more frequent.")
                .define("chance", 120, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Tomato Vines generation").push("wild_tomatoes");
        CHANCE_WILD_TOMATOES = builder.comment("Chance of generating clusters. Smaller value = more frequent.")
                .define("chance", 100, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Wild Rice generation").push("wild_rice");
        CHANCE_WILD_RICE = builder.comment("Chance of generating clusters. Smaller value = more frequent.")
                .define("chance", 20, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Brown Mushroom Colony generation").push("brown_mushroom_colonies");
        GENERATE_BROWN_MUSHROOM_COLONIES = builder.comment("Generate brown mushroom colonies on mushroom fields")
                .define("genBrownMushroomColony", true);
        CHANCE_BROWN_MUSHROOM_COLONIES = builder.comment("Chance of generating clusters. Smaller value = more frequent.")
                .define("chance", 15, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Red Mushroom Colony generation").push("red_mushroom_colonies");
        GENERATE_RED_MUSHROOM_COLONIES = builder.comment("Generate red mushroom colonies on mushroom fields")
                .define("genRedMushroomColony", true);
        CHANCE_RED_MUSHROOM_COLONIES = builder.comment("Chance of generating clusters. Smaller value = more frequent.")
                .define("chance", 15, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.pop();

        COMMON_CONFIG = builder.build();

        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ConfigBuilder clientBuilder = ConfigBuilder.create(FarmersDelight.MODID, ConfigType.CLIENT);

            clientBuilder.comment("Client settings").push(CATEGORY_CLIENT);
            NOURISHED_HUNGER_OVERLAY = clientBuilder.comment("Should the hunger bar have a gilded overlay when the player has the Nourishment effect?")
                    .define("nourishmentHungerOverlay", true);
            COMFORT_HEALTH_OVERLAY = clientBuilder.comment("Should the health bar have a silver sheen when the player has the Comfort effect?")
                    .define("comfortHealthOverlay", true);
            FOOD_EFFECT_TOOLTIP = clientBuilder.comment("Should meal and drink tooltips display which effects they provide?")
                    .define("foodEffectTooltip", true);
            clientBuilder.pop();

            CLIENT_CONFIG = clientBuilder.build();
        }else{
            CLIENT_CONFIG = null;
        }
    }
}
