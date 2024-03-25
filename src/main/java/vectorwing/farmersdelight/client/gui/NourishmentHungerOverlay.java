package vectorwing.farmersdelight.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.Random;

/**
 * Credits to squeek502 (AppleSkin) for the implementation reference!
 * https://www.curseforge.com/minecraft/mc-mods/appleskin
 */

public class NourishmentHungerOverlay {
    public static int foodIconsOffset;
    private static final ResourceLocation MOD_ICONS_TEXTURE = new ResourceLocation(FarmersDelight.MODID, "textures/gui/fd_icons.png");

    public static void init() {
        HudRenderCallback.EVENT.register(NourishmentHungerOverlay::onRenderGuiOverlayPost);
    }

    private static void onRenderGuiOverlayPost(GuiGraphics graphics, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        var gui = mc.gui;
        if (!mc.options.hideGui && mc.gameMode.canHurtPlayer()) {
            renderComfortOverlay(mc, gui, graphics);
        }
    }

    public static void renderComfortOverlay(Minecraft mc, Gui gui, GuiGraphics graphics) {
        boolean isMounted = mc.player != null && mc.player.getVehicle() instanceof LivingEntity;
        if (!isMounted && !mc.options.hideGui && mc.gameMode.canHurtPlayer()) {
            renderNourishmentOverlay(gui, graphics);
        }
    }

    public static void renderNourishmentOverlay(Gui gui, GuiGraphics graphics) {
        if (!Configuration.NOURISHED_HUNGER_OVERLAY.get()) {
            return;
        }

        foodIconsOffset = 49;
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player == null) {
            return;
        }

        FoodData stats = player.getFoodData();
        int top = minecraft.getWindow().getGuiScaledHeight() - foodIconsOffset + 10;
        int left = minecraft.getWindow().getGuiScaledWidth() / 2 + 91;

        boolean isPlayerHealingWithSaturation =
                player.level().getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION)
                        && player.isHurt()
                        && stats.getFoodLevel() >= 18;

        if (player.getEffect(ModEffects.NOURISHMENT.get()) != null) {
            drawNourishmentOverlay(stats, minecraft, graphics, left, top, isPlayerHealingWithSaturation);
        }
    }

    public static void drawNourishmentOverlay(FoodData stats, Minecraft mc, GuiGraphics graphics, int left, int top, boolean naturalHealing) {
        float saturation = stats.getSaturationLevel();
        int foodLevel = stats.getFoodLevel();
        int ticks = mc.gui.getGuiTicks();
        Random rand = new Random();
        rand.setSeed(ticks * 312871);

//		RenderSystem.setShaderTexture(0, MOD_ICONS_TEXTURE);
        RenderSystem.enableBlend();

        for (int j = 0; j < 10; ++j) {
            int x = left - j * 8 - 9;
            int y = top;

            if (saturation <= 0.0F && ticks % (foodLevel * 3 + 1) == 0) {
                y = top + (rand.nextInt(3) - 1);
            }

            // Background texture
            graphics.blit(MOD_ICONS_TEXTURE, x, y, 0, 0, 9, 9);

            float effectiveHungerOfBar = (stats.getFoodLevel()) / 2.0F - j;
            int naturalHealingOffset = naturalHealing ? 18 : 0;

            // Gilded hunger icons
            if (effectiveHungerOfBar >= 1)
                graphics.blit(MOD_ICONS_TEXTURE, x, y, 18 + naturalHealingOffset, 0, 9, 9);
            else if (effectiveHungerOfBar >= .5)
                graphics.blit(MOD_ICONS_TEXTURE, x, y, 9 + naturalHealingOffset, 0, 9, 9);
        }

        RenderSystem.disableBlend();
//		RenderSystem.setShaderTexture(0, Gui.GUI_ICONS_LOCATION);
    }
}
