package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import vectorwing.farmersdelight.FarmersDelight;

public class ModParticleTypes
{
	public static final LazyRegistrar<ParticleType<?>> PARTICLE_TYPES = LazyRegistrar.create(BuiltInRegistries.PARTICLE_TYPE, FarmersDelight.MODID);

	public static final RegistryObject<SimpleParticleType> STAR = PARTICLE_TYPES.register("star",
			() -> FabricParticleTypes.simple(true));
	public static final RegistryObject<SimpleParticleType> STEAM = PARTICLE_TYPES.register("steam",
			() -> FabricParticleTypes.simple(true));
}
