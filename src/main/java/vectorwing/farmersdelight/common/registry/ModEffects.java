package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.effect.ComfortEffect;
import vectorwing.farmersdelight.common.effect.NourishmentEffect;

public class ModEffects
{
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, FarmersDelight.MODID);

	public static final Holder<MobEffect> NOURISHMENT = EFFECTS.register("nourishment", NourishmentEffect::new);
	public static final Holder<MobEffect> COMFORT = EFFECTS.register("comfort", ComfortEffect::new);
}
