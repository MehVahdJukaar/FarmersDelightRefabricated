package vectorwing.farmersdelight.common.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.client.FarmersDelightClient;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;

public class ModNetworking {

    public static void init() {
        PayloadTypeRegistry.playS2C().register(SendRecipeBookValuesMessage.TYPE, SendRecipeBookValuesMessage.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(SendNaturalRegenerationValueMessage.TYPE, SendNaturalRegenerationValueMessage.STREAM_CODEC);

        PayloadTypeRegistry.playC2S().register(FlipSkilletMessage.TYPE, FlipSkilletMessage.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(FlipSkilletMessage.TYPE, (payload, context) -> payload.handle(context.server(), context.player()));

    }

    public static void initClient() {
        ClientPlayNetworking.registerGlobalReceiver(SendRecipeBookValuesMessage.TYPE, (payload, context) -> payload.handle());
        ClientPlayNetworking.registerGlobalReceiver(SendNaturalRegenerationValueMessage.TYPE, (payload, context) -> payload.handle());
    }

    public static class FlipSkilletMessage implements CustomPacketPayload {
        public static final Identifier ID = FarmersDelight.res("flip_skillet");
        public static final FlipSkilletMessage INSTANCE = new FlipSkilletMessage();
        public static final Type<FlipSkilletMessage> TYPE = new Type<>(ID);
        public static final StreamCodec<RegistryFriendlyByteBuf, FlipSkilletMessage> STREAM_CODEC = StreamCodec.unit(INSTANCE);

        public FlipSkilletMessage() {
        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }

        public void handle(MinecraftServer server, ServerPlayer player) {
            server.execute(() -> {
                ItemStack stack = player.getUseItem();
                if (stack.getItem() instanceof SkilletItem) {
                    stack.set(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get(), player.level().getGameTime());
                }
            });
        }
    }

    public record SendRecipeBookValuesMessage(boolean open, boolean filtering) implements CustomPacketPayload {
        public static final Identifier ID = FarmersDelight.res("send_recipe_book_values");
        public static final Type<SendRecipeBookValuesMessage> TYPE = new Type<>(ID);
        public static final StreamCodec<RegistryFriendlyByteBuf, SendRecipeBookValuesMessage> STREAM_CODEC = StreamCodec.of(SendRecipeBookValuesMessage::write, SendRecipeBookValuesMessage::new);

        public SendRecipeBookValuesMessage(FriendlyByteBuf buf) {
            this(buf.readBoolean(), buf.readBoolean());
        }

        public static void write(RegistryFriendlyByteBuf buf, SendRecipeBookValuesMessage message) {
            buf.writeBoolean(message.open);
            buf.writeBoolean(message.filtering);
        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }

        public void handle() {
            Minecraft.getInstance().execute(() -> {
                ClientRecipeBook recipeBook = Minecraft.getInstance().player.getRecipeBook();
                recipeBook.setOpen(FDRecipeBookTypes.COOKING, open);
                recipeBook.setFiltering(FDRecipeBookTypes.COOKING, filtering);
            });
        }
    }

    public record SendNaturalRegenerationValueMessage(boolean value) implements CustomPacketPayload {
        public static final Identifier ID = FarmersDelight.res("send_natural_regeneration_value");
        public static final Type<SendNaturalRegenerationValueMessage> TYPE = new Type<>(ID);
        public static final StreamCodec<RegistryFriendlyByteBuf, SendNaturalRegenerationValueMessage> STREAM_CODEC = StreamCodec.of(SendNaturalRegenerationValueMessage::write, SendNaturalRegenerationValueMessage::new);

        public SendNaturalRegenerationValueMessage(FriendlyByteBuf buf) {
            this(buf.readBoolean());
        }

        public static void write(RegistryFriendlyByteBuf buf, SendNaturalRegenerationValueMessage message) {
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
}
