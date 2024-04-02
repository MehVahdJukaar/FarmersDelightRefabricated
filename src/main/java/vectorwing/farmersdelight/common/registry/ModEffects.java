package vectorwing.farmersdelight.common.registry;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.effect.ComfortEffect;
import vectorwing.farmersdelight.common.effect.NourishmentEffect;

import java.util.function.Supplier;

public class ModEffects {
    // we need to register immediately so addons can use them if they happen to load before FD
    public static final Supplier<MobEffect> NOURISHMENT = register("nourishment", NourishmentEffect::new);


    public static final Supplier<MobEffect> COMFORT = register("comfort", ComfortEffect::new);

    @NotNull
    private static <T extends MobEffect> Supplier<T> register(String id, Supplier<T> supplier) {
        return Suppliers.memoize(() ->
                Registry.register(BuiltInRegistries.MOB_EFFECT, FarmersDelight.res(id), supplier.get()));
    }

}
