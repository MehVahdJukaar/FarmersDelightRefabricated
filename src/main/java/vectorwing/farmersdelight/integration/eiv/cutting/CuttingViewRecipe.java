package vectorwing.farmersdelight.integration.eiv.cutting;

import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.api.recipe.IEivViewRecipe;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import de.crafty.eiv.common.recipe.inventory.RecipeViewScreen;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.*;

public class CuttingViewRecipe implements IEivViewRecipe {

    private final LinkedHashMap<SlotContent, Float> rollableResults;
    private SlotContent ingredient;
    private List<SlotContent> results;
    private SlotContent tool;

    public CuttingViewRecipe(CuttingServerRecipe shapelessRecipe) {
        this.results = new ArrayList<>();
        this.rollableResults = new LinkedHashMap<>();


        shapelessRecipe.getResults().forEach(ingredient -> {
            this.results.add(SlotContent.of(ingredient));
        });

        shapelessRecipe.getRollableResults().forEach(ingredient -> {
            this.rollableResults.put(SlotContent.of(ingredient.stack()), ingredient.chance());
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
        int i = 0;
        for (Map.Entry<SlotContent, Float> result : rollableResults.entrySet()) {
            slotFillContext.bindSlot(i+2, result.getKey());
            var chance = result.getValue();
            if (chance != 1.0) {
                slotFillContext.addAdditionalStackModifier(i+2, (stack, tooltip) -> {
                    tooltip.add(Component.literal("Production chance: %s%%".formatted(chance * 100)).setStyle(Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.GOLD))));
                });
            }
            i++;
        }
    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int x = 75;
        int y = 20;
        int slot = 0;
        for (Map.Entry<SlotContent, Float> result : rollableResults.entrySet()) {
            int offset = 0;
            if (result.getValue() != 1.0) {
                offset = 18;
            }
            guiGraphics.blit(RenderType::guiTextured, FarmersDelight.res("textures/gui/jei/cutting_board.png"), x-1,y-1,offset,58, 18,18, 256,256);
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