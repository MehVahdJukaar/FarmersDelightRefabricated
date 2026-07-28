package vectorwing.farmersdelight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class PlaceableItem extends BlockItem
{
	public PlaceableItem(Block block, Properties properties) {
		super(block, properties);
	}

	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(TextUtils.PLACEABLE);
	}
}
