package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.item.crafting.RecipeBookCategory;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regRecipeBookCategory;

public class ModRecipeBookCategories {
    public static final Supplier<RecipeBookCategory> COOKING_MEALS = regRecipeBookCategory("cooking_meals", RecipeBookCategory::new);
    public static final Supplier<RecipeBookCategory> COOKING_DRINKS = regRecipeBookCategory("cooking_drinks", RecipeBookCategory::new);
    public static final Supplier<RecipeBookCategory> COOKING_MISC = regRecipeBookCategory("cooking_misc", RecipeBookCategory::new);
    public static final Supplier<RecipeBookCategory> CUTTING = regRecipeBookCategory("cutting", RecipeBookCategory::new);

    public static void touch() {

    }
}
