package com.nhoryzon.mc.farmersdelight.registry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.tag.ModTags;

@Deprecated(forRemoval = true)
public class TagsRegistry {
    public static final TagKey<Item> KNIVES = ModTags.KNIVES;

    public static final TagKey<Block> ROPES = ModTags.ROPES;

    public static final TagKey<Block> HEAT_SOURCES = ModTags.HEAT_SOURCES;
    public static final TagKey<Block> HEAT_CONDUCTORS = ModTags.HEAT_CONDUCTORS;
    public static final TagKey<Block> TRAY_HEAT_SOURCES = ModTags.TRAY_HEAT_SOURCES;
    public static final TagKey<Block> COMPOST_ACTIVATORS = ModTags.COMPOST_ACTIVATORS;
    public static final TagKey<Block> UNAFFECTED_BY_RICH_SOIL = ModTags.UNAFFECTED_BY_RICH_SOIL;
    public static final TagKey<Block> MUSHROOM_COLONY_GROWABLE_ON = ModTags.MUSHROOM_COLONY_GROWABLE_ON;
    public static final TagKey<Block> MINABLE_KNIFE = ModTags.MINEABLE_WITH_KNIFE;
    public static final TagKey<Block> DROPS_CAKE_SLICE = ModTags.DROPS_CAKE_SLICE;

    public static final TagKey<Item> WILD_CROPS_ITEM = ModTags.WILD_CROPS_ITEM;
    public static final TagKey<Item> STRAW_HARVESTERS = ModTags.STRAW_HARVESTERS;
    //public static final TagKey<Item> COMFORT_FOODS = ModTags;
    public static final TagKey<Item> WOLF_PREY = ModTags.WOLF_PREY;
    public static final TagKey<Item> CABBAGE_ROLL_INGREDIENTS = ModTags.CABBAGE_ROLL_INGREDIENTS;
    public static final TagKey<Item> OFFHAND_EQUIPMENT = ModTags.OFFHAND_EQUIPMENT;

    public static final TagKey<EntityType<?>> DOG_FOOD_USERS = ModTags.DOG_FOOD_USERS;
    public static final TagKey<EntityType<?>> HORSE_FEED_USERS = ModTags.HORSE_FEED_USERS;
}
