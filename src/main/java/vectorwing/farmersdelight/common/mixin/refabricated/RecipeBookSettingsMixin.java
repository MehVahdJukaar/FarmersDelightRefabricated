package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@Mixin(RecipeBookSettings.class)
public class RecipeBookSettingsMixin {
    @Unique
    private RecipeBookSettings.TypeSettings fdrf$cooking = RecipeBookSettings.TypeSettings.DEFAULT;

    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;mapCodec(Ljava/util/function/Function;)Lcom/mojang/serialization/MapCodec;"))
    private static MapCodec<RecipeBookSettings> fdrf$modifyCodec(MapCodec<RecipeBookSettings> original) {
         return RecordCodecBuilder.mapCodec(inst -> inst.group(
                original
                        .forGetter(Function.identity()),
                RecipeBookSettingsTypeSettingsInvoker.fdrf$invokeCodec("isFarmersDelightCookingGuiOpen", "isFarmersDelightCookingFilteringCraftable")
                        .forGetter(recipeBookSettings -> ((RecipeBookSettingsMixin)(Object)recipeBookSettings).fdrf$cooking)
        ).apply(inst, (recipeBookSettings, typeSettings) -> {
            ((RecipeBookSettingsMixin)(Object)recipeBookSettings).fdrf$cooking = typeSettings;
            return recipeBookSettings;
        }));
    }

    @ModifyReturnValue(method = "copy", at = @At("RETURN"))
    private RecipeBookSettings fdrf$copyCookingBook(RecipeBookSettings original) {
        ((RecipeBookSettingsMixin)(Object)original).fdrf$cooking = this.fdrf$cooking;
        return original;
    }

    @Inject(method = "replaceFrom", at = @At("TAIL"))
    private void fdrf$replaceFromCookingBook(RecipeBookSettings other, CallbackInfo ci) {
        this.fdrf$cooking = ((RecipeBookSettingsMixin)(Object)other).fdrf$cooking;
    }

    @Inject(method = "getSettings", at = @At("HEAD"), cancellable = true)
    private void fdrf$getCookingBookSettings(RecipeBookType type, CallbackInfoReturnable<RecipeBookSettings.TypeSettings> cir) {
        if (type == FDRecipeBookTypes.COOKING) {
            cir.setReturnValue(fdrf$cooking);
        }
    }

    @Inject(method = "updateSettings", at = @At("HEAD"), cancellable = true)
    private void fdrf$updateCookingBookSettings(RecipeBookType type, UnaryOperator<RecipeBookSettings.TypeSettings> updater, CallbackInfo ci) {
        if (type == FDRecipeBookTypes.COOKING) {
            this.fdrf$cooking = updater.apply(fdrf$cooking);
            ci.cancel();
        }
    }
}
