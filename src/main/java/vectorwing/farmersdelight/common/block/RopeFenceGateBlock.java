package vectorwing.farmersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

// Refabricated: Sounds replaced via mixin to ensure other mixins can function with this block.
public class RopeFenceGateBlock extends FenceGateBlock
{
	protected static final VoxelShape Z_SHAPE = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 14.0D, 9.0D);
	protected static final VoxelShape X_SHAPE = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 14.0D, 16.0D);
	protected static final VoxelShape Z_COLLISION_SHAPE = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 24.0D, 9.0D);
	protected static final VoxelShape X_COLLISION_SHAPE = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 24.0D, 16.0D);

	public RopeFenceGateBlock(Properties props) {
		// Spruce is a placeholder.
		super(WoodType.OAK, props);
	}

	public BlockState updateShape(BlockState state,
								  LevelReader level,
								  ScheduledTickAccess ticks,
								  BlockPos pos,
								  Direction directionToNeighbour,
								  BlockPos neighbourPos,
								  BlockState neighbourState,
								  RandomSource random) {
		Direction.Axis axis = directionToNeighbour.getAxis();
		if (state.getValue(FACING).getClockWise().getAxis() != axis) {
			return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
		} else {
			boolean isBorderedByWalls = this.isWall(state) && this.isWall(level.getBlockState(pos.relative(directionToNeighbour.getOpposite())));
			return state.setValue(IN_WALL, isBorderedByWalls);
		}
	}

	protected boolean isWall(BlockState state) {
		return state.is(BlockTags.WALLS);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return state.getValue(FACING).getAxis() == Direction.Axis.X ? X_SHAPE : Z_SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(OPEN)) {
			return Shapes.empty();
		} else {
			return state.getValue(FACING).getAxis() == Direction.Axis.Z ? Z_COLLISION_SHAPE : X_COLLISION_SHAPE;
		}
	}
}
