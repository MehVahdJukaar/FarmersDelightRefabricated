package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.*;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.cuboid.ItemTransforms;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.mixin.refabricated.CuboidItemModelWrapperAccessor;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.refabricated.duck.SkilletEntityRenderState;

import java.util.function.Consumer;

public class SkilletFlipItemRenderer implements ItemModel {
    public static final Identifier ID = FarmersDelight.id("skillet_flip");
    private static final SpecialRenderer SPECIAL_RENDERER = new SpecialRenderer();

    private final CuboidItemModelWrapper model;
    private final ModelRenderProperties properties;

    public SkilletFlipItemRenderer(CuboidItemModelWrapper model,
                                   ModelRenderProperties properties) {
        this.model = model;
        this.properties = properties;
    }

    @Override
    public void update(@NonNull ItemStackRenderState output,
                       @NonNull ItemStack item,
                       @NonNull ItemModelResolver resolver,
                       @NonNull ItemDisplayContext displayContext,
                       @Nullable ClientLevel level,
                       @Nullable ItemOwner owner,
                       int seed) {
        output.appendModelIdentityElement(this);
        ItemStackRenderState.LayerRenderState layer = output.newLayer();

        Argument argument = SPECIAL_RENDERER.extractArgument(item);
        properties.applyToLayer(layer, displayContext);
        if (argument != null) {
            model.update(
                    argument.skillet,
                    item,
                    resolver,
                    ItemDisplayContext.NONE,
                    level,
                    owner,
                    seed
            );
            resolver.updateForTopItem(
                    argument.ingredient,
                    item.getOrDefault(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY).getStack(),
                    ItemDisplayContext.FIXED,
                    level,
                    owner,
                    seed
            );
            argument.displayContext = displayContext;
        }
        layer.setupSpecialModel(SPECIAL_RENDERER, argument);
        if (argument != null) {
            output.appendModelIdentityElement(argument);
        }
    }

    public static class SpecialRenderer implements SpecialModelRenderer<Argument> {
        @Override
        public void submit(@Nullable Argument argument,
                           @NonNull PoseStack poseStack,
                           @NonNull SubmitNodeCollector submitNodeCollector,
                           int lightCoords,
                           int overlayCoords,
                           boolean hasFoil,
                           int outlineColor) {
            if (argument == null)
                return;

            Minecraft mc = Minecraft.getInstance();
            ClientLevel level = mc.level;

            float animation = 0;

            if (!argument.ingredient.isEmpty() && level != null) {
                poseStack.pushPose();
                poseStack.translate(0.5, 1 / 16f, 0.5);

                long gameTime = level.getGameTime();
                if (argument.flipTimestamp != -1 && argument.displayContext != ItemDisplayContext.GUI) {
                    float partialTicks = mc.getDeltaTracker().getGameTimeDeltaPartialTick(false);
                    animation = ((gameTime - argument.flipTimestamp) + partialTicks) / SkilletItem.FLIP_TIME;
                    animation = Mth.clamp(animation, 0, 1);
                    float maxH = 0.4F;
                    poseStack.translate(0, maxH * Mth.sin(animation * Mth.PI), 0);
                    float rotationAnimation = argument.flipped ? animation + 1.0F : animation;
                    poseStack.mulPose(Axis.XP.rotationDegrees(180 * rotationAnimation));
                } else {
                    poseStack.mulPose(Axis.XP.rotationDegrees(argument.flipped ? 180 : 0));
                }

                poseStack.mulPose(Axis.XP.rotationDegrees(90));
                poseStack.scale(0.5F, 0.5F, 0.5F);

                argument.ingredient.submit(poseStack, submitNodeCollector, lightCoords, overlayCoords, outlineColor);

                poseStack.popPose();
            }

            poseStack.pushPose();
            poseStack.translate(0.5, 0.5, 0.5);

            if (animation != 0 && argument.displayContext.firstPerson()) {
                poseStack.translate(0, 0, 1);
                poseStack.mulPose(Axis.XN.rotationDegrees(Mth.sin(animation * Mth.TWO_PI) * 15));
                poseStack.translate(0F, 0, -1);
                poseStack.translate(0, 0, -Mth.sin(animation * Mth.PI) * 0.2);
            }

            argument.skillet.submit(poseStack, submitNodeCollector, lightCoords, overlayCoords, outlineColor);

            poseStack.popPose();
        }

        @Override
        public void getExtents(@NonNull Consumer<Vector3fc> output) {

        }

        @Override
        public @Nullable Argument extractArgument(@NonNull ItemStack stack) {
            Argument argument = new Argument();
            argument.flipTimestamp = stack.getOrDefault(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get(), -1L);
            argument.flipped = stack.getOrDefault(ModDataComponents.SKILLET_FLIPPED.get(), false);
            return argument;
        }
    }

    public static class Argument {
        public ItemStackRenderState skillet = new ItemStackRenderState();
        public ItemStackRenderState ingredient = new ItemStackRenderState();

        public long flipTimestamp;
        public boolean flipped;

        public ItemDisplayContext displayContext;
    }

    public record Unbaked(CuboidItemModelWrapper.Unbaked model) implements ItemModel.Unbaked {
        public static MapCodec<Unbaked> CODEC = CuboidItemModelWrapper.Unbaked.MAP_CODEC.xmap(
                Unbaked::new,
                Unbaked::model
        );

        @Override
        public ItemModel bake(@NonNull BakingContext context, @NonNull Matrix4fc transformation) {
            CuboidItemModelWrapper baked = (CuboidItemModelWrapper) this.model.bake(context, new Matrix4f());

            ModelRenderProperties properties = ((CuboidItemModelWrapperAccessor)baked).fdrf$getProperties();
            ((CuboidItemModelWrapperAccessor)baked).fdrf$setProperties(new ModelRenderProperties(
                    properties.usesBlockLight(),
                    properties.particleMaterial(),
                    ItemTransforms.NO_TRANSFORMS
            ));

            return new SkilletFlipItemRenderer(
                    baked,
                    properties
            );
        }

        @Override
        public void resolveDependencies(@NonNull Resolver resolver) {
            model.resolveDependencies(resolver);
        }

        @Override
        public MapCodec<? extends ItemModel.Unbaked> type() {
            return CODEC;
        }
    }

    public static void applyTransform(ClientLevel level, HumanoidModel<?> model, SkilletEntityRenderState renderState, HumanoidArm arm) {
        long time = renderState.fdrf$getSkilletFlipTimestamp();
        if (time == -1)
            return;

        float partialTicks = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(false);
        float animation = ((level.getGameTime() - time) + partialTicks) / SkilletItem.FLIP_TIME;
        animation = Mth.clamp(animation, 0, 1);

        if (arm == HumanoidArm.LEFT) {
            model.leftArm.xRot = (-Mth.sin(animation * Mth.TWO_PI) * 15 - 20) * (float) (Math.PI / 180.0);
        } else {
            model.rightArm.xRot = (-Mth.sin(animation * Mth.TWO_PI) * 15 - 20) * (float) (Math.PI / 180.0);
        }
    }
}
