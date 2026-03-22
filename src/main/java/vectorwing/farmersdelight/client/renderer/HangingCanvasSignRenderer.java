package vectorwing.farmersdelight.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.state.HangingSignRenderState;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.HangingSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import vectorwing.farmersdelight.common.registry.ModAtlases;

import java.util.Map;
import java.util.stream.Stream;

public class HangingCanvasSignRenderer extends AbstractCanvasSignRenderer
{
	private static final Vec3 TEXT_OFFSET = new Vec3(0.0, -0.32f, 0.073f);
	private final Map<HangingSignBlock.Attachment, Model.Simple> hangingSignModels;

	public HangingCanvasSignRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
		this.hangingSignModels = Stream.of(HangingSignBlock.Attachment.values())
                .collect(ImmutableMap.toImmutableMap(attachmentType -> attachmentType,
                        (attachmentType) -> HangingSignRenderer.createSignModel(context.entityModelSet(), WoodType.SPRUCE, attachmentType)));
	}

	@Override
	protected Model.Simple getSignModel(BlockState state) {
		HangingSignBlock.Attachment attachmentType = HangingSignBlock.Attachment.byBlockState(state);
		return this.hangingSignModels.get(attachmentType);
	}

	@Override
	protected SpriteId getSignMaterial(DyeColor dyeColor) {
		return ModAtlases.getHangingCanvasSignMaterial(dyeColor);
	}

	public float getSignModelRenderScale() {
		return 1.0F;
	}

	public float getSignTextRenderScale() {
		return 0.8F;
	}

	@Override
	protected Vec3 getTextOffset() {
		return TEXT_OFFSET;
	}

	@Override
	protected void translateSign(PoseStack poseStack, float yRot, BlockState state) {
		poseStack.translate(0.5, 0.9375, 0.5);
		poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
		poseStack.translate(0.0F, -0.3125F, 0.0F);
	}

}
