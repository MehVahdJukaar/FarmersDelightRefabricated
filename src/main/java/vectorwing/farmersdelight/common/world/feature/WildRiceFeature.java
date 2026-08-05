package vectorwing.farmersdelight.common.world.feature;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import vectorwing.farmersdelight.common.block.WildRiceBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class WildRiceFeature implements Feature {
	public static MapCodec<WildRiceFeature> CODEC = MapCodec.unit(new WildRiceFeature());

	public WildRiceFeature() {
		super();
	}

	@Override
	public MapCodec<? extends Feature> codec() {
		return CODEC;
	}

	@Override
	public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {

		if (level.getBlockState(origin).getBlock() == Blocks.WATER && level.getBlockState(origin.above()).getBlock() == Blocks.AIR) {
			BlockState bottomRiceState = ModBlocks.WILD_RICE.get().defaultBlockState().setValue(WildRiceBlock.HALF, DoubleBlockHalf.LOWER);
			if (bottomRiceState.canSurvive(level, origin)) {
				DoublePlantBlock.placeAt(level, bottomRiceState, origin, 2);
				return true;
			}
		}
		return false;
	}
}
