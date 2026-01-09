package vectorwing.farmersdelight.common;

import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.level.block.DispenserBlock;
import vectorwing.farmersdelight.common.registry.ModItems;

public class CommonSetup
{
	public static void init() {
		registerDispenserBehaviors();
		registerItemSetAdditions();
	}

	public static void registerDispenserBehaviors() {
		DispenserBlock.registerProjectileBehavior(ModItems.ROTTEN_TOMATO.get());
	}

	/**
	 * Allows Villagers to actually use Farmer's Delight crops.
	 */
	public static void registerItemSetAdditions() {
//		Set<Item> newWantedItems = Sets.newHashSet(
//				ModItems.CABBAGE.get(),
//				ModItems.TOMATO.get(),
//				ModItems.ONION.get(),
//				ModItems.RICE.get(),
//				ModItems.CABBAGE_SEEDS.get(),
//				ModItems.TOMATO_SEEDS.get(),
//				ModItems.RICE_PANICLE.get());
//		newWantedItems.addAll(Villager.WANTED_ITEMS);
//		Villager.WANTED_ITEMS = ImmutableSet.copyOf(newWantedItems);
		Villager.FOOD_POINTS.put(ModItems.CABBAGE.get(), 1);
		Villager.FOOD_POINTS.put(ModItems.TOMATO.get(), 1);
		Villager.FOOD_POINTS.put(ModItems.ONION.get(), 1);
		Villager.FOOD_POINTS.put(ModItems.RICE.get(), 2);
	}
}
