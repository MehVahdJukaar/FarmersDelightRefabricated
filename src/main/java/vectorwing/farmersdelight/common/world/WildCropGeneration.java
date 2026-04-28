package vectorwing.farmersdelight.common.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import vectorwing.farmersdelight.FarmersDelight;

@SuppressWarnings("SameParameterValue")
public class WildCropGeneration
{

	// Those are unused, but kept for reference just in case
    public static ResourceKey<ConfiguredFeature<?, ?>> SANDY_SHRUB = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "sandy_shrub"));
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_CABBAGES = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_cabbages"));
	public static ResourceKey<ConfiguredFeature<?, ?>> WILD_ONIONS = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_onions"));
	public static ResourceKey<ConfiguredFeature<?, ?>> WILD_TOMATOES = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_tomatoes"));
	public static ResourceKey<ConfiguredFeature<?, ?>> WILD_CARROTS = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_carrots"));
	public static ResourceKey<ConfiguredFeature<?, ?>> WILD_POTATOES = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_potatoes"));
	public static ResourceKey<ConfiguredFeature<?, ?>> WILD_BEETROOTS = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_beetroots"));
	public static ResourceKey<ConfiguredFeature<?, ?>> WILD_RICE = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_rice"));
	public static ResourceKey<ConfiguredFeature<?, ?>> BROWN_MUSHROOM_COLONIES = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "brown_mushroom_colony"));
	public static ResourceKey<ConfiguredFeature<?, ?>> RED_MUSHROOM_COLONIES = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "red_mushroom_colony"));

    public static ResourceKey<PlacedFeature> PATCH_SANDY_SHRUB = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "patch_sandy_shrub"));
    public static ResourceKey<PlacedFeature> PATCH_WILD_CABBAGES = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "patch_wild_cabbages"));
	public static ResourceKey<PlacedFeature> PATCH_WILD_ONIONS = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "patch_wild_onions"));
	public static ResourceKey<PlacedFeature> PATCH_WILD_TOMATOES = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "patch_wild_tomatoes"));
	public static ResourceKey<PlacedFeature> PATCH_WILD_CARROTS = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "patch_wild_carrots"));
	public static ResourceKey<PlacedFeature> PATCH_WILD_POTATOES = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "patch_wild_potatoes"));
	public static ResourceKey<PlacedFeature> PATCH_WILD_BEETROOTS = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "patch_wild_beetroots"));
	public static ResourceKey<PlacedFeature> PATCH_WILD_RICE = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "patch_wild_rice"));
	public static ResourceKey<PlacedFeature> PATCH_BROWN_MUSHROOM_COLONIES = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "patch_brown_mushroom_colony"));
	public static ResourceKey<PlacedFeature> PATCH_RED_MUSHROOM_COLONIES = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "patch_red_mushroom_colony"));

	public static void load() {
	}

	// FIXME: This.
    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
//        context.register(FEATURE_PATCH_SANDY_SHRUB, new ConfiguredFeature<>(
//                Feature.RANDOM_PATCH,
//                new RandomPatchConfiguration(32, 2, 3,
//                        plantPlacedFeature(ModBlocks.SANDY_SHRUB.get(), BlockTags.SAND))
//        ));
//        context.register(FEATURE_PATCH_WILD_CABBAGES, wildCropConfiguredFeature(
//                ModBlocks.WILD_CABBAGES.get(),
//                ModBlocks.SANDY_SHRUB.get(),
//                BlockTags.SAND
//        ));
//        context.register(FEATURE_PATCH_WILD_BEETROOTS, wildCropConfiguredFeature(
//                ModBlocks.WILD_BEETROOTS.get(),
//                ModBlocks.SANDY_SHRUB.get(),
//                BlockTags.SAND
//        ));
//        context.register(FEATURE_PATCH_WILD_CARROTS, wildCropConfiguredFeature(
//                ModBlocks.WILD_CARROTS.get(),
//                Blocks.SHORT_GRASS,
//                Blocks.COARSE_DIRT,
//                BlockTags.DIRT
//        ));
//        context.register(FEATURE_PATCH_WILD_ONIONS, wildCropConfiguredFeature(
//                ModBlocks.WILD_ONIONS.get(),
//                Blocks.ALLIUM,
//                BlockTags.DIRT
//        ));
//        context.register(FEATURE_PATCH_WILD_POTATOES, wildCropConfiguredFeature(
//                ModBlocks.WILD_POTATOES.get(),
//                Blocks.FERN,
//                BlockTags.DIRT
//        ));
//        context.register(FEATURE_PATCH_WILD_TOMATOES, wildCropConfiguredFeature(
//                ModBlocks.WILD_TOMATOES.get(), Blocks.DEAD_BUSH, ModTags.Blocks.TERRAIN
//        ));
//        context.register(FEATURE_PATCH_WILD_RICE, new ConfiguredFeature<>(
//                ModBiomeFeatures.WILD_RICE.get(),
//                new RandomPatchConfiguration(96, 7, 3,
//                        plantPlacedFeature(ModBlocks.WILD_RICE.get(), BlockTags.DIRT)
//                )
//        ));
//        context.register(FEATURE_PATCH_BROWN_MUSHROOM_COLONIES, mushroomColonyConfiguredFeature(
//                ModBlocks.BROWN_MUSHROOM_COLONY.get(),
//                Blocks.BROWN_MUSHROOM
//        ));
//        context.register(FEATURE_PATCH_RED_MUSHROOM_COLONIES, mushroomColonyConfiguredFeature(
//                ModBlocks.RED_MUSHROOM_COLONY.get(),
//                Blocks.RED_MUSHROOM
//        ));
    }

    public static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
//        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureLookup = context.lookup(Registries.CONFIGURED_FEATURE);
//        context.register(PATCH_WILD_CABBAGES, createPlacedFeature(configuredFeatureLookup, FEATURE_PATCH_WILD_CABBAGES, 30));
//        context.register(PATCH_WILD_ONIONS, createPlacedFeature(configuredFeatureLookup, FEATURE_PATCH_WILD_ONIONS, 120));
//        context.register(PATCH_WILD_TOMATOES, createPlacedFeature(configuredFeatureLookup, FEATURE_PATCH_WILD_TOMATOES, 100));
//        context.register(PATCH_WILD_CARROTS, createPlacedFeature(configuredFeatureLookup, FEATURE_PATCH_WILD_CARROTS, 120));
//        context.register(PATCH_WILD_POTATOES, createPlacedFeature(configuredFeatureLookup, FEATURE_PATCH_WILD_POTATOES, 100));
//        context.register(PATCH_WILD_BEETROOTS, createPlacedFeature(configuredFeatureLookup, FEATURE_PATCH_WILD_BEETROOTS, 30));
//        context.register(PATCH_WILD_RICE, createPlacedFeature(configuredFeatureLookup, FEATURE_PATCH_WILD_RICE, 20));
//        context.register(PATCH_BROWN_MUSHROOM_COLONIES, createPlacedFeature(configuredFeatureLookup, FEATURE_PATCH_BROWN_MUSHROOM_COLONIES, 15));
//        context.register(PATCH_RED_MUSHROOM_COLONIES, createPlacedFeature(configuredFeatureLookup, FEATURE_PATCH_RED_MUSHROOM_COLONIES, 15));
    }

}
