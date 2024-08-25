package vectorwing.farmersdelight.common.event;

import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingEntityUseItemEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.FoodValues;

public class CommonEvents
{
	public static void init() {
		LivingEntityUseItemEvent.Finish.EVENT.register(CommonEvents::handleVanillaSoupEffects);
	}

	public static void handleVanillaSoupEffects(LivingEntityUseItemEvent.Finish finish) {
		Item food = finish.getItem().getItem();

		if (Configuration.RABBIT_STEW_BUFF.get() && food.equals(Items.RABBIT_STEW)) {
			finish.getEntity().addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 1));
		}

		if (Configuration.VANILLA_SOUP_EXTRA_EFFECTS.get()) {
			FoodProperties soupEffects = FoodValues.VANILLA_SOUP_EFFECTS.get(food);

			if (soupEffects != null) {
				for (FoodProperties.PossibleEffect effect : soupEffects.effects()) {
					finish.getEntity().addEffect(effect.effect());
				}
			}
		}
	}
}
