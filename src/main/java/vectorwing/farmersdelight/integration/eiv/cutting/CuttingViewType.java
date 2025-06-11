package vectorwing.farmersdelight.integration.eiv.cutting;

import com.mojang.datafixers.util.Pair;
import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import me.shedaniel.math.Point;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class CuttingViewType implements IEivRecipeViewType {

    public static final CuttingViewType INSTANCE = new CuttingViewType();

    @Override
    public Component getDisplayName() {
        return Component.translatable("farmersdelight.jei.cutting");
    }

    @Override
    public ResourceLocation getId() {
        return ResourceLocation.withDefaultNamespace("cutting");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModItems.CUTTING_BOARD.get());
    }

    @Override
    public int getDisplayWidth() {
        return 118;
    }

    @Override
    public int getDisplayHeight() {
        return 58;
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return FarmersDelight.res("textures/gui/eiv/cutting_board.png");
    }


    @Override
    public int getSlotCount() {
        return 6;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 16, 28); // ingredient
        slotDefinition.addItemSlot(1, 16, 8); // tool
        slotDefinition.addItemSlot(2, 75, 20); // result #1 TODO the rest of the results
        slotDefinition.addItemSlot(3, 93, 20); // result #2
        slotDefinition.addItemSlot(4, 75, 38); // result #3
        slotDefinition.addItemSlot(5, 93, 38); // result #4


    }

    @Override
    public List<ItemStack> getCraftReferences() {
        return List.of(new ItemStack(ModItems.CUTTING_BOARD.get()));
    }
}