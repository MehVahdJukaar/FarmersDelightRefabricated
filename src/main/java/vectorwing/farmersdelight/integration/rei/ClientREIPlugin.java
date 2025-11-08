package vectorwing.farmersdelight.integration.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultShapelessDisplay;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
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
import vectorwing.farmersdelight.integration.rei.display.DecompositionDisplay;

import java.util.List;
import java.util.Optional;

;

public class ClientREIPlugin implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CookingPotRecipe.class, ModRecipeTypes.COOKING.get(), CookingPotDisplay::new);
        registry.registerRecipeFiller(CuttingBoardRecipe.class, ModRecipeTypes.CUTTING.get(), CuttingDisplay::new);
        registry.add(new DecompositionDisplay());

        ShapelessRecipe specialWheatDoughRecipe = getSpecialWheatDoughRecipe(registry.getRecipeManager());
        if (specialWheatDoughRecipe != null) {
            registry.add(new DefaultShapelessDisplay(specialWheatDoughRecipe));
        }

        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.of(ModItems.WHEAT_DOUGH.get()), Component.translatable("item.farmersdelight.wheat_dough")).lines(TextUtils.getTranslation("jei.info.dough")));
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
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(new Rectangle(89, 25, 24, 17), CookingPotScreen.class, REICategoryIdentifiers.COOKING);
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

    public ShapelessRecipe getSpecialWheatDoughRecipe(RecipeManager recipeManager) {
        Optional<? extends Recipe<?>> specialRecipe = recipeManager.byKey(FarmersDelight.res("wheat_dough_from_water"));

        return specialRecipe.map((recipe) -> {
            NonNullList<Ingredient> inputs = NonNullList.of(
                    Ingredient.EMPTY,
                    Ingredient.of(Items.WHEAT),
                    Ingredient.of(Items.WATER_BUCKET)
            );
            ItemStack output = new ItemStack(ModItems.WHEAT_DOUGH.get());

            ResourceLocation id = recipe.getId();
            return new ShapelessRecipe(id, "fd_dough", CraftingBookCategory.MISC, output, inputs);
        }).orElse(null);
    }
}
