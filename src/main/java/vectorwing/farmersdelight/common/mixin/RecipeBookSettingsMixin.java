package vectorwing.farmersdelight.common.mixin;

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

import java.util.HashMap;
import java.util.Map;

@Mixin(RecipeBookSettings.class)
public class RecipeBookSettingsMixin {
    @Final
    @Mutable
    @Shadow
    private static Map<RecipeBookType, Pair<String, String>> TAG_FIELDS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyTagFields(CallbackInfo ci) {
        Map<RecipeBookType, Pair<String, String>> newMap = new HashMap<>(TAG_FIELDS);
        newMap.put(RecipeBookType.valueOf("FARMERSDELIGHT_COOKING"), Pair.of("isFarmersDelightCookingGuiOpen", "isFarmersDelightCookingFilteringCraftable"));
        TAG_FIELDS = Map.copyOf(newMap);
    }
}
