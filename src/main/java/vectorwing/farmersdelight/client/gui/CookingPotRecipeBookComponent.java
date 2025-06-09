package vectorwing.farmersdelight.client.gui;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.client.recipe.CookingPotRecipeDisplay;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.mixin.refabricated.GhostSlotsInvoker;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeBookCategories;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class CookingPotRecipeBookComponent extends RecipeBookComponent<CookingPotMenu>
{
	private static final SearchRecipeBookCategory COOKING_SEARCH_CATEGORY = SearchRecipeBookCategory.valueOf("FARMERSDELIGHT_COOKING");

	protected static final WidgetSprites RECIPE_BOOK_BUTTONS = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "recipe_book/cooking_pot_enabled"),
			ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "recipe_book/cooking_pot_disabled"),
			ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "recipe_book/cooking_pot_enabled_highlighted"),
			ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "recipe_book/cooking_pot_disabled_highlighted"));
	private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
			new RecipeBookComponent.TabInfo(COOKING_SEARCH_CATEGORY),
			new RecipeBookComponent.TabInfo(ModItems.VEGETABLE_NOODLES.get(), ModRecipeBookCategories.COOKING_MEALS.get()),
			new RecipeBookComponent.TabInfo(ModItems.APPLE_CIDER.get(), ModRecipeBookCategories.COOKING_DRINKS.get()),
			new RecipeBookComponent.TabInfo(ModItems.DUMPLINGS.get(), ModItems.TOMATO_SAUCE.get(), ModRecipeBookCategories.COOKING_MISC.get())
	);

	public CookingPotRecipeBookComponent(CookingPotMenu menu) {
		super(menu, TABS);
	}

	@Override
	protected void initFilterButtonTextures() {
		this.filterButton.initTextureValues(RECIPE_BOOK_BUTTONS);
	}

	@Override
	protected boolean isCraftingSlot(Slot slot) {
		return switch (slot.index) {
			case 0, 1, 2, 3, 4, 5 -> true;
			default -> false;
		};
	}

	@Override
	protected void selectMatchingRecipes(RecipeCollection possibleRecipes, StackedItemContents stackedItemContents) {
		possibleRecipes.selectRecipes(stackedItemContents, recipeDisplay -> recipeDisplay instanceof CookingPotRecipeDisplay);
	}

	@Override
	@NotNull
	protected Component getRecipeFilterName() {
		return TextUtils.getTranslation("container.recipe_book.cookable");
	}

	@Override
	protected void fillGhostRecipe(GhostSlots ghostSlots, RecipeDisplay recipeDisplay, ContextMap contextMap) {
		((GhostSlotsInvoker)ghostSlots).fdrf$setResult(this.menu.getSlot(6), contextMap, recipeDisplay.result());
		if (recipeDisplay instanceof CookingPotRecipeDisplay cookingPotRecipeDisplay) {
			for (int i = 0; i < cookingPotRecipeDisplay.ingredients().size(); ++i) {
				SlotDisplay display = cookingPotRecipeDisplay.ingredients().get(i);
				((GhostSlotsInvoker)ghostSlots).fdrf$setInput(menu.getSlot(i), contextMap, display);
			}

			if (menu.getSlot(7).getItem().isEmpty() && cookingPotRecipeDisplay.container().isPresent()) {
				((GhostSlotsInvoker)ghostSlots).fdrf$setInput(menu.getSlot(7), contextMap, cookingPotRecipeDisplay.container().get());
			}
		}
	}
}
