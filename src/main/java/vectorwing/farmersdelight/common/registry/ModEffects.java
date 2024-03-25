package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.effect.ComfortEffect;
import vectorwing.farmersdelight.common.effect.NourishmentEffect;

public class ModEffects
{
	public static final LazyRegistrar<MobEffect> EFFECTS = LazyRegistrar.create(BuiltInRegistries.MOB_EFFECT, FarmersDelight.MODID);

	public static final RegistryObject<MobEffect> NOURISHMENT = EFFECTS.register("nourishment", NourishmentEffect::new);
	public static final RegistryObject<MobEffect> COMFORT = EFFECTS.register("comfort", ComfortEffect::new);
}
