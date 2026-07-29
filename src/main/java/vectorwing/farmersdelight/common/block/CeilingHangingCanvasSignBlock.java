package vectorwing.farmersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignTextSlot;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CanvasSign;

public class CeilingHangingCanvasSignBlock extends CeilingHangingSignBlock implements CanvasSign
{
	private final DyeColor backgroundColor;

	public CeilingHangingCanvasSignBlock(Properties properties, @Nullable DyeColor backgroundColor) {
		super(WoodType.SPRUCE, properties);
		this.backgroundColor = backgroundColor;
	}

	@Nullable
	public DyeColor getBackgroundColor() {
		return this.backgroundColor;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(level, pos, state, placer, stack);

		if (level.getBlockEntity(pos) instanceof SignBlockEntity sign && state.getBlock() instanceof CanvasSign canvasSignBlock) {
			if (canvasSignBlock.isDarkBackground()) {
				sign.updateText((signText) -> signText.asMutable().setColor(DyeColor.WHITE).asImmutable(), SignTextSlot.FRONT);
				sign.updateText((signText) -> signText.asMutable().setColor(DyeColor.WHITE).asImmutable(), SignTextSlot.BACK);
			}
		}
	}
}
