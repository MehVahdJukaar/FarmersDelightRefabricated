package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.Model;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.SignRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.SpriteGetter;
import net.minecraft.client.resources.model.SpriteId;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CanvasSign;

// Vanilla copy of AbstractSignRenderer with WoodType replaced with DyeColor
public abstract class AbstractCanvasSignRenderer
    implements BlockEntityRenderer<SignBlockEntity, SignRenderState> {
    private static final int OUTLINE_RENDER_DISTANCE = Mth.square(16);
    private final Font font;
    private final SpriteGetter materials;

    public AbstractCanvasSignRenderer(BlockEntityRendererProvider.Context context) {
        this.font = context.font();
        this.materials = context.sprites();
    }

    protected abstract Model.Simple getSignModel(BlockState state);

    protected abstract SpriteId getSignMaterial(DyeColor dyeColor);

    protected abstract float getSignModelRenderScale();

    protected abstract float getSignTextRenderScale();

    protected abstract Vec3 getTextOffset();

    protected abstract void translateSign(PoseStack poseStack, float yRot, BlockState state);

    @Override
    public void submit(SignRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        BlockState blockState = renderState.blockState;
        SignBlock signBlock = (SignBlock)blockState.getBlock();
        DyeColor dye = DyeColor.WHITE;
        if (signBlock instanceof CanvasSign canvasSign) {
            dye = canvasSign.getBackgroundColor();
        }

        Model.Simple simple = this.getSignModel(blockState);
        this.submitSignWithText(renderState, poseStack, blockState, signBlock, dye, simple, renderState.breakProgress, nodeCollector);
    }

    private void submitSignWithText(SignRenderState renderState, PoseStack poseStack, BlockState blockState, SignBlock sign, DyeColor dyeColor, Model.Simple model, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay, SubmitNodeCollector nodeCollector) {
        poseStack.pushPose();
        this.translateSign(poseStack, -sign.getYRotationDegrees(blockState), blockState);
        this.submitSign(poseStack, renderState.lightCoords, dyeColor, model, crumblingOverlay, nodeCollector);
        this.submitSignText(renderState, poseStack, nodeCollector, true);
        this.submitSignText(renderState, poseStack, nodeCollector, false);
        poseStack.popPose();
    }

    protected void submitSign(PoseStack poseStack, int packedLight, DyeColor dyColor, Model.Simple model, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay, SubmitNodeCollector nodeCollector) {
        poseStack.pushPose();
        float scale = this.getSignModelRenderScale();
        poseStack.scale(scale, -scale, -scale);
        SpriteId material = this.getSignMaterial(dyColor);
        var renderType = material.renderType(model::renderType);
        nodeCollector.submitModel(model, Unit.INSTANCE, poseStack, renderType, packedLight, OverlayTexture.NO_OVERLAY, -1, this.materials.get(material), 0, crumblingOverlay);
        poseStack.popPose();
    }

    private void submitSignText(SignRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, boolean isFront) {
        SignText signText = isFront ? renderState.frontText : renderState.backText;
        if (signText != null) {
            poseStack.pushPose();
            this.translateSignText(poseStack, isFront, this.getTextOffset());
            int i = getDarkColor(signText);
            int j = 4 * renderState.textLineHeight / 2;
            FormattedCharSequence[] formattedCharSequences = signText.getRenderMessages(renderState.isTextFilteringEnabled, (component) -> {
                List<FormattedCharSequence> list = this.font.split(component, renderState.maxTextLineWidth);
                return list.isEmpty() ? FormattedCharSequence.EMPTY : (FormattedCharSequence)list.get(0);
            });
            int k;
            boolean bl;
            int l;
            if (signText.hasGlowingText()) {
                k = signText.getColor().getTextColor();
                bl = k == DyeColor.BLACK.getTextColor() || renderState.drawOutline;
                l = 15728880;
            } else {
                k = i;
                bl = false;
                l = renderState.lightCoords;
            }

            for(int m = 0; m < 4; ++m) {
                FormattedCharSequence formattedCharSequence = formattedCharSequences[m];
                float f = (float)(-this.font.width(formattedCharSequence) / 2);
                nodeCollector.submitText(poseStack, f, (float)(m * renderState.textLineHeight - j), formattedCharSequence, false, Font.DisplayMode.POLYGON_OFFSET, l, k, 0, bl ? i : 0);
            }

            poseStack.popPose();
        }
    }

    private void translateSignText(PoseStack poseStack, boolean isFront, Vec3 offset) {
        if (!isFront) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
        }
        float f = 0.015625f * this.getSignTextRenderScale();
        poseStack.translate(offset);
        poseStack.scale(f, -f, f);
    }

    private static boolean isOutlineVisible(BlockPos pos) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer localPlayer = minecraft.player;
        if (localPlayer != null && minecraft.options.getCameraType().isFirstPerson() && localPlayer.isScoping()) {
            return true;
        }
        Entity entity = minecraft.getCameraEntity();
        return entity != null && entity.distanceToSqr(Vec3.atCenterOf(pos)) < (double)OUTLINE_RENDER_DISTANCE;
    }

    public static int getDarkColor(SignText text) {
        int i = text.getColor().getTextColor();
        return i == DyeColor.BLACK.getTextColor() && text.hasGlowingText() ? -988212 : ARGB.scaleRGB(i, 0.4F);
    }

    @Override
    public SignRenderState createRenderState() {
        return new SignRenderState();
    }

    @Override
    public void extractRenderState(SignBlockEntity blockEntity, SignRenderState renderState, float partialTick, Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);
        renderState.maxTextLineWidth = blockEntity.getMaxTextLineWidth();
        renderState.textLineHeight = blockEntity.getTextLineHeight();
        renderState.frontText = blockEntity.getFrontText();
        renderState.backText = blockEntity.getBackText();
        renderState.isTextFilteringEnabled = Minecraft.getInstance().isTextFilteringEnabled();
        renderState.drawOutline = isOutlineVisible(blockEntity.getBlockPos());
    }
}