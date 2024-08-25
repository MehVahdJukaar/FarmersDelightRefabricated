package vectorwing.farmersdelight.common.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.impl.screenhandler.client.ClientNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

public class ModNetworking {

    public static void init() {
        PayloadTypeRegistry.playC2S().register(FlipSkilletMessage.TYPE, FlipSkilletMessage.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(FlipSkilletMessage.TYPE, (payload, context) -> payload.handle(context.server(), context.player()));
    }


    public static class FlipSkilletMessage implements CustomPacketPayload {
        public static final ResourceLocation ID = FarmersDelight.res("flip_skillet");
        public static final Type<FlipSkilletMessage> TYPE = new Type<>(ID);
        public static final StreamCodec<RegistryFriendlyByteBuf, FlipSkilletMessage> STREAM_CODEC = StreamCodec.unit(new FlipSkilletMessage());

        public FlipSkilletMessage() {
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
}
