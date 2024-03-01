package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.world.modifier.AddFeaturesByFilterBiomeModifier;

import java.util.Optional;
import java.util.function.Predicate;

public class ModBiomeModifiers {

    public static RegistryObject<Codec<AddFeaturesByFilterBiomeModifier>> ADD_FEATURES_BY_FILTER = BIOME_MODIFIER_SERIALIZERS.register("add_features_by_filter", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("allowed_biomes").forGetter(AddFeaturesByFilterBiomeModifier::allowedBiomes),
                    Biome.LIST_CODEC.optionalFieldOf("denied_biomes").orElse(Optional.empty()).forGetter(AddFeaturesByFilterBiomeModifier::deniedBiomes),
                    Codec.FLOAT.optionalFieldOf("min_temperature").orElse(Optional.empty()).forGetter(AddFeaturesByFilterBiomeModifier::minimumTemperature),
                    Codec.FLOAT.optionalFieldOf("max_temperature").orElse(Optional.empty()).forGetter(AddFeaturesByFilterBiomeModifier::maximumTemperature),

                    PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(AddFeaturesByFilterBiomeModifier::features),
                    GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(AddFeaturesByFilterBiomeModifier::step)
            ).apply(builder, AddFeaturesByFilterBiomeModifier::new)));

    public static void addFeaturesToBiome() {

    }

    public static void init() {
        BiomeModifications.addFeature(biomeSelectionContext -> true,
                GenerationStep.Decoration.VEGETAL_DECORATION, ModFeatures.CABBAGE_PATCH.get());
    }

    public record FDBiomeSelector(float minTemperature, float maxTemperature, TagKey<Biome> allowed,
                                  TagKey<Biome> denied)
            implements Predicate<BiomeSelectionContext> {

        @Override
        public boolean test(BiomeSelectionContext biomeSelectionContext) {
            return false;
        }
    }
}
