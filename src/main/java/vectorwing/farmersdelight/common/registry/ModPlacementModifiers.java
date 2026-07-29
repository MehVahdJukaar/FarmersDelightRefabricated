package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import vectorwing.farmersdelight.common.world.filter.BiomeTagFilter;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regPlacementMod;

public class ModPlacementModifiers
{
	public static final Supplier<PlacementModifierType<BiomeTagFilter>> BIOME_TAG = regPlacementMod("biome_tag", () -> typeConvert(BiomeTagFilter.CODEC));

	private static <P extends PlacementModifier> PlacementModifierType<P> typeConvert(MapCodec<P> codec) {
		return () -> codec;
	}

	public static void touch(){

	}
}
