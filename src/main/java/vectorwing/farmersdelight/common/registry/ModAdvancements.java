package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.advancement.CuttingBoardTrigger;

import java.util.function.Supplier;

public class ModAdvancements
{
	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, FarmersDelight.MODID);

	public static final Supplier<CuttingBoardTrigger> USE_CUTTING_BOARD = TRIGGERS.register("use_cutting_board", CuttingBoardTrigger::new);
}
