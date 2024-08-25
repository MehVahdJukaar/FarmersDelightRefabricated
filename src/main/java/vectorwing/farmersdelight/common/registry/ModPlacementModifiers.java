package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.MapCodec;
import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.world.filter.BiomeTagFilter;

import java.util.function.Supplier;

public class ModPlacementModifiers
{
	public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS = DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE.key(), FarmersDelight.MODID);

	public static final Supplier<PlacementModifierType<BiomeTagFilter>> BIOME_TAG = PLACEMENT_MODIFIERS.register("biome_tag", () -> typeConvert(BiomeTagFilter.CODEC));

	private static <P extends PlacementModifier> PlacementModifierType<P> typeConvert(MapCodec<P> codec) {
		return () -> codec;
	}
}
