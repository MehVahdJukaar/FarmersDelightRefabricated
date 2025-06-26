package vectorwing.farmersdelight.client.gui;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.mixin.refabricated.AbstractRecipeBookScreenAccessor;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookingPotScreen extends AbstractRecipeBookScreen<CookingPotMenu> implements RecipeUpdateListener
{
	private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "textures/gui/cooking_pot.png");
	private static final Rectangle HEAT_ICON = new Rectangle(47, 55, 17, 15);
	private static final Rectangle PROGRESS_ARROW = new Rectangle(89, 25, 0, 17);

	public CookingPotScreen(CookingPotMenu screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, new CookingPotRecipeBookComponent(screenContainer), inv, titleIn);
	}

	@Override
	public void init() {
		if (Configuration.ENABLE_RECIPE_BOOK_COOKING_POT.get()) {
			super.init();
		} else {
			RecipeBookComponent<?> component = ((AbstractRecipeBookScreenAccessor)this).fdrf$getRecipeBookComponent();
			if (component.isVisible()) {
				component.toggleVisibility();
			}
			this.addWidget(component);
		}
		titleLabelX = 28;
	}

	@Override
	protected ScreenPosition getRecipeBookButtonPosition() {
		return new ScreenPosition(this.leftPos + 5, this.height / 2 - 49);
	}

	private void renderHeatIndicatorTooltip(GuiGraphics gui, int mouseX, int mouseY) {
		if (this.isHovering(HEAT_ICON.x, HEAT_ICON.y, HEAT_ICON.width, HEAT_ICON.height, mouseX, mouseY)) {
			String key = "container.cooking_pot." + (this.menu.isHeated() ? "heated" : "not_heated");
			gui.setTooltipForNextFrame(TextUtils.getTranslation(key), mouseX, mouseY);
		}
	}

	@Override
	protected void renderTooltip(GuiGraphics gui, int mouseX, int mouseY) {
		if (renderMealDisplayTooltip(gui, mouseX, mouseY))
			return;
		super.renderTooltip(gui, mouseX, mouseY);
		renderHeatIndicatorTooltip(gui, mouseX, mouseY);
	}

	protected boolean renderMealDisplayTooltip(GuiGraphics gui, int mouseX, int mouseY) {
		if (this.minecraft != null && this.minecraft.player != null && this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
			if (this.hoveredSlot.index == 6) {
				List<Component> tooltip = new ArrayList<>();

				ItemStack mealStack = this.hoveredSlot.getItem();
				tooltip.add(Component.translatable(mealStack.getItem().getDescriptionId()).withStyle(mealStack.getRarity().color()));

				ItemStack containerStack = this.menu.blockEntity.getContainer();
				String container = !containerStack.isEmpty() ? Component.translatable(mealStack.getItem().getDescriptionId()).getString() : "";

				tooltip.add(TextUtils.getTranslation("container.cooking_pot.served_on", container).withStyle(ChatFormatting.GRAY));

				gui.setTooltipForNextFrame(font, tooltip, Optional.empty(), mouseX, mouseY);
				return true;
			}
		}
		return false;
	}

	@Override
	protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
		if (this.minecraft == null)
			return;

		gui.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		// Render heat icon
		if (this.menu.isHeated()) {
			gui.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, this.leftPos + HEAT_ICON.x, this.topPos + HEAT_ICON.y, 176, 0, HEAT_ICON.width, HEAT_ICON.height, 256, 256);
		}

		// Render progress arrow
		int l = this.menu.getCookProgressionScaled();
		gui.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height, 256, 256);
	}
}
