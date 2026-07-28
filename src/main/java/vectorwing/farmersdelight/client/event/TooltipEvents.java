package vectorwing.farmersdelight.client.event;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class TooltipEvents
{
    public static void addTooltipToVanillaSoups(ItemStack stack, TooltipFlag tooltipType, List<Component> lines) {
		Item food = stack.getItem();
		FoodProperties soupEffects = FoodValues.VANILLA_SOUP_EFFECTS.get(food);

		if (food.equals(Items.PUMPKIN_PIE)) {
			lines.add(Configuration.ENABLE_PUMPKIN_PIE_SNEAK_TO_PLACE.get() ? TextUtils.PLACEABLE_SNEAKING : TextUtils.PLACEABLE);
		}

		if (!Configuration.ENABLE_VANILLA_SOUP_EXTRA_EFFECTS.get() || !Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
			return;
		}

        if (soupEffects != null) {
            for (Pair<MobEffectInstance, Float> pair : soupEffects.getEffects()) {
                MobEffectInstance effect = pair.getFirst();
                MutableComponent effectText = Component.translatable(effect.getDescriptionId());
                Player player = Minecraft.getInstance().player;
                if (effect.getDuration() > 20) {
                    effectText = Component.translatable("potion.withDuration", effectText, MobEffectUtil.formatDuration(effect, 1));
                }
                lines.add(effectText.withStyle(effect.getEffect().getCategory().getTooltipFormatting()));
            }
        }
    }
}