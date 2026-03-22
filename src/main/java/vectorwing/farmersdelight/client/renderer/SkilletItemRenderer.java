package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.refabricated.duck.SkilletEntityRenderState;

import java.util.function.Consumer;

public class SkilletItemRenderer implements SpecialModelRenderer<SkilletItemRenderer.SkilletData> {
    public static final Identifier ID = FarmersDelight.res("skillet");

    public SkilletItemRenderer() {
    }

	@Override
	public void submit(SkilletItemRenderer.@Nullable SkilletData patterns, PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor) {
		// TODO 1.21.10
		if (patterns == null)
			return;

		Minecraft mc = Minecraft.getInstance();

		float animation = 0;

		if (!patterns.ingredient.isEmpty() && mc.level != null) {
			poseStack.pushPose();
			poseStack.translate(0.5, 1 / 16f, 0.5);

			long gameTime = mc.level.getGameTime();
			if (patterns.skilletFlipTimestamp != -1 && mode != ItemDisplayContext.GUI) {
				float partialTicks = mc.getDeltaTracker().getGameTimeDeltaPartialTick(false);
				animation = ((gameTime - patterns.skilletFlipTimestamp) + partialTicks) / SkilletItem.FLIP_TIME;
				animation = Mth.clamp(animation, 0, 1);
				float maxH = 0.4F;
				poseStack.translate(0, maxH * Mth.sin(animation * Mth.PI), 0);
				float rotationAnimation = patterns.flipped ? animation + 1.0F : animation;
				poseStack.mulPose(Axis.XP.rotationDegrees(180 * rotationAnimation));
			} else {
				poseStack.mulPose(Axis.XP.rotationDegrees(patterns.flipped ? 180 : 0));
			}

			poseStack.mulPose(Axis.XP.rotationDegrees(90));
			poseStack.scale(0.5F, 0.5F, 0.5F);

			if (mode != ItemDisplayContext.GUI) {
				ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
				Minecraft.getInstance().getItemModelResolver().updateForTopItem(itemStackRenderState, patterns.ingredient, ItemDisplayContext.FIXED, Minecraft.getInstance().level, null, 0);
				itemStackRenderState.submit(poseStack, nodeCollector, packedLight, packedOverlay, outlineColor);
			}

			poseStack.popPose();
		}

		poseStack.pushPose();

		if (animation != 0 && mode.firstPerson()) {
			poseStack.translate(0, 0, 1);
			poseStack.mulPose(Axis.XN.rotationDegrees(Mth.sin(animation * Mth.TWO_PI) * 15));
			poseStack.translate(0F, 0, -1);
			poseStack.translate(0, 0, -Mth.sin(animation * Mth.PI) * 0.2);
		}
		nodeCollector.submitBlock(poseStack, patterns.state, packedLight, packedOverlay, outlineColor);

		poseStack.popPose();
	}

	// TODO: See if leaving this empty is okay.
    @Override
    public void getExtents(Consumer<Vector3fc> consumer) {
    }

    @Override
    public @Nullable SkilletData extractArgument(ItemStack stack) {
        if (!(stack.getItem() instanceof BlockItem blockItem))
            return null;

        BlockState state = blockItem.getBlock().defaultBlockState();
        ItemStackWrapper stackWrapper = stack.getOrDefault(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY);

        return new SkilletData(state,
                stackWrapper.getStack(),
                stack.getOrDefault(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get(), -1L),
                stack.getOrDefault(ModDataComponents.SKILLET_FLIPPED.get(), false));
    }

    public static class Unbaked implements SpecialModelRenderer.Unbaked<SkilletData> {
        public static final MapCodec<SpecialModelRenderer.Unbaked<SkilletData>> CODEC = MapCodec.unit(new Unbaked());

        @Override
        public @Nullable SpecialModelRenderer<SkilletData> bake(BakingContext context) {
            return new SkilletItemRenderer();
        }

        @Override
        public MapCodec<? extends SpecialModelRenderer.Unbaked<SkilletData>> type() {
            return CODEC;
        }
    }

    public static void applyTransform(HumanoidModel<?> model, SkilletEntityRenderState renderState, HumanoidArm arm) {
        long time = renderState.fdrf$getSkilletFlipTimestamp();
        if (time == -1)
            return;
        float partialTicks = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(false);
        float animation = ((Minecraft.getInstance().level.getGameTime() - time) + partialTicks) / SkilletItem.FLIP_TIME;
        animation = Mth.clamp(animation, 0, 1);

        if (arm == HumanoidArm.LEFT) {
            model.leftArm.xRot = (-Mth.sin(animation * Mth.TWO_PI) * 15 - 20) * (float) (Math.PI / 180.0);
        } else {
            model.rightArm.xRot = (-Mth.sin(animation * Mth.TWO_PI) * 15 - 20) * (float) (Math.PI / 180.0);
        }
    }

    public record SkilletData(BlockState state, ItemStack ingredient, long skilletFlipTimestamp, boolean flipped) {}
}
