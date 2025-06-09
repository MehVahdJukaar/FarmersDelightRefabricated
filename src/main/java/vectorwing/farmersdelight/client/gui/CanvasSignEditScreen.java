package vectorwing.farmersdelight.client.gui;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.SignEditScreen;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CanvasSign;
import vectorwing.farmersdelight.common.registry.ModAtlases;

public class CanvasSignEditScreen extends SignEditScreen
{
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
			guiGraphics.pose().translate(0.0F, 31.0F, 0.0F);
			if (!isFrontText) {
				guiGraphics.pose().mulPose(Axis.YP.rotationDegrees(180));
			}
			guiGraphics.pose().scale(SignEditScreen.MAGIC_SCALE_NUMBER, SignEditScreen.MAGIC_SCALE_NUMBER, -SignEditScreen.MAGIC_SCALE_NUMBER);
			Material material = ModAtlases.getCanvasSignMaterial(dye);
			guiGraphics.drawSpecial(bufferSource -> {
				VertexConsumer vertexconsumer = material.buffer(bufferSource, this.signModel::renderType);
				this.signModel.renderToBuffer(guiGraphics.pose(), vertexconsumer, 15728880, OverlayTexture.NO_OVERLAY);
			});
		}
	}
}
