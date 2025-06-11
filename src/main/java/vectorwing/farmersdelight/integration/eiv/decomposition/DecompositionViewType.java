package vectorwing.farmersdelight.integration.eiv.decomposition;

import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;

public class DecompositionViewType implements IEivRecipeViewType {

    protected static final DecompositionViewType INSTANCE = new DecompositionViewType();

    @Override
    public Component getDisplayName() {
        return Component.translatable("emi.category.farmersdelight.decomposition");
    }

    @Override
    public int getDisplayWidth() {
        return 118;
    }

    @Override
    public int getDisplayHeight() {
        return 71;
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return FarmersDelight.res("textures/gui/eiv/decomposition.png");
    }

    @Override
    public int getSlotCount() {
        return 3;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        //Tell EIV where your slots are located by calling slotDefinition.addItemSlot();
        //NOTE: Slot position is relative to your gui texture

        slotDefinition.addItemSlot(0, 9, 26);
        slotDefinition.addItemSlot(1, 93, 26);
        slotDefinition.addItemSlot(2, 64, 52);

    }

    @Override
    public ResourceLocation getId() {
        return FarmersDelight.res("decomposition");
    }

    @Override
    public ItemStack getIcon() {
        return ModItems.ORGANIC_COMPOST.get().getDefaultInstance();
    }
}
