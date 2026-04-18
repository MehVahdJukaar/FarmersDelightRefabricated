package vectorwing.farmersdelight.integration.rrv.cooking_pot;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;

public class CookingPotViewType implements ReliableClientRecipeType {

    public static final CookingPotViewType INSTANCE = new CookingPotViewType();

    @Override
    public Component getDisplayName() {
        return Component.translatable("farmersdelight.jei.cooking");
    }

    @Override
    public Identifier getId() {
        return FarmersDelight.id("cooking");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModItems.COOKING_POT.get());
    }

    @Override
    public int getDisplayWidth() {
        return 132;
    }

    @Override
    public int getDisplayHeight() {
        return 70;
    }

    @Override
    public Identifier getGuiTexture() {
        return FarmersDelight.id("textures/gui/eiv/cooking_pot.png");
    }


    @Override
    public int getSlotCount() {
        return 8;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {

        //Input slots
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                slotDefinition.addItemSlot(x + y * 3, 9 + x * 18,  7 + y * 18);
            }
        }

        //Result Slot
        slotDefinition.addItemSlot(6, 103, 45);
        // Container Slot
        slotDefinition.addItemSlot(7, 71, 45);

    }

    @Override
    public List<ItemStack> getCraftReferences() {
        return List.of(new ItemStack(ModItems.COOKING_POT.get()));
    }
}