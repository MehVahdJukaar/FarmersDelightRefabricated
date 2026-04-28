package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.MapCodec;
import vectorwing.farmersdelight.common.loot.function.CopySkilletFunction;
import vectorwing.farmersdelight.common.loot.function.SmokerCookFunction;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regLootFunc;

public class ModLootFunctions
{
	public static final Supplier<MapCodec<CopySkilletFunction>> COPY_SKILLET = regLootFunc(CopySkilletFunction.ID, CopySkilletFunction.CODEC);
	public static final Supplier<MapCodec<SmokerCookFunction>> SMOKER_COOK = regLootFunc(SmokerCookFunction.ID, SmokerCookFunction.CODEC);

	public static void touch() {

	}
}
