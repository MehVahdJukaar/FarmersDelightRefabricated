package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.block.StoveBlock;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

import java.util.Random;

public class SkilletRenderer implements BlockEntityRenderer<SkilletBlockEntity> {
    private final Random random = new Random();

    public SkilletRenderer(BlockEntityRendererProvider.Context context) {
    }

	@Override
	public void render(SkilletBlockEntity skillet, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		Direction direction = skillet.getBlockState().getValue(StoveBlock.FACING);
		ItemStackHandler inventory = skillet.getInventory();
		int posLong = (int) skillet.getBlockPos().asLong();

        ItemStack stack = inventory.getStackInSlot(0);
        int seed = stack.isEmpty() ? 187 : Item.getId(stack.getItem()) + stack.getDamageValue();
        this.random.setSeed(seed);

        if (!stack.isEmpty()) {
            int itemRenderCount = this.getModelCount(stack);
            for (int i = 0; i < itemRenderCount; i++) {
                poseStack.pushPose();

                // Stack up items in the skillet, with a slight offset per item
                float xOffset = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
                float zOffset = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
                poseStack.translate(0.5D + xOffset, 0.1D + 0.03 * (i + 1), 0.5D + zOffset);

                // Rotate item to face the skillet's front side
                float degrees = -direction.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(degrees));

                // epic flip last item
                if(i == itemRenderCount - 1) {
                    long gameTime = Minecraft.getInstance().level.getGameTime();
                    long time = skillet.lastFlippedTime;
                    float animation = ((gameTime - time) + partialTicks) / SkilletItem.FLIP_TIME;
                    if (animation < 1) {
                        float maxH = 0.5f;
                        poseStack.translate(0, maxH * Mth.sin(animation * Mth.PI), 0);
                        poseStack.mulPose(Axis.XP.rotationDegrees(360 * animation));
                    }
                }


				if (skillet.getLevel() != null)
					Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, skillet.getLevel(), posLong);
				poseStack.popPose();
			}
		}
	}

	protected int getModelCount(ItemStack stack) {
		int modelCount = 1;

		if (stack.getCount() > 1) {
			modelCount += Mth.ceil(((float) stack.getCount() / stack.getMaxStackSize()) * 4);
		}

		return modelCount;
	}
}
