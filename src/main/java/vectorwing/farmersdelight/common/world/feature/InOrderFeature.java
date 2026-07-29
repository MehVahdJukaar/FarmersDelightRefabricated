package vectorwing.farmersdelight.common.world.feature;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import vectorwing.farmersdelight.common.world.configuration.InOrderFeatureConfiguration;

public class InOrderFeature implements Feature {

	public InOrderFeature() {
		super();
	}

	@Override
	public MapCodec<? extends Feature> codec() {
		return InOrderFeatureConfiguration.CODEC;
	}

	@Override
	public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {

		int successes = 0;
		for (Holder<PlacedFeature> feature : config.features()) {
			if (!feature.isBound())
				continue;

			if (feature.value().place(level, chunkGenerator, random, origin)) {
				++successes;
			}
		}
		return successes > 0;
	}
}
