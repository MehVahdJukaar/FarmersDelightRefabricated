package vectorwing.farmersdelight.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.effect.NourishmentEffect;
import vectorwing.farmersdelight.common.effect.ComfortEffect;

import java.util.function.Supplier;

public class ModEffects
{
	public static final Supplier<MobEffect> NOURISHMENT = NourishmentEffect::new;
	public static final Supplier<MobEffect> COMFORT = ComfortEffect::new;

    // There was some hack here. I'm tired boss.
    public static void register() {
        Registry.register(BuiltInRegistries.MOB_EFFECT, FarmersDelight.res("nourishment"), NOURISHMENT.get());
        Registry.register(BuiltInRegistries.MOB_EFFECT, FarmersDelight.res("comfort"), COMFORT.get());
    }
}
