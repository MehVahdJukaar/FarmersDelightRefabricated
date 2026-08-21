package vectorwing.farmersdelight.common.block;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;

import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.integration.launchpad.LaunchpadClientEvents;
import vectorwing.farmersdelight.integration.launchpad.LaunchpadEvents;

@SuppressWarnings("deprecation")
public class TomatoBlock extends CropBlock
{
	public static final IntegerProperty VINE_AGE = BlockStateProperties.AGE_3;
	public static final BooleanProperty ROPELOGGED = BooleanProperty.create("ropelogged");
	private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

	public TomatoBlock(Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(getAgeProperty(), 0).setValue(ROPELOGGED, false));
	}

	protected TomatoBlock(Properties properties, boolean dummy) {
		super(properties);
	}

	protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		int age = state.getValue(getAgeProperty());
		boolean isMature = age == getMaxAge();
		return !isMature && stack.is(Items.BONE_MEAL) ? InteractionResult.PASS : super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		int age = state.getValue(getAgeProperty());
		boolean isMature = age == getMaxAge();
		if (isMature) {
			int quantity = 1 + level.getRandom().nextInt(2);
			popResource(level, pos, new ItemStack(ModItems.TOMATO.get(), quantity));

			if (level.getRandom().nextFloat() < 0.05) {
				popResource(level, pos, new ItemStack(ModItems.ROTTEN_TOMATO.get()));
			}

			level.playSound(null, pos, ModSounds.BLOCK_TOMATOES_PICK_TOMATOES.get(), SoundSource.BLOCKS, 1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
			level.setBlock(pos, state.setValue(getAgeProperty(), 0), 2);
			return InteractionResult.SUCCESS;
		} else {
			return super.useWithoutItem(state, level, pos, player, hit);
		}
	}

	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(level, pos)) {
			level.destroyBlock(pos, true);
			if (state.getValue(TomatoBlock.ROPELOGGED)) {
				destroyAndPlaceRope(level, pos);
			}
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.hasChunksAt(pos.offset(-1, -1 , -1), pos.offset(1, 1, 1))) return;

		// TODO: Remove this conversion, as well as the ropelogged state, in future versions.
		if (state.is(ModBlocks.TOMATO_CROP.get()) && state.getValue(ROPELOGGED)) {
			level.setBlockAndUpdate(pos, ModBlocks.TOMATO_CROP_ON_ROPE.get().defaultBlockState().setValue(VINE_AGE, this.getAge(state)));
			return;
		}
		if (level.getRawBrightness(pos, 0) >= 9) {
			int age = this.getAge(state);
			if (age < this.getMaxAge()) {
				float speed = getGrowthSpeed(state, level, pos);
				if (random.nextInt((int) (25.0F / speed) + 1) == 0) {
					level.setBlock(pos, state.setValue(getAgeProperty(), age + 1), 2);
				}
			}
			climbRopeAbove(level, pos);
		}
	}

	@Override
	public BlockState getStateForAge(int age) {
		return this.defaultBlockState().setValue(this.getAgeProperty(), age);
	}

	@Override
	public IntegerProperty getAgeProperty() {
		return VINE_AGE;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public int getMaxAge() {
		return 3;
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return ModItems.TOMATO_SEEDS.get();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(VINE_AGE, ROPELOGGED);
	}


	public boolean canClimbBlock(BlockState stateAbove) {
		return Configuration.ENABLE_TOMATO_VINE_CLIMBING_TAGGED_ROPES.get() ? stateAbove.is(ModTags.Blocks.ROPES) : stateAbove.is(ModBlocks.ROPE.get());
	}

	@Nullable
	public BlockState getClimbingState(BlockState stateAbove) {
		if (this.canClimbBlock(stateAbove)){
			return ModBlocks.TOMATO_CROP_ON_ROPE.get().defaultBlockState();
		}
		return null;
	}

	public void climbRopeAbove(ServerLevel level, BlockPos pos) {
		BlockPos posAbove = pos.above();
		BlockState stateAbove = level.getBlockState(posAbove);
		BlockState climbingState = getClimbingState(stateAbove);
		if (climbingState != null) {
			int vineHeight;
			for (vineHeight = 1; level.getBlockState(pos.below(vineHeight)).is(this); ++vineHeight) {
			}
			if (vineHeight < 3) {
				level.setBlockAndUpdate(posAbove, climbingState);
			}
		}
	}

	@Override
	protected int getBonemealAgeIncrease(Level level) {
		return super.getBonemealAgeIncrease(level) / 2;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source) {
		if (!this.isMaxAge(state)) {
			return true;
		}

		BlockPos.MutableBlockPos mutablePos = pos.mutable();
		for (int height = 0; height < 2; height++) {
			mutablePos.move(Direction.UP);
			BlockState nextState = level.getBlockState(mutablePos);
			if (canClimbBlock(nextState)) {
				return true;
			}
			if (nextState.getBlock() instanceof HangingTomatoBlock) {
				if (!isMaxAge(nextState)) {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
		// TODO: Remove this conversion, as well as the ropelogged state, in future versions.
		if (state.is(ModBlocks.TOMATO_CROP.get()) && state.getValue(ROPELOGGED)) {
			level.setBlockAndUpdate(pos, ModBlocks.TOMATO_CROP_ON_ROPE.get().defaultBlockState().setValue(VINE_AGE, this.getAge(state)));
			return;
		}
		int newAge = this.getAge(state) + this.getBonemealAgeIncrease(level);
		if (newAge <= this.getMaxAge()) {
			level.setBlockAndUpdate(pos, state.setValue(getAgeProperty(), newAge));
			if (random.nextFloat() < 0.3F) {
				climbRopeAbove(level, pos);
			}
		} else {
			BlockState aboveState = level.getBlockState(pos.above());
			if (canClimbBlock(level.getBlockState(pos.above()))) {
				climbRopeAbove(level, pos);
			} else if (aboveState.is(ModBlocks.TOMATO_CROP_ON_ROPE.get()) && isValidBonemealTarget(level, pos, aboveState, source)) {
				performBonemeal(level, random, pos.above(), aboveState, source);
			}
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos belowPos = pos.below();
		BlockState belowState = level.getBlockState(belowPos);

		if (belowState.getBlock() instanceof TomatoBlock) {
			return hasGoodCropConditions(level, pos);
		}

		return super.canSurvive(state, level, pos);
	}

	public boolean hasGoodCropConditions(LevelReader level, BlockPos pos) {
		return level.getRawBrightness(pos, 0) >= 8 || level.canSeeSky(pos);
	}

	@Override
	public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
		if (!state.canSurvive(level, pos)) {
			ticks.scheduleTick(pos, this, 1);
		}

		return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
	}

	@Deprecated(forRemoval = true)
	@Override
	public void playerDestroy(ServerLevel level, ServerPlayer player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
		super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);

		if (state.hasProperty(TomatoBlock.ROPELOGGED) && state.getValue(TomatoBlock.ROPELOGGED)) {
			destroyAndPlaceRope(level, pos);
		}
	}

	/**
	 * Deprecated - This block will no longer use its ropelogged state. Refer to HangingTomatoBlock instead.
	 */
	@Deprecated(forRemoval = true)
	public static void destroyAndPlaceRope(Level level, BlockPos pos) {
		Block configuredRopeBlock = BuiltInRegistries.BLOCK.getValue(Identifier.parse(Configuration.DEFAULT_TOMATO_VINE_ROPE.get()));
		Block finalRopeBlock = configuredRopeBlock != null ? configuredRopeBlock : ModBlocks.ROPE.get();
		level.setBlockAndUpdate(pos, finalRopeBlock.defaultBlockState());
	}

	protected float getGrowthSpeed(BlockState state, ServerLevel level, BlockPos pos) {
		if (FabricLoader.getInstance().isModLoaded("launchpad")) {
			return LaunchpadEvents.getGrowthSpeed(state, level, pos);
		}
		else return getGrowthSpeed(state.getBlock(), level, pos);
	}
}
