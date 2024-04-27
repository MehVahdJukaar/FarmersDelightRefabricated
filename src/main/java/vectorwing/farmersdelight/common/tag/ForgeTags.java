package vectorwing.farmersdelight.common.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * References to tags under the Forge namespace.
 * These tags are generally used for crafting recipes across different mods.
 * <p>
 * Deprecated but modified and kept for the sake of multiplatform mods.
 * Use {@link ConventionalTags} instead as a Fabric only dev.
 */
@Deprecated
public class ForgeTags
{
	// Blocks that are efficiently mined with a Knife.
	public static final TagKey<Block> MINEABLE_WITH_KNIFE = forgeBlockTag("mineable/knife");

	public static final TagKey<Item> BERRIES = forgeItemTag("foods/berries");

	public static final TagKey<Item> BREAD = forgeItemTag("foods/breads");
	public static final TagKey<Item> BREAD_WHEAT = forgeItemTag("foods/breads/wheat");

	public static final TagKey<Item> COOKED_BACON = forgeItemTag("foods/cooked_meats/cooked_bacon");
	public static final TagKey<Item> COOKED_BEEF = forgeItemTag("foods/cooked_meats/cooked_beef");
	public static final TagKey<Item> COOKED_CHICKEN = forgeItemTag("foods/cooked_meats/cooked_chicken");
	public static final TagKey<Item> COOKED_PORK = forgeItemTag("foods/cooked_meats/cooked_pork");
	public static final TagKey<Item> COOKED_MUTTON = forgeItemTag("foods/cooked_meats/cooked_mutton");
	public static final TagKey<Item> COOKED_EGGS = forgeItemTag("foods/cooked_meats/cooked_eggs");
	public static final TagKey<Item> COOKED_FISHES = forgeItemTag("foods/cooked_fishes");
	public static final TagKey<Item> COOKED_FISHES_COD = forgeItemTag("foods/cooked_fishes/cod");
	public static final TagKey<Item> COOKED_FISHES_SALMON = forgeItemTag("foods/cooked_fishes/salmon");

	public static final TagKey<Item> CROPS = forgeItemTag("crops");
	public static final TagKey<Item> CROPS_CABBAGE = forgeItemTag("crops/cabbage");
	public static final TagKey<Item> CROPS_ONION = forgeItemTag("crops/onion");
	public static final TagKey<Item> CROPS_RICE = forgeItemTag("crops/rice");
	public static final TagKey<Item> CROPS_TOMATO = forgeItemTag("crops/tomato");

	public static final TagKey<Item> DOUGH = forgeItemTag("foods/doughs");
	public static final TagKey<Item> DOUGH_WHEAT = forgeItemTag("foods/doughs/wheat");

	public static final TagKey<Item> EGGS = forgeItemTag("eggs");

	public static final TagKey<Item> GRAIN = forgeItemTag("grains");
	public static final TagKey<Item> GRAIN_WHEATS = forgeItemTag("grains/wheats");
	public static final TagKey<Item> GRAIN_RICES = forgeItemTag("grains/rices");

	public static final TagKey<Item> MILK = forgeItemTag("milks");
	public static final TagKey<Item> MILK_BUCKET = forgeItemTag("milks/milk_buckets");
	public static final TagKey<Item> MILK_BOTTLE = forgeItemTag("milk/milk_bottles");

	public static final TagKey<Item> PASTA = forgeItemTag("food/pastas");
	public static final TagKey<Item> PASTA_RAW_PASTA = forgeItemTag("food/pastas/raw_pasta");

	public static final TagKey<Item> RAW_BACON = forgeItemTag("foods/raw_meats/raw_bacon");
	public static final TagKey<Item> RAW_BEEF = forgeItemTag("foods/raw_meats/raw_beef");
	public static final TagKey<Item> RAW_CHICKEN = forgeItemTag("foods/raw_meats/raw_chicken");
	public static final TagKey<Item> RAW_PORK = forgeItemTag("foods/raw_meats/raw_pork");
	public static final TagKey<Item> RAW_MUTTON = forgeItemTag("foods/raw_meats/raw_mutton");
	public static final TagKey<Item> RAW_FISHES = forgeItemTag("foods/raw_fishes");
	public static final TagKey<Item> RAW_FISHES_COD = forgeItemTag("foods/raw_fishes/cod");
	public static final TagKey<Item> RAW_FISHES_SALMON = forgeItemTag("foods/raw_fishes/salmon");
	public static final TagKey<Item> RAW_FISHES_TROPICAL = forgeItemTag("foods/raw_fishes/tropical_fish");

	public static final TagKey<Item> SALAD_INGREDIENTS = forgeItemTag("salad_ingredients");
	public static final TagKey<Item> SALAD_INGREDIENTS_CABBAGE = forgeItemTag("salad_ingredients/cabbages");

	public static final TagKey<Item> SEEDS = forgeItemTag("seeds");
	public static final TagKey<Item> SEEDS_CABBAGE = forgeItemTag("seeds/cabbage");
	public static final TagKey<Item> SEEDS_RICE = forgeItemTag("seeds/rice");
	public static final TagKey<Item> SEEDS_TOMATO = forgeItemTag("seeds/tomato");

	public static final TagKey<Item> VEGETABLES = forgeItemTag("foods/vegetables");
	public static final TagKey<Item> VEGETABLES_BEETROOT = forgeItemTag("foods/vegetables/beetroots");
	public static final TagKey<Item> VEGETABLES_CARROT = forgeItemTag("foods/vegetables/carrots");
	public static final TagKey<Item> VEGETABLES_ONION = forgeItemTag("foods/vegetables/onions");
	public static final TagKey<Item> VEGETABLES_POTATO = forgeItemTag("foods/vegetables/potatoes");
	public static final TagKey<Item> VEGETABLES_TOMATO = forgeItemTag("foods/vegetables/tomatoes");

	public static final TagKey<Item> TOOLS = forgeItemTag("tools");
	public static final TagKey<Item> TOOLS_AXES = minecraftItemTag("axes");
	public static final TagKey<Item> TOOLS_KNIVES = forgeItemTag("tools/knives");
	public static final TagKey<Item> TOOLS_PICKAXES = minecraftItemTag("pickaxes");
	public static final TagKey<Item> TOOLS_SHOVELS = minecraftItemTag("shovels");

	private static TagKey<Block> forgeBlockTag(String path) {
		// Change namespace to 'c'. Porting Lib does this too.
		return TagKey.create(Registries.BLOCK, new ResourceLocation("c", path));
	}

	private static TagKey<Item> forgeItemTag(String path) {
		// Change namespace to 'c'. Porting Lib does this too.
		return TagKey.create(Registries.ITEM, new ResourceLocation("c", path));
	}

	private static TagKey<Item> minecraftItemTag(String path) {
		// Change namespace to 'c'. Porting Lib does this too.
		return TagKey.create(Registries.ITEM, new ResourceLocation(path));
	}

}
