package vectorwing.farmersdelight.refabricated;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.FarmersDelight;

public class FDRefabricatedTags {
	// TODO: 26.1.x: Swap out whitelist and blacklist tags for 'fabric:remove' entries.
	public static class Biomes {
		public static final TagKey<Biome> HAS_BROWN_MUSHROOM_COLONY = modBiomeTag("has_brown_mushroom_colony");
		public static final TagKey<Biome> HAS_RED_MUSHROOM_COLONY = modBiomeTag("has_red_mushroom_colony");
		public static final TagKey<Biome> HAS_WILD_CABBAGE = modBiomeTag("has_wild_cabbage");
		public static final TagKey<Biome> HAS_WILD_BEETROOTS = modBiomeTag("has_wild_beetroots");
		public static final TagKey<Biome> WILD_CARROTS_WHITELIST = modBiomeTag("wild_carrots_whitelist");
		public static final TagKey<Biome> WILD_CARROTS_BLACKLIST = modBiomeTag("wild_carrots_blacklist");
		public static final TagKey<Biome> WILD_ONIONS_WHITELIST = modBiomeTag("wild_onions_whitelist");
		public static final TagKey<Biome> WILD_ONIONS_BLACKLIST = modBiomeTag("wild_onions_blacklist");
		public static final TagKey<Biome> WILD_POTATOES_WHITELIST = modBiomeTag("wild_potatoes_whitelist");
		public static final TagKey<Biome> WILD_POTATOES_BLACKLIST = modBiomeTag("wild_potatoes_blacklist");
		public static final TagKey<Biome> WILD_RICE_WHITELIST = modBiomeTag("wild_rice_whitelist");
		public static final TagKey<Biome> WILD_RICE_BLACKLIST = modBiomeTag("wild_rice_blacklist");
		public static final TagKey<Biome> WILD_TOMATOES_WHITELIST = modBiomeTag("wild_tomatoes_whitelist");
		public static final TagKey<Biome> WILD_TOMATOES_BLACKLIST = modBiomeTag("wild_tomatoes_blacklist");
	}

	public static class Blocks {
		public static final TagKey<Block> KNIFE_INSTANTLY_MINES = modBlockTag("knife_instantly_mines");
		public static final TagKey<Block> GROWS_MUSHROOM_COLONIES = modBlockTag("grows_mushroom_colonies");
		public static final TagKey<Block> GROWS_WILD_CROPS = modBlockTag("grows_wild_crops");
		public static final TagKey<Block> SURVIVES_RICH_SOIL = modBlockTag("survives/rich_soil");
		public static final TagKey<Block> DOES_NOT_SURVIVE_RICH_SOIL = modBlockTag("does_not_survive/rich_soil");

		public static final TagKey<Block> SURVIVES_RICH_SOIL_FARMLAND = modBlockTag("survives/rich_soil_farmland");
		public static final TagKey<Block> DOES_NOT_SURVIVE_RICH_SOIL_FARMLAND = modBlockTag("does_not_survive/rich_soil_farmland");

	}

	public static class EntityTypes {
		public static final TagKey<EntityType<?>> DROPS_LEATHER = modEntityTag("drops_leather");
	}

	public static class Items {
		// Will likely be moved to base package when Farmer's Delight ports to 26.1.
		public static final TagKey<Item> FLINT_TOOL_MATERIALS = modItemTag("flint_tool_materials");
	}

	public static class MobEffects {
		public static final TagKey<MobEffect> MILK_BOTTLE_IGNORED = modEffectTag("ignored/milk_bottle");
		public static final TagKey<MobEffect> HOT_COCOA_IGNORED = modEffectTag("ignored/hot_cocoa");
	}


	private static TagKey<Biome> modBiomeTag(String path) {
		return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, path));
	}

	private static TagKey<Block> modBlockTag(String path) {
		return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, path));
	}

	private static TagKey<EntityType<?>> modEntityTag(String path) {
		return TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, path));
	}

	private static TagKey<Item> modItemTag(String path) {
		return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, path));
	}

	private static TagKey<MobEffect> modEffectTag(String path) {
		return TagKey.create(Registries.MOB_EFFECT, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, path));
	}

}
