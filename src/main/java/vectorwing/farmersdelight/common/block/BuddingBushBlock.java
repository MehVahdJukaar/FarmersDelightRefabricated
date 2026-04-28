package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.registry.ModItems;

/**
 * A bush which grows, representing the earlier stage of another plant.
 * Once mature, a budding bush can "grow past" it, and turn into something different.
 */
@SuppressWarnings("deprecation")
public class BuddingBushBlock extends VegetationBlock
{
	public static final MapCodec<BuddingBushBlock> CODEC = simpleCodec(BuddingBushBlock::new);

	public static final int MAX_AGE = 3;
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D)};

	public BuddingBushBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends VegetationBlock> codec() {
		return CODEC;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_BY_AGE[state.getValue(getAgeProperty())];
	}

	@Override
	public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(BlockTags.SUPPORTS_CROPS);
	}

	public IntegerProperty getAgeProperty() {
		return AGE;
	}

	public int getMaxAge() {
		return MAX_AGE;
	}

	protected int getAge(BlockState state) {
		return state.getValue(getAgeProperty());
	}

	public BlockState getStateForAge(int age) {
		return defaultBlockState().setValue(getAgeProperty(), age);
	}

	public boolean isMaxAge(BlockState state) {
		return state.getValue(getAgeProperty()) >= getMaxAge();
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return canGrowPastMaxAge() || !isMaxAge(state);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.hasChunksAt(pos.offset(-1, -1 , -1), pos.offset(1, 1, 1))) return;
		if (level.getRawBrightness(pos, 0) >= 9) {
			int age = getAge(state);
			if (age <= getMaxAge()) {
				float growthSpeed = getGrowthSpeed(state, level, pos);
				if (random.nextInt((int) (25.0F / growthSpeed) + 1) == 0) {
					if (isMaxAge(state)) {
						growPastMaxAge(state, level, pos, random);
					} else {
						level.setBlockAndUpdate(pos, getStateForAge(age + 1));
					}
				}
			}
		}
	}

	/**
	 * Determines if this bush should keep ticking at max age. If true, calls growPastMaxAge() on each growth success.
	 */
	public boolean canGrowPastMaxAge() {
		return false;
	}

	public void growPastMaxAge(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
	}

    protected static float getGrowthSpeed(BlockState state, BlockGetter level, BlockPos pos) {
        float speed = 1.0F;
        BlockPos posBelow = pos.below();

		for (int posX = -1; posX <= 1; ++posX) {
			for (int posZ = -1; posZ <= 1; ++posZ) {
				float speedBonus = 0.0F;
				BlockState stateBelow = level.getBlockState(posBelow.offset(posX, 0, posZ));
				TriState soilDecision = stateBelow.canSustainPlant(level, posBelow.offset(posX, 0, posZ), net.minecraft.core.Direction.UP, state);
				if (soilDecision.isDefault()) {
					speedBonus = 1.0F;
					if (stateBelow.isFertile(level, pos.offset(posX, 0, posZ))) {
						speedBonus = 3.0F;
					}
				}

				if (posX != 0 || posZ != 0) {
					speedBonus /= 4.0F;
				}

				speed += speedBonus;
			}
		}

		BlockPos posNorth = pos.north();
		BlockPos posSouth = pos.south();
		BlockPos posWest = pos.west();
		BlockPos posEast = pos.east();
		Block block = state.getBlock();
		boolean matchesEastWestRow = level.getBlockState(posWest).is(block) || level.getBlockState(posEast).is(block);
		boolean matchesNorthSouthRow = level.getBlockState(posNorth).is(block) || level.getBlockState(posSouth).is(block);
		if (matchesEastWestRow && matchesNorthSouthRow) {
			speed /= 2.0F;
		} else {
			boolean matchesDiagonalRows = level.getBlockState(posWest.north()).is(block) || level.getBlockState(posEast.north()).is(block) || level.getBlockState(posEast.south()).is(block) || level.getBlockState(posWest.south()).is(block);
			if (matchesDiagonalRows) {
				speed /= 2.0F;
			}
		}

        return speed;
	}


	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return (level.getRawBrightness(pos, 0) >= 8 || level.canSeeSky(pos)) && super.canSurvive(state, level, pos);
	}

	@Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean bl) {
		if (entity instanceof Ravager && level instanceof ServerLevel serverLevel && serverLevel.getGameRules().get(GameRules.MOB_GRIEFING)) {
			level.destroyBlock(pos, true, entity);
		}
		super.entityInside(state, level, pos, entity, effectApplier, bl);
	}

	protected ItemLike getBaseSeedId() {
		return ModItems.TOMATO_SEEDS.get();
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
		return new ItemStack(getBaseSeedId());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(AGE);
	}
}
