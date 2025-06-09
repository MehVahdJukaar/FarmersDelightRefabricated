package vectorwing.farmersdelight.common.mixin.refabricated;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vectorwing.farmersdelight.common.networking.ModNetworking;

@Mixin(GameRules.class)
public class GameRulesMixin {
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;register(Ljava/lang/String;Lnet/minecraft/world/level/GameRules$Category;Lnet/minecraft/world/level/GameRules$Type;)Lnet/minecraft/world/level/GameRules$Key;"))
    private static void fdrf$modifyNaturalRegenerationGameRule(Args args) {
        if (args.get(0).equals("naturalRegeneration")) {
            GameRulesTypeAccessor<Boolean> typeAccessor = args.get(2);
            typeAccessor.fdrf$setCallback(typeAccessor.fdrf$getCallback().andThen((server, bool) -> {
                for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
                    ServerPlayNetworking.send(serverPlayer, new ModNetworking.SendNaturalRegenerationValueMessage(bool));
                }
            }));
            args.set(2, typeAccessor);
        }
    }
}
