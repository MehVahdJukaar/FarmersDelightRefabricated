package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.item.consume_effects.ConsumeEffect;
import vectorwing.farmersdelight.common.item.component.consumable.HealConsumeEffect;
import vectorwing.farmersdelight.common.item.component.consumable.RemoveRandomStatusEffectsConsumeEffect;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regConsumeEffectType;

public class ModConsumeEffectTypes {
    public static final Supplier<ConsumeEffect.Type<RemoveRandomStatusEffectsConsumeEffect>> REMOVE_RANDOM_EFFECTS = regConsumeEffectType("remove_random_effects",
            () -> new ConsumeEffect.Type<>(RemoveRandomStatusEffectsConsumeEffect.CODEC, RemoveRandomStatusEffectsConsumeEffect.STREAM_CODEC));
    public static final Supplier<ConsumeEffect.Type<HealConsumeEffect>> HEAL = regConsumeEffectType("heal",
            () -> new ConsumeEffect.Type<>(HealConsumeEffect.CODEC, HealConsumeEffect.STREAM_CODEC));

    public static void touch() {}
}
