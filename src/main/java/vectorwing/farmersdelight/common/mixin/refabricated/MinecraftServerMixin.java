package vectorwing.farmersdelight.common.mixin.refabricated;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.network.payload.SendNaturalRegenerationValuePayload;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
	@Shadow
	private PlayerList playerList;

	// Silly little workaround for gamerules not being synced to the client.
	@Inject(method = "onGameRuleChanged", at = @At("HEAD"))
	private <T> void fdrf$syncNaturalRegenerationGameRule(GameRule<T> rule, T value, CallbackInfo ci) {
		if (rule == GameRules.NATURAL_HEALTH_REGENERATION) {
			this.playerList.getPlayers().forEach((player) -> ServerPlayNetworking.send(player, new SendNaturalRegenerationValuePayload((boolean) value)));
		}
	}
}
