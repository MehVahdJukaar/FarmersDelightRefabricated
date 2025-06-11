package vectorwing.farmersdelight.integration.eiv.cutting;

import com.mojang.datafixers.util.Pair;
import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.api.recipe.IEivViewRecipe;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import de.crafty.eiv.common.recipe.inventory.RecipeViewScreen;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import me.shedaniel.math.Point;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CuttingViewRecipe implements IEivViewRecipe {

    private SlotContent ingredient;
    private List<SlotContent> results;
    private SlotContent tool;
    private List<ChanceResult> rollableResults;

    public CuttingViewRecipe(CuttingServerRecipe shapelessRecipe) {
        this.results = new ArrayList<>();

        shapelessRecipe.getResults().forEach(ingredient -> {
            this.results.add(SlotContent.of(ingredient));
        });

        this.ingredient = SlotContent.of(shapelessRecipe.getIngredient());
        this.tool = SlotContent.of(shapelessRecipe.getTool());
        this.rollableResults = shapelessRecipe.getRollableResults();
    }

    @Override
    public IEivRecipeViewType getViewType() {
        return CuttingViewType.INSTANCE;
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {

        slotFillContext.bindSlot(0, ingredient);
        slotFillContext.bindSlot(1, tool);

    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int x = 75;
        int y = 20;
        int slot = 0;
        for (SlotContent result : results) {
            guiGraphics.blit(RenderType::guiTextured, FarmersDelight.res("textures/gui/jei/cutting_board.png"), x-1,y-1,0,58, 18,18, 256,256);
            guiGraphics.renderItem(result.getByIndex(0), x, y);
            if (ClientRenderUtils.isCursorInsideBounds(x, y, 18, 18, mouseX, mouseY)) {
                guiGraphics.renderTooltip(Minecraft.getInstance().font, result.getByIndex(0), mouseX, mouseY);
            }
            slot++;
            if (slot == 2) {
                y+=18;
                x-=18;
                slot = 0;
            } else {
                x += 18;
            }
        }
    }

    @Override
    public List<SlotContent> getIngredients() {
        return Collections.singletonList(this.ingredient);
    }

    @Override
    public List<SlotContent> getResults() {
        return this.results;
    }

}