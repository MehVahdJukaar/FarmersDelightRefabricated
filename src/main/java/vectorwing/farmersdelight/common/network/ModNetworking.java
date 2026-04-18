package vectorwing.farmersdelight.common.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BoneMealItem;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.network.payload.FlipSkilletPayload;
import vectorwing.farmersdelight.common.network.payload.RichSoilBoostParticlesPayload;
import vectorwing.farmersdelight.common.network.payload.SendRecipeBookValuesPayload;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;

public class ModNetworking {

    public static void init() {
        PayloadTypeRegistry.playS2C().register(SendRecipeBookValuesPayload.TYPE, SendRecipeBookValuesPayload.STREAM_CODEC);

        PayloadTypeRegistry.playC2S().register(FlipSkilletPayload.TYPE, FlipSkilletPayload.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(FlipSkilletPayload.TYPE, (payload, context) -> payload.handle(context.server(), context.player()));


		registrar.playToClient(RichSoilBoostParticlesPayload.TYPE, RichSoilBoostParticlesPayload.STREAM_CODEC, ModNetworking1.ClientPayloadHandler::handleRichSoilBoostParticles);

    }

	public static class ClientPayloadHandler
	{
		public static void handleRichSoilBoostParticles(RichSoilBoostParticlesPayload payload, IPayloadContext context) {
			BoneMealItem.addGrowthParticles(context.player().level(), payload.pos(), 15);
		}
	}

    public static void initClient() {
        ClientPlayNetworking.registerGlobalReceiver(SendRecipeBookValuesPayload.TYPE, (payload, context) -> payload.handle());
    }

}
