package vectorwing.farmersdelight.common.registry;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.effect.ComfortEffect;
import vectorwing.farmersdelight.common.effect.NourishmentEffect;

import java.util.function.Supplier;

public class ModEffects {
    public static final Supplier<MobEffect> NOURISHMENT = Suppliers.memoize(() ->
            Registry.register(BuiltInRegistries.MOB_EFFECT, FarmersDelight.res("nourishment"), new NourishmentEffect()));

	public static final Supplier<MobEffect> COMFORT = Suppliers.memoize(() ->
			Registry.register(BuiltInRegistries.MOB_EFFECT, FarmersDelight.res("comfort"), new ComfortEffect()));
}
