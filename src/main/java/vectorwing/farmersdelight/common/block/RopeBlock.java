package vectorwing.farmersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;

public class RopeBlock extends IronBarsBlock
{
	public static final BooleanProperty TIED_TO_BELL = BooleanProperty.create("tied_to_bell");
	protected static final VoxelShape LOWER_SUPPORT_AABB = Block.box(7, 0, 7, 9, 1, 9);

	public RopeBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(CrossCollisionBlock.NORTH, false)
				.setValue(CrossCollisionBlock.SOUTH, false)
				.setValue(CrossCollisionBlock.EAST, false)
				.setValue(CrossCollisionBlock.WEST, false)
				.setValue(TIED_TO_BELL, false)
				.setValue(CrossCollisionBlock.WATERLOGGED, false)
		);
	}

	@Override
	public boolean isPathfindable(BlockState state, PathComputationType type) {
		return true;
	}

	protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (stack.is(ModItems.ROPE.get())) {
			return InteractionResult.PASS;
		}
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		if (Configuration.ENABLE_ROPE_REELING.get() && player.isSecondaryUseActive()) {
			if (player.getAbilities().mayBuild && (player.getAbilities().instabuild || player.getInventory().add(new ItemStack(this.asItem())))) {
				BlockPos.MutableBlockPos reelingPos = pos.mutable().move(Direction.DOWN);
				int minBuildHeight = level.getMinY();

				while (reelingPos.getY() >= minBuildHeight - 1) {
					BlockState blockStateBelow = level.getBlockState(reelingPos);
					if (blockStateBelow.is(this)) {
						reelingPos.move(Direction.DOWN);
					} else {
						reelingPos.move(Direction.UP);
						level.destroyBlock(reelingPos, false, player);
						return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
					}
				}
			}
		} else {
			BlockPos.MutableBlockPos bellRingingPos = pos.mutable().move(Direction.UP);

			for (int i = 0; i < 24; i++) {
				BlockState blockStateAbove = level.getBlockState(bellRingingPos);
				Block blockAbove = blockStateAbove.getBlock();
				if (blockAbove == Blocks.BELL) {
					((BellBlock) blockAbove).attemptToRing(level, bellRingingPos, blockStateAbove.getValue(BellBlock.FACING).getClockWise());
					return InteractionResult.SUCCESS;
				} else if (blockAbove == ModBlocks.ROPE.get()) {
					bellRingingPos.move(Direction.UP);
				} else {
					return InteractionResult.PASS;
				}
			}
		}

		return InteractionResult.PASS;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return getStateWithConnections(this.defaultBlockState(), context.getLevel(), context.getClickedPos(), context.getClickedFace());
	}

	public static BlockState getStateWithConnections(BlockState state, LevelAccessor level, BlockPos pos, Direction clickedFace) {
		FluidState fluidState = level.getFluidState(pos);
		BlockPos northPos = pos.north();
		BlockPos southPos = pos.south();
		BlockPos westPos = pos.west();
		BlockPos eastPos = pos.east();
		BlockState northState = level.getBlockState(northPos);
		BlockState southState = level.getBlockState(southPos);
		BlockState westState = level.getBlockState(westPos);
		BlockState eastState = level.getBlockState(eastPos);

		boolean isHorizontalPlacement = clickedFace.getAxis().isHorizontal();

		return state.setValue(TIED_TO_BELL, level.getBlockState(pos.above()).getBlock() == Blocks.BELL)
				.setValue(NORTH, isHorizontalPlacement ? tieToAnythingValid(northState, northState.isFaceSturdy(level, northPos, Direction.SOUTH)) : tieToRopeAndWalls(northState))
				.setValue(SOUTH, isHorizontalPlacement ? tieToAnythingValid(southState, southState.isFaceSturdy(level, southPos, Direction.NORTH)) : tieToRopeAndWalls(southState))
				.setValue(WEST, isHorizontalPlacement ? tieToAnythingValid(westState, westState.isFaceSturdy(level, westPos, Direction.EAST)) : tieToRopeAndWalls(westState))
				.setValue(EAST, isHorizontalPlacement ? tieToAnythingValid(eastState, eastState.isFaceSturdy(level, eastPos, Direction.WEST)) : tieToRopeAndWalls(eastState))
				.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
	}

	public static boolean tieToAnythingValid(BlockState state, boolean solidSide) {
		return !isExceptionForConnection(state) && solidSide || tieToRopeAndWalls(state);
	}

	public static boolean tieToRopeAndWalls(BlockState state) {
		return state.getBlock() instanceof IronBarsBlock || state.is(BlockTags.WALLS);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
		return LOWER_SUPPORT_AABB;
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return useContext.getItemInHand().getItem() == this.asItem();
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
        if (state.getValue(CrossCollisionBlock.WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        boolean tiedToBell = state.getValue(TIED_TO_BELL);
        if (facing == Direction.UP) {
            tiedToBell = level.getBlockState(facingPos).getBlock() == Blocks.BELL;
        }

        return facing.getAxis().isHorizontal()
                ? state.setValue(TIED_TO_BELL, tiedToBell).setValue(CrossCollisionBlock.PROPERTY_BY_DIRECTION.get(facing), this.attachsTo(facingState, facingState.isFaceSturdy(level, facingPos, facing.getOpposite())))
                : super.updateShape(state.setValue(TIED_TO_BELL, tiedToBell), level, scheduledTickAccess, currentPos, facing, facingPos, facingState, random);
    }

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(CrossCollisionBlock.NORTH, CrossCollisionBlock.EAST, CrossCollisionBlock.WEST, CrossCollisionBlock.SOUTH, CrossCollisionBlock.WATERLOGGED, TIED_TO_BELL);
	}
}