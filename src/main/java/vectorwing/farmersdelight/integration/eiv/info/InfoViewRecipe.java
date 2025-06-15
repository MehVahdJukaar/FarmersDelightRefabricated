package vectorwing.farmersdelight.integration.eiv.info;

import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.api.recipe.IEivViewRecipe;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import de.crafty.eiv.common.recipe.inventory.RecipeViewScreen;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class InfoViewRecipe implements IEivViewRecipe {
    private final SlotContent output;
    private final String translationKey;

    public InfoViewRecipe(List<ItemStack> output, String translationKey) {
        this.output = SlotContent.of(output);
        this.translationKey = translationKey;
    }

    public InfoViewRecipe(ItemStack output, String translationKey) {
        this.output = SlotContent.of(output);
        this.translationKey = translationKey;
    }

    @Override
    public IEivRecipeViewType getViewType() {
        return InfoViewType.INSTANCE; //Here you need your type's instance you created before
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        //Tell EIV which SlotContent belongs to which of your previously defined slots
        slotFillContext.bindSlot(0, this.output);
    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.drawWordWrap(Minecraft.getInstance().font, FormattedText.of(I18n.get(translationKey)), 5, 20, 112, 1842204, false);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(); //Return all of your inputs here
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.output); //Return all of your outputs here
    }
}
