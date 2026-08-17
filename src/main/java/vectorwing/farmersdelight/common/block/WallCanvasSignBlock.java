package vectorwing.farmersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CanvasSign;

public class WallCanvasSignBlock extends WallSignBlock implements CanvasSign
{
	private final DyeColor backgroundColor;

	public WallCanvasSignBlock(Properties properties, @Nullable DyeColor backgroundColor) {
		super(WoodType.SPRUCE, properties);
		this.backgroundColor = backgroundColor;
	}

	@Nullable
	public DyeColor getBackgroundColor() {
		return this.backgroundColor;
	}
}
