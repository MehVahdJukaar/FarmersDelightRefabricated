package vectorwing.farmersdelight.common;

import net.minecraft.world.level.block.DispenserBlock;
import vectorwing.farmersdelight.common.registry.ModItems;

public class CommonSetup
{
	public static void registerDispenserBehaviors() {
		DispenserBlock.registerProjectileBehavior(ModItems.ROTTEN_TOMATO.get());
	}
}
