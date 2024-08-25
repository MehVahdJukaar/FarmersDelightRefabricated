package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.loot.function.CopyMealFunction;
import vectorwing.farmersdelight.common.loot.function.CopySkilletFunction;
import vectorwing.farmersdelight.common.loot.function.SmokerCookFunction;

import java.util.function.Supplier;

public class ModLootFunctions
{
	public static final LazyRegistrar<LootItemFunctionType> LOOT_FUNCTIONS = LazyRegistrar.create(BuiltInRegistries.LOOT_FUNCTION_TYPE.key(), FarmersDelight.MODID);

	public static final Supplier<LootItemFunctionType> COPY_SKILLET = LOOT_FUNCTIONS.register("copy_skillet", () -> new LootItemFunctionType(CopySkilletFunction.CODEC));
	public static final Supplier<LootItemFunctionType> SMOKER_COOK = LOOT_FUNCTIONS.register("smoker_cook", () -> new LootItemFunctionType(SmokerCookFunction.CODEC));
}
