package vectorwing.farmersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.refabricated.FDRefabricatedTags;

public class WildRiceBlock extends DoublePlantBlock implements SimpleWaterloggedBlock, BonemealableBlock
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public WildRiceBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, true).setValue(HALF, DoubleBlockHalf.LOWER));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HALF, WATERLOGGED);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		FluidState fluid = level.getFluidState(pos);
		BlockPos floorPos = pos.below();
		if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
			return super.canSurvive(state, level, pos) && this.mayPlaceOn(level.getBlockState(floorPos), level, floorPos) && fluid.is(FluidTags.WATER) && fluid.getAmount() == 8;
		}
		return super.canSurvive(state, level, pos) && level.getBlockState(pos.below()).getBlock() == ModBlocks.WILD_RICE.get();
	}

	@Override
	public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(FDRefabricatedTags.Blocks.GROWS_WILD_CROPS);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return false;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		level.setBlock(pos.above(), this.defaultBlockState().setValue(WATERLOGGED, false).setValue(HALF, DoubleBlockHalf.UPPER), 3);
	}


	@Override
	public BlockState updateShape(
			BlockState state,
			LevelReader level,
			ScheduledTickAccess scheduledTickAccess,
			BlockPos currentPos,
			Direction facing,
			BlockPos facingPos,
			BlockState facingState,
			RandomSource random) {
		BlockState currentState = super.updateShape(state, level, scheduledTickAccess, currentPos, facing, facingPos, facingState, random);
		DoubleBlockHalf half = state.getValue(HALF);
		if (!currentState.isAir()) {
			scheduledTickAccess.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		if (facing.getAxis() != Direction.Axis.Y || half == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.getBlock() == this && facingState.getValue(HALF) != half) {
			return half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : state;
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
		return pos.getY() < context.getLevel().getMaxY() - 1
				&& fluid.is(FluidTags.WATER)
				&& fluid.getAmount() == 8
				&& context.getLevel().getBlockState(pos.above()).isAir()
				? super.getStateForPlacement(context) : null;
	}

	@Override
	public boolean canPlaceLiquid(@Nullable LivingEntity player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluidIn) {
		return state.getValue(HALF) == DoubleBlockHalf.LOWER;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(HALF) == DoubleBlockHalf.LOWER
				? Fluids.WATER.getSource(false)
				: Fluids.EMPTY.defaultFluidState();
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
		return (double) random.nextFloat() < 0.3F;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
		popResource(level, pos, new ItemStack(this));
	}
}
