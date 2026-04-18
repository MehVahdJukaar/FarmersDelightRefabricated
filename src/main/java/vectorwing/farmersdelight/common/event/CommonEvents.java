package vectorwing.farmersdelight.common.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.FoodValues;

public class CommonEvents {
    //called by mixin

    public static void onItemUseFinished(Level level, LivingEntity livingEntity, ItemStack stack) {
        handleVanillaSoupEffects(level, livingEntity, stack);
    }

    public static void handleVanillaSoupEffects(Level level, LivingEntity livingEntity, ItemStack stack) {
        Item food = stack.getItem();

        if (Configuration.ENABLE_RABBIT_STEW_BUFF.get() && food.equals(Items.RABBIT_STEW)) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 1));
        }

        if (Configuration.ENABLE_VANILLA_SOUP_EXTRA_EFFECTS.get()) {
            FoodProperties soupEffects = FoodValues.VANILLA_SOUP_EFFECTS.get(food);

            if (soupEffects != null) {
                for (FoodProperties.PossibleEffect effect : soupEffects.effects()) {
                    livingEntity.addEffect(effect.effect());
                }
            }
        }
    }

}
