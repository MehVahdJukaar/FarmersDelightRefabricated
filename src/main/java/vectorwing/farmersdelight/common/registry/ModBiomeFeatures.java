package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.feature.Feature;
import vectorwing.farmersdelight.common.world.feature.InOrderFeature;
import vectorwing.farmersdelight.common.world.feature.WildRiceFeature;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regFeature;

public class ModBiomeFeatures {
    // Refabricated (26.3): Configured Features and Placed Features were reworked again.
    public static final Supplier<MapCodec<InOrderFeature>> IN_ORDER = regFeature("in_order", InOrderFeature.CODEC);
    public static final Supplier<MapCodec<WildRiceFeature>> WILD_RICE = regFeature("wild_rice", WildRiceFeature.CODEC);

    public static void touch() {
    }
}
