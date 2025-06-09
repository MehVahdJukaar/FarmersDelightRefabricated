package vectorwing.farmersdelight.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CanvasSign;
import vectorwing.farmersdelight.common.registry.ModAtlases;

;import java.util.Arrays;
import java.util.Map;

public class HangingCanvasSignRenderer extends HangingSignRenderer implements ICanvasSignRenderer
{
	private final Map<HangingSignRenderer.AttachmentType, Model> hangingSignModels;

	public HangingCanvasSignRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
		this.hangingSignModels = Arrays.stream(HangingSignRenderer.AttachmentType.values()).collect(ImmutableMap.toImmutableMap(attachmentType -> attachmentType, (attachmentType) ->
				HangingSignRenderer.createSignModel(context.getModelSet(), WoodType.SPRUCE, attachmentType)));
	}

	public float getSignModelRenderScale() {
		return 1.0F;
	}

	public float getSignTextRenderScale() {
		return 0.8F;
	}

	@Override
	public void render(SignBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, Vec3 cameraPos) {
		BlockState state = blockEntity.getBlockState();
		SignBlock block = (SignBlock) state.getBlock();
		Model model = getSignModel(state);

		DyeColor dye = null;
		if (block instanceof CanvasSign canvasSign) {
			dye = canvasSign.getBackgroundColor();
		}

		renderSignWithText(blockEntity, poseStack, bufferSource, packedLight, packedOverlay, state, block, dye, model);
	}

	protected Model getSignModel(BlockState state) {
		HangingSignRenderer.AttachmentType attachmentType = HangingSignRenderer.AttachmentType.byBlockState(state);
		return this.hangingSignModels.get(attachmentType);
	}

	@Override
	public void translateSign(PoseStack poseStack, float angle, BlockState state) {
		poseStack.translate(0.5, 0.9375, 0.5);
		poseStack.mulPose(Axis.YP.rotationDegrees(angle));
		poseStack.translate(0.0F, -0.3125F, 0.0F);
	}

	@Override
	public Material getCanvasSignMaterial(@Nullable DyeColor dyeColor) {
		return ModAtlases.getHangingCanvasSignMaterial(dyeColor);
	}

	@Override
	public int getCustomVerticalOffset() {
		return 0;
	}

	@Override
	public Vec3 getTextOffset() {
		return super.getTextOffset();
	}
}
