package vectorwing.farmersdelight.common.networking;

import me.pepperbell.simplenetworking.C2SPacket;
import me.pepperbell.simplenetworking.S2CPacket;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.SkilletItem;

public class ModNetworking {

    public static final SimpleChannel CHANNEL = new SimpleChannel(FarmersDelight.res("channel"));

    static {
        CHANNEL.registerC2SPacket(FlipSkilletMessage.class, 0, FlipSkilletMessage::new);
        CHANNEL.initServerListener();
        CHANNEL.registerS2CPacket(SendRecipeBookValuesMessage.class, 0, SendRecipeBookValuesMessage::new);
    }

    public static void init() {
    }

    public static void initClient() {
        CHANNEL.initClientListener();
    }

    public static class FlipSkilletMessage implements C2SPacket {

        public FlipSkilletMessage(FriendlyByteBuf buf) {
            int aa = 1;
        }

        public FlipSkilletMessage(){
        }

        @Override
        public void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl listener, PacketSender responseSender, SimpleChannel channel) {
            ItemStack stack = player.getUseItem();
            if (stack.getItem() instanceof SkilletItem) {
                CompoundTag tag = stack.getOrCreateTag();
                if (!tag.contains("FlipTimeStamp")) {
                    tag.putLong("FlipTimeStamp", player.level().getGameTime());
                }
            }
        }

        @Override
        public void encode(FriendlyByteBuf buf) {
        }

    }

    public record SendRecipeBookValuesMessage(boolean open, boolean filtering) implements S2CPacket {

        public SendRecipeBookValuesMessage(FriendlyByteBuf buf) {
            this(buf.readBoolean(), buf.readBoolean());
        }

        @Override
        public void handle(Minecraft client, ClientPacketListener listener, PacketSender responseSender, SimpleChannel channel) {
            client.execute(() -> {
                ClientRecipeBook recipeBook = Minecraft.getInstance().player.getRecipeBook();
                recipeBook.setOpen(FarmersDelight.RECIPE_TYPE_COOKING, open);
                recipeBook.setFiltering(FarmersDelight.RECIPE_TYPE_COOKING, filtering);
            });
        }

        @Override
        public void encode(FriendlyByteBuf buf) {
            buf.writeBoolean(open);
            buf.writeBoolean(filtering);
        }
    }
}
