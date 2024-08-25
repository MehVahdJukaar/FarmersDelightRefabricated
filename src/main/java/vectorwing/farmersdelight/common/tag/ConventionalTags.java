package vectorwing.farmersdelight.common.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * References to tags under the c namespace.
 * These tags are generally used for crafting recipes across different mods within Fabric.
 */
public class ConventionalTags
{
	// Blocks that are efficiently mined with a Knife.
	public static final TagKey<Block> MINEABLE_WITH_KNIFE = cBlockTag("mineable/knife");

	public static final TagKey<Item> CROPS = cItemTag("crops");
	public static final TagKey<Item> CROPS_CABBAGE = cItemTag("crops/cabbage");
	public static final TagKey<Item> CROPS_ONION = cItemTag("crops/onion");
	public static final TagKey<Item> CROPS_RICE = cItemTag("crops/rice");
	public static final TagKey<Item> CROPS_TOMATO = cItemTag("crops/tomato");

	public static final TagKey<Item> FOODS_BERRIES = cItemTag("foods/berries");

	public static final TagKey<Item> FOODS_BREADS = cItemTag("foods/breads");
	public static final TagKey<Item> FOODS_BREADS_WHEAT = cItemTag("foods/breads/wheat");

	public static final TagKey<Item> FOODS_COOKED_FISHES = cItemTag("foods/cooked_fishes");
	public static final TagKey<Item> FOODS_COOKED_FISHES_COD = cItemTag("foods/cooked_fishes/cod");
	public static final TagKey<Item> FOODS_COOKED_FISHES_SALMON = cItemTag("foods/cooked_fishes/salmon");

	public static final TagKey<Item> FOODS_COOKED_MEATS = cItemTag("foods/cooked_meats");
	public static final TagKey<Item> FOODS_COOKED_MEATS_COOKED_BACON = cItemTag("foods/cooked_meats/cooked_bacon");
	public static final TagKey<Item> FOODS_COOKED_MEATS_COOKED_BEEF = cItemTag("foods/cooked_meats/cooked_beef");
	public static final TagKey<Item> FOODS_COOKED_MEATS_COOKED_CHICKEN = cItemTag("foods/cooked_meats/cooked_chicken");
	public static final TagKey<Item> FOODS_COOKED_MEATS_COOKED_PORK = cItemTag("foods/cooked_meats/cooked_pork");
	public static final TagKey<Item> FOODS_COOKED_MEATS_COOKED_MUTTON = cItemTag("foods/cooked_meats/cooked_mutton");
	public static final TagKey<Item> FOODS_COOKED_MEATS_COOKED_EGGS = cItemTag("foods/cooked_meats/cooked_eggs");

	public static final TagKey<Item> FOODS_DOUGHS = cItemTag("foods/doughs");
	public static final TagKey<Item> FOODS_DOUGHS_WHEAT = cItemTag("foods/doughs/wheat");

	public static final TagKey<Item> FOODS_PASTAS = cItemTag("foods/pastas");
	public static final TagKey<Item> FOODS_PASTA_RAW_PASTAS = cItemTag("foods/pastas/raw_pastas");

	public static final TagKey<Item> FOODS_RAW_FISHES = cItemTag("foods/raw_fishes");
	public static final TagKey<Item> FOODS_RAW_FISHES_COD = cItemTag("foods/raw_fishes/cod");
	public static final TagKey<Item> FOODS_RAW_FISHES_SALMON = cItemTag("foods/raw_fishes/salmon");
	public static final TagKey<Item> FOODS_RAW_FISHES_TROPICAL = cItemTag("foods/raw_fishes/tropical_fish");

	public static final TagKey<Item> FOODS_RAW_MEATS = cItemTag("foods/raw_meats");
	public static final TagKey<Item> FOODS_RAW_MEATS_RAW_BACON = cItemTag("foods/raw_meats/raw_bacon");
	public static final TagKey<Item> FOODS_RAW_MEATS_RAW_BEEF = cItemTag("foods/raw_meats/raw_beef");
	public static final TagKey<Item> FOODS_RAW_MEATS_RAW_CHICKEN = cItemTag("foods/raw_meats/raw_chicken");
	public static final TagKey<Item> FOODS_RAW_MEATS_RAW_PORK = cItemTag("foods/raw_meats/raw_pork");
	public static final TagKey<Item> FOODS_RAW_MEATS_RAW_MUTTON = cItemTag("foods/raw_meats/raw_mutton");

	public static final TagKey<Item> FOODS_VEGETABLES = cItemTag("foods/vegetables");
	public static final TagKey<Item> FOODS_VEGETABLES_BEETROOT = cItemTag("foods/vegetables/beetroots");
	public static final TagKey<Item> FOODS_VEGETABLES_CARROT = cItemTag("foods/vegetables/carrots");
	public static final TagKey<Item> FOODS_VEGETABLES_ONION = cItemTag("foods/vegetables/onions");
	public static final TagKey<Item> FOODS_VEGETABLES_POTATO = cItemTag("foods/vegetables/potatoes");
	public static final TagKey<Item> FOODS_VEGETABLES_TOMATO = cItemTag("foods/vegetables/tomatoes");

	public static final TagKey<Item> GRAINS = cItemTag("grains");
	public static final TagKey<Item> GRAINS_WHEATS = cItemTag("grains/wheat");
	public static final TagKey<Item> GRAINS_RICES = cItemTag("grains/rice");

	public static final TagKey<Item> MILKS = cItemTag("milks");
	public static final TagKey<Item> MILK_BUCKET = cItemTag("milks/milk_buckets");
	public static final TagKey<Item> MILK_BOTTLE = cItemTag("milks/milk_bottles");

	public static final TagKey<Item> SALAD_INGREDIENTS = cItemTag("salad_ingredients");
	public static final TagKey<Item> SALAD_INGREDIENTS_CABBAGE = cItemTag("salad_ingredients/cabbage");

	public static final TagKey<Item> SEEDS = cItemTag("seeds");
	public static final TagKey<Item> SEEDS_CABBAGE = cItemTag("seeds/cabbage");
	public static final TagKey<Item> SEEDS_RICE = cItemTag("seeds/rice");
	public static final TagKey<Item> SEEDS_TOMATO = cItemTag("seeds/tomato");

	public static final TagKey<Item> TOOLS_KNIVES = cItemTag("tools/knives");

	private static TagKey<Block> cBlockTag(String path) {
		return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", path));
	}

	private static TagKey<Item> cItemTag(String path) {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", path));
	}

}
