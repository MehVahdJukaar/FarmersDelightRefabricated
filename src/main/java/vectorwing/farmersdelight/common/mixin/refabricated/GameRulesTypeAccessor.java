package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.BiConsumer;

@Mixin(GameRules.Type.class)
public interface GameRulesTypeAccessor<T> {
    @Accessor("callback") @Mutable
    BiConsumer<MinecraftServer, T> fdrf$getCallback();

    @Accessor("callback") @Mutable
    void fdrf$setCallback(BiConsumer<MinecraftServer, T> value);
}
