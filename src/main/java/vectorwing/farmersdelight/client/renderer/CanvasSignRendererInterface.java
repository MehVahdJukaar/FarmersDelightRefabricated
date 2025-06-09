package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CanvasSign;
import vectorwing.farmersdelight.common.registry.ModAtlases;

;

public class CanvasSignRendererInterface extends SignRenderer implements ICanvasSignRenderer
{

	private final Models signModels;
	private final Font font;

	public CanvasSignRendererInterface(BlockEntityRendererProvider.Context context) {
		super(context);

		this.signModels = new Models(
				SignRenderer.createSignModel(context.getModelSet(), WoodType.SPRUCE, true),
				SignRenderer.createSignModel(context.getModelSet(), WoodType.SPRUCE, false)
		);
		this.font = context.getFont();
	}

	@Override
	public void render(SignBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, Vec3 cameraPos) {
		BlockState state = blockEntity.getBlockState();
		SignBlock block = (SignBlock) state.getBlock();
		Model model = block instanceof StandingSignBlock ? signModels.standing : signModels.wall;

		DyeColor dye = null;
		if (block instanceof CanvasSign canvasSign) {
			dye = canvasSign.getBackgroundColor();
		}

		renderSignWithText(blockEntity, poseStack, bufferSource, packedLight, packedOverlay, state, block, dye, model);
	}

	@Override
	public void translateSign(PoseStack poseStack, float angle, BlockState state) {
		super.translateSign(poseStack, angle, state);
	}

	public Material getCanvasSignMaterial(@Nullable DyeColor dyeColor) {
		return ModAtlases.getCanvasSignMaterial(dyeColor);
	}

	@Override
	public float getSignModelRenderScale() {
		return super.getSignModelRenderScale();
	}

	@Override
	public float getSignTextRenderScale() {
		return super.getSignTextRenderScale();
	}

	@Override
	public Vec3 getTextOffset() {
		return super.getTextOffset();
	}

	record Models(Model standing, Model wall) {
	}
}
