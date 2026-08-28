package vectorwing.farmersdelight.common.event;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.FoodValues;

import java.util.ArrayList;
import java.util.List;

public class CommonModBusEvents
{
	public static void init() {
		DefaultItemComponentEvents.MODIFY.register(CommonModBusEvents::onModifyDefaultComponents);
	}

	public static void onModifyDefaultComponents(DefaultItemComponentEvents.ModifyContext context) {
		if (Configuration.ENABLE_STACKABLE_SOUP_ITEMS.get()) {
			Configuration.SOUP_ITEM_LIST.get().forEach((key) -> {
				Item item = BuiltInRegistries.ITEM.getValue(Identifier.parse(key));
				context.modify(item, (builder) -> builder.set(DataComponents.MAX_STACK_SIZE, 16));
			});
		}
		// Handle Rabbit Stew first.
		if (Configuration.ENABLE_RABBIT_STEW_BUFF.get()) {
			context.modify(Items.RABBIT_STEW, builder -> builder
					.set(DataComponents.FOOD, FoodValues.RABBIT_STEW_BUFF));
		}

		if (Configuration.ENABLE_VANILLA_SOUP_EXTRA_EFFECTS.get() || Configuration.ENABLE_RABBIT_STEW_BUFF.get()) {
			for (Item item : FoodValues.ConsumableValues.VANILLA_SOUP_EFFECTS.keySet()) {
				context.modify(item, builder -> {
					List<MobEffectInstance> effects = new ArrayList<>();
					if (item == Items.RABBIT_STEW && Configuration.ENABLE_RABBIT_STEW_BUFF.get()) {
						effects.add(new MobEffectInstance(MobEffects.JUMP_BOOST, 200, 1));
					}
					builder.set(DataComponents.CONSUMABLE, addVanillaSoupEffect(builder, item, effects));
				});
			}
		}
		if (Configuration.ENABLE_VANILLA_STRAW_BED_RENAME.get()) {
			context.modify(Items.STRAW_BED, builder -> {
				builder.set(DataComponents.ITEM_NAME, Component.translatable("block.farmersdelight.hay_bed"));
			});
		}

	}

	private static Consumable addVanillaSoupEffect(DataComponentMap.Builder builder, Item item, List<MobEffectInstance> additionalEffects) {
		Consumable consumable = builder.getOrCreate(DataComponents.CONSUMABLE, () -> Consumables.DEFAULT_FOOD);
		List<ConsumeEffect> onConsumeEffects = new ArrayList<>(consumable.onConsumeEffects());
		if (!additionalEffects.isEmpty()) {
			onConsumeEffects.add(new ApplyStatusEffectsConsumeEffect(additionalEffects));
		}
		if (Configuration.ENABLE_VANILLA_SOUP_EXTRA_EFFECTS.get()) {
			onConsumeEffects.add(FoodValues.ConsumableValues.VANILLA_SOUP_EFFECTS.get(item));
		}
		return new Consumable(consumable.consumeSeconds(), consumable.animation(), consumable.sound(), consumable.hasConsumeParticles(), onConsumeEffects);
	}
}
