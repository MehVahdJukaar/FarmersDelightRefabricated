package vectorwing.farmersdelight.common.mixin;

import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = RecipeBookType.class, priority = 2904) // Random priority to make sure this applies in a hopefully consistent order.
public class RecipeBookTypeMixin {
    @Final
    @Mutable
    @Shadow
    private static RecipeBookType[] $VALUES;

    @Invoker("<init>")
    private static RecipeBookType createRecipeBookType(String name, int ordinal) {
        throw new RuntimeException();
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addCookingRecipeCategory(CallbackInfo ci) {
        List<RecipeBookType> recipeBookTypeList = new ArrayList<>(List.of($VALUES));
        recipeBookTypeList.add(createRecipeBookType("COOKING", $VALUES.length));
        $VALUES = recipeBookTypeList.toArray(RecipeBookType[]::new);
    }
}
