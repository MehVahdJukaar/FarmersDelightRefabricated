package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import vectorwing.farmersdelight.common.world.feature.InOrderFeature;
import vectorwing.farmersdelight.common.world.feature.WildRiceFeature;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regFeature;

public class ModBiomeFeatures {
    // Refabricated (26.1): Configured Features and Placed Features were reworked entirely to put predicates entirely inside placed features.
    public static final Supplier<Feature> IN_ORDER = regFeature("in_order", () -> new InOrderFeature());
    public static final Supplier<Feature> WILD_RICE = regFeature("wild_rice", () -> new WildRiceFeature());

    public static void touch() {
    }
}
