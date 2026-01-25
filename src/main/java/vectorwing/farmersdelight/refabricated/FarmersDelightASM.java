package vectorwing.farmersdelight.refabricated;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import vectorwing.farmersdelight.common.registry.ModRecipeBookCategories;

import java.util.function.Supplier;

public class FarmersDelightASM implements Runnable {
    public static final String COOKING_RECIPE_BOOK_TYPE = "FARMERSDELIGHT_COOKING";
    public static final String COOKING_SEARCH_RECIPE_BOOK_CATEGORY = "FARMERSDELIGHT_COOKING";

    @Override
    public void run() {
        String recipeBookTypeTarget = "net.minecraft.world.inventory.RecipeBookType";
        ClassTinkerers.enumBuilder(recipeBookTypeTarget).addEnum(COOKING_RECIPE_BOOK_TYPE).build();
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            String searchRecipeBookCategoryTarget = "net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory";
            String categoriesParamType = "[Lnet.minecraft.world.item.crafting.RecipeBookCategory;";
            ClassTinkerers.enumBuilder(searchRecipeBookCategoryTarget, categoriesParamType).addEnum(COOKING_SEARCH_RECIPE_BOOK_CATEGORY, getSearchCategories()).build();
        }
    }

    public static Supplier<Object[]> getSearchCategories() {
        return () -> new Object[]{new RecipeBookCategory[]{ModRecipeBookCategories.COOKING_MEALS.get(), ModRecipeBookCategories.COOKING_DRINKS.get(), ModRecipeBookCategories.COOKING_MISC.get()}};
    }
}
