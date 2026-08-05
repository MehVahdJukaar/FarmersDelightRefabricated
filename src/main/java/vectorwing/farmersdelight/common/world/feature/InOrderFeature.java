package vectorwing.farmersdelight.common.world.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record InOrderFeature(HolderSet<PlacedFeature> features) implements Feature {

	public static final MapCodec<InOrderFeature> CODEC = RecordCodecBuilder.mapCodec((config) -> config.group(
		PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(InOrderFeature::features)
	).apply(config, InOrderFeature::new));


	@Override
	public MapCodec<? extends Feature> codec() {
		return CODEC;
	}

	@Override
	public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {

		int successes = 0;
		for (Holder<PlacedFeature> feature : features) {
			if (!feature.isBound())
				continue;

			if (feature.value().place(level, chunkGenerator, random, origin)) {
				++successes;
			}
		}
		return successes > 0;
	}
}
