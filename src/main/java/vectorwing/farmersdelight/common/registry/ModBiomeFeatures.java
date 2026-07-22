package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;
import vectorwing.farmersdelight.common.world.feature.WildCropFeature;
import vectorwing.farmersdelight.common.world.feature.WildRiceFeature;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regFeature;

public class ModBiomeFeatures {
    public static final Supplier<Feature<NoneFeatureConfiguration>> WILD_RICE = regFeature("wild_rice", () -> new WildRiceFeature(NoneFeatureConfiguration.CODEC));
	public static final Supplier<Feature<WildCropConfiguration>> WILD_CROP = regFeature("wild_crop", () -> new WildCropFeature(WildCropConfiguration.CODEC));

	public static void touch() {
    }
}
