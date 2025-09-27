package vectorwing.farmersdelight.integration.eiv.decomposition;

import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.api.recipe.IEivViewRecipe;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import de.crafty.eiv.common.recipe.inventory.RecipeViewScreen;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;

import java.util.List;

public class DecompositionViewRecipe implements IEivViewRecipe {
    private final SlotContent input;
    private final SlotContent output;
    private static final SlotContent activators = SlotContent.ofItemList(List.of(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM, Items.MYCELIUM, Items.PODZOL, ModItems.RED_MUSHROOM_COLONY.get(), ModItems.BROWN_MUSHROOM_COLONY.get(), ModItems.RICH_SOIL.get(), ModItems.RICH_SOIL_FARMLAND.get()));

    private static final MutableComponent LIGHT_TOOLTIP = createTooltip(".light");
    private static final MutableComponent FLUID_TOOLTIP = createTooltip(".fluid");
    private static final MutableComponent ACCELERATORS_TOOLTIP = createTooltip(".accelerators");


    public DecompositionViewRecipe(ItemStack input, ItemStack output) {
        this.input = SlotContent.of(input);
        this.output = SlotContent.of(output);
    }

    @Override
    public IEivRecipeViewType getViewType() {
        return DecompositionViewType.INSTANCE; //Here you need your type's instance you created before
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        //Tell EIV which SlotContent belongs to which of your previously defined slots
        slotFillContext.bindSlot(0, this.input);
        slotFillContext.bindSlot(1, this.output);
        slotFillContext.bindSlot(2, activators);
    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, RecipePosition recipePosition, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int iconY = 38;
        int iconX = 38;
        if (ClientRenderUtils.isCursorInsideBounds(iconX, iconY, 11, 11, mouseX, mouseY)) {
            guiGraphics.setTooltipForNextFrame(Minecraft.getInstance().font, LIGHT_TOOLTIP, mouseX, mouseY);
        } else if (ClientRenderUtils.isCursorInsideBounds(iconX+12, iconY, 11, 11, mouseX, mouseY)) {
            guiGraphics.setTooltipForNextFrame(Minecraft.getInstance().font, FLUID_TOOLTIP, mouseX, mouseY);
        } else if (ClientRenderUtils.isCursorInsideBounds(iconX+27, iconY, 11, 11, mouseX, mouseY)) {
            guiGraphics.setTooltipForNextFrame(Minecraft.getInstance().font, ACCELERATORS_TOOLTIP, mouseX, mouseY);
        }
    }

    private static MutableComponent createTooltip(@NotNull String suffix) {
        return Component.translatable(FarmersDelight.MODID + ".jei.decomposition" + suffix);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.input); //Return all of your inputs here
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.output); //Return all of your outputs here
    }
}
