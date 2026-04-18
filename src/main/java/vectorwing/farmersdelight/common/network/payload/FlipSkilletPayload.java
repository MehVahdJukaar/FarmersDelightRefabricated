package vectorwing.farmersdelight.common.network.payload;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

public class FlipSkilletPayload implements CustomPacketPayload {
	public static final ResourceLocation ID = FarmersDelight.res("flip_skillet");
	public static final FlipSkilletPayload INSTANCE = new FlipSkilletPayload();
	public static final Type<FlipSkilletPayload> TYPE = new Type<>(ID);
	public static final StreamCodec<RegistryFriendlyByteBuf, FlipSkilletPayload> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	public FlipSkilletPayload() {
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void handle(MinecraftServer server, ServerPlayer player) {
		ItemStack stack = player.getUseItem();
		if (stack.getItem() instanceof SkilletItem) {
			stack.set(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get(), player.level().getGameTime());
		}
	}
}
