package vectorwing.farmersdelight.common.world.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record InOrderFeatureConfiguration(HolderSet<PlacedFeature> features) implements FeatureConfiguration {
	public static final Codec<InOrderFeatureConfiguration> CODEC = RecordCodecBuilder.create((config) -> config.group(
			PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(InOrderFeatureConfiguration::features)
    ).apply(config, InOrderFeatureConfiguration::new));
}
