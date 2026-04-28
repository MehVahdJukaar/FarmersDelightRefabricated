package vectorwing.farmersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;

@SuppressWarnings("deprecation")
public class HangingTomatoBlock extends TomatoBlock
{
	public HangingTomatoBlock(Properties properties) {
		super(properties, false);
		registerDefaultState(stateDefinition.any().setValue(getAgeProperty(), 0));
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(level, pos)) {
			level.destroyBlock(pos, true);
		}
	}

	@Override
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
		this.playerWillDestroy(level, pos, state, player);
		placeRope(level, pos);
	}

	// TODO: Mixin to remove logic.
	public void onRemove(BlockState state, BlockState newState, LevelAccessor level, BlockPos pos, int updateFlags) {
		if (Configuration.ENABLE_TOMATO_ROPE_PERMANENCE.get() && (updateFlags ^ Block.UPDATE_MOVE_BY_PISTON) != 0 && !state.is(newState.getBlock()) && newState.isAir()) {
			placeRope(level, pos);
		}
	}

	public static boolean placeRope(LevelAccessor level, BlockPos pos) {
		Block configuredRopeBlock = BuiltInRegistries.BLOCK.getValue(Identifier.parse(Configuration.DEFAULT_TOMATO_VINE_ROPE.get()));
		if (configuredRopeBlock == null) {
			configuredRopeBlock = ModBlocks.ROPE.get();
		}
		BlockState finalRopeState = configuredRopeBlock.equals(ModBlocks.ROPE.get())
				? RopeBlock.getStateWithConnections(ModBlocks.ROPE.get().defaultBlockState(), level, pos, Direction.UP)
				: configuredRopeBlock.defaultBlockState();

		return level.setBlock(pos, finalRopeState, level.isClientSide() ? 11 : 3);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(VINE_AGE);
	}
}
