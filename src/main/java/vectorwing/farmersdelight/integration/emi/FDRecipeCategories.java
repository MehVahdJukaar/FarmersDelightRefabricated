package vectorwing.farmersdelight.integration.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import vectorwing.farmersdelight.FarmersDelight;

public class FDRecipeCategories {
    // TODO: Add simplified icons.
    public static final EmiRecipeCategory COOKING = new EmiRecipeCategory(FarmersDelight.res("cooking"), FDRecipeWorkstations.COOKING_POT);
    public static final EmiRecipeCategory CUTTING = new EmiRecipeCategory(FarmersDelight.res("cutting"), FDRecipeWorkstations.CUTTING_BOARD);
    public static final EmiRecipeCategory DECOMPOSITION = new EmiRecipeCategory(FarmersDelight.res("decomposition"), FDRecipeWorkstations.ORGANIC_COMPOST);

}
