package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.item.crafting.display.RecipeDisplay;
import vectorwing.farmersdelight.client.recipe.CookingPotRecipeDisplay;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regRecipeDisplay;

public class ModRecipeDisplays {
    public static final Supplier<RecipeDisplay.Type<CookingPotRecipeDisplay>> COOKING = regRecipeDisplay("cooking", () -> CookingPotRecipeDisplay.TYPE);

    public static void touch() {

    }
}
