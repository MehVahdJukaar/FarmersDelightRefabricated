package vectorwing.farmersdelight.common.registry;

import vectorwing.farmersdelight.common.advancement.CuttingBoardTrigger;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.FabricUtils.regTrigger;

public class ModAdvancements
{
	public static final Supplier<CuttingBoardTrigger> USE_CUTTING_BOARD = regTrigger("use_cutting_board", CuttingBoardTrigger::new);

	public static void touch() {
	}
}
