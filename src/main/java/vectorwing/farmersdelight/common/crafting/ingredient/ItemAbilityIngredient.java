package vectorwing.farmersdelight.common.crafting.ingredient;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.fabricators_of_create.porting_lib.tool.ItemAbility;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredient;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredientSerializer;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.List;

/**
 * Ingredient that checks if the given stack can perform a ItemAbility from Forge.
 */
@MethodsReturnNonnullByDefault
public class ItemAbilityIngredient implements CustomIngredient
{
	public static final Serializer SERIALIZER = new Serializer();
	public static final ResourceLocation SERIALIZER_ID = FarmersDelight.res("item_ability");

	protected final ItemAbility itemAbility;
	protected List<ItemStack> itemStacks;

	public ItemAbilityIngredient(ItemAbility itemAbility) {
		this.itemAbility = itemAbility;
	}

	public static void register() {
		CustomIngredientSerializer.register(SERIALIZER);
	}

	protected void dissolve() {
		if (this.itemStacks == null) {
			itemStacks = BuiltInRegistries.ITEM.stream()
					.map(ItemStack::new)
					.filter(stack -> stack.canPerformAction(itemAbility))
					.toList();
		}
	}

	@Override
	public boolean test(@Nullable ItemStack stack) {
		return stack != null && stack.canPerformAction(itemAbility);
	}

	public ItemAbility getItemAbility() {
		return itemAbility;
	}

	@Override
	public List<ItemStack> getMatchingStacks() {
		dissolve();
		return itemStacks;
	}

	@Override
	public boolean requiresTesting() {
		return false;
	}

	@Override
	public CustomIngredientSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	public static class Serializer implements CustomIngredientSerializer<ItemAbilityIngredient> {
		public static final MapCodec<ItemAbilityIngredient> CODEC = RecordCodecBuilder.mapCodec(inst ->
				inst.group(ItemAbility.CODEC.fieldOf("action").forGetter(ItemAbilityIngredient::getItemAbility)
				).apply(inst, ItemAbilityIngredient::new));
		public static final StreamCodec<RegistryFriendlyByteBuf, ItemAbilityIngredient> STREAM_CODEC = ByteBufCodecs.fromCodec(ItemAbility.CODEC).map(ItemAbilityIngredient::new, ItemAbilityIngredient::getItemAbility).cast();

		@Override
		public ResourceLocation getIdentifier() {
			return SERIALIZER_ID;
		}

		@Override
		public MapCodec<ItemAbilityIngredient> getCodec(boolean allowEmpty) {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, ItemAbilityIngredient> getPacketCodec() {
			return STREAM_CODEC;
		}
	}

}
