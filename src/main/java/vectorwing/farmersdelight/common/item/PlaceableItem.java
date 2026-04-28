package vectorwing.farmersdelight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.function.Consumer;

public class PlaceableItem extends BlockItem
{
	public PlaceableItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public void appendHoverText(final ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
		builder.accept(TextUtils.PLACEABLE);
	}
}
