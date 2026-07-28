package vectorwing.farmersdelight.common.mixin;

import net.minecraft.network.protocol.game.ClientboundRecipePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.ServerRecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.network.ModNetworking;

import java.util.List;

@Mixin(ServerRecipeBook.class)
public class ServerRecipeBookMixin extends RecipeBook {
    @Inject(method = "sendRecipes", at = @At("TAIL"))
    private void fdrf$sendCookingRecipeValues(ClientboundRecipePacket.State state, ServerPlayer player, List<ResourceLocation> recipes, CallbackInfo ci) {
        ModNetworking.CHANNEL.sendToClient(new ModNetworking.SendRecipeBookValuesMessage(getBookSettings().isOpen(FarmersDelight.RECIPE_TYPE_COOKING), getBookSettings().isFiltering(FarmersDelight.RECIPE_TYPE_COOKING)), player);
    }
}
