package vectorwing.farmersdelight.integration.rei.categories;

import it.unimi.dsi.fastutil.Pair;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;
import vectorwing.farmersdelight.integration.rei.display.CuttingDisplay;

import java.util.ArrayList;
import java.util.List;

public class CuttingCategory implements DisplayCategory<CuttingDisplay> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(FarmersDelight.MODID, "textures/gui/jei/cutting_board.png");
    public static final int OUTPUT_GRID_X = 76;
    public static final int OUTPUT_GRID_Y = 10;

    @Override
    public CategoryIdentifier<? extends CuttingDisplay> getCategoryIdentifier() {
        return REICategoryIdentifiers.CUTTING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("farmersdelight.jei.cutting");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CUTTING_BOARD.get());
    }

    @Override
    public int getDisplayHeight() {
        return 89;
    }

    @Override
    public int getDisplayWidth(CuttingDisplay display) {
        return 155;
    }

    @Override
    public List<Widget> setupDisplay(CuttingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();

        Point startPoint = new Point(bounds.getCenterX() - 58, bounds.getCenterY() - 28);

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(BACKGROUND, startPoint.x, startPoint.y,0, 0, 117, 57));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 16, startPoint.y + 8)).entries(display.getTool()).disableBackground());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 16, startPoint.y + 27)).entries(display.getInputEntries().get(0)).disableBackground());

        int size = display.getRollableOutputs().size();
        int centerX = size > 1 ? 1 : 10;
        int centerY = size > 2 ? 1 : 10;

        for (int i = 0; i < size; ++i) {
            int xOffset = centerX + (i % 2 == 0 ? 0 : 19);
            int yOffset = centerY + ((i / 2) * 19);

            Pair<EntryIngredient, Float> output = display.getRollableOutputs().get(i);

            EntryIngredient ingredient = output.first();
            if (!ingredient.isEmpty()) {
                float chance = output.second();
                if (chance < 1.0F) {
                    ingredient = ingredient.map(stack -> stack.copy().tooltip(TextUtils.getTranslation("jei.chance", chance < 0.01 ? "<1" : (int) (chance * 100))
                            .withStyle(ChatFormatting.GOLD)));
                }
            }

            widgets.add(Widgets.createTexturedWidget(BACKGROUND, startPoint.x + OUTPUT_GRID_X + xOffset - 1, startPoint.y + OUTPUT_GRID_Y + yOffset - 1, output.second() < 1 ?  18 : 0, 58, 18, 18));
            widgets.add(Widgets.createSlot(new Point(startPoint.x + OUTPUT_GRID_X + xOffset, startPoint.y + OUTPUT_GRID_Y + yOffset)).entries(ingredient).disableBackground());
        }

        return widgets;
    }
}
