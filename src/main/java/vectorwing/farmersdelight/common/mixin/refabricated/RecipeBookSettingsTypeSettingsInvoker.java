package vectorwing.farmersdelight.common.mixin.refabricated;

import com.mojang.serialization.MapCodec;
import net.minecraft.stats.RecipeBookSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RecipeBookSettings.TypeSettings.class)
public interface RecipeBookSettingsTypeSettingsInvoker {
    @Invoker("codec")
    static MapCodec<RecipeBookSettings.TypeSettings> fdrf$invokeCodec(String openField, String filteringField) {
        throw new RuntimeException("");
    }
}
