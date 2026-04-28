package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.CompatibilityTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ItemTags extends FabricTagsProvider.ItemTagsProvider
{
	public ItemTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider, FabricTagsProvider.BlockTagsProvider blockTagProvider) {
		super(output, provider, blockTagProvider);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		copy(ModTags.Blocks.WILD_CROPS, ModTags.Items.WILD_CROPS);
		copy(BlockTags.SMALL_FLOWERS, net.minecraft.tags.ItemTags.SMALL_FLOWERS);

		this.registerMinecraftTags();
		this.registerModTags();
		this.registerConventionalTags();
		this.registerCommonTags();
		this.registerCompatibilityTags();
	}

	private void registerMinecraftTags() {
		tagBuilder(net.minecraft.tags.ItemTags.BREAKS_DECORATED_POTS).addTag(ModTags.Items.KNIVES);
		tagBuilder(net.minecraft.tags.ItemTags.PIGLIN_LOVED).add(ModItems.GOLDEN_KNIFE.get());
		tagBuilder(net.minecraft.tags.ItemTags.SIGNS).addTag(ModTags.Items.CANVAS_SIGNS);
		tagBuilder(net.minecraft.tags.ItemTags.HANGING_SIGNS).addTag(ModTags.Items.HANGING_CANVAS_SIGNS);
		tagBuilder(net.minecraft.tags.ItemTags.VILLAGER_PLANTABLE_SEEDS)
			.add(ModItems.CABBAGE_SEEDS.get())
			.add(ModItems.TOMATO_SEEDS.get())
			.add(ModItems.ONION.get());

		tagBuilder(net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE).addTag(ModTags.Items.KNIVES).add(ModItems.SKILLET.get());
		tagBuilder(net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE).addTag(ModTags.Items.KNIVES).add(ModItems.SKILLET.get());
		tagBuilder(net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE).addTag(ModTags.Items.KNIVES).add(ModItems.SKILLET.get());
		tagBuilder(net.minecraft.tags.ItemTags.FIRE_ASPECT_ENCHANTABLE).addTag(ModTags.Items.KNIVES).add(ModItems.SKILLET.get());
		tagBuilder(net.minecraft.tags.ItemTags.MELEE_WEAPON_ENCHANTABLE).addTag(ModTags.Items.KNIVES).add(ModItems.SKILLET.get());
		tagBuilder(net.minecraft.tags.ItemTags.MINING_ENCHANTABLE).addTag(ModTags.Items.KNIVES);
		tagBuilder(net.minecraft.tags.ItemTags.MINING_LOOT_ENCHANTABLE).addTag(ModTags.Items.KNIVES);
		tagBuilder(net.minecraft.tags.ItemTags.MEAT)
			.add(ModItems.MINCED_BEEF.get())
			.add(ModItems.BEEF_PATTY.get())
			.add(ModItems.CHICKEN_CUTS.get())
			.add(ModItems.COOKED_CHICKEN_CUTS.get())
			.add(ModItems.BACON.get())
			.add(ModItems.COOKED_BACON.get())
			.add(ModItems.MUTTON_CHOPS.get())
			.add(ModItems.COOKED_MUTTON_CHOPS.get())
			.add(ModItems.HAM.get())
			.add(ModItems.SMOKED_HAM.get())
			.add(ModItems.DOG_FOOD.get());
		tagBuilder(net.minecraft.tags.ItemTags.CAT_FOOD)
			.add((ModItems.SALMON_SLICE.get()))
			.add((ModItems.COD_SLICE.get()));
		tagBuilder(net.minecraft.tags.ItemTags.CHICKEN_FOOD)
			.add(ModItems.CABBAGE_SEEDS.get())
			.add(ModItems.TOMATO_SEEDS.get())
			.add(ModItems.RICE.get());
		tagBuilder(net.minecraft.tags.ItemTags.PIG_FOOD)
			.add(ModItems.CABBAGE.get())
			.add(ModItems.TOMATO.get());
		tagBuilder(net.minecraft.tags.ItemTags.RABBIT_FOOD)
			.add(ModItems.CABBAGE.get());
		tagBuilder(net.minecraft.tags.ItemTags.PARROT_FOOD)
			.add(ModItems.CABBAGE_SEEDS.get())
			.add(ModItems.TOMATO_SEEDS.get())
			.add(ModItems.RICE.get());
		tagBuilder(net.minecraft.tags.ItemTags.HORSE_TEMPT_ITEMS)
			.add(ModItems.HORSE_FEED.get());
	}


	private void registerModTags() {
		tagBuilder(ModTags.Items.SNACKS).add(
			ModItems.BARBECUE_STICK.get(),
			ModItems.EGG_SANDWICH.get(),
			ModItems.CHICKEN_SANDWICH.get(),
			ModItems.HAMBURGER.get(),
			ModItems.BACON_SANDWICH.get(),
			ModItems.MUTTON_WRAP.get(),
			ModItems.DUMPLINGS.get(),
			ModItems.STUFFED_POTATO.get(),
			ModItems.CABBAGE_ROLLS.get(),
			ModItems.SALMON_ROLL.get(),
			ModItems.COD_ROLL.get(),
			ModItems.KELP_ROLL.get(),
			ModItems.KELP_ROLL_SLICE.get()
		);
		tagBuilder(ModTags.Items.MEALS).add(
			Items.MUSHROOM_STEW,
			Items.BEETROOT_SOUP,
			Items.RABBIT_STEW,
			ModItems.MIXED_SALAD.get(),
			ModItems.COOKED_RICE.get(),
			ModItems.BONE_BROTH.get(),
			ModItems.BEEF_STEW.get(),
			ModItems.VEGETABLE_SOUP.get(),
			ModItems.FISH_STEW.get(),
			ModItems.CHICKEN_SOUP.get(),
			ModItems.FRIED_RICE.get(),
			ModItems.PUMPKIN_SOUP.get(),
			ModItems.BAKED_COD_STEW.get(),
			ModItems.NOODLE_SOUP.get(),
			ModItems.ONION_SOUP.get(),
			ModItems.BACON_AND_EGGS.get(),
			ModItems.RATATOUILLE.get(),
			ModItems.STEAK_AND_POTATOES.get(),
			ModItems.PASTA_WITH_MEATBALLS.get(),
			ModItems.PASTA_WITH_MUTTON_CHOP.get(),
			ModItems.MUSHROOM_RICE.get(),
			ModItems.ROASTED_MUTTON_CHOPS.get(),
			ModItems.VEGETABLE_NOODLES.get(),
			ModItems.SQUID_INK_PASTA.get(),
			ModItems.GRILLED_SALMON.get(),
			ModItems.ROAST_CHICKEN.get(),
			ModItems.STUFFED_PUMPKIN.get(),
			ModItems.HONEY_GLAZED_HAM.get(),
			ModItems.SHEPHERDS_PIE.get(),
			ModItems.GLEAMING_SALAD.get()
		);
		tagBuilder(ModTags.Items.DRINKS).add(
			ModItems.MILK_BOTTLE.get(),
			ModItems.APPLE_CIDER.get(),
			ModItems.MELON_JUICE.get(),
			ModItems.HOT_COCOA.get()
		);
		tagBuilder(ModTags.Items.SWEETS).add(
			Items.CAKE,
			Items.COOKIE,
			ModItems.CAKE_SLICE.get(),
			ModItems.APPLE_PIE_SLICE.get(),
			ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get(),
			ModItems.CHOCOLATE_PIE_SLICE.get(),
			ModItems.PUMPKIN_PIE_SLICE.get(),
			ModItems.SWEET_BERRY_COOKIE.get(),
			ModItems.HONEY_COOKIE.get(),
			ModItems.MELON_POPSICLE.get(),
			ModItems.GLOW_BERRY_CUSTARD.get(),
			ModItems.FRUIT_SALAD.get()
		);
		copy(ModTags.Blocks.FEASTS, ModTags.Items.FEASTS);
		tagBuilder(ModTags.Items.PIES).add(
			Items.PUMPKIN_PIE,
			ModItems.APPLE_PIE.get(),
			ModItems.SWEET_BERRY_CHEESECAKE.get(),
			ModItems.CHOCOLATE_PIE.get()
		);
		tagBuilder(ModTags.Items.KNIVES).add(ModItems.FLINT_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.GOLDEN_KNIFE.get(), ModItems.NETHERITE_KNIFE.get());
		tagBuilder(ModTags.Items.KNIFE_ENCHANTABLE).addTag(ModTags.Items.KNIVES);
		tagBuilder(ModTags.Items.STRAW_HARVESTERS).addTag(ModTags.Items.KNIVES);
		tagBuilder(ModTags.Items.CANVAS_SIGNS)
			.add(ModItems.CANVAS_SIGN.get())
			.add(ModItems.WHITE_CANVAS_SIGN.get())
			.add(ModItems.ORANGE_CANVAS_SIGN.get())
			.add(ModItems.MAGENTA_CANVAS_SIGN.get())
			.add(ModItems.LIGHT_BLUE_CANVAS_SIGN.get())
			.add(ModItems.YELLOW_CANVAS_SIGN.get())
			.add(ModItems.LIME_CANVAS_SIGN.get())
			.add(ModItems.PINK_CANVAS_SIGN.get())
			.add(ModItems.GRAY_CANVAS_SIGN.get())
			.add(ModItems.LIGHT_GRAY_CANVAS_SIGN.get())
			.add(ModItems.CYAN_CANVAS_SIGN.get())
			.add(ModItems.PURPLE_CANVAS_SIGN.get())
			.add(ModItems.BLUE_CANVAS_SIGN.get())
			.add(ModItems.BROWN_CANVAS_SIGN.get())
			.add(ModItems.GREEN_CANVAS_SIGN.get())
			.add(ModItems.RED_CANVAS_SIGN.get())
			.add(ModItems.BLACK_CANVAS_SIGN.get());
		tagBuilder(ModTags.Items.HANGING_CANVAS_SIGNS)
			.add(ModItems.HANGING_CANVAS_SIGN.get())
			.add(ModItems.WHITE_HANGING_CANVAS_SIGN.get())
			.add(ModItems.ORANGE_HANGING_CANVAS_SIGN.get())
			.add(ModItems.MAGENTA_HANGING_CANVAS_SIGN.get())
			.add(ModItems.LIGHT_BLUE_HANGING_CANVAS_SIGN.get())
			.add(ModItems.YELLOW_HANGING_CANVAS_SIGN.get())
			.add(ModItems.LIME_HANGING_CANVAS_SIGN.get())
			.add(ModItems.PINK_HANGING_CANVAS_SIGN.get())
			.add(ModItems.GRAY_HANGING_CANVAS_SIGN.get())
			.add(ModItems.LIGHT_GRAY_HANGING_CANVAS_SIGN.get())
			.add(ModItems.CYAN_HANGING_CANVAS_SIGN.get())
			.add(ModItems.PURPLE_HANGING_CANVAS_SIGN.get())
			.add(ModItems.BLUE_HANGING_CANVAS_SIGN.get())
			.add(ModItems.BROWN_HANGING_CANVAS_SIGN.get())
			.add(ModItems.GREEN_HANGING_CANVAS_SIGN.get())
			.add(ModItems.RED_HANGING_CANVAS_SIGN.get())
			.add(ModItems.BLACK_HANGING_CANVAS_SIGN.get());
		copy(ModTags.Blocks.CABINETS, ModTags.Items.CABINETS);
		copy(ModTags.Blocks.CABINETS_WOODEN, ModTags.Items.CABINETS_WOODEN);

		copy(ModTags.Blocks.MUSHROOM_COLONIES, ModTags.Items.MUSHROOM_COLONIES);

		tagBuilder(ModTags.Items.SERVING_CONTAINERS).add(Items.BOWL, Items.GLASS_BOTTLE, Items.BUCKET);
		tagBuilder(ModTags.Items.FLAT_ON_CUTTING_BOARD).add(Items.TRIDENT, Items.SPYGLASS)
			.addOptional(Identifier.parse("supplementaries:quiver"))
			.addOptional(Identifier.parse("autumnity:turkey"))
			.addOptional(Identifier.parse("autumnity:cooked_turkey"));
	}

	private void registerConventionalTags() {
		// Add our custom tags to "common" tag groups
		tagBuilder(ConventionalItemTags.TALL_FLOWERS).add(ModItems.WILD_RICE.get());
		tagBuilder(ConventionalItemTags.CROPS)
			.addTag(CommonTags.Items.CROPS_GRAIN);
		tagBuilder(ConventionalItemTags.DRINKS)
			.addTag(ModTags.Items.DRINKS);
		tagBuilder(ConventionalItemTags.FOODS)
			.add(ModItems.TOMATO_SAUCE.get())
			.add(ModItems.PIE_CRUST.get())
			.add(ModItems.PUMPKIN_SLICE.get())
			.add(ModItems.HAM.get())
			.add(ModItems.SMOKED_HAM.get())
			.add(ModItems.DOG_FOOD.get())
			.addTag(ModTags.Items.SNACKS)
			.addTag(ModTags.Items.MEALS)
			.addTag(ModTags.Items.SWEETS)
			.addTag(CommonTags.Items.FOODS_LEAFY_GREEN)
			.addTag(CommonTags.Items.FOODS_DOUGH)
			.addTag(CommonTags.Items.FOODS_PASTA)
			.addTag(CommonTags.Items.FOODS_COOKED_EGG);

		tagBuilder(ConventionalItemTags.FENCES).add(ModItems.ROPE_FENCE.get());
		tagBuilder(ConventionalItemTags.FENCE_GATES).add(ModItems.ROPE_FENCE_GATE.get());

		tagBuilder(ConventionalItemTags.MILK_DRINKS).add(ModItems.MILK_BOTTLE.get());

		tagBuilder(ConventionalItemTags.VEGETABLE_FOODS).add(ModItems.ONION.get(), ModItems.TOMATO.get());
		tagBuilder(ConventionalItemTags.COOKIE_FOODS).add(ModItems.HONEY_COOKIE.get(), ModItems.SWEET_BERRY_COOKIE.get());
		tagBuilder(ConventionalItemTags.DOUGH_FOODS).addTag(CommonTags.Items.FOODS_DOUGH_WHEAT);
		tagBuilder(ConventionalItemTags.RAW_MEAT_FOODS).addTags(CommonTags.Items.FOODS_RAW_CHICKEN, CommonTags.Items.FOODS_RAW_PORK, CommonTags.Items.FOODS_RAW_BEEF, CommonTags.Items.FOODS_RAW_MUTTON);
		tagBuilder(ConventionalItemTags.RAW_FISH_FOODS).addTags(CommonTags.Items.FOODS_RAW_COD, CommonTags.Items.FOODS_RAW_SALMON);
		tagBuilder(ConventionalItemTags.COOKED_MEAT_FOODS).addTags(CommonTags.Items.FOODS_COOKED_CHICKEN, CommonTags.Items.FOODS_COOKED_PORK, CommonTags.Items.FOODS_COOKED_BEEF, CommonTags.Items.FOODS_COOKED_MUTTON);
		tagBuilder(ConventionalItemTags.COOKED_FISH_FOODS).addTags(CommonTags.Items.FOODS_COOKED_COD, CommonTags.Items.FOODS_COOKED_SALMON);
		tagBuilder(ConventionalItemTags.FOOD_POISONING_FOODS).add(
			ModItems.WHEAT_DOUGH.get(),
			ModItems.RAW_PASTA.get(),
			ModItems.CHICKEN_CUTS.get(),
			ModItems.NETHER_SALAD.get()
		);
		tagBuilder(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
			.add(ModItems.APPLE_PIE.get())
			.add(ModItems.SWEET_BERRY_CHEESECAKE.get())
			.add(ModItems.CHOCOLATE_PIE.get())
			.addTag(ModTags.Items.FEASTS);
		tagBuilder(ConventionalItemTags.SOUP_FOODS)
			.add(ModItems.BONE_BROTH.get())
			.add(ModItems.BEEF_STEW.get())
			.add(ModItems.VEGETABLE_SOUP.get())
			.add(ModItems.CHICKEN_SOUP.get())
			.add(ModItems.FISH_STEW.get())
			.add(ModItems.PUMPKIN_SOUP.get())
			.add(ModItems.BAKED_COD_STEW.get())
			.add(ModItems.NOODLE_SOUP.get());
		tagBuilder(ConventionalItemTags.PIE_FOODS)
			.add(ModItems.APPLE_PIE_SLICE.get())
			.add(ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get())
			.add(ModItems.CHOCOLATE_PIE_SLICE.get())
			.add(ModItems.PUMPKIN_PIE_SLICE.get());

		tagBuilder(ConventionalItemTags.TOOLS).addTag(CommonTags.Items.TOOLS_KNIFE);
		tagBuilder(ConventionalItemTags.ROPES).add(ModItems.ROPE.get());
		tagBuilder(ConventionalItemTags.SEEDS).add(ModItems.CABBAGE_SEEDS.get(), ModItems.RICE.get(), ModItems.TOMATO_SEEDS.get());
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
	}

	public void registerCommonTags() {
		tagBuilder(CommonTags.Items.CROPS_CABBAGE).add(ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get());
		tagBuilder(CommonTags.Items.CROPS_ONION).add(ModItems.ONION.get());
		tagBuilder(CommonTags.Items.CROPS_TOMATO).add(ModItems.TOMATO.get());
		tagBuilder(CommonTags.Items.CROPS_RICE).add(ModItems.RICE.get());

		tagBuilder(CommonTags.Items.FOODS_CABBAGE).add(ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get());
		tagBuilder(CommonTags.Items.FOODS_TOMATO).add(ModItems.TOMATO.get());
		tagBuilder(CommonTags.Items.FOODS_ONION).add(ModItems.ONION.get());

		tagBuilder(CommonTags.Items.FOODS_DOUGH_WHEAT).add(ModItems.WHEAT_DOUGH.get());
		tagBuilder(CommonTags.Items.CROPS_GRAIN).add(Items.WHEAT, ModItems.RICE.get());
		tagBuilder(CommonTags.Items.FOODS_PASTA).add(ModItems.RAW_PASTA.get());
		tagBuilder(CommonTags.Items.FOODS_LEAFY_GREEN).addTag(CommonTags.Items.FOODS_CABBAGE);

		tagBuilder(CommonTags.Items.FOODS_RAW_BACON).add(ModItems.BACON.get());
		tagBuilder(CommonTags.Items.FOODS_RAW_BEEF).add(Items.BEEF, ModItems.MINCED_BEEF.get());
		tagBuilder(CommonTags.Items.FOODS_RAW_CHICKEN).add(Items.CHICKEN, ModItems.CHICKEN_CUTS.get());
		tagBuilder(CommonTags.Items.FOODS_RAW_PORK).add(Items.PORKCHOP).addTag(CommonTags.Items.FOODS_RAW_BACON);
		tagBuilder(CommonTags.Items.FOODS_RAW_MUTTON).add(Items.MUTTON, ModItems.MUTTON_CHOPS.get());
		tagBuilder(CommonTags.Items.FOODS_RAW_COD).add(Items.COD, ModItems.COD_SLICE.get());
		tagBuilder(CommonTags.Items.FOODS_RAW_SALMON).add(Items.SALMON, ModItems.SALMON_SLICE.get());
		// TODO: 26.1: Use tag entry removal PR to remove Pufferfish.
		tagBuilder(CommonTags.Items.FOODS_SAFE_RAW_FISH).addTags(CommonTags.Items.FOODS_RAW_COD, CommonTags.Items.FOODS_RAW_SALMON);
//		tagBuilder(CommonTags.Items.FOODS_SAFE_RAW_FISH).addTag(ConventionalItemTags.RAW_FISH_FOODS).remove(Items.PUFFERFISH);

		tagBuilder(CommonTags.Items.FOODS_COOKED_BACON).add(ModItems.COOKED_BACON.get());
		tagBuilder(CommonTags.Items.FOODS_COOKED_BEEF).add(Items.COOKED_BEEF, ModItems.BEEF_PATTY.get());
		tagBuilder(CommonTags.Items.FOODS_COOKED_CHICKEN).add(Items.COOKED_CHICKEN, ModItems.COOKED_CHICKEN_CUTS.get());
		tagBuilder(CommonTags.Items.FOODS_COOKED_PORK).add(Items.COOKED_PORKCHOP).addTag(CommonTags.Items.FOODS_COOKED_BACON);
		tagBuilder(CommonTags.Items.FOODS_COOKED_MUTTON).add(Items.COOKED_MUTTON, ModItems.COOKED_MUTTON_CHOPS.get());
		tagBuilder(CommonTags.Items.FOODS_COOKED_COD).add(Items.COOKED_COD, ModItems.COOKED_COD_SLICE.get());
		tagBuilder(CommonTags.Items.FOODS_COOKED_SALMON).add(Items.COOKED_SALMON, ModItems.COOKED_SALMON_SLICE.get());
		tagBuilder(CommonTags.Items.FOODS_COOKED_EGG).add(ModItems.FRIED_EGG.get());

		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_CARROT).add(ModItems.CARROT_CRATE.get());
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_POTATO).add(ModItems.POTATO_CRATE.get());
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_BEETROOT).add(ModItems.BEETROOT_CRATE.get());
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_CABBAGE).add(ModItems.CABBAGE_CRATE.get());
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_TOMATO).add(ModItems.TOMATO_CRATE.get());
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_ONION).add(ModItems.ONION_CRATE.get());
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_RICE).add(ModItems.RICE_BAG.get());
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_RICE_PANICLE).add(ModItems.RICE_BALE.get());
		tagBuilder(CommonTags.Items.STORAGE_BLOCKS_STRAW).add(ModItems.STRAW_BALE.get());

		tagBuilder(CommonTags.Items.TOOLS_KNIFE).add(ModItems.FLINT_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.GOLDEN_KNIFE.get(), ModItems.NETHERITE_KNIFE.get());
	}

	public void registerCompatibilityTags() {
		tagBuilder(CompatibilityTags.CREATE_UPRIGHT_ON_BELT)
			.addTag(ModTags.Items.MEALS)
			.addTag(ModTags.Items.DRINKS)
			.addTag(ModTags.Items.FEASTS)
			.add(ModItems.TOMATO_SAUCE.get())
			.add(ModItems.DOG_FOOD.get())
			.add(ModItems.FRUIT_SALAD.get())
			.add(ModItems.NETHER_SALAD.get())
			.add(ModItems.PIE_CRUST.get())
			.add(ModItems.APPLE_PIE.get())
			.add(ModItems.SWEET_BERRY_CHEESECAKE.get())
			.add(ModItems.CHOCOLATE_PIE.get());

		tagBuilder(CompatibilityTags.CREATE_CA_PLANT_FOODS)
			.add(ModItems.PUMPKIN_SLICE.get())
			.add(ModItems.ROTTEN_TOMATO.get())
			.add(ModItems.RICE_PANICLE.get());
		tagBuilder(CompatibilityTags.CREATE_CA_PLANTS)
			.add(ModItems.SANDY_SHRUB.get())
			.add(ModItems.BROWN_MUSHROOM_COLONY.get())
			.add(ModItems.RED_MUSHROOM_COLONY.get());

		tagBuilder(CompatibilityTags.ORIGINS_MEAT)
			.add(ModItems.FRIED_EGG.get())
			.add(ModItems.COD_SLICE.get())
			.add(ModItems.COOKED_COD_SLICE.get())
			.add(ModItems.SALMON_SLICE.get())
			.add(ModItems.COOKED_SALMON_SLICE.get())
			.add(ModItems.BACON_AND_EGGS.get());

		tagBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS)
			.add(ModItems.CABBAGE_SEEDS.get())
			.add(ModItems.ONION.get())
			.add(ModItems.RICE.get());
		tagBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS)
			.add(ModItems.ONION.get());
		tagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS)
			.add(ModItems.TOMATO_SEEDS.get())
			.add(ModItems.RICE.get());
		tagBuilder(CompatibilityTags.SERENE_SEASONS_WINTER_CROPS)
			.add(ModItems.CABBAGE_SEEDS.get());

		tagBuilder(CompatibilityTags.TINKERS_CONSTRUCT_SEEDS).add(ModItems.ONION.get());
	}

	private RefabricatedTagBuilder tagBuilder(TagKey<Item> tagKey) {
		return new RefabricatedTagBuilder(tagKey);
	}

	public class RefabricatedTagBuilder {
		private TagAppender<Item, Item> valueLookupBuilder;

		private TagBuilder rawBuilder;

		public RefabricatedTagBuilder(TagKey<Item> tag) {
			this.valueLookupBuilder = valueLookupBuilder(tag);

			this.rawBuilder = getOrCreateRawBuilder(tag);
		}

		public RefabricatedTagBuilder add(Item item) {
			valueLookupBuilder = valueLookupBuilder.add(item);
			return this;
		}

		public RefabricatedTagBuilder add(Supplier<Item> item) {
			valueLookupBuilder = valueLookupBuilder.add(item.get());
			return this;
		}

		public RefabricatedTagBuilder addOptionalTag(TagKey<Item> itemTagKey) {
			valueLookupBuilder = valueLookupBuilder.addOptionalTag(itemTagKey);
			return this;
		}

		public RefabricatedTagBuilder add(Item... items) {
			valueLookupBuilder = valueLookupBuilder.add(items);
			return this;
		}

		public RefabricatedTagBuilder addOptional(Identifier item) {
			rawBuilder = rawBuilder.addOptionalElement(item);
			return this;
		}

		@SafeVarargs
		public final void addTags(TagKey<Item>... tags) {
			for (TagKey<Item> tag : tags) {
				valueLookupBuilder.forceAddTag(tag);
			}
		}

		public RefabricatedTagBuilder addTag(TagKey<Item> tagKey) {
			valueLookupBuilder.forceAddTag(tagKey);
			return this;
		}
	}
}