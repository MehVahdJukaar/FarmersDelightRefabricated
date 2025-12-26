package vectorwing.farmersdelight.common.mixin;

import com.google.common.base.Suppliers;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = RecipeBookCategories.class, priority = 2904) // Random priority to make sure this applies in a hopefully consistent order.
public class RecipeBookCategoriesMixin {
    @Final
    @Mutable
    @Shadow
    private static RecipeBookCategories[] $VALUES;

    @Invoker("<init>")
    private static RecipeBookCategories createRecipeBookCategory(String name, int ordinal, ItemStack... itemStacks) {
        throw new RuntimeException();
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addCookingRecipeCategory(CallbackInfo ci) {
        List<RecipeBookCategories> recipeBookTypeList = new ArrayList<>(List.of($VALUES));
        recipeBookTypeList.add(createRecipeBookCategory("COOKING_SEARCH", $VALUES.length, new ItemStack(Items.COMPASS)));
        recipeBookTypeList.add(createRecipeBookCategory("COOKING_MEALS", $VALUES.length + 1, new ItemStack(ModItems.VEGETABLE_NOODLES.get())));
        recipeBookTypeList.add(createRecipeBookCategory("COOKING_DRINKS", $VALUES.length + 2, new ItemStack(ModItems.APPLE_CIDER.get())));
        recipeBookTypeList.add(createRecipeBookCategory("COOKING_MISC", $VALUES.length + 3, new ItemStack(ModItems.DUMPLINGS.get()), new ItemStack(ModItems.TOMATO_SAUCE.get())));
        $VALUES = recipeBookTypeList.toArray(RecipeBookCategories[]::new);
    }
}
