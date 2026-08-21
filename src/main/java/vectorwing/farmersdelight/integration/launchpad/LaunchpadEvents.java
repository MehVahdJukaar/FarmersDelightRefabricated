package vectorwing.farmersdelight.integration.launchpad;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LaunchpadEvents {
	// avoid neo patch breaking everything
	public static float getGrowthSpeed(BlockState state, ServerLevel level, BlockPos pos) {
		Block type = state.getBlock();
		float speed = 1.0F;
		BlockPos below = pos.below();

		for(int xx = -1; xx <= 1; ++xx) {
			for(int zz = -1; zz <= 1; ++zz) {
				float blockSpeed = 0.0F;
				BlockState blockState = level.getBlockState(below.offset(xx, 0, zz));
				if (blockState.is(BlockTags.GROWS_CROPS)) {
					blockSpeed = 1.0F;
					if (blockState.getValueOrElse(FarmlandBlock.MOISTURE, 0) > 0) {
						blockSpeed = 3.0F;
					}
				}

				if (xx != 0 || zz != 0) {
					blockSpeed /= 4.0F;
				}

				speed += blockSpeed;
			}
		}

		BlockPos north = pos.north();
		BlockPos south = pos.south();
		BlockPos west = pos.west();
		BlockPos east = pos.east();
		boolean horizontal = level.getBlockState(west).is(type) || level.getBlockState(east).is(type);
		boolean vertical = level.getBlockState(north).is(type) || level.getBlockState(south).is(type);
		if (horizontal && vertical) {
			speed /= 2.0F;
		} else {
			boolean diagonal = level.getBlockState(west.north()).is(type) || level.getBlockState(east.north()).is(type) || level.getBlockState(east.south()).is(type) || level.getBlockState(west.south()).is(type);
			if (diagonal) {
				speed /= 2.0F;
			}
		}

		return speed;
	}
}
