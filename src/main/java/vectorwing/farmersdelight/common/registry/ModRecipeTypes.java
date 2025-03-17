package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.FabricUtils.regRecipe;

public class ModRecipeTypes {
    public static final Supplier<RecipeType<CookingPotRecipe>> COOKING = regRecipe("cooking", () -> registerRecipeType("cooking"));
    public static final Supplier<RecipeType<CuttingBoardRecipe>> CUTTING = regRecipe("cutting", () -> registerRecipeType("cutting"));

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<>() {
            public String toString() {
                return FarmersDelight.MODID + ":" + identifier;
            }
        };
    }

    public static void touch() {

    }
}
