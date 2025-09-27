package vectorwing.farmersdelight.common.mixin.refabricated;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.ServerRecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.networking.ModNetworking;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;

@Mixin(ServerRecipeBook.class)
public class ServerRecipeBookMixin extends RecipeBook {
    @Inject(method = "sendInitialRecipeBook", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;send(Lnet/minecraft/network/protocol/Packet;)V", ordinal = 1))
    private void fdrf$sendInitialCategoryRecipeBook(ServerPlayer player, CallbackInfo ci) {
        ServerPlayNetworking.send(player, new ModNetworking.SendRecipeBookValuesMessage(getBookSettings().isOpen(FDRecipeBookTypes.COOKING), getBookSettings().isFiltering(FDRecipeBookTypes.COOKING)));
    }
}
