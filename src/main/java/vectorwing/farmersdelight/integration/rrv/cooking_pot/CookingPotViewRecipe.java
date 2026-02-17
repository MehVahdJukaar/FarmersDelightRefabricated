package vectorwing.farmersdelight.integration.rrv.cooking_pot;

import cc.cassian.rrv.api.ActionType;
import cc.cassian.rrv.api.recipe.ReliableClientRecipe;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewScreen;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookingPotViewRecipe implements ReliableClientRecipe {

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
        // Workaround to make sure that the container does not get included in transfers.
        // This luckily doesn't seem to affect anything else.
        this.container.setType(ActionType.RESULT);
    }

    @Override
    public ReliableClientRecipeType getViewType() {
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
    public void renderRecipe(RecipeViewScreen screen, RecipePosition recipePosition, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.renderItem(this.result.getByIndex(0), 103, 17);
        if ((mouseX > 65 && mouseX < 90) && mouseY < 37 && mouseY > 5) {
            List<Component> tooltip = new ArrayList<>();
            if (cookTime > 0) {
                tooltip.add(Component.translatable("eiv.cooking.time", this.cookTime/20));
            }
            if (experience > 0) {
                tooltip.add(Component.translatable("eiv.cooking.experience", this.experience));
            }
            guiGraphics.setTooltipForNextFrame(Minecraft.getInstance().font, tooltip, Optional.empty(), mouseX, mouseY);
        }
        if (ClientRenderUtils.isCursorInsideBounds(103, 17, 18, 18, mouseX, mouseY)) {
            guiGraphics.setTooltipForNextFrame(Minecraft.getInstance().font, this.result.getByIndex(0), mouseX, mouseY);
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
        transferMap.linkSlots(7, 7);
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