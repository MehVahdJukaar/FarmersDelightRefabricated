package vectorwing.farmersdelight.common.network.payload;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.client.FarmersDelightClient;

public record SendNaturalRegenerationValuePayload(boolean value) implements CustomPacketPayload {
	public static final Identifier ID = FarmersDelight.id("send_natural_regeneration_value");
	public static final Type<SendNaturalRegenerationValuePayload> TYPE = new Type<>(ID);
	public static final StreamCodec<RegistryFriendlyByteBuf, SendNaturalRegenerationValuePayload> STREAM_CODEC = StreamCodec.of(SendNaturalRegenerationValuePayload::write, SendNaturalRegenerationValuePayload::new);

	public SendNaturalRegenerationValuePayload(FriendlyByteBuf buf) {
		this(buf.readBoolean());
	}

	public static void write(RegistryFriendlyByteBuf buf, SendNaturalRegenerationValuePayload message) {
		buf.writeBoolean(message.value);
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void handle() {
		Minecraft.getInstance().execute(() -> FarmersDelightClient.naturalRegenerationEnabled = value);
	}
}