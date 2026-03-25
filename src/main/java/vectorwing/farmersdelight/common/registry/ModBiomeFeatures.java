package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import vectorwing.farmersdelight.common.world.configuration.InOrderFeatureConfiguration;
import vectorwing.farmersdelight.common.world.feature.InOrderFeature;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regFeature;

public class ModBiomeFeatures {
    // Refabricated (26.1): Configured Features and Placed Features were reworked entirely to put predicates entirely inside placed features.
    public static final Supplier<Feature<InOrderFeatureConfiguration>> IN_ORDER = regFeature("in_order", () -> new InOrderFeature(InOrderFeatureConfiguration.CODEC));

    public static void touch() {
    }
}
