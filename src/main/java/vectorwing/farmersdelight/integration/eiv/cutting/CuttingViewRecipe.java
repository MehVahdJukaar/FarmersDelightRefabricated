package vectorwing.farmersdelight.integration.eiv.cutting;

import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.api.recipe.IEivViewRecipe;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import de.crafty.eiv.common.recipe.inventory.RecipeViewScreen;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CuttingViewRecipe implements IEivViewRecipe {

    private SlotContent ingredient;
    private List<SlotContent> results;
    private SlotContent tool;

    public CuttingViewRecipe(CuttingServerRecipe shapelessRecipe) {
        this.results = new ArrayList<>();

        shapelessRecipe.getResults().forEach(ingredient -> {
            this.results.add(SlotContent.of(ingredient));
        });

        this.ingredient = SlotContent.of(shapelessRecipe.getIngredient());
        this.tool = SlotContent.of(shapelessRecipe.getTool());
    }

    @Override
    public IEivRecipeViewType getViewType() {
        return CuttingViewType.INSTANCE;
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, ingredient);
        slotFillContext.bindSlot(1, tool);
        for (int i = 0; i < results.size(); i++) {
            slotFillContext.bindSlot(i+2, results.get(i));
        }
    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int x = 75;
        int y = 20;
        int slot = 0;
        for (SlotContent result : results) {
            guiGraphics.blit(RenderType::guiTextured, FarmersDelight.res("textures/gui/jei/cutting_board.png"), x-1,y-1,0,58, 18,18, 256,256);
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