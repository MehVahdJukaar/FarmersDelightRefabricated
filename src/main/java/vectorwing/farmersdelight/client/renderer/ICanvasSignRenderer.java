package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.Model;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ICanvasSignRenderer {
    int OUTLINE_RENDER_DISTANCE = Mth.square(16);
    Vec3 TEXT_OFFSET = new Vec3(0.0, -0.32F, 0.073F);

    default void renderSignWithText(SignBlockEntity signBlockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, BlockState state, SignBlock block, @Nullable DyeColor dye, Model model) {
        poseStack.pushPose();
        translateSign(poseStack, -block.getYRotationDegrees(state), state);
        renderSign(poseStack, bufferSource, packedLight, packedOverlay, dye, model);
        renderSignText(signBlockEntity.getBlockPos(), signBlockEntity.getFrontText(), poseStack, bufferSource, packedLight, signBlockEntity.getTextLineHeight(), signBlockEntity.getMaxTextLineWidth(), true);
        renderSignText(signBlockEntity.getBlockPos(), signBlockEntity.getBackText(), poseStack, bufferSource, packedLight, signBlockEntity.getTextLineHeight(), signBlockEntity.getMaxTextLineWidth(), false);
        poseStack.popPose();
    }

    default void translateSign(PoseStack poseStack, float angle, BlockState state) {
        poseStack.translate(0.5F, 0.75F * this.getSignModelRenderScale(), 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(angle));
        if (!(state.getBlock() instanceof StandingSignBlock)) {
            poseStack.translate(0.0F, -0.3125F, -0.4375F);
        }
    }

    default void renderSign(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, @Nullable DyeColor dye, Model model) {
        poseStack.pushPose();
        float rootScale = getSignModelRenderScale();
        poseStack.scale(rootScale, -rootScale, -rootScale);
        Material material = getCanvasSignMaterial(dye);
        VertexConsumer vertexConsumer = material.buffer(bufferSource, model::renderType);
        this.renderSignModel(poseStack, packedLight, packedOverlay, model, vertexConsumer);
        poseStack.popPose();
    }

    default void renderSignModel(PoseStack poseStack, int packedLight, int packedOverlay, Model model, VertexConsumer vertexConsumer) {
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    default void renderSignText(BlockPos pos, SignText text, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textLineHeight, int maxTextLineWidth, boolean isFrontText) {
        poseStack.pushPose();
        translateSignText(poseStack, isFrontText, getTextOffset());

        FormattedCharSequence[] formattedCharSequenceList = text.getRenderMessages(Minecraft.getInstance().isTextFilteringEnabled(), (component) -> {
            List<FormattedCharSequence> list = Minecraft.getInstance().font.split(component, maxTextLineWidth);
            return list.isEmpty() ? FormattedCharSequence.EMPTY : list.get(0);
        });

        int darkColor;
        int baseColor;
        boolean hasOutline;
        int light;
        if (text.hasGlowingText()) {
            darkColor = getDarkColor(text, true);
            baseColor = text.getColor().getTextColor();
            hasOutline = isOutlineVisible(pos, baseColor);
            light = 15728880;
        } else {
            darkColor = getDarkColor(text, false);
            baseColor = darkColor;
            hasOutline = false;
            light = packedLight;
        }

        int verticalOffset = 2 * textLineHeight + this.getCustomVerticalOffset();

        for (int i = 0; i < 4; ++i) {
            FormattedCharSequence formattedCharSequence = formattedCharSequenceList[i];
            float x = (float) (-Minecraft.getInstance().font.width(formattedCharSequence) / 2);
            float y = i * textLineHeight - verticalOffset;
            if (hasOutline) {
                Minecraft.getInstance().font.drawInBatch8xOutline(formattedCharSequence, x, y, baseColor, darkColor, poseStack.last().pose(), bufferSource, light);
            } else {
                Minecraft.getInstance().font.drawInBatch(formattedCharSequence, x, y, baseColor, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.POLYGON_OFFSET, 0, light);
            }
        }

        poseStack.popPose();
    }

    default void translateSignText(PoseStack poseStack, boolean isFrontText, Vec3 pos) {
        if (!isFrontText) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        }

        float textScale = 0.015625F * this.getSignTextRenderScale();
        poseStack.translate(pos.x, pos.y, pos.z);
        poseStack.scale(textScale, -textScale, textScale);
    }

    static boolean isOutlineVisible(BlockPos pos, int textColor) {
        if (textColor == DyeColor.BLACK.getTextColor()) {
            return true;
        } else {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer localPlayer = minecraft.player;
            if (localPlayer != null && minecraft.options.getCameraType().isFirstPerson() && localPlayer.isScoping()) {
                return true;
            } else {
                Entity entity = minecraft.getCameraEntity();
                return entity != null && entity.distanceToSqr(Vec3.atCenterOf(pos)) < (double) OUTLINE_RENDER_DISTANCE;
            }
        }
    }

    static int getDarkColor(SignText text, boolean isOutlineVisible) {
        int textColor = text.getColor().getTextColor();
        if (textColor == DyeColor.BLACK.getTextColor() && text.hasGlowingText()) {
            return -988212;
        } else {
            double brightness = isOutlineVisible ? 0.4D : 0.6D;
            int red = (int) ((double) ARGB.red(textColor) * brightness);
            int green = (int) ((double) ARGB.green(textColor) * brightness);
            int blue = (int) ((double) ARGB.blue(textColor) * brightness);
            return ARGB.color(255, red, green, blue);
        }
    }

    default int getCustomVerticalOffset() {
        return -1;
    }

    Material getCanvasSignMaterial(@Nullable DyeColor dyeColor);

    float getSignModelRenderScale();

    float getSignTextRenderScale();

    Vec3 getTextOffset();

}
