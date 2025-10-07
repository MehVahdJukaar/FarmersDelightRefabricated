package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.renderer.state.StoveRenderState;
import vectorwing.farmersdelight.common.block.StoveBlock;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class StoveRenderer implements BlockEntityRenderer<StoveBlockEntity, StoveRenderState>
{
    private final ItemModelResolver itemModelResolver;
	public StoveRenderer(BlockEntityRendererProvider.Context context) {
        itemModelResolver = context.itemModelResolver();
	}

	@Override
	public void extractRenderState(StoveBlockEntity stoveEntity, StoveRenderState renderState, float partialTick, Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(stoveEntity, renderState, partialTick, cameraPosition, breakProgress);
        renderState.direction = stoveEntity.getBlockState().getValue(StoveBlock.FACING).getOpposite();
        int posLong = (int) stoveEntity.getBlockPos().asLong();

        ItemStackHandler inventory = stoveEntity.getInventory();
		renderState.stoveStacks = new ItemStackRenderState[inventory.getSlotCount()];
		for (int i = 0; i < inventory.getSlotCount(); ++i) {
			ItemStack stoveStack = inventory.getStackInSlot(i);
			if (!stoveStack.isEmpty()) {
				renderState.stoveStacks[i] = new ItemStackRenderState();
				this.itemModelResolver.updateForTopItem(renderState.stoveStacks[i], stoveStack, ItemDisplayContext.FIXED, stoveEntity.getLevel(), null, posLong + i);
			}

		}
	}

	@Override
	public StoveRenderState createRenderState() {
		return new StoveRenderState();
	}

	@Override
	public void submit(StoveRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
		Direction direction = renderState.direction;

		for (int i = 0; i < renderState.stoveStacks.length; ++i) {
			ItemStackRenderState stoveStack = renderState.stoveStacks[i];
			if (stoveStack != null) {
				poseStack.pushPose();

				// Center item above the stove
				poseStack.translate(0.5D, 1.02D, 0.5D);

				// Rotate item to face the stove's front side
				float f = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(f));

				// Rotate item flat on the stove. Use X and Y from now on
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

				// Neatly align items according to their index
				Vec2 itemOffset = StoveBlockEntity.getStoveItemOffset(i);
				poseStack.translate(itemOffset.x, itemOffset.y, 0.0D);

				// Resize the items
				poseStack.scale(0.375F, 0.375F, 0.375F);

                renderState.stoveStacks[i].submit(poseStack, nodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);

				poseStack.popPose();
			}
		}
	}
}