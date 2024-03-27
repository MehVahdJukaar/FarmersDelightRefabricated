package vectorwing.farmersdelight.integration.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.client.categories.DefaultInformationCategory;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import mezz.jei.api.constants.VanillaTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.rei.categories.CookingPotCategory;
import vectorwing.farmersdelight.integration.rei.categories.CuttingCategory;
import vectorwing.farmersdelight.integration.rei.categories.DecompositionCategory;
import vectorwing.farmersdelight.integration.rei.display.CookingPotDisplay;
import vectorwing.farmersdelight.integration.rei.display.CuttingDisplay;
import vectorwing.farmersdelight.integration.rei.display.DecompositionDisplay;;import java.util.List;

public class ClientREIPlugin implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CookingPotRecipe.class, ModRecipeTypes.COOKING.get(), CookingPotDisplay::new);
        registry.registerRecipeFiller(CuttingBoardRecipe.class, ModRecipeTypes.CUTTING.get(), CuttingDisplay::new);
        registry.add(new DecompositionDisplay());

        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.of(ModItems.STRAW.get()), Component.translatable("item.farmersdelight.straw")).lines(TextUtils.getTranslation("jei.info.straw")));
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.of(ModItems.HAM.get()), Component.translatable("item.farmersdelight.ham")).lines(TextUtils.getTranslation("jei.info.ham")));
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.FLINT_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.NETHERITE_KNIFE.get(), ModItems.GOLDEN_KNIFE.get())), Component.translatable("tag.item.farmersdelight.tools.knives")).lines(TextUtils.getTranslation("jei.info.knife")));

        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_CABBAGES.get(), ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get())), Component.translatable("item.farmersdelight.cabbage")).lines(TextUtils.getTranslation("jei.info.wild_cabbages")));
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_BEETROOTS.get(), Items.BEETROOT)), Component.translatable("item.minecraft.beetroot")).lines(TextUtils.getTranslation("jei.info.wild_beetroots")));
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_CARROTS.get(), Items.CARROT)), Component.translatable("item.minecraft.carrot")).lines(TextUtils.getTranslation("jei.info.wild_carrots")));
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_ONIONS.get(), ModItems.ONION.get())), Component.translatable("item.farmersdelight.onion")).lines(TextUtils.getTranslation("jei.info.wild_onions")));
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_POTATOES.get(), Items.POTATO)), Component.translatable("item.minecraft.potato")).lines(TextUtils.getTranslation("jei.info.wild_potatoes")));
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_TOMATOES.get(), ModItems.TOMATO.get())), Component.translatable("item.farmersdelight.tomato")).lines(TextUtils.getTranslation("jei.info.wild_tomatoes")));
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_RICE.get(), ModItems.RICE_PANICLE.get())), Component.translatable("item.farmersdelight.rice")).lines(TextUtils.getTranslation("jei.info.wild_rice")));
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(
                new CookingPotCategory(),
                new CuttingCategory(),
                new DecompositionCategory()
        );

        registry.addWorkstations(REICategoryIdentifiers.COOKING, EntryStacks.of(ModItems.COOKING_POT.get()));
        registry.addWorkstations(REICategoryIdentifiers.CUTTING, EntryStacks.of(ModItems.CUTTING_BOARD.get()));
    }

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {
        registry.register(SimpleTransferHandler.create(CookingPotMenu.class, REICategoryIdentifiers.COOKING, new SimpleTransferHandler.IntRange(0, 6)));
    }
}
