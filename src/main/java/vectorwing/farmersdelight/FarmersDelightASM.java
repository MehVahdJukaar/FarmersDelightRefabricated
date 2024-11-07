package vectorwing.farmersdelight;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;

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
        String recipeBookCategoriesTarget = remapper.mapClassName("intermediary", "net.minecraft.class_314");
        String itemStackParamType = "[L" + remapper.mapClassName("intermediary", "net.minecraft.class_1799") + ";";
        ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, itemStackParamType).addEnum(COOKING_SEARCH_RECIPE_BOOK_CATEGORY, () -> new Object[]{new ItemStack[]{new ItemStack(Items.COMPASS)}}).build();
        ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, itemStackParamType).addEnum(COOKING_MEALS_RECIPE_BOOK_CATEGORY, () -> new Object[]{new ItemStack[]{new ItemStack(ModItems.VEGETABLE_NOODLES.get())}}).build();
        ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, itemStackParamType).addEnum(COOKING_DRINKS_RECIPE_BOOK_CATEGORY,() -> new Object[]{new ItemStack[]{new ItemStack(ModItems.APPLE_CIDER.get())}}).build();
        ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, itemStackParamType).addEnum(COOKING_MISC_RECIPE_BOOK_CATEGORY, () -> new Object[]{new ItemStack[]{new ItemStack(ModItems.DUMPLINGS.get()), new ItemStack(ModItems.TOMATO_SAUCE.get())}}).build();
    }

}
