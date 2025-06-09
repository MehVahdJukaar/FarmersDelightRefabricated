package vectorwing.farmersdelight.common;

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
	 * Unused due to Villager wanted items being a tag.
	 */
	@Deprecated
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
	}
}
