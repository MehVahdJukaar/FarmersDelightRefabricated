package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BlockItemTagAppender;
import net.minecraft.references.BlockItemId;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ColorCollection;
import org.jspecify.annotations.NonNull;
import vectorwing.farmersdelight.common.references.ModBlockItemIds;
import vectorwing.farmersdelight.common.references.ModItemIds;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.CompatibilityTags;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.refabricated.FDRefabricatedTags;

import java.util.concurrent.CompletableFuture;

public class ItemTags extends FabricTagsProvider.ItemTagsProvider
{
	public ItemTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider, FabricTagsProvider.BlockTagsProvider blockTagProvider) {
		super(output, provider, blockTagProvider);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		copy(ModTags.Blocks.WILD_CROPS, ModTags.Items.WILD_CROPS);
		copy(BlockTags.SMALL_FLOWERS, BlockItemTags.SMALL_FLOWERS.item());

		this.registerMinecraftTags();
		this.registerModTags();
		this.registerRefabricatedTags();
		this.registerConventionalTags();
		this.registerCommonTags();
		this.registerCompatibilityTags();
	}

	private void registerMinecraftTags() {
		tagBuilder(net.minecraft.tags.ItemTags.BREAKS_DECORATED_POTS).addTag(ModTags.Items.KNIVES);
		tagBuilder(net.minecraft.tags.ItemTags.PIGLIN_LOVED).add(ModItemIds.GOLDEN_KNIFE);
		tagBuilder(net.minecraft.tags.ItemTags.SIGNS).addTag(ModTags.Items.CANVAS_SIGNS);
		tagBuilder(net.minecraft.tags.ItemTags.HANGING_SIGNS).addTag(ModTags.Items.HANGING_CANVAS_SIGNS);
		tagBuilder(net.minecraft.tags.ItemTags.VILLAGER_PLANTABLE_SEEDS)
			.add(ModBlockItemIds.CABBAGE_CROP)
			.add(ModBlockItemIds.TOMATO_CROP)
			.add(ModBlockItemIds.ONION_CROP);

		tagBuilder(net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE).addTag(ModTags.Items.KNIVES).add(ModBlockItemIds.SKILLET);
		tagBuilder(net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE).addTag(ModTags.Items.KNIVES).add(ModBlockItemIds.SKILLET);
		tagBuilder(net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE).addTag(ModTags.Items.KNIVES).add(ModBlockItemIds.SKILLET);
		tagBuilder(net.minecraft.tags.ItemTags.FIRE_ASPECT_ENCHANTABLE).addTag(ModTags.Items.KNIVES).add(ModBlockItemIds.SKILLET);
		tagBuilder(net.minecraft.tags.ItemTags.MELEE_WEAPON_ENCHANTABLE).addTag(ModTags.Items.KNIVES).add(ModBlockItemIds.SKILLET);
		tagBuilder(net.minecraft.tags.ItemTags.MINING_ENCHANTABLE).addTag(ModTags.Items.KNIVES);
		tagBuilder(net.minecraft.tags.ItemTags.MINING_LOOT_ENCHANTABLE).addTag(ModTags.Items.KNIVES);
		tagBuilder(net.minecraft.tags.ItemTags.MEAT)
			.add(ModItemIds.MINCED_BEEF)
			.add(ModItemIds.BEEF_PATTY)
			.add(ModItemIds.CHICKEN_CUTS)
			.add(ModItemIds.COOKED_CHICKEN_CUTS)
			.add(ModItemIds.BACON)
			.add(ModItemIds.COOKED_BACON)
			.add(ModItemIds.MUTTON_CHOPS)
			.add(ModItemIds.COOKED_MUTTON_CHOPS)
			.add(ModItemIds.HAM)
			.add(ModItemIds.SMOKED_HAM)
			.add(ModItemIds.DOG_FOOD);
		tagBuilder(net.minecraft.tags.ItemTags.CAT_FOOD)
			.add((ModItemIds.SALMON_SLICE))
			.add((ModItemIds.COD_SLICE));
		tagBuilder(net.minecraft.tags.ItemTags.CHICKEN_FOOD)
			.add(ModBlockItemIds.CABBAGE_CROP)
			.add(ModBlockItemIds.TOMATO_CROP)
			.add(ModBlockItemIds.RICE_CROP);
		tagBuilder(net.minecraft.tags.ItemTags.PIG_FOOD)
			.add(ModItemIds.CABBAGE)
			.add(ModItemIds.TOMATO);
		tagBuilder(net.minecraft.tags.ItemTags.RABBIT_FOOD)
			.add(ModItemIds.CABBAGE);
		tagBuilder(net.minecraft.tags.ItemTags.PARROT_FOOD)
			.add(ModBlockItemIds.CABBAGE_CROP)
			.add(ModBlockItemIds.TOMATO_CROP)
			.add(ModBlockItemIds.RICE_CROP);
		tagBuilder(net.minecraft.tags.ItemTags.HORSE_TEMPT_ITEMS)
			.add(ModItemIds.HORSE_FEED);
	}


	private void registerModTags() {
		tagBuilder(ModTags.Items.SNACKS).add(
			ModItemIds.BARBECUE_STICK,
			ModItemIds.EGG_SANDWICH,
			ModItemIds.CHICKEN_SANDWICH,
			ModItemIds.HAMBURGER,
			ModItemIds.BACON_SANDWICH,
			ModItemIds.MUTTON_WRAP,
			ModItemIds.DUMPLINGS,
			ModItemIds.STUFFED_POTATO,
			ModItemIds.CABBAGE_ROLLS,
			ModItemIds.SALMON_ROLL,
			ModItemIds.COD_ROLL,
			ModItemIds.KELP_ROLL,
			ModItemIds.KELP_ROLL_SLICE
		);
		tagBuilder(ModTags.Items.MEALS).add(
			ItemIds.MUSHROOM_STEW,
			ItemIds.BEETROOT_SOUP,
			ItemIds.RABBIT_STEW,
			ModItemIds.MIXED_SALAD,
			ModItemIds.COOKED_RICE,
			ModItemIds.BONE_BROTH,
			ModItemIds.BEEF_STEW,
			ModItemIds.VEGETABLE_SOUP,
			ModItemIds.FISH_STEW,
			ModItemIds.CHICKEN_SOUP,
			ModItemIds.FRIED_RICE,
			ModItemIds.PUMPKIN_SOUP,
			ModItemIds.BAKED_COD_STEW,
			ModItemIds.NOODLE_SOUP,
			ModItemIds.ONION_SOUP,
			ModItemIds.BACON_AND_EGGS,
			ModItemIds.RATATOUILLE,
			ModItemIds.STEAK_AND_POTATOES,
			ModItemIds.PASTA_WITH_MEATBALLS,
			ModItemIds.PASTA_WITH_MUTTON_CHOP,
			ModItemIds.MUSHROOM_RICE,
			ModItemIds.ROASTED_MUTTON_CHOPS,
			ModItemIds.VEGETABLE_NOODLES,
			ModItemIds.SQUID_INK_PASTA,
			ModItemIds.GRILLED_SALMON,
			ModItemIds.ROAST_CHICKEN,
			ModItemIds.STUFFED_PUMPKIN,
			ModItemIds.HONEY_GLAZED_HAM,
			ModItemIds.SHEPHERDS_PIE,
			ModItemIds.GLEAMING_SALAD
		);
		tagBuilder(ModTags.Items.DRINKS).add(
			ModItemIds.MILK_BOTTLE,
			ModItemIds.APPLE_CIDER,
			ModItemIds.MELON_JUICE,
			ModItemIds.HOT_COCOA
		);
		tagBuilder(ModTags.Items.SWEETS).add(
			BlockItemIds.CAKE).add(
			ItemIds.COOKIE,
			ModItemIds.CAKE_SLICE,
			ModItemIds.APPLE_PIE_SLICE,
			ModItemIds.SWEET_BERRY_CHEESECAKE_SLICE,
			ModItemIds.CHOCOLATE_PIE_SLICE,
			ModItemIds.PUMPKIN_PIE_SLICE,
			ModItemIds.SWEET_BERRY_COOKIE,
			ModItemIds.HONEY_COOKIE,
			ModItemIds.MELON_POPSICLE,
			ModItemIds.GLOW_BERRY_CUSTARD,
			ModItemIds.FRUIT_SALAD
		);
		copy(ModTags.Blocks.FEASTS, ModTags.Items.FEASTS);
		tagBuilder(ModTags.Items.PIES).add(
			ItemIds.PUMPKIN_PIE).add(
			ModBlockItemIds.APPLE_PIE,
			ModBlockItemIds.SWEET_BERRY_CHEESECAKE,
			ModBlockItemIds.CHOCOLATE_PIE
		);
		tagBuilder(ModTags.Items.KNIVES).add(ModItemIds.FLINT_KNIFE, ModItemIds.COPPER_KNIFE, ModItemIds.IRON_KNIFE, ModItemIds.DIAMOND_KNIFE, ModItemIds.GOLDEN_KNIFE, ModItemIds.NETHERITE_KNIFE);
		tagBuilder(ModTags.Items.KNIFE_ENCHANTABLE).addTag(ModTags.Items.KNIVES);
		tagBuilder(ModTags.Items.STRAW_HARVESTERS).addTag(ModTags.Items.KNIVES);
		tagBuilder(ModTags.Items.CANVAS_SIGNS)
			.add(ModBlockItemIds.CANVAS_SIGN)
			.addColorId(ModBlockItemIds.DYED_CANVAS_SIGN);
		tagBuilder(ModTags.Items.HANGING_CANVAS_SIGNS)
			.add(ModBlockItemIds.HANGING_CANVAS_SIGN)
			.addColorId(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN);
		copy(ModTags.Blocks.CABINETS, ModTags.Items.CABINETS);
		copy(ModTags.Blocks.CABINETS_WOODEN, ModTags.Items.CABINETS_WOODEN);

		copy(ModTags.Blocks.MUSHROOM_COLONIES, ModTags.Items.MUSHROOM_COLONIES);

		tagBuilder(ModTags.Items.SERVING_CONTAINERS).add(ItemIds.BOWL, ItemIds.GLASS_BOTTLE, ItemIds.BUCKET);
		tagBuilder(ModTags.Items.FLAT_ON_CUTTING_BOARD).add(ItemIds.TRIDENT, ItemIds.SPYGLASS)
			.addOptional(Identifier.parse("supplementaries:quiver"))
			.addOptional(Identifier.parse("autumnity:turkey"))
			.addOptional(Identifier.parse("autumnity:cooked_turkey"));
	}

	private void registerRefabricatedTags() {
		tagBuilder(FDRefabricatedTags.Items.FLINT_TOOL_MATERIALS)
			.add(ItemIds.FLINT);
	}

	private void registerConventionalTags() {
		// Add our custom tags to "common" tag groups
		tagBuilder(ConventionalItemTags.TALL_FLOWERS).add(ModBlockItemIds.WILD_RICE);
		tagBuilder(ConventionalItemTags.CROPS)
			.addTag(CommonTags.Items.CROPS_GRAIN);
		tagBuilder(ConventionalItemTags.DRINKS)
			.addTag(ModTags.Items.DRINKS);
		tagBuilder(ConventionalItemTags.FOODS)
			.add(ModItemIds.TOMATO_SAUCE)
			.add(ModItemIds.PIE_CRUST)
			.add(ModItemIds.PUMPKIN_SLICE)
			.add(ModItemIds.HAM)
			.add(ModItemIds.SMOKED_HAM)
			.add(ModItemIds.DOG_FOOD)
			.addTag(ModTags.Items.SNACKS)
			.addTag(ModTags.Items.MEALS)
			.addTag(ModTags.Items.SWEETS)
			.addTag(CommonTags.Items.FOODS_LEAFY_GREEN)
			.addTag(CommonTags.Items.FOODS_DOUGH)
			.addTag(CommonTags.Items.FOODS_PASTA)
			.addTag(CommonTags.Items.FOODS_COOKED_EGG);

		tagBuilder(ConventionalItemTags.FENCES).add(ModBlockItemIds.ROPE_FENCE);
		tagBuilder(ConventionalItemTags.FENCE_GATES).add(ModBlockItemIds.ROPE_FENCE_GATE);

		tagBuilder(ConventionalItemTags.MILK_DRINKS).add(ModItemIds.MILK_BOTTLE);

		tagBuilder(ConventionalItemTags.VEGETABLE_FOODS).add(ModBlockItemIds.ONION_CROP).add(ModItemIds.TOMATO);
		tagBuilder(ConventionalItemTags.COOKIE_FOODS).add(ModItemIds.HONEY_COOKIE, ModItemIds.SWEET_BERRY_COOKIE);
		tagBuilder(ConventionalItemTags.DOUGH_FOODS).addTag(CommonTags.Items.FOODS_DOUGH_WHEAT);
		tagBuilder(ConventionalItemTags.RAW_MEAT_FOODS).addTags(CommonTags.Items.FOODS_RAW_CHICKEN, CommonTags.Items.FOODS_RAW_PORK, CommonTags.Items.FOODS_RAW_BEEF, CommonTags.Items.FOODS_RAW_MUTTON);
		tagBuilder(ConventionalItemTags.RAW_FISH_FOODS).addTags(CommonTags.Items.FOODS_RAW_COD, CommonTags.Items.FOODS_RAW_SALMON);
		tagBuilder(ConventionalItemTags.COOKED_MEAT_FOODS).addTags(CommonTags.Items.FOODS_COOKED_CHICKEN, CommonTags.Items.FOODS_COOKED_PORK, CommonTags.Items.FOODS_COOKED_BEEF, CommonTags.Items.FOODS_COOKED_MUTTON);
		tagBuilder(ConventionalItemTags.COOKED_FISH_FOODS).addTags(CommonTags.Items.FOODS_COOKED_COD, CommonTags.Items.FOODS_COOKED_SALMON);
		tagBuilder(ConventionalItemTags.FOOD_POISONING_FOODS).add(
			ModItemIds.WHEAT_DOUGH,
			ModItemIds.RAW_PASTA,
			ModItemIds.CHICKEN_CUTS,
			ModItemIds.NETHER_SALAD
		);
		tagBuilder(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
			.add(ModBlockItemIds.APPLE_PIE)
			.add(ModBlockItemIds.SWEET_BERRY_CHEESECAKE)
			.add(ModBlockItemIds.CHOCOLATE_PIE)
			.addTag(ModTags.Items.FEASTS);
		tagBuilder(ConventionalItemTags.SOUP_FOODS)
			.add(ModItemIds.BONE_BROTH)
			.add(ModItemIds.BEEF_STEW)
			.add(ModItemIds.VEGETABLE_SOUP)
			.add(ModItemIds.CHICKEN_SOUP)
			.add(ModItemIds.FISH_STEW)
			.add(ModItemIds.PUMPKIN_SOUP)
			.add(ModItemIds.BAKED_COD_STEW)
			.add(ModItemIds.NOODLE_SOUP);
		tagBuilder(ConventionalItemTags.PIE_FOODS)
			.add(ModItemIds.APPLE_PIE_SLICE)
			.add(ModItemIds.SWEET_BERRY_CHEESECAKE_SLICE)
			.add(ModItemIds.CHOCOLATE_PIE_SLICE)
			.add(ModItemIds.PUMPKIN_PIE_SLICE);

		tagBuilder(ConventionalItemTags.TOOLS).addTag(CommonTags.Items.TOOLS_KNIFE);
		tagBuilder(ConventionalItemTags.ROPES).add(ModBlockItemIds.ROPE);
		tagBuilder(ConventionalItemTags.SEEDS).add(ModBlockItemIds.CABBAGE_CROP, ModBlockItemIds.RICE_CROP, ModBlockItemIds.TOMATO_CROP);
		tagBuilder(ConventionalItemTags.CROPS).addTags(CommonTags.Items.CROPS_CABBAGE, CommonTags.Items.CROPS_ONION, CommonTags.Items.CROPS_RICE, CommonTags.Items.CROPS_TOMATO);
		tagBuilder(ConventionalItemTags.STORAGE_BLOCKS).addTags(
			CommonTags.Items.STORAGE_BLOCKS_CARROT,
			CommonTags.Items.STORAGE_BLOCKS_POTATO,
			CommonTags.Items.STORAGE_BLOCKS_BEETROOT,
			CommonTags.Items.STORAGE_BLOCKS_CABBAGE,
			CommonTags.Items.STORAGE_BLOCKS_TOMATO,
			CommonTags.Items.STORAGE_BLOCKS_ONION,
			CommonTags.Items.STORAGE_BLOCKS_RICE,
			CommonTags.Items.STORAGE_BLOCKS_RICE_PANICLE,
			CommonTags.Items.STORAGE_BLOCKS_STRAW
		);
		tagBuilder(ConventionalItemTags.HIDDEN_FROM_RECIPE_VIEWERS)
			.add(ModItemIds.DEBUG_PUMPKIN_PIE);
	}

	public void registerCommonTags() {
		tagBuilder(CommonTags.Items.CROPS_CABBAGE).add(ModItemIds.CABBAGE, ModItemIds.CABBAGE_LEAF);
		tagBuilder(CommonTags.Items.CROPS_ONION).add(ModBlockItemIds.ONION_CROP);
		tagBuilder(CommonTags.Items.CROPS_TOMATO).add(ModItemIds.TOMATO);
		tagBuilder(CommonTags.Items.CROPS_RICE).add(ModBlockItemIds.RICE_CROP);

		tagBuilder(CommonTags.Items.FOODS_CABBAGE).add(ModItemIds.CABBAGE, ModItemIds.CABBAGE_LEAF);
		tagBuilder(CommonTags.Items.FOODS_TOMATO).add(ModItemIds.TOMATO);
		tagBuilder(CommonTags.Items.FOODS_ONION).add(ModBlockItemIds.ONION_CROP);

		tagBuilder(CommonTags.Items.FOODS_DOUGH_WHEAT).add(ModItemIds.WHEAT_DOUGH);
		tagBuilder(CommonTags.Items.CROPS_GRAIN).add(ItemIds.WHEAT).add(ModBlockItemIds.RICE_CROP);
		tagBuilder(CommonTags.Items.FOODS_PASTA).add(ModItemIds.RAW_PASTA);
		tagBuilder(CommonTags.Items.FOODS_LEAFY_GREEN).addTag(CommonTags.Items.FOODS_CABBAGE);

		tagBuilder(CommonTags.Items.FOODS_RAW_BACON).add(ModItemIds.BACON);
		tagBuilder(CommonTags.Items.FOODS_RAW_BEEF).add(ItemIds.BEEF, ModItemIds.MINCED_BEEF);
		tagBuilder(CommonTags.Items.FOODS_RAW_CHICKEN).add(ItemIds.CHICKEN, ModItemIds.CHICKEN_CUTS);
		tagBuilder(CommonTags.Items.FOODS_RAW_PORK).add(ItemIds.PORKCHOP).addTag(CommonTags.Items.FOODS_RAW_BACON);
		tagBuilder(CommonTags.Items.FOODS_RAW_MUTTON).add(ItemIds.MUTTON, ModItemIds.MUTTON_CHOPS);
		tagBuilder(CommonTags.Items.FOODS_RAW_COD).add(ItemIds.COD, ModItemIds.COD_SLICE);
		tagBuilder(CommonTags.Items.FOODS_RAW_SALMON).add(ItemIds.SALMON, ModItemIds.SALMON_SLICE);
		// TODO: 26.1: Use tag entry removal PR to remove Pufferfish.
		tagBuilder(CommonTags.Items.FOODS_SAFE_RAW_FISH).addTags(CommonTags.Items.FOODS_RAW_COD, CommonTags.Items.FOODS_RAW_SALMON);
//		tagBuilder(CommonTags.Items.FOODS_SAFE_RAW_FISH).addTag(ConventionalItemTags.RAW_FISH_FOODS).remove(Items.PUFFERFISH);

		tagBuilder(CommonTags.Items.FOODS_COOKED_BACON).add(ModItemIds.COOKED_BACON);
		tagBuilder(CommonTags.Items.FOODS_COOKED_BEEF).add(ItemIds.COOKED_BEEF, ModItemIds.BEEF_PATTY);
		tagBuilder(CommonTags.Items.FOODS_COOKED_CHICKEN).add(ItemIds.COOKED_CHICKEN, ModItemIds.COOKED_CHICKEN_CUTS);
		tagBuilder(CommonTags.Items.FOODS_COOKED_PORK).add(ItemIds.COOKED_PORKCHOP).addTag(CommonTags.Items.FOODS_COOKED_BACON);
		tagBuilder(CommonTags.Items.FOODS_COOKED_MUTTON).add(ItemIds.COOKED_MUTTON, ModItemIds.COOKED_MUTTON_CHOPS);
		tagBuilder(CommonTags.Items.FOODS_COOKED_COD).add(ItemIds.COOKED_COD, ModItemIds.COOKED_COD_SLICE);
		tagBuilder(CommonTags.Items.FOODS_COOKED_SALMON).add(ItemIds.COOKED_SALMON, ModItemIds.COOKED_SALMON_SLICE);
		tagBuilder(CommonTags.Items.FOODS_COOKED_EGG).add(ModItemIds.FRIED_EGG);

		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_CARROT).add(ModBlockItemIds.CARROT_CRATE);
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_POTATO).add(ModBlockItemIds.POTATO_CRATE);
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_BEETROOT).add(ModBlockItemIds.BEETROOT_CRATE);
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_CABBAGE).add(ModBlockItemIds.CABBAGE_CRATE);
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_TOMATO).add(ModBlockItemIds.TOMATO_CRATE);
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_ONION).add(ModBlockItemIds.ONION_CRATE);
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_RICE).add(ModBlockItemIds.RICE_BAG);
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_RICE_PANICLE).add(ModBlockItemIds.RICE_BALE);
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_STRAW).add(ModBlockItemIds.STRAW_BALE);

		tagBuilder(CommonTags.Items.TOOLS_KNIFE).add(ModItemIds.FLINT_KNIFE, ModItemIds.COPPER_KNIFE, ModItemIds.IRON_KNIFE, ModItemIds.DIAMOND_KNIFE, ModItemIds.GOLDEN_KNIFE, ModItemIds.NETHERITE_KNIFE);
	}

	public void registerCompatibilityTags() {
		tagBuilder(CompatibilityTags.CREATE_UPRIGHT_ON_BELT)
			.addTag(ModTags.Items.MEALS)
			.addTag(ModTags.Items.DRINKS)
			.addTag(ModTags.Items.FEASTS)
			.add(ModItemIds.TOMATO_SAUCE)
			.add(ModItemIds.DOG_FOOD)
			.add(ModItemIds.FRUIT_SALAD)
			.add(ModItemIds.NETHER_SALAD)
			.add(ModItemIds.PIE_CRUST)
			.add(ModBlockItemIds.APPLE_PIE)
			.add(ModBlockItemIds.SWEET_BERRY_CHEESECAKE)
			.add(ModBlockItemIds.CHOCOLATE_PIE);

		tagBuilder(CompatibilityTags.CREATE_CA_PLANT_FOODS)
			.add(ModItemIds.PUMPKIN_SLICE)
			.add(ModItemIds.ROTTEN_TOMATO)
			.add(ModBlockItemIds.RICE_CROP_PANICLES);
		tagBuilder(CompatibilityTags.CREATE_CA_PLANTS)
			.add(ModBlockItemIds.SANDY_SHRUB)
			.add(ModBlockItemIds.BROWN_MUSHROOM_COLONY)
			.add(ModBlockItemIds.RED_MUSHROOM_COLONY);

		tagBuilder(CompatibilityTags.ORIGINS_MEAT)
			.add(ModItemIds.FRIED_EGG)
			.add(ModItemIds.COD_SLICE)
			.add(ModItemIds.COOKED_COD_SLICE)
			.add(ModItemIds.SALMON_SLICE)
			.add(ModItemIds.COOKED_SALMON_SLICE)
			.add(ModItemIds.BACON_AND_EGGS);

		tagBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS)
			.add(ModBlockItemIds.CABBAGE_CROP)
			.add(ModBlockItemIds.ONION_CROP)
			.add(ModBlockItemIds.RICE_CROP);
		tagBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS)
			.add(ModBlockItemIds.ONION_CROP);
		tagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS)
			.add(ModBlockItemIds.TOMATO_CROP)
			.add(ModBlockItemIds.RICE_CROP);
		tagBuilder(CompatibilityTags.SERENE_SEASONS_WINTER_CROPS)
			.add(ModBlockItemIds.CABBAGE_CROP);

		tagBuilder(CompatibilityTags.TINKERS_CONSTRUCT_SEEDS).add(ModBlockItemIds.ONION_CROP);
	}

	private RefabricatedTagBuilder tagBuilder(TagKey<Item> tagKey) {
		return new RefabricatedTagBuilder(tagKey);
	}

	public class RefabricatedTagBuilder {
		private BlockItemTagAppender<Item> builder;

		private TagBuilder rawBuilder;

		public RefabricatedTagBuilder(TagKey<Item> tag) {
			this.builder = builder(tag);

			this.rawBuilder = getOrCreateRawBuilder(tag);
		}

		public RefabricatedTagBuilder addOptionalTag(TagKey<Item> itemTagKey) {
			builder = builder.addOptionalTag(itemTagKey);
			return this;
		}

		public RefabricatedTagBuilder add(BlockItemId... items) {
			builder.add(items);
			return this;
		}

		@SafeVarargs
		public final RefabricatedTagBuilder add(ResourceKey<Item>... items) {
			builder.add(items);
			return this;
		}

		public RefabricatedTagBuilder addColorKey(ColorCollection<ResourceKey<Item>> items) {
			builder.addAll(items);
			return this;
		}

		public RefabricatedTagBuilder addColorId(ColorCollection<BlockItemId> items) {
			builder.addAll(items.map(BlockItemId::item));
			return this;
		}

		public RefabricatedTagBuilder addOptional(Identifier item) {
			rawBuilder = rawBuilder.addOptionalElement(item);
			return this;
		}

		@SafeVarargs
		public final void addTags(TagKey<Item>... tags) {
			for (TagKey<Item> tag : tags) {
				builder.forceAddTag(tag);
			}
		}

		public RefabricatedTagBuilder addTag(TagKey<Item> tagKey) {
			builder.forceAddTag(tagKey);
			return this;
		}
	}
}
