package vectorwing.farmersdelight.common.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.effect.NourishmentEffect;
import vectorwing.farmersdelight.common.effect.ComfortEffect;

public class ModEffects
{
	public static final Holder<MobEffect> NOURISHMENT = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, FarmersDelight.id("nourishment"), new NourishmentEffect());
	public static final Holder<MobEffect> COMFORT = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, FarmersDelight.id("comfort"), new ComfortEffect());

	public static void register() {
	}

	public static void touch() {

	}
}
