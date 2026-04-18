package vectorwing.farmersdelight.common.network.payload;


import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;

public record SendRecipeBookValuesPayload(boolean open, boolean filtering) implements CustomPacketPayload {
	public static final ResourceLocation ID = FarmersDelight.res("send_recipe_book_values");
	public static final Type<SendRecipeBookValuesPayload> TYPE = new Type<>(ID);
	public static final StreamCodec<RegistryFriendlyByteBuf, SendRecipeBookValuesPayload> STREAM_CODEC = StreamCodec.of(SendRecipeBookValuesPayload::write, SendRecipeBookValuesPayload::new);

	public SendRecipeBookValuesPayload(FriendlyByteBuf buf) {
		this(buf.readBoolean(), buf.readBoolean());
	}

	public static void write(RegistryFriendlyByteBuf buf, SendRecipeBookValuesPayload message) {
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

