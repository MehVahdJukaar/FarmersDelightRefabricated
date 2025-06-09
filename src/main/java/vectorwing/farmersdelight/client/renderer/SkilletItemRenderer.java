package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.refabricated.access.SkilletEntityRenderState;

public class SkilletItemRenderer implements SpecialModelRenderer<SkilletItemRenderer.SkilletData> {
    public static final ResourceLocation ID = FarmersDelight.res("skillet");

    public SkilletItemRenderer() {
    }

    @Override
    public void render(@Nullable SkilletItemRenderer.SkilletData patterns, ItemDisplayContext mode, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, boolean hasFoilType) {
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
                var itemRenderer = mc.getItemRenderer();
                itemRenderer.renderStatic(patterns.ingredient, ItemDisplayContext.FIXED, packedLight,
                        packedOverlay, poseStack, buffer, null, 0);
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
        mc.getBlockRenderer().renderSingleBlock(patterns.state, poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();
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

    public static class Unbaked implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> CODEC = MapCodec.unit(new Unbaked());

        @Override
        public SpecialModelRenderer<?> bake(EntityModelSet modelSett) {
            return new SkilletItemRenderer();
        }

        @Override
        public MapCodec<Unbaked> type() {
            return CODEC;
        }
    }

    public static void applyTransform(HumanoidModel<?> model, SkilletEntityRenderState renderState, HumanoidArm arm) {
        long time = renderState.getSkilletFlipTimestamp();
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
