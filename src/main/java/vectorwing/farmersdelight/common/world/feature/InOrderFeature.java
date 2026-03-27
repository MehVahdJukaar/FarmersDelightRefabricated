package vectorwing.farmersdelight.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import vectorwing.farmersdelight.common.world.configuration.InOrderFeatureConfiguration;

public class InOrderFeature extends Feature<InOrderFeatureConfiguration> {
	public InOrderFeature(Codec<InOrderFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<InOrderFeatureConfiguration> context) {
		InOrderFeatureConfiguration config = context.config();

        int successes = 0;
        for (Holder<PlacedFeature> feature : config.features()) {
            if (!feature.isBound())
                continue;

            if (feature.value().place(context.level(), context.chunkGenerator(), context.random(), context.origin())) {
                ++successes;
            }
        }
        return successes > 0;
	}
}
