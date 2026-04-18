package vectorwing.farmersdelight.common.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regParticle;

public class ModParticleTypes {

	public static final Supplier<SimpleParticleType> STAR = regParticle("star",
			() -> FabricParticleTypes.simple(true));
	public static final Supplier<SimpleParticleType> STEAM = regParticle("steam",
			() -> FabricParticleTypes.simple(true));
	public static final Supplier<SimpleParticleType> SPARKLE = regParticle("sparkle",
			() -> FabricParticleTypes.simple(true));
    public static void touch() {

    }
}
