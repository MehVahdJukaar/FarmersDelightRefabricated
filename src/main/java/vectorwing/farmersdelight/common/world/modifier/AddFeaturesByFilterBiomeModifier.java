package vectorwing.farmersdelight.common.world.modifier;

import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Optional;

/**
 * Deprecated: Look at {@link vectorwing.farmersdelight.common.registry.ModBiomeModifiers} instead.
 */
@Deprecated
public record AddFeaturesByFilterBiomeModifier(
		HolderSet<Biome> allowedBiomes,
		Optional<HolderSet<Biome>> deniedBiomes,
		Optional<Float> minimumTemperature,
		Optional<Float> maximumTemperature,
		HolderSet<PlacedFeature> features,
		GenerationStep.Decoration step
)
{
	/*
	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD && this.allowedBiomes.contains(biome)) {
			if (deniedBiomes.isPresent() && this.deniedBiomes.get().contains(biome)) {
				return;
			}
			if (minimumTemperature.isPresent() && biome.value().getBaseTemperature() < minimumTemperature.get()) {
				return;
			}
			if (maximumTemperature.isPresent() && biome.value().getBaseTemperature() > maximumTemperature.get()) {
				return;
			}
			BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
			this.features.forEach(holder -> generationSettings.addFeature(this.step, holder));
		}
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return ModBiomeModifiers.ADD_FEATURES_BY_FILTER.get();
	}
	 */
}
