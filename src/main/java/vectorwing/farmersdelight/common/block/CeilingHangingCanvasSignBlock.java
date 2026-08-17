package vectorwing.farmersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.state.CanvasSign;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class CeilingHangingCanvasSignBlock extends CeilingHangingSignBlock implements CanvasSign
{
	private final DyeColor backgroundColor;

	public CeilingHangingCanvasSignBlock(@NotNull DyeColor backgroundColor) {
		super(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), WoodType.SPRUCE);
		this.backgroundColor = backgroundColor;
	}

	@NotNull
	public DyeColor getBackgroundColor() {
		return this.backgroundColor;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return ModBlockEntityTypes.HANGING_CANVAS_SIGN.get().create(pos, state);
	}
}
