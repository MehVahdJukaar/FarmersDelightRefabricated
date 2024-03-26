package vectorwing.farmersdelight.integration.emi.recipe;

import com.google.common.collect.ImmutableList;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;
import vectorwing.farmersdelight.integration.emi.FDRecipeCategories;

import java.util.ArrayList;
import java.util.List;

public class CookingPotEmiRecipe implements EmiRecipe {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(FarmersDelight.MODID, "textures/gui/cooking_pot.png");

    private final ResourceLocation id;
    private final List<EmiIngredient> emiInputs;
    private final List<EmiIngredient> recipeInputs;
    private final EmiStack output;
    private final EmiStack container;
    private final int cookTime;
    private final float experience;
    private final List<ClientTooltipComponent> tooltipComponents;

    public CookingPotEmiRecipe(ResourceLocation id, List<EmiIngredient> inputs, EmiStack output,
                               EmiStack container, int cookTime, float experience) {
        this.id = id;
        this.recipeInputs = inputs;
        this.output = output;
        this.container = container;
        this.cookTime = cookTime;
        this.experience = experience;
        this.tooltipComponents = createTooltipComponents();
        ImmutableList.Builder<EmiIngredient> emiInputsBuilder = new ImmutableList.Builder<>();
        emiInputsBuilder.addAll(this.recipeInputs);
        emiInputsBuilder.add(this.container);
        this.emiInputs = emiInputsBuilder.build();
    }

    private List<ClientTooltipComponent> createTooltipComponents() {
        List<ClientTooltipComponent> tooltipStrings = new ArrayList<>();

        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            tooltipStrings.add(ClientTooltipComponent.create(Component.translatable("emi.cooking.time", cookTimeSeconds).getVisualOrderText()));
        }
        if (experience > 0) {
            tooltipStrings.add(ClientTooltipComponent.create(Component.translatable("emi.cooking.experience", experience).getVisualOrderText()));
        }

        return tooltipStrings;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return FDRecipeCategories.COOKING;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return emiInputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(output);
    }

    @Override
    public int getDisplayWidth() {
        return 120;
    }

    @Override
    public int getDisplayHeight() {
        return 60;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BACKGROUND, 4, 4, 116, 56, 29, 16);

        int borderSlotSize = 18;
        for (int row = 0; row < 2; ++row) {
            for (int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipeInputs.size()) {
                    addSlot(widgets, recipeInputs.get(inputIndex), (column * borderSlotSize) + 4, (row * borderSlotSize) + 4);
                }
            }
        }
        addSlot(widgets, output, 98, 13);
        addSlot(widgets, container, 66, 42);
        addSlot(widgets, output, 98, 42).recipeContext(this);

        // Arrow
        widgets.addAnimatedTexture(BACKGROUND, 64, 13, 24, 17, 176, 15, 1000 * 10, true, false, false);
        // Heat Indicator
        widgets.addTexture(BACKGROUND, 22, 43, 17, 15, 176, 0);
        // Time Icon
        widgets.addTexture(BACKGROUND, 68, 6, 8, 11, 176, 32);
        // Experience Icon
        if (experience > 0) {
            widgets.addTexture(BACKGROUND, 67,25, 9, 9, 176, 43);
        }

        widgets.addTooltip((mouseX, mouseY) -> {
            if (ClientRenderUtils.isCursorInsideBounds(65, 6, 22, 28, mouseX, mouseY)) {
                return tooltipComponents;
            }
            return List.of();
        }, 0, 0, widgets.getWidth(), widgets.getHeight());
    }

    private SlotWidget addSlot(WidgetHolder widgets, EmiIngredient ingredient, int x, int y) {
        return widgets.addSlot(ingredient, x, y).drawBack(false);
    }
}
