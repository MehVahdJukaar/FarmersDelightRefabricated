// TODO: Reimport when EIV updates.
//package vectorwing.farmersdelight.integration.eiv.info;
//
//import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
//import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import vectorwing.farmersdelight.FarmersDelight;
//
//public class InfoViewType implements IEivRecipeViewType {
//
//    protected static final InfoViewType INSTANCE = new InfoViewType();
//
//    @Override
//    public Component getDisplayName() {
//        return Component.translatable("eiv.category.farmersdelight.info");
//    }
//
//    @Override
//    public int getDisplayWidth() {
//        return 120;
//    }
//
//    @Override
//    public int getDisplayHeight() {
//        return 120;
//    }
//
//    @Override
//    public ResourceLocation getGuiTexture() {
//        return FarmersDelight.res("textures/gui/eiv/info.png");
//    }
//
//    @Override
//    public int getSlotCount() {
//        return 1;
//    }
//
//    @Override
//    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
//        //Tell EIV where your slots are located by calling slotDefinition.addItemSlot();
//        //NOTE: Slot position is relative to your gui texture
//
//        slotDefinition.addItemSlot(0, 53, 3);
//
//    }
//
//    @Override
//    public ResourceLocation getId() {
//        return FarmersDelight.res("info");
//    }
//
//    @Override
//    public ItemStack getIcon() {
//        return Items.BOOK.getDefaultInstance();
//    }
//}
