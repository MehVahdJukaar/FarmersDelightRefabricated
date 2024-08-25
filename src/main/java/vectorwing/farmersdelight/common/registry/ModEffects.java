package vectorwing.farmersdelight.common.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.effect.ComfortEffect;
import vectorwing.farmersdelight.common.effect.NourishmentEffect;

public class ModEffects
{
	public static final Holder<MobEffect> NOURISHMENT = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, FarmersDelight.res("nourishment"), new NourishmentEffect());
	public static final Holder<MobEffect> COMFORT = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, FarmersDelight.res("comfort"), new ComfortEffect());

	public static void register() {
	}
}
