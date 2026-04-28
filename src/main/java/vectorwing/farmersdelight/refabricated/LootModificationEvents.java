package vectorwing.farmersdelight.refabricated;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.List;

/**
 * Events for modifying vanilla/FD loot tables based on Farmer's Delight's Loot Modifiers.
 * FD loot tables are included for easier upstream merges.
 */
public class LootModificationEvents {
    private static final ResourceKey<LootTable> BLOCKS_CAKE = vanillaKey("blocks/cake");
    private static final ResourceKey<LootTable> BLOCKS_PUMPKIN = vanillaKey("blocks/pumpkin");
    private static final ResourceKey<LootTable> BLOCKS_SHORT_GRASS = vanillaKey("blocks/short_grass");
    private static final ResourceKey<LootTable> BLOCKS_TALL_GRASS = vanillaKey("blocks/tall_grass");
    private static final ResourceKey<LootTable> ENTITIES_CAVE_SPIDER = vanillaKey("entities/cave_spider");
    private static final ResourceKey<LootTable> ENTITIES_CHICKEN = vanillaKey("entities/chicken");
    private static final ResourceKey<LootTable> ENTITIES_HOGLIN = vanillaKey("entities/hoglin");
    private static final ResourceKey<LootTable> ENTITIES_PIG = vanillaKey("entities/pig");
    private static final ResourceKey<LootTable> ENTITIES_RABBIT = vanillaKey("entities/rabbit");
    private static final ResourceKey<LootTable> ENTITIES_SHULKER = vanillaKey("entities/shulker");
    private static final ResourceKey<LootTable> ENTITIES_SPIDER = vanillaKey("entities/spider");
    private static final ResourceKey<LootTable> BLOCKS_WHEAT = vanillaKey("blocks/wheat");

    private static final ResourceKey<LootTable> BLOCKS_APPLE_PIE = key("blocks/apple_pie");
    private static final ResourceKey<LootTable> BLOCKS_CHOCOLATE_PIE = key("blocks/chocolate_pie");
	private static final ResourceKey<LootTable> BLOCKS_PUMPKIN_PIE = key("blocks/pumpkin_pie");
    private static final ResourceKey<LootTable> BLOCKS_RICE_PANICLES = key("blocks/rice_panicles");
    private static final ResourceKey<LootTable> BLOCKS_SANDY_SHRUB = key("blocks/sandy_shrub");
    private static final ResourceKey<LootTable> BLOCKS_SWEET_BERRY_CHEESECAKE = key("blocks/sweet_berry_cheesecake");

    public static final ResourceKey<LootTable> FD_ABANDONED_MINESHAFT = key("chests/fd_abandoned_mineshaft");
    public static final ResourceKey<LootTable> FD_BASTION_HOGLIN_STABLE = key("chests/fd_bastion_hoglin_stable");
    public static final ResourceKey<LootTable> FD_BASTION_TREASURE = key("chests/fd_bastion_treasure");
    public static final ResourceKey<LootTable> FD_END_CITY_TREASURE = key("chests/fd_end_city_treasure");
    public static final ResourceKey<LootTable> FD_PILLAGER_OUTPOST = key("chests/fd_pillager_outpost");
    public static final ResourceKey<LootTable> FD_RUINED_PORTAL = key("chests/fd_ruined_portal");
    public static final ResourceKey<LootTable> FD_SHIPWRECK_SUPPLY = key("chests/fd_shipwreck_supply");
    public static final ResourceKey<LootTable> FD_SIMPLE_DUNGEON = key("chests/fd_simple_dungeon");
    public static final ResourceKey<LootTable> FD_VILLAGE_BUTCHER = key("chests/fd_village_butcher");
    public static final ResourceKey<LootTable> FD_VILLAGE_DESERT_HOUSE = key("chests/fd_village_desert_house");
    public static final ResourceKey<LootTable> FD_VILLAGE_PLAINS_HOUSE = key("chests/fd_village_plains_house");
    public static final ResourceKey<LootTable> FD_VILLAGE_SAVANNA_HOUSE = key("chests/fd_village_savanna_house");
    public static final ResourceKey<LootTable> FD_VILLAGE_SNOWY_HOUSE = key("chests/fd_village_snowy_house");
    public static final ResourceKey<LootTable> FD_VILLAGE_TAIGA_HOUSE = key("chests/fd_village_taiga_house");

    public static void init() {
        LootTableEvents.MODIFY.register(LootModificationEvents::modifyTable);
    }

    private static void modifyTable(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, HolderLookup.Provider registries) {
        chestLoot(key, tableBuilder, source, registries);
        scavengingLoot(key, tableBuilder, source, registries);
        slicingLoot(key, tableBuilder, source, registries);
        straw(key, tableBuilder, source, registries);
    }

