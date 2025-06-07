package vectorwing.farmersdelight;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

public class FarmersDelightASM implements Runnable {
    public static final String COOKING_RECIPE_BOOK_TYPE = "FARMERSDELIGHT_COOKING";
    public static final String COOKING_SEARCH_RECIPE_BOOK_CATEGORY = "FARMERSDELIGHT_COOKING_SEARCH";
    public static final String COOKING_MEALS_RECIPE_BOOK_CATEGORY = "FARMERSDELIGHT_COOKING_MEALS";
    public static final String COOKING_DRINKS_RECIPE_BOOK_CATEGORY = "FARMERSDELIGHT_COOKING_DRINKS";
    public static final String COOKING_MISC_RECIPE_BOOK_CATEGORY = "FARMERSDELIGHT_COOKING_MISC";

    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();
        String recipeBookTypeTarget = remapper.mapClassName("intermediary", "net.minecraft.class_5421");
        ClassTinkerers.enumBuilder(recipeBookTypeTarget).addEnum(COOKING_RECIPE_BOOK_TYPE).build();
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            String recipeBookCategoriesTarget = remapper.mapClassName("intermediary", "net.minecraft.class_314");
            String itemStackParamType = "[L" + remapper.mapClassName("intermediary", "net.minecraft.class_1799") + ";";
            ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, itemStackParamType).addEnum(COOKING_SEARCH_RECIPE_BOOK_CATEGORY, getSearchCategoryStacks()).build();
            ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, itemStackParamType).addEnum(COOKING_MEALS_RECIPE_BOOK_CATEGORY, getMealsCategoryStacks()).build();
            ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, itemStackParamType).addEnum(COOKING_DRINKS_RECIPE_BOOK_CATEGORY, getDrinksCategoryStacks()).build();
            ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, itemStackParamType).addEnum(COOKING_MISC_RECIPE_BOOK_CATEGORY, getMiscCategoryStacks()).build();
        }
    }

    public static Supplier<Object[]> getSearchCategoryStacks() {
        // Requires a Supplier based workaround to make sure that ItemLike isn't loaded.
        // See https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/77
        return () -> new Object[]{new ItemStack[]{new ItemStack(((Supplier<Item>)() -> Items.COMPASS).get())}};
    }

    public static Supplier<Object[]> getMealsCategoryStacks() {
        return () -> new Object[]{new ItemStack[]{new ItemStack(ModItems.VEGETABLE_NOODLES.get())}};
    }

    public static Supplier<Object[]> getDrinksCategoryStacks() {
        return () -> new Object[]{new ItemStack[]{new ItemStack(ModItems.APPLE_CIDER.get())}};
    }

    public static Supplier<Object[]> getMiscCategoryStacks() {
        return () -> new Object[]{new ItemStack[]{new ItemStack(ModItems.DUMPLINGS.get()), new ItemStack(ModItems.TOMATO_SAUCE.get())}};
    }
}
