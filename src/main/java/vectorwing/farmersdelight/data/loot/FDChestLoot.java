package vectorwing.farmersdelight.data.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import vectorwing.farmersdelight.common.registry.ModChestLootTables;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class FDChestLoot extends SimpleFabricLootTableSubProvider {

	protected final HolderLookup.Provider registries;

	public FDChestLoot(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.CHEST);
		this.registries = registryLookup.join();
	}
	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		HolderLookup.RegistryLookup<Enchantment> enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT);

		consumer.accept(ModChestLootTables.ABANDONED_MINESHAFT, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(ModItems.COOKING_POT.get()))
						.add(LootItem.lootTableItem(ModItems.SKILLET.get())
								.apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.15F, 0.8F))))
						.add(EmptyLootItem.emptyItem().setWeight(6)))
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 4))
						.add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
						.add(LootItem.lootTableItem(ModItems.CABBAGE_SEEDS.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
						.add(LootItem.lootTableItem(ModItems.RICE.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
						.add(EmptyLootItem.emptyItem().setWeight(2))
				).withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(3))
						.add(LootItem.lootTableItem(ModItems.ROPE.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 12))))
						.add(EmptyLootItem.emptyItem().setWeight(2))
				));


		consumer.accept(ModChestLootTables.BASTION_HOGLIN_STABLE, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(ModItems.DIAMOND_KNIFE.get())
								.apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.15F, 0.8F)))
								.apply(EnchantRandomlyFunction.randomApplicableEnchantment(enchantments)))
						.add(LootItem.lootTableItem(ModItems.GOLDEN_KNIFE.get()).setWeight(2)
								.apply(EnchantRandomlyFunction.randomApplicableEnchantment(enchantments)))
						.add(EmptyLootItem.emptyItem().setWeight(2))

				).withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 2))
						.add(LootItem.lootTableItem(ModItems.HAM.get()).setWeight(4)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 5))))
						.add(LootItem.lootTableItem(ModItems.SMOKED_HAM.get()).setWeight(2)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 5))))
						.add(EmptyLootItem.emptyItem().setWeight(2))
				));

		consumer.accept(ModChestLootTables.BASTION_TREASURE, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(ModItems.DIAMOND_KNIFE.get())
								.apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8F, 1.0F)))
								.apply(EnchantRandomlyFunction.randomApplicableEnchantment(enchantments)))
						.add(LootItem.lootTableItem(ModItems.DIAMOND_KNIFE.get()))
						.add(EmptyLootItem.emptyItem().setWeight(6))
				));

		consumer.accept(ModChestLootTables.END_CITY_TREASURE, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(ModItems.DIAMOND_KNIFE.get())
								.apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.between(20, 39))))
						.add(LootItem.lootTableItem(ModItems.IRON_KNIFE.get())
								.apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.between(20, 39))))
						.add(EmptyLootItem.emptyItem().setWeight(6))
				));

		consumer.accept(ModChestLootTables.PILLAGER_OUTPOST, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 2))
						.add(LootItem.lootTableItem(ModItems.ONION.get()).setWeight(5)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 12))))
						.add(LootItem.lootTableItem(ModItems.ONION_CRATE.get()).setWeight(2)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
						.add(EmptyLootItem.emptyItem().setWeight(2))
				));

		consumer.accept(ModChestLootTables.RUINED_PORTAL, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(ModItems.GOLDEN_KNIFE.get())
								.apply(EnchantRandomlyFunction.randomApplicableEnchantment(enchantments)))
						.add(EmptyLootItem.emptyItem().setWeight(3))
				));

		consumer.accept(ModChestLootTables.SHIPWRECK_SUPPLY, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 2))
						.add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS.get()).setWeight(6)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
						.add(LootItem.lootTableItem(ModItems.CABBAGE_SEEDS.get()).setWeight(6)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
						.add(LootItem.lootTableItem(ModItems.ONION.get()).setWeight(6)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
						.add(LootItem.lootTableItem(ModItems.RICE.get()).setWeight(6)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
				).withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 3))
						.add(LootItem.lootTableItem(ModItems.ROPE.get()).setWeight(2)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 12))))
						.add(EmptyLootItem.emptyItem().setWeight(1))
				));

		consumer.accept(ModChestLootTables.SIMPLE_DUNGEON, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 4))
						.add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
						.add(LootItem.lootTableItem(ModItems.CABBAGE_SEEDS.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
						.add(EmptyLootItem.emptyItem().setWeight(2))
				).withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(3))
						.add(LootItem.lootTableItem(ModItems.ROPE.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 12))))
						.add(EmptyLootItem.emptyItem().setWeight(2))
				));

		consumer.accept(ModChestLootTables.VILLAGE_BUTCHER, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(ModItems.FLINT_KNIFE.get()))
						.add(LootItem.lootTableItem(ModItems.IRON_KNIFE.get()))
						.add(EmptyLootItem.emptyItem()))
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 2))
						.add(LootItem.lootTableItem(ModItems.HAM.get()))
						.add(LootItem.lootTableItem(ModItems.MINCED_BEEF.get()).setWeight(3)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6))))
						.add(LootItem.lootTableItem(ModItems.BACON.get()).setWeight(3)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6))))
						.add(LootItem.lootTableItem(ModItems.MUTTON_CHOPS.get()).setWeight(3)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6))))
						.add(EmptyLootItem.emptyItem())));

		consumer.accept(ModChestLootTables.VILLAGE_DESERT_HOUSE, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 3))
						.add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
						.add(LootItem.lootTableItem(ModItems.RICE.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))));

		consumer.accept(ModChestLootTables.VILLAGE_PLAINS_HOUSE, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 3))
						.add(LootItem.lootTableItem(ModItems.ONION.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
						.add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))));

		consumer.accept(ModChestLootTables.VILLAGE_SAVANNA_HOUSE, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 3))
						.add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
						.add(LootItem.lootTableItem(ModItems.CABBAGE_SEEDS.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))));

		consumer.accept(ModChestLootTables.VILLAGE_SNOWY_HOUSE, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 3))
						.add(LootItem.lootTableItem(ModItems.ONION.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
						.add(LootItem.lootTableItem(ModItems.CABBAGE_SEEDS.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))));

		consumer.accept(ModChestLootTables.VILLAGE_TAIGA_HOUSE, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ContextIntProviders.between(1, 3))
						.add(LootItem.lootTableItem(ModItems.CABBAGE_SEEDS.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
						.add(LootItem.lootTableItem(ModItems.RICE.get())
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))));
	}

	@Override
	public String getName() {
		return "Farmer's Delight Chest Loot";
	}

	@Override
	public void run() {

	}
}
