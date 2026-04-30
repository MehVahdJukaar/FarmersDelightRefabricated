package vectorwing.farmersdelight.integration.rrv.decomposition;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class DecompositionClientRecipeType implements ReliableClientRecipeType {

    protected static final DecompositionClientRecipeType INSTANCE = new DecompositionClientRecipeType();

    @Override
    public Component getDisplayName() {
        return TextUtils.JEI("decomposition");
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
    public Identifier getGuiTexture() {
        return FarmersDelight.id("textures/gui/eiv/decomposition.png");
    }

    @Override
    public int getSlotCount() {
        return 3;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        //Tell RRV where your slots are located by calling slotDefinition.addItemSlot();
        //NOTE: Slot position is relative to your gui texture

        slotDefinition.addItemSlot(0, 9, 26);
        slotDefinition.addItemSlot(1, 93, 26);
        slotDefinition.addItemSlot(2, 64, 52);

    }

    @Override
    public Identifier getId() {
        return FarmersDelight.id("decomposition");
    }

    @Override
    public ItemStack getIcon() {
        return ModItems.ORGANIC_COMPOST.get().getDefaultInstance();
    }
}
