package vectorwing.farmersdelight.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.Random;

public class ComfortHealthOverlay {
    protected static int healthIconsOffset;
    private static final ResourceLocation MOD_ICONS_TEXTURE = new ResourceLocation(FarmersDelight.MODID, "textures/gui/fd_icons.png");

    public static void init() {
        HudRenderCallback.EVENT.register(ComfortHealthOverlay::onRenderGuiOverlayPost);
    }

    private static void onRenderGuiOverlayPost(GuiGraphics graphics, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        var gui = mc.gui;
        if (!mc.options.hideGui && mc.gameMode.canHurtPlayer()) {
            renderComfortOverlay(mc, gui, graphics);
        }
    }

    public static void renderComfortOverlay(Minecraft mc, Gui gui, GuiGraphics graphics) {
        if (!Configuration.COMFORT_HEALTH_OVERLAY.get()) {
            return;
        }

        healthIconsOffset = 49; //leftHeight... no equivalent stuff here. will break when mre hearts are displayed
        Player player = mc.player;

        if (player == null) {
            return;
        }

        FoodData stats = player.getFoodData();
        int top = mc.getWindow().getGuiScaledHeight() - healthIconsOffset + 10;
        int left = mc.getWindow().getGuiScaledWidth() / 2 - 91;

        boolean isPlayerEligibleForComfort = stats.getSaturationLevel() == 0.0F
                && player.isHurt()
                && !player.hasEffect(MobEffects.REGENERATION);

        if (player.getEffect(ModEffects.COMFORT.get()) != null && isPlayerEligibleForComfort) {
            drawComfortOverlay(player, mc, graphics, left, top);
        }
    }

    public static void drawComfortOverlay(Player player, Minecraft minecraft, GuiGraphics graphics, int left, int top) {
        int ticks = minecraft.gui.getGuiTicks();
        Random rand = new Random();
        rand.setSeed( (ticks * 312871));

        int health = Mth.ceil(player.getHealth());
        float absorb = Mth.ceil(player.getAbsorptionAmount());
        AttributeInstance attrMaxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        float healthMax = (float) attrMaxHealth.getValue();

        int regen = -1;
        if (player.hasEffect(MobEffects.REGENERATION)) regen = ticks % 25;

        int healthRows = Mth.ceil((healthMax + absorb) / 2.0F / 10.0F);
        int rowHeight = Math.max(10 - (healthRows - 2), 3);

        int comfortSheen = ticks % 50;
        int comfortHeartFrame = comfortSheen % 2;
        int[] textureWidth = {5, 9};

        RenderSystem.setShaderTexture(0, MOD_ICONS_TEXTURE);
        RenderSystem.enableBlend();

        int healthMaxSingleRow = Mth.ceil(Math.min(healthMax, 20) / 2.0F);
        int leftHeightOffset = ((healthRows - 1) * rowHeight); // This keeps the overlay on the bottommost row of hearts

        for (int i = 0; i < healthMaxSingleRow; ++i) {
            int column = i % 10;
            int x = left + column * 8;
            int y = top + leftHeightOffset;

            if (health <= 4) y += rand.nextInt(2);
            if (i == regen) y -= 2;

            if (column == comfortSheen / 2) {
                graphics.blit(MOD_ICONS_TEXTURE, x, y, 0, 9, textureWidth[comfortHeartFrame], 9);
            }
            if (column == (comfortSheen / 2) - 1 && comfortHeartFrame == 0) {
                graphics.blit(MOD_ICONS_TEXTURE, x + 5, y, 5, 9, 4, 9);
            }
        }

        RenderSystem.disableBlend();
    }
}
