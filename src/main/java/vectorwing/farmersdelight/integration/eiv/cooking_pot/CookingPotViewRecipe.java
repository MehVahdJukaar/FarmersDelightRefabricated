package vectorwing.farmersdelight.integration.eiv.cooking_pot;

import de.crafty.eiv.common.api.recipe.IEivViewRecipe;
import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import de.crafty.eiv.common.recipe.inventory.RecipeViewScreen;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookingPotViewRecipe implements IEivViewRecipe {

    private final SlotContent result;
    private final List<SlotContent> ingredients;
    private final SlotContent container;
    private final int cookTime;
    private final float experience;

    public CookingPotViewRecipe(CookingPotServerRecipe shapelessRecipe) {
        this.ingredients = new ArrayList<>();

        shapelessRecipe.getIngredients().forEach(ingredient -> {
            this.ingredients.add(SlotContent.of(ingredient));
        });

        this.result = SlotContent.of(shapelessRecipe.getResult());
        this.container = SlotContent.of(shapelessRecipe.getContainer());
        this.experience = shapelessRecipe.getExperience();
        this.cookTime = shapelessRecipe.getCookTime();
    }

    @Override
    public IEivRecipeViewType getViewType() {
        return CookingPotViewType.INSTANCE;
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {

        for (int i = 0; i < ingredients.size() && i < 6; i++) {
            slotFillContext.bindSlot(i, ingredients.get(i));
        }


        slotFillContext.bindSlot(6, this.result);
        slotFillContext.bindSlot(7, this.container);
    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.renderItem(this.result.getByIndex(0), 103, 45);
        if ((mouseX > 65 && mouseX < 90) && mouseY < 37 && mouseY > 5) {
            List<Component> tooltip = new ArrayList<>();
            if (cookTime > 0) {
                tooltip.add(Component.translatable("eiv.cooking.time", this.cookTime/20));
            }
            if (experience > 0) {
                tooltip.add(Component.translatable("eiv.cooking.experience", this.experience));
            }
            guiGraphics.renderTooltip(Minecraft.getInstance().font, tooltip, Optional.empty(), mouseX, mouseY);
        }
        if ((mouseX > 100 && mouseX < 120) && mouseY < 62 && mouseY > 42) {
            guiGraphics.renderTooltip(Minecraft.getInstance().font, this.result.getByIndex(0), mouseX, mouseY);
        }
    }

    @Override
    public List<SlotContent> getIngredients() {
        return this.ingredients;
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.result);
    }


    @Override
    public boolean supportsItemTransfer() {
        return true;
    }

    @Override
    public void mapRecipeItems(RecipeTransferMap transferMap, AbstractContainerScreen<?> screen) {
        for (int i = 0; i < ingredients.size() && i < 6; i++) {
            transferMap.linkSlots(i, i);
        }
    }

    @Override
    public List<Class<? extends AbstractContainerScreen<?>>> getTransferClasses() {
        return List.of(CookingPotScreen.class);
    }

    @Override
    public boolean canTransferToScreen(AbstractContainerScreen<?> screen) {
        return screen instanceof CookingPotScreen;
    }
}