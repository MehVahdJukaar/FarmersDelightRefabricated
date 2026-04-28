package vectorwing.farmersdelight.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class TooltipEvents
{
	public static void addTooltipToVanillaSoups(ItemStack stack, Item.TooltipContext tooltipContext, TooltipFlag tooltipType, List<Component> lines) {
		if (!Configuration.VANILLA_SOUP_EXTRA_EFFECTS.get() || !Configuration.FOOD_EFFECT_TOOLTIP.get()) {
			return;
		}

		Item food = stack.getItem();
		ApplyStatusEffectsConsumeEffect soupEffects = FoodValues.ConsumableValues.VANILLA_SOUP_EFFECTS.get(food);

		if (soupEffects != null) {
			for (MobEffectInstance effectInstance : soupEffects.effects()) {
				MutableComponent effectText = Component.translatable(effectInstance.getDescriptionId());
				Player player = Minecraft.getInstance().player;
				if (effectInstance.getDuration() > 20) {
					effectText = Component.translatable("potion.withDuration", effectText, MobEffectUtil.formatDuration(effectInstance, 1, player == null ? 20 : player.level().tickRateManager().tickrate()));
				}
				lines.add(effectText.withStyle(effectInstance.getEffect().value().getCategory().getTooltipFormatting()));
			}
		}
	}
}
