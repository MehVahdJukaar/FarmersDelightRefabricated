package vectorwing.farmersdelight.refabricated.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public interface ReplaceOnRemoval {
	boolean onRemove(BlockState state, BlockState newState, LevelAccessor level, BlockPos pos, int updateFlags);
}
