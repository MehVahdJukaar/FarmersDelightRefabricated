package vectorwing.farmersdelight.integration.rrv.cutting;

import cc.cassian.rrv.api.recipe.ReliableClientRecipe;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.*;

public class CuttingViewRecipe implements ReliableClientRecipe {

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
    public ReliableClientRecipeType getViewType() {
        return CuttingViewType.INSTANCE;
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        int i = 0;
        slotFillContext.bindSlot(i, ingredient);
        i++;
        slotFillContext.bindSlot(i, tool);
        i++;
        for (Map.Entry<SlotContent, Float> result : rollableResults.entrySet()) {
            var chance = result.getValue();
            if (chance != 1.0) {
                slotFillContext.bindOptionalSlot(i, result.getKey(), CuttingSlotRenderer.CHANCE_OUTPUT);
                slotFillContext.addAdditionalStackModifier(i, (stack, tooltip) -> {
                    tooltip.add(Component.literal("Production chance: %s%%".formatted(chance * 100)).setStyle(Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.GOLD))));
                });
            } else {
                slotFillContext.bindOptionalSlot(i, result.getKey(), CuttingSlotRenderer.GUARANTEED_OUTPUT);
            }
            i++;
        }
        if (i<=2) {
            slotFillContext.bindOptionalSlot(i, SlotContent.of(ItemStack.EMPTY), RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
            i++;
        }
        if (i<=3) {
            slotFillContext.bindOptionalSlot(i, SlotContent.of(ItemStack.EMPTY), RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
            i++;
        }
        if (i<=4) {
            slotFillContext.bindOptionalSlot(i, SlotContent.of(ItemStack.EMPTY), RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
            i++;
        }
        if (i<=5) {
            slotFillContext.bindOptionalSlot(i, SlotContent.of(ItemStack.EMPTY), RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
        }
    }

    public interface CuttingSlotRenderer {
        Identifier SLOT_TEXTURE = FarmersDelight.res("textures/gui/jei/cutting_board.png");

        RecipeViewMenu.OptionalSlotRenderer GUARANTEED_OUTPUT = (guiGraphics, mouseX, mouseY, partialTicks) -> guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SLOT_TEXTURE, 0,0,0,58, 18,18, 256,256);
        RecipeViewMenu.OptionalSlotRenderer CHANCE_OUTPUT = (guiGraphics, mouseX, mouseY, partialTicks) -> guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SLOT_TEXTURE, 0,0,18,58, 18,18, 256,256);

        void render(GuiGraphicsExtractor var1, int var2, int var3, float var4);
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