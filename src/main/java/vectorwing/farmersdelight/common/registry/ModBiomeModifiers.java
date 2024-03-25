package vectorwing.farmersdelight.common.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.function.Predicate;

public class ModBiomeModifiers {

    private static final ResourceKey<PlacedFeature> BROWN_COLONY = modFeature("patch_brown_mushroom_colony");
    private static final ResourceKey<PlacedFeature> RED_COLONY = modFeature("patch_red_mushroom_colony");
    private static final ResourceKey<PlacedFeature> WILD_CABBAGE = modFeature("patch_wild_cabbages");
    private static final ResourceKey<PlacedFeature> WILD_BEETROOT = modFeature("patch_wild_beetroots");
    private static final ResourceKey<PlacedFeature> WILD_CARROTS = modFeature("patch_wild_carrots");
    private static final ResourceKey<PlacedFeature> WILD_ONIONS = modFeature("patch_wild_onions");
    private static final ResourceKey<PlacedFeature> WILD_TOMATOES = modFeature("patch_wild_tomatoes");
    private static final ResourceKey<PlacedFeature> WILD_POTATOES = modFeature("patch_wild_potatoes");
    private static final ResourceKey<PlacedFeature> WILD_RICE = modFeature("patch_wild_rice");

    @NotNull
    private static ResourceKey<PlacedFeature> modFeature(String red_colony) {
        return ResourceKey.create(Registries.PLACED_FEATURE, FarmersDelight.res(red_colony));
    }

    public static void init() {
        BiomeModifications.addFeature(new FDBiomeSelector(ModTags.HAS_BROWN_MUSHROOM_COLONY),
                GenerationStep.Decoration.VEGETAL_DECORATION, BROWN_COLONY);
        BiomeModifications.addFeature(new FDBiomeSelector(ModTags.HAS_RED_MUSHROOM_COLONY),
                GenerationStep.Decoration.VEGETAL_DECORATION, RED_COLONY);
        BiomeModifications.addFeature(new FDBiomeSelector(ModTags.HAS_WILD_CABBAGE),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_CABBAGE);

        BiomeModifications.addFeature(new FDBiomeSelector(ModTags.HAS_WILD_BEETROOTS),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_BEETROOT);

        BiomeModifications.addFeature(new FDBiomeSelector(0.4f, 0.9f,
                        ModTags.WILD_CARROTS_WHITELIST, ModTags.WILD_CARROTS_BLACKLIST),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_CARROTS);

        BiomeModifications.addFeature(new FDBiomeSelector(0.4f, 0.9f,
                        ModTags.WILD_ONIONS_WHITELIST, ModTags.WILD_ONIONS_BLACKLIST),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_ONIONS);

        BiomeModifications.addFeature(new FDBiomeSelector(0.1f, 0.3f,
                        ModTags.WILD_POTATOES_WHITELIST, ModTags.WILD_POTATOES_BLACKLIST),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_POTATOES);

        BiomeModifications.addFeature(new FDBiomeSelector(-4, 4,
                        ModTags.WILD_RICE_WHITELIST, ModTags.WILD_RICE_BLACKLIST),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_RICE);

        BiomeModifications.addFeature(new FDBiomeSelector(-4f, 4f,
                        ModTags.WILD_TOMATOES_WHITELIST, ModTags.WILD_TOMATOES_BLACKLIST),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_TOMATOES);
    }

    //TODO: use humidity too? for rice
    public record FDBiomeSelector(float minTemperature, float maxTemperature, TagKey<Biome> allowed,
                                  @Nullable TagKey<Biome> denied) implements Predicate<BiomeSelectionContext> {

        public FDBiomeSelector(TagKey<Biome> tagKey) {
            this(-4f, 4f, tagKey, null);
        }

        @Override
        public boolean test(BiomeSelectionContext context) {
            Holder<Biome> biome = context.getBiomeRegistryEntry();
            float temp = biome.value().getBaseTemperature();
            if (denied != null && biome.is(denied)) return false;
            return biome.is(allowed) && temp >= minTemperature && temp <= maxTemperature;
        }
    }
}
