package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;
import vectorwing.farmersdelight.common.world.feature.WildCropFeature;
import vectorwing.farmersdelight.common.world.feature.WildRiceFeature;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.FabricUtils.regFeature;

public class ModBiomeFeatures {
    public static final Supplier<Feature<RandomPatchConfiguration>> WILD_RICE = regFeature("wild_rice", () -> new WildRiceFeature(RandomPatchConfiguration.CODEC));
    public static final Supplier<Feature<WildCropConfiguration>> WILD_CROP = regFeature("wild_crop", () -> new WildCropFeature(WildCropConfiguration.CODEC));

    public static void touch() {
    }
}