    private static void chestLoot(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, HolderLookup.Provider registries) {
        if (!Configuration.GENERATE_FD_CHEST_LOOT.get())
            return;

        if (key == BuiltInLootTables.ABANDONED_MINESHAFT)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_ABANDONED_MINESHAFT)));

        if (key == BuiltInLootTables.BASTION_HOGLIN_STABLE)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_BASTION_HOGLIN_STABLE)));

        if (key == BuiltInLootTables.BASTION_TREASURE)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_BASTION_TREASURE)));

        if (key == BuiltInLootTables.END_CITY_TREASURE)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_END_CITY_TREASURE)));

        if (key == BuiltInLootTables.PILLAGER_OUTPOST)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_PILLAGER_OUTPOST)));

        if (key == BuiltInLootTables.RUINED_PORTAL)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_RUINED_PORTAL)));

        if (key == BuiltInLootTables.SHIPWRECK_SUPPLY)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_SHIPWRECK_SUPPLY)));

        if (key == BuiltInLootTables.SIMPLE_DUNGEON)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_SIMPLE_DUNGEON)));

        if (key == BuiltInLootTables.VILLAGE_BUTCHER)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_VILLAGE_BUTCHER)));

        if (key == BuiltInLootTables.VILLAGE_DESERT_HOUSE)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_VILLAGE_DESERT_HOUSE)));

        if (key == BuiltInLootTables.VILLAGE_PLAINS_HOUSE)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_VILLAGE_PLAINS_HOUSE)));

        if (key == BuiltInLootTables.VILLAGE_SAVANNA_HOUSE)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_VILLAGE_SAVANNA_HOUSE)));

        if (key == BuiltInLootTables.VILLAGE_SNOWY_HOUSE)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_VILLAGE_SNOWY_HOUSE)));

        if (key == BuiltInLootTables.VILLAGE_TAIGA_HOUSE)
            tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(FD_VILLAGE_TAIGA_HOUSE)));
    }

    private static void scavengingLoot(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, HolderLookup.Provider registries) {
        HolderLookup<Enchantment> enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT);

        // scavenging_feather
        if (key == ENTITIES_CHICKEN) {
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.FEATHER)
                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                            EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                    )))));
        }
        // scavenging_ham_from_hoglin and scavenging_smoked_ham_from_hoglin
        if (key == ENTITIES_HOGLIN) {
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.HAM.get())
                            .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                                    EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                            )).and(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(false)))))))
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.SMOKED_HAM.get())
                            .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                                    EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                            )).and(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))));
        }
        // scavenging_ham_from_pig and scavenging_smoked_ham_from_pig
        if (key == ENTITIES_PIG) {
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.HAM.get())
                            .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                                            EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                                    )).and(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(false))))
                                    .and(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.5F, 0.1F)))))
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.SMOKED_HAM.get())
                            .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                                    EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                            )).and(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))
                                    .and(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.5F, 0.1F))))));
        }
        // scavenging_pumpkin
        if (key == BLOCKS_PUMPKIN) {
            tableBuilder.modifyPools(builder -> builder.conditionally(
                    MatchTool.toolMatches(ItemPredicate.Builder.item()
                            .of(ModTags.Items.KNIVES)
                    ).and(MatchTool.toolMatches(ItemPredicate.Builder.item()
                            .withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(
                                    new EnchantmentPredicate(enchantments.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.ANY)
                            )))).invert()
                    ).invert().build())
            ).withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.PUMPKIN_SLICE.get())
                            .when(MatchTool.toolMatches(ItemPredicate.Builder.item()
                                    .of(ModTags.Items.KNIVES)
                            ).and(MatchTool.toolMatches(ItemPredicate.Builder.item()
                                    .withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(
                                            new EnchantmentPredicate(enchantments.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.ANY)
                                    )))).invert()
                            )).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))));
        }
        // scavenging_leather
        if (key.location().getPath().startsWith("entities/")) {
            HolderLookup<EntityType<?>> lookup = registries.lookupOrThrow(Registries.ENTITY_TYPE);
            var entityType = lookup.get(ResourceKey.create(Registries.ENTITY_TYPE, key.location()
                    .withPath(s -> s.substring(9))));
            // Make sure we only add to the necessary entity loot tables by loading the tag early.
            // Otherwise, you'd be modifying every entity with a condition that's never true, which seems like a no-no.
            // TODO: Figure out if we need to even load the tag early or if we should modify no matter what with a `this` entity property.
            if (entityType.isPresent() && TagUtils.isDropsLeatherTag(entityType.get(), lookup)) {
                tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.LEATHER)
                        .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                                EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                        )))));
            }
        }

        // scavenging_rabbit_hide
        if (key == ENTITIES_RABBIT) {
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.RABBIT_HIDE)
                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                            EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                    )))));
        }

        // scavenging_shulker_shell
        if (key == ENTITIES_SHULKER) {
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.SHULKER_SHELL)
                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                            EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                    )))));
        }

        // scavenging_shulker_shell
        if (key == ENTITIES_SHULKER) {
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.SHULKER_SHELL)
                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                            EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                    )))));
        }

        // scavenging_string
        // TODO: Figure out if this needs a tag similar to the Drops Leather tag.
        if (key == ENTITIES_SPIDER || key == ENTITIES_CAVE_SPIDER) {
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.STRING)
                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                            EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                    )))));
        }
    }

    private static void slicingLoot(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, HolderLookup.Provider registries) {
        // slicing_apple_pie
        if (key == BLOCKS_APPLE_PIE)
            pastrySlicing(tableBuilder, ModBlocks.APPLE_PIE.get(), ModItems.APPLE_PIE_SLICE.get(), PieBlock.BITES, 4);
        // slicing_cake
        if (key == BLOCKS_CAKE)
            pastrySlicing(tableBuilder, Blocks.CAKE, ModItems.CAKE_SLICE.get(), CakeBlock.BITES, 7);
        // slicing_candle_cake
        if (key.location().getPath().startsWith("blocks/")) {
            HolderLookup<Block> lookup = registries.lookupOrThrow(Registries.BLOCK);
            var block = lookup.get(ResourceKey.create(Registries.BLOCK, key.location().withPath(s -> s.substring(7))));
            if (block.isPresent() && TagUtils.isCandleDropsCakeSliceTag(block.get(), lookup)) {
                tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.CAKE_SLICE.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(7.0F)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES)))));
            }
        }
        // slicing_chocolate_pie
        if (key == BLOCKS_CHOCOLATE_PIE)
            pastrySlicing(tableBuilder, ModBlocks.CHOCOLATE_PIE.get(), ModItems.CHOCOLATE_PIE_SLICE.get(), PieBlock.BITES, 4);
		// slicing_pumpkin_pie
		if (key == BLOCKS_PUMPKIN_PIE)
			pastrySlicing(tableBuilder, ModBlocks.PUMPKIN_PIE.get(), ModItems.PUMPKIN_PIE_SLICE.get(), PieBlock.BITES, 4);
		// slicing_sweet_berry_cheesecake
        if (key == BLOCKS_SWEET_BERRY_CHEESECAKE)
            pastrySlicing(tableBuilder, ModBlocks.SWEET_BERRY_CHEESECAKE.get(), ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get(), PieBlock.BITES, 4);
    }

    public static void pastrySlicing(LootTable.Builder tableBuilder, Block block, ItemLike slice, IntegerProperty property, int maxValue) {
        tableBuilder.modifyPools(builder -> builder.when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES)).invert()));
        for (int value : property.getPossibleValues()) {
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(slice).apply(SetItemCountFunction.setCount(ConstantValue.exactly(maxValue - value))))
                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES))
                            .and(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, value)))));
        }
    }

    private static void straw(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, HolderLookup.Provider registries) {
        if (key == BLOCKS_SHORT_GRASS || key == BLOCKS_TALL_GRASS)
            strawChance02(tableBuilder);
        if (key == BLOCKS_SANDY_SHRUB)
            strawChance03(tableBuilder);
        if (key == BLOCKS_RICE_PANICLES)
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.STRAW.get())
                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.STRAW_HARVESTERS))
                            .and(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RICE_CROP_PANICLES.get())
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 3))))));
        if (key == BLOCKS_WHEAT)
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.STRAW.get())
                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.STRAW_HARVESTERS))
                            .and(LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.WHEAT)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7))))));
    }

    public static void strawChance02(LootTable.Builder tableBuilder) {
        tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.STRAW.get())
                .when(LootItemRandomChanceCondition.randomChance(0.3F)
                        .and(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.STRAW_HARVESTERS))))));
    }

    public static void strawChance03(LootTable.Builder tableBuilder) {
        tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.STRAW.get())
                .when(LootItemRandomChanceCondition.randomChance(0.3F)
                        .and(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.STRAW_HARVESTERS))))));
    }

    private static ResourceKey<LootTable> vanillaKey(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace(path));
    }

    private static ResourceKey<LootTable> key(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, FarmersDelight.res(path));
    }

}
