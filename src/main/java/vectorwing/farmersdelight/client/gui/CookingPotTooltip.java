package vectorwing.farmersdelight.client.gui;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.ARGB;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class CookingPotTooltip implements ClientTooltipComponent
{
	private static final int ITEM_SIZE = 16;
	private static final int MARGIN = 4;

	private final int textSpacing = Minecraft.getInstance().font.lineHeight + 1;
	private final ItemStack mealStack;

	public CookingPotTooltip(CookingPotTooltipComponent tooltip) {
		this.mealStack = tooltip.mealStack;
	}

	@Override
	public int getHeight(Font font) {
		return mealStack.isEmpty() ? textSpacing : textSpacing + ITEM_SIZE;
	}

	@Override
	public int getWidth(Font font) {
		if (!mealStack.isEmpty()) {
			MutableComponent textServingsOf = mealStack.getCount() == 1
					? TextUtils.getTranslation("tooltip.cooking_pot.single_serving")
					: TextUtils.getTranslation("tooltip.cooking_pot.many_servings", mealStack.getCount());
			return Math.max(font.width(textServingsOf), font.width(mealStack.getHoverName()) + 20);
		} else {
			return font.width(TextUtils.getTranslation("tooltip.cooking_pot.empty"));
		}
	}

	@Override
	public void extractImage(Font font, int mouseX, int mouseY, int width, int height, GuiGraphicsExtractor gui) {
		if (mealStack.isEmpty()) return;
		gui.item(mealStack, mouseX, mouseY + textSpacing, 0);
	}

	@Override
	public void extractText(GuiGraphicsExtractor guiGraphics, Font font, int x, int y) {
		Integer color = ChatFormatting.GRAY.getColor();
		int gray = color == null ? -1 : ARGB.opaque(color);

		if (!mealStack.isEmpty()) {
			MutableComponent textServingsOf = mealStack.getCount() == 1
					? TextUtils.getTranslation("tooltip.cooking_pot.single_serving")
					: TextUtils.getTranslation("tooltip.cooking_pot.many_servings", mealStack.getCount());

			guiGraphics.textWithWordWrap(font, textServingsOf, x, y, 96, gray);
			guiGraphics.textWithWordWrap(font, mealStack.getHoverName(), x + ITEM_SIZE + MARGIN, y + textSpacing + MARGIN, 96, -1);
		} else {
			MutableComponent textEmpty = TextUtils.getTranslation("tooltip.cooking_pot.empty");
			guiGraphics.textWithWordWrap(font, textEmpty, x, y, 96, gray);
		}
	}

	public record CookingPotTooltipComponent(ItemStack mealStack) implements TooltipComponent
	{
	}
}
