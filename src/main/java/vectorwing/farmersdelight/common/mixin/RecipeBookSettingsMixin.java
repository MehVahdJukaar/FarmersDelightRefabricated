package vectorwing.farmersdelight.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.datafixers.util.Pair;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Mixin(RecipeBookSettings.class)
public class RecipeBookSettingsMixin {
    @Shadow
    @Final
    @Mutable
    private static Map<RecipeBookType, Pair<String, String>> TAG_FIELDS;

    @Shadow
    @Final
    private Map<RecipeBookType, RecipeBookSettings.TypeSettings> states;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void fdrf$modifyTagFields(CallbackInfo ci) {
        Map<RecipeBookType, Pair<String, String>> newMap = new HashMap<>(TAG_FIELDS);
        newMap.put(FarmersDelight.RECIPE_TYPE_COOKING, Pair.of("isFarmersDelightCookingGuiOpen", "isFarmersDelightCookingFilteringCraftable"));
        TAG_FIELDS = Map.copyOf(newMap);
    }

    @Inject(method = "<init>(Ljava/util/Map;)V", at = @At("TAIL"))
    private void fdrf$defaultCookingRecipeBookTypeStates(CallbackInfo ci) {
        if (!states.containsKey(FarmersDelight.RECIPE_TYPE_COOKING))
            states.put(FarmersDelight.RECIPE_TYPE_COOKING, new RecipeBookSettings.TypeSettings(false, false));
    }

    @ModifyExpressionValue(method = "read(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/stats/RecipeBookSettings;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/RecipeBookType;values()[Lnet/minecraft/world/inventory/RecipeBookType;"))
    private static RecipeBookType[] fdrf$modifyReadFDRecipeBookSettingsToVanilla(RecipeBookType[] original) {
        return Arrays.stream(original).filter(recipeBookType -> recipeBookType != FarmersDelight.RECIPE_TYPE_COOKING).toArray(RecipeBookType[]::new);
    }

    @ModifyExpressionValue(method = "write(Lnet/minecraft/network/FriendlyByteBuf;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/RecipeBookType;values()[Lnet/minecraft/world/inventory/RecipeBookType;"))
    private RecipeBookType[] fdrf$modifyWrittenFDRecipeBookSettingsToVanilla(RecipeBookType[] original) {
        return Arrays.stream(original).filter(recipeBookType -> recipeBookType != FarmersDelight.RECIPE_TYPE_COOKING).toArray(RecipeBookType[]::new);
    }
}
