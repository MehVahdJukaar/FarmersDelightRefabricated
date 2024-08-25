package vectorwing.farmersdelight.integration.rei.categories;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;
import vectorwing.farmersdelight.integration.rei.display.DecompositionDisplay;

import java.util.ArrayList;
import java.util.List;

public class DecompositionCategory implements DisplayCategory<DecompositionDisplay> {
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "textures/gui/jei/decomposition.png");
    public static final int OUTPUT_GRID_X = 76;
    public static final int OUTPUT_GRID_Y = 10;

    @Override
    public CategoryIdentifier<? extends DecompositionDisplay> getCategoryIdentifier() {
        return REICategoryIdentifiers.DECOMPOSITION;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("farmersdelight.jei.decomposition");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.ORGANIC_COMPOST.get());
    }

    @Override
    public int getDisplayHeight() {
        return 102;
    }

    @Override
    public int getDisplayWidth(DecompositionDisplay display) {
        return 150;
    }

    @Override
    public List<Widget> setupDisplay(DecompositionDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();

        Point startPoint = new Point(bounds.getCenterX() - 59, bounds.getCenterY() - 40);

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(BACKGROUND, startPoint.x, startPoint.y, 0, 0, 118, 80));

        widgets.add(Widgets.createSlot(new Point( startPoint.x + 9, startPoint.y + 26)).entry(EntryStacks.of(ModBlocks.ORGANIC_COMPOST.get())));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 93, startPoint.y + 26)).entry(EntryStacks.of(ModBlocks.RICH_SOIL.get())));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 64, startPoint.y + 54)).entries(EntryIngredients.ofItemTag(ModTags.COMPOST_ACTIVATORS)));

        widgets.add(Widgets.createTooltip(new Rectangle( startPoint.x + 40, startPoint.y + 38, 11, 11), translateKey(".light")));
        widgets.add(Widgets.createTooltip(new Rectangle(startPoint.x + 53, startPoint.y + 38, 11, 11), translateKey(".fluid")));
        widgets.add(Widgets.createTooltip(new Rectangle(startPoint.x + 67, startPoint.y + 38, 11, 11), translateKey(".accelerators")));

        return widgets;
    }

    private static MutableComponent translateKey(@NotNull String suffix) {
        return Component.translatable(FarmersDelight.MODID + ".jei.decomposition" + suffix);
    }

    @Override
    public int getFixedDisplaysPerPage() {
        return 1;
    }

}
