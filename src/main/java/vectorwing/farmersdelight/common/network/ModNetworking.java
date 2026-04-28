package vectorwing.farmersdelight.common.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.item.BoneMealItem;
import vectorwing.farmersdelight.common.network.payload.FlipSkilletPayload;
import vectorwing.farmersdelight.common.network.payload.RichSoilBoostParticlesPayload;
import vectorwing.farmersdelight.common.network.payload.SendNaturalRegenerationValuePayload;
import vectorwing.farmersdelight.common.network.payload.SendRecipeBookValuesPayload;

public class ModNetworking {

    public static void init() {
        PayloadTypeRegistry.clientboundPlay().register(SendRecipeBookValuesPayload.TYPE, SendRecipeBookValuesPayload.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(RichSoilBoostParticlesPayload.TYPE, RichSoilBoostParticlesPayload.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(SendNaturalRegenerationValuePayload.TYPE, SendNaturalRegenerationValuePayload.STREAM_CODEC);

        PayloadTypeRegistry.serverboundPlay().register(FlipSkilletPayload.TYPE, FlipSkilletPayload.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(FlipSkilletPayload.TYPE, (payload, context) -> payload.handle(context.server(), context.player()));
    }

	public static class ClientPayloadHandler
	{
		public static void handleRichSoilBoostParticles(RichSoilBoostParticlesPayload payload, ClientPlayNetworking.Context context) {
			BoneMealItem.addGrowthParticles(context.player().level(), payload.pos(), 15);
		}
	}

    public static void initClient() {
        ClientPlayNetworking.registerGlobalReceiver(SendRecipeBookValuesPayload.TYPE, (payload, context) -> payload.handle());
        ClientPlayNetworking.registerGlobalReceiver(RichSoilBoostParticlesPayload.TYPE, ModNetworking.ClientPayloadHandler::handleRichSoilBoostParticles);
		ClientPlayNetworking.registerGlobalReceiver(SendNaturalRegenerationValuePayload.TYPE, (payload, context) -> payload.handle());
	}

}
