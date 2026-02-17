package vectorwing.farmersdelight.client.gui.renderer;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.SpriteGetter;
import net.minecraft.client.resources.model.SpriteId;
import vectorwing.farmersdelight.client.gui.state.GuiCanvasSignRenderState;
import vectorwing.farmersdelight.common.registry.ModAtlases;

public class GuiCanvasSignRenderer extends PictureInPictureRenderer<GuiCanvasSignRenderState> {
	private final SpriteGetter materials;
	public GuiCanvasSignRenderer(MultiBufferSource.BufferSource bufferSource, SpriteGetter materials) {
		super(bufferSource);
		this.materials = materials;
	}

	@Override
	public Class<GuiCanvasSignRenderState> getRenderStateClass() {
		return GuiCanvasSignRenderState.class;
	}

	protected void renderToTexture(GuiCanvasSignRenderState guiSignRenderState, PoseStack poseStack) {
		Minecraft.getInstance().gameRenderer.getLighting().setupFor(Lighting.Entry.ITEMS_FLAT);
		poseStack.translate(0.0F, -0.75F, 0.0F);
		SpriteId material = ModAtlases.getCanvasSignMaterial(guiSignRenderState.dye());
		Model model = guiSignRenderState.signModel();
		VertexConsumer vertexConsumer = material.buffer(this.materials, this.bufferSource, model::renderType);
		model.renderToBuffer(poseStack, vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY);
	}

	@Override
	protected String getTextureLabel() {
		return "sign";
	}
}
