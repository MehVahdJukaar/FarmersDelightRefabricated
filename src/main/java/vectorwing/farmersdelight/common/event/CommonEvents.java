package vectorwing.farmersdelight.common.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CommonEvents {

    /*
     * Now handled through default components
     */
    @Deprecated
    public static void onItemUseFinished(Level level, LivingEntity livingEntity, ItemStack stack) {
        handleVanillaSoupEffects(level, livingEntity, stack);
    }

    /*
     * Now handled through default components
     */
    @Deprecated
    public static void handleVanillaSoupEffects(Level level, LivingEntity livingEntity, ItemStack stack) {
//        Item food = stack.getItem();

//        if (Configuration.RABBIT_STEW_BUFF.get() && food.equals(Items.RABBIT_STEW)) {
//            livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 1));
//        }

//        if (Configuration.VANILLA_SOUP_EXTRA_EFFECTS.get()) {
//            FoodProperties soupEffects = FoodValues.VANILLA_SOUP_EFFECTS.get(food);

//            if (soupEffects != null) {
//                for (FoodProperties.PossibleEffect effect : soupEffects.effects()) {
//                    livingEntity.addEffect(effect.effect());
//                }
//            }
//        }
    }

}
