package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;

import java.util.List;
import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regComponent;
import static vectorwing.farmersdelight.refabricated.RegUtils.regEnchComponent;

public class ModDataComponents
{
	// Cooking Pot
	public static final Supplier<DataComponentType<ItemStackWrapper>> MEAL =regComponent(
			"meal", builder -> builder.persistent(ItemStackWrapper.CODEC).networkSynchronized(ItemStackWrapper.STREAM_CODEC).cacheEncoding()
	);
	public static final Supplier<DataComponentType<ItemStackWrapper>> CONTAINER =regComponent(
			"container", builder -> builder.persistent(ItemStackWrapper.CODEC).networkSynchronized(ItemStackWrapper.STREAM_CODEC).cacheEncoding()
	);

	// Skillet
	public static final Supplier<DataComponentType<Integer>> COOKING_TIME_LENGTH =regComponent(
			"cooking_time_length", (builder) -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT)
	);

	public static final Supplier<DataComponentType<ItemStackWrapper>> SKILLET_INGREDIENT =regComponent(
			"skillet_ingredient", (builder) -> builder.persistent(ItemStackWrapper.CODEC).networkSynchronized(ItemStackWrapper.STREAM_CODEC).cacheEncoding()
	);

    public static final Supplier<DataComponentType<Long>> SKILLET_FLIP_TIMESTAMP =regComponent(
            "skillet_flip_timestamp", (builder) -> builder.persistent(Codec.LONG).networkSynchronized(ByteBufCodecs.VAR_LONG).cacheEncoding()
    );
    public static final Supplier<DataComponentType<Boolean>> SKILLET_FLIPPED =regComponent(
            "skillet_flipped", (builder) -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).cacheEncoding()
    );

	// Enchantment Effects
	public static final Supplier<DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> BACKSTABBING = regEnchComponent(
			"backstabbing", builder -> builder.persistent(
					ConditionalEffect.codec(EnchantmentValueEffect.CODEC).listOf()
			));


	public static void touch() {

	}
}
