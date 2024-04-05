package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;
import vectorwing.farmersdelight.common.world.feature.WildCropFeature;
import vectorwing.farmersdelight.common.world.feature.WildRiceFeature;

import java.util.function.Supplier;

public class ModBiomeFeatures
{
	public static final LazyRegistrar<Feature<?>> FEATURES = LazyRegistrar.create(BuiltInRegistries.FEATURE, FarmersDelight.MODID);

	public static final Supplier<Feature<RandomPatchConfiguration>> WILD_RICE = FEATURES.register("wild_rice", () -> new WildRiceFeature(RandomPatchConfiguration.CODEC));
	public static final Supplier<Feature<WildCropConfiguration>> WILD_CROP = FEATURES.register("wild_crop", () -> new WildCropFeature(WildCropConfiguration.CODEC));
}
