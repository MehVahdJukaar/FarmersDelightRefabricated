package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import vectorwing.farmersdelight.common.registry.ModAtlases;

public class CanvasSignRenderer extends AbstractCanvasSignRenderer
{
	public static final float RENDER_SCALE = 0.6666667f;
	private static final Vec3 TEXT_OFFSET = new Vec3(0.0, 0.3333333432674408, 0.046666666865348816);
	private final Models signModels;

	public CanvasSignRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
		this.signModels = new Models(SignRenderer.createSignModel(context.entityModelSet(), WoodType.SPRUCE, true), SignRenderer.createSignModel(context.entityModelSet(), WoodType.SPRUCE, false));
	}

	@Override
	protected Model.Simple getSignModel(BlockState state) {
		return state.getBlock() instanceof StandingSignBlock ? signModels.standing() : signModels.wall();
	}

	@Override
	protected Material getSignMaterial(DyeColor dyeColor) {
		return ModAtlases.getCanvasSignMaterial(dyeColor);
	}

	public float getSignModelRenderScale() {
		return RENDER_SCALE;
	}

	public float getSignTextRenderScale() {
		return RENDER_SCALE;
	}

	private static void translateBase(PoseStack poseStack, float yRot) {
		poseStack.translate(0.5f, 0.5f, 0.5f);
		poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
	}

	@Override
	protected Vec3 getTextOffset() {
		return TEXT_OFFSET;
	}

	@Override
	protected void translateSign(PoseStack poseStack, float yRot, BlockState state) {
		translateBase(poseStack, yRot);
		if (!(state.getBlock() instanceof StandingSignBlock)) {
			poseStack.translate(0.0f, -0.3125f, -0.4375f);
		}
	}

	record Models(Model.Simple standing, Model.Simple wall) {
	}
}
