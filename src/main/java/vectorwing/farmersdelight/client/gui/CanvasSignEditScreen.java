package vectorwing.farmersdelight.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.SignEditScreen;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.gui.state.GuiCanvasSignRenderState;
import vectorwing.farmersdelight.common.block.state.CanvasSign;

public class CanvasSignEditScreen extends SignEditScreen {
	@Nullable
	protected Model signModel;
	@Nullable
	protected DyeColor dye;
	protected final boolean isFrontText;

	public CanvasSignEditScreen(SignBlockEntity signBlockEntity, boolean isFront, boolean isTextFilteringEnabled) {
		super(signBlockEntity, isFront, isTextFilteringEnabled);
		Block block = signBlockEntity.getBlockState().getBlock();
		if (block instanceof CanvasSign canvasSign) {
			this.dye = canvasSign.getBackgroundColor();
		}
		this.isFrontText = isFront;
	}

	protected void init() {
		super.init();
		boolean bl = this.sign.getBlockState().getBlock() instanceof StandingSignBlock;
		this.signModel = SignRenderer.createSignModel(this.minecraft.getEntityModels(), this.woodType, bl);
	}

	@Override
	protected void renderSignBackground(GuiGraphics guiGraphics) {
		if (this.signModel != null) {
			int i = this.width / 2;
			int j = i - 48;
			int l = i + 48;
			guiGraphics.guiRenderState.submitPicturesInPictureState(new GuiCanvasSignRenderState(signModel, dye, j, 66, l, 168, 62.500004F, guiGraphics.scissorStack.peek()));
		}
	}
}