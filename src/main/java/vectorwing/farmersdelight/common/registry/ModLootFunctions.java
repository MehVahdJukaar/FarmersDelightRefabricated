package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.loot.function.CopySkilletFunction;
import vectorwing.farmersdelight.common.loot.function.SmokerCookFunction;

import java.util.function.Supplier;

public class ModLootFunctions
{
	public static final DeferredRegister<LootItemFunctionType<?>> LOOT_FUNCTIONS = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, FarmersDelight.MODID);

	public static final Supplier<LootItemFunctionType<CopySkilletFunction>> COPY_SKILLET = LOOT_FUNCTIONS.register("copy_skillet", () -> new LootItemFunctionType<>(CopySkilletFunction.CODEC));
	public static final Supplier<LootItemFunctionType<SmokerCookFunction>> SMOKER_COOK = LOOT_FUNCTIONS.register("smoker_cook", () -> new LootItemFunctionType<>(SmokerCookFunction.CODEC));
}
