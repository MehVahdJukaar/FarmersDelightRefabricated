package vectorwing.farmersdelight.common.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;
import vectorwing.farmersdelight.FarmersDelight;

@SuppressWarnings("SameParameterValue")
public class WildCropGeneration
{

	// Those are unused, but kept for reference just in case
    public static ResourceKey<Feature> SANDY_SHRUB = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "sandy_shrub"));
    public static ResourceKey<Feature> WILD_CABBAGES = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_cabbages"));
	public static ResourceKey<Feature> WILD_ONIONS = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_onions"));
	public static ResourceKey<Feature> WILD_TOMATOES = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_tomatoes"));
	public static ResourceKey<Feature> WILD_CARROTS = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_carrots"));
	public static ResourceKey<Feature> WILD_POTATOES = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_potatoes"));
	public static ResourceKey<Feature> WILD_BEETROOTS = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_beetroots"));
	public static ResourceKey<Feature> WILD_RICE = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "wild_rice"));
	public static ResourceKey<Feature> BROWN_MUSHROOM_COLONIES = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "brown_mushroom_colony"));
	public static ResourceKey<Feature> RED_MUSHROOM_COLONIES = ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "red_mushroom_colony"));

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
    public static void bootstrapConfiguredFeatures(BootstrapContext<Feature> context) {
//        context.register(FEATURE_PATCH_SANDY_SHRUB, new Feature<>(
//                Feature.RANDOM_PATCH,
//                new RandomPatchConfiguration(32, 2, 3,
//                        plantPlacedFeature(ModBlocks.SANDY_SHRUB.get(), BlockTags.SAND))
//        ));
//        context.register(FEATURE_PATCH_WILD_CABBAGES, wildCropFeature(
//                ModBlocks.WILD_CABBAGES.get(),
//                ModBlocks.SANDY_SHRUB.get(),
//                BlockTags.SAND
//        ));
//        context.register(FEATURE_PATCH_WILD_BEETROOTS, wildCropFeature(
//                ModBlocks.WILD_BEETROOTS.get(),
//                ModBlocks.SANDY_SHRUB.get(),
//                BlockTags.SAND
//        ));
//        context.register(FEATURE_PATCH_WILD_CARROTS, wildCropFeature(
//                ModBlocks.WILD_CARROTS.get(),
//                Blocks.SHORT_GRASS,
//                Blocks.COARSE_DIRT,
//                BlockTags.DIRT
//        ));
//        context.register(FEATURE_PATCH_WILD_ONIONS, wildCropFeature(
//                ModBlocks.WILD_ONIONS.get(),
//                Blocks.ALLIUM,
//                BlockTags.DIRT
//        ));
//        context.register(FEATURE_PATCH_WILD_POTATOES, wildCropFeature(
//                ModBlocks.WILD_POTATOES.get(),
//                Blocks.FERN,
//                BlockTags.DIRT
//        ));
//        context.register(FEATURE_PATCH_WILD_TOMATOES, wildCropFeature(
//                ModBlocks.WILD_TOMATOES.get(), Blocks.DEAD_BUSH, ModTags.Blocks.TERRAIN
//        ));
//        context.register(FEATURE_PATCH_WILD_RICE, new Feature<>(
//                ModBiomeFeatures.WILD_RICE.get(),
//                new RandomPatchConfiguration(96, 7, 3,
//                        plantPlacedFeature(ModBlocks.WILD_RICE.get(), BlockTags.DIRT)
//                )
//        ));
//        context.register(FEATURE_PATCH_BROWN_MUSHROOM_COLONIES, mushroomColonyFeature(
//                ModBlocks.BROWN_MUSHROOM_COLONY.get(),
//                Blocks.BROWN_MUSHROOM
//        ));
//        context.register(FEATURE_PATCH_RED_MUSHROOM_COLONIES, mushroomColonyFeature(
//                ModBlocks.RED_MUSHROOM_COLONY.get(),
//                Blocks.RED_MUSHROOM
//        ));
    }

    public static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
//        HolderGetter<Feature> configuredFeatureLookup = context.lookup(Registries.FEATURE);
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
