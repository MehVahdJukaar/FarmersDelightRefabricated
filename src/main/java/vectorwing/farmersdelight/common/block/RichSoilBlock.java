package vectorwing.farmersdelight.common.block;

import io.github.fabricators_of_create.porting_lib.common.util.IPlantable;
import io.github.fabricators_of_create.porting_lib.common.util.PlantType;
import io.github.fabricators_of_create.porting_lib.tool.ToolAction;
import io.github.fabricators_of_create.porting_lib.tool.ToolActions;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.MathUtils;

;

@SuppressWarnings("deprecation")
public class RichSoilBlock extends Block
{
	public RichSoilBlock(Properties properties) {
		super(properties);
	}

	public static void init() {
		TillableBlockRegistry.register(ModBlocks.RICH_SOIL.get(), HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(ModBlocks.RICH_SOIL_FARMLAND.get().defaultBlockState()));
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		if (!level.isClientSide) {
			BlockPos abovePos = pos.above();
			BlockState aboveState = level.getBlockState(abovePos);
			Block aboveBlock = aboveState.getBlock();

			// Do nothing if the plant is unaffected by rich soil
			if (aboveState.is(ModTags.UNAFFECTED_BY_RICH_SOIL)) {
				return;
			}

			// Convert mushrooms to colonies if it's dark enough
			if (aboveBlock == Blocks.BROWN_MUSHROOM) {
				level.setBlockAndUpdate(pos.above(), ModBlocks.BROWN_MUSHROOM_COLONY.get().defaultBlockState());
				return;
			}
			if (aboveBlock == Blocks.RED_MUSHROOM) {
				level.setBlockAndUpdate(pos.above(), ModBlocks.RED_MUSHROOM_COLONY.get().defaultBlockState());
				return;
			}

			if (Configuration.RICH_SOIL_BOOST_CHANCE.get() == 0.0) {
				return;
			}

			// If all else fails, and it's a plant, give it a growth boost now and then!
			if (aboveBlock instanceof BonemealableBlock growable && MathUtils.RAND.nextFloat() <= Configuration.RICH_SOIL_BOOST_CHANCE.get()) {
				if (growable.isValidBonemealTarget(level, pos.above(), aboveState, false)) {
					growable.performBonemeal(level, level.random, pos.above(), aboveState);
					level.levelEvent(2005, pos.above(), 0);
				}
			}
		}
	}

	// Likely will go unused until PortingLib fixes this.
	// Look at init method for new impl.
	@Override
	@Nullable
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
		if (toolAction.equals(ToolActions.HOE_TILL) && context.getLevel().getBlockState(context.getClickedPos().above()).isAir()) {
			return ModBlocks.RICH_SOIL_FARMLAND.get().defaultBlockState();
		}
		return null;
	}


	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
		PlantType plantType = plantable.getPlantType(world, pos.relative(facing));
		return plantType != PlantType.CROP && plantType != PlantType.NETHER && plantType != PlantType.WATER;
	}
}
