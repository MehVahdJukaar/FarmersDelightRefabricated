package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

public class SkilletItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
	public SkilletItemRenderer() {
	}

	@Override
	public void render(ItemStack stack, ItemDisplayContext mode, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		//render block
		poseStack.pushPose();
		BlockItem item = ((BlockItem) stack.getItem());
		BlockState state = item.getBlock().defaultBlockState();
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, buffer, packedLight, packedOverlay);
		poseStack.popPose();

		ItemStackWrapper stackWrapper = stack.getOrDefault(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY);
		ItemStack ingredientStack = stackWrapper.getStack();

		if (!ingredientStack.isEmpty()) {
			poseStack.pushPose();
			poseStack.translate(0.5, 1 / 16f, 0.5);

			long gameTime = Minecraft.getInstance().level.getGameTime();
			if (stack.has(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get())) {
				long time = stack.get(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get());
				float partialTicks = Minecraft.getInstance().getTimer().getRealtimeDeltaTicks();
				float animation = ((gameTime - time) + partialTicks) / SkilletItem.FLIP_TIME;
				float maxH = 1;
				poseStack.translate(0, maxH * Mth.sin(animation * Mth.PI), 0);
				poseStack.mulPose(Axis.XP.rotationDegrees(360 * animation));
			}

			poseStack.mulPose(Axis.XP.rotationDegrees(90));
			poseStack.scale(0.5F, 0.5F, 0.5F);

			var itemRenderer = Minecraft.getInstance().getItemRenderer();
			itemRenderer.renderStatic(ingredientStack, ItemDisplayContext.FIXED, packedLight,
					packedOverlay, poseStack, buffer, null, 0);

			poseStack.popPose();
		}
	}
}
