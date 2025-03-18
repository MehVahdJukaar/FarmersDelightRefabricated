package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import vectorwing.farmersdelight.common.loot.function.CopySkilletFunction;
import vectorwing.farmersdelight.common.loot.function.SmokerCookFunction;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regLootFunc;

public class ModLootFunctions
{
	public static final Supplier<LootItemFunctionType<CopySkilletFunction>> COPY_SKILLET = regLootFunc("copy_skillet", () -> new LootItemFunctionType<>(CopySkilletFunction.CODEC));
	public static final Supplier<LootItemFunctionType<SmokerCookFunction>> SMOKER_COOK = regLootFunc("smoker_cook", () -> new LootItemFunctionType<>(SmokerCookFunction.CODEC));
}
