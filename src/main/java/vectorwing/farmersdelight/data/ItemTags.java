package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.CompatibilityTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ItemTags extends FabricTagsProvider.ItemTagsProvider
{
	public ItemTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider, BlockTagsProvider blockTagProvider) {
		super(output, provider, blockTagProvider);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		copy(ModTags.Blocks.WILD_CROPS, ModTags.Items.WILD_CROPS);
		copy(BlockTags.SMALL_FLOWERS, net.minecraft.tags.ItemTags.SMALL_FLOWERS);

		this.registerMinecraftTags();
		this.registerModTags();
		this.registerNeoForgeTags();
		this.registerCommonTags();
		this.registerCompatibilityTags();
	}

	private void registerMinecraftTags() {
		valueLookupBuilder(net.minecraft.tags.ItemTags.BREAKS_DECORATED_POTS).addTag(ModTags.KNIVES);
		valueLookupBuilder(ConventionalItemTags.TALL_FLOWERS).add(ModItems.WILD_RICE.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.PIGLIN_LOVED).add(ModItems.GOLDEN_KNIFE.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.SIGNS).addTag(ModTags.CANVAS_SIGNS);
		valueLookupBuilder(net.minecraft.tags.ItemTags.HANGING_SIGNS).addTag(ModTags.HANGING_CANVAS_SIGNS);
		valueLookupBuilder(net.minecraft.tags.ItemTags.VILLAGER_PLANTABLE_SEEDS)
				.add(ModItems.CABBAGE_SEEDS.get())
				.add(ModItems.TOMATO_SEEDS.get())
				.add(ModItems.ONION.get());

		valueLookupBuilder(net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.MELEE_WEAPON_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.FIRE_ASPECT_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.SWEEPING_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.MINING_ENCHANTABLE).addTag(ModTags.KNIVES);
		valueLookupBuilder(net.minecraft.tags.ItemTags.MINING_LOOT_ENCHANTABLE).addTag(ModTags.KNIVES);

		valueLookupBuilder(net.minecraft.tags.ItemTags.MEAT)
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
		valueLookupBuilder(net.minecraft.tags.ItemTags.CHICKEN_FOOD)
				.add(ModItems.CABBAGE_SEEDS.get())
				.add(ModItems.TOMATO_SEEDS.get())
				.add(ModItems.RICE.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.PIG_FOOD)
				.add(ModItems.CABBAGE.get())
				.add(ModItems.TOMATO.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.WOLF_FOOD)
				.add(ModItems.BEEF_STEW.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.RABBIT_FOOD)
				.add(ModItems.CABBAGE.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.PARROT_FOOD)
				.add(ModItems.CABBAGE_SEEDS.get())
				.add(ModItems.TOMATO_SEEDS.get())
				.add(ModItems.RICE.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.HORSE_TEMPT_ITEMS)
				.add(ModItems.HORSE_FEED.get());
		valueLookupBuilder(net.minecraft.tags.ItemTags.VILLAGER_PICKS_UP)
				.add(
						ModItems.CABBAGE.get(),
						ModItems.TOMATO.get(),
						ModItems.ONION.get(),
						ModItems.RICE.get(),
						ModItems.CABBAGE_SEEDS.get(),
						ModItems.TOMATO_SEEDS.get(),
						ModItems.RICE_PANICLE.get()
				);
	}

	private void registerModTags() {
		tag(ModTags.SNACKS).add(
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
		tag(ModTags.MEALS).add(
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
				ModItems.SHEPHERDS_PIE.get()
		);
		valueLookupBuilder(ModTags.DRINKS).add(
				ModItems.MILK_BOTTLE.get(),
				ModItems.APPLE_CIDER.get(),
				ModItems.MELON_JUICE.get(),
				ModItems.HOT_COCOA.get()
		);
		tag(ModTags.SWEETS).add(
				ModItems.CAKE_SLICE.get(),
				ModItems.APPLE_PIE_SLICE.get(),
				ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get(),
				ModItems.CHOCOLATE_PIE_SLICE.get(),
				ModItems.SWEET_BERRY_COOKIE.get(),
				ModItems.HONEY_COOKIE.get(),
				ModItems.MELON_POPSICLE.get(),
				ModItems.GLOW_BERRY_CUSTARD.get(),
				ModItems.FRUIT_SALAD.get()
		);
		tag(ModTags.FEASTS).add(
				ModItems.ROAST_CHICKEN_BLOCK.get(),
				ModItems.STUFFED_PUMPKIN_BLOCK.get(),
				ModItems.SHEPHERDS_PIE_BLOCK.get(),
				ModItems.HONEY_GLAZED_HAM_BLOCK.get(),
				ModItems.RICE_ROLL_MEDLEY_BLOCK.get()
		);
		valueLookupBuilder(ModTags.KNIVES).add(ModItems.FLINT_KNIFE.get(), ModItems.COPPER_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.GOLDEN_KNIFE.get(), ModItems.NETHERITE_KNIFE.get());
		valueLookupBuilder(ModTags.KNIFE_ENCHANTABLE).forceAddTag(ModTags.KNIVES);
		valueLookupBuilder(ModTags.STRAW_HARVESTERS).forceAddTag(ModTags.KNIVES);
		valueLookupBuilder(ModTags.CABBAGE_ROLL_INGREDIENTS).forceAddTag(CommonTags.FOODS_RAW_PORK).forceAddTag(CommonTags.FOODS_SAFE_RAW_FISH).forceAddTag(CommonTags.FOODS_RAW_CHICKEN).forceAddTag(CommonTags.FOODS_RAW_BEEF).forceAddTag(CommonTags.FOODS_RAW_MUTTON).forceAddTag(ConventionalItemTags.EGGS).forceAddTag(ConventionalItemTags.MUSHROOMS).add(Items.CARROT, Items.POTATO, Items.BEETROOT);
		valueLookupBuilder(ModTags.CANVAS_SIGNS)
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
		valueLookupBuilder(ModTags.HANGING_CANVAS_SIGNS)
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
		valueLookupBuilder(ModTags.WOODEN_CABINETS)
				.add(ModItems.OAK_CABINET.get())
				.add(ModItems.SPRUCE_CABINET.get())
				.add(ModItems.BIRCH_CABINET.get())
				.add(ModItems.JUNGLE_CABINET.get())
				.add(ModItems.ACACIA_CABINET.get())
				.add(ModItems.DARK_OAK_CABINET.get())
				.add(ModItems.MANGROVE_CABINET.get())
				.add(ModItems.CHERRY_CABINET.get())
				.add(ModItems.BAMBOO_CABINET.get())
				.add(ModItems.PALE_OAK_CABINET.get())
				.add(ModItems.CRIMSON_CABINET.get())
				.add(ModItems.WARPED_CABINET.get());
		valueLookupBuilder(ModTags.CABINETS).forceAddTag(ModTags.WOODEN_CABINETS);
		valueLookupBuilder(ModTags.OFFHAND_EQUIPMENT).add(Items.SHIELD);
		getOrCreateRawBuilder(ModTags.OFFHAND_EQUIPMENT)
				.addOptionalElement(Identifier.parse("create:extendo_grip"));
		valueLookupBuilder(ModTags.SERVING_CONTAINERS).add(Items.BOWL, Items.GLASS_BOTTLE, Items.BUCKET);
		valueLookupBuilder(ModTags.FLAT_ON_CUTTING_BOARD).add(Items.TRIDENT, Items.SPYGLASS);
		getOrCreateRawBuilder(ModTags.FLAT_ON_CUTTING_BOARD)
				.addOptionalElement(Identifier.parse("supplementaries:quiver"))
				.addOptionalElement(Identifier.parse("autumnity:turkey"))
				.addOptionalElement(Identifier.parse("autumnity:cooked_turkey"));
		valueLookupBuilder(ModTags.FLINT_TOOL_MATERIALS).add(Items.FLINT);
	}

	@SuppressWarnings("unchecked")
	private void registerNeoForgeTags() {
		// Add our custom tags to "common" tag groups
		tag(ConventionalItemTags.CROPS)
				.addTag(CommonTags.CROPS_GRAIN);
		tag(ConventionalItemTags.DRINKS)
				.addTag(ModTags.DRINKS);
		tag(ConventionalItemTags.FOODS)
				.add(ModItems.TOMATO_SAUCE.get())
				.add(ModItems.PIE_CRUST.get())
				.add(ModItems.PUMPKIN_SLICE.get())
				.add(ModItems.HAM.get())
				.add(ModItems.SMOKED_HAM.get())
				.add(ModItems.DOG_FOOD.get())
				.addTag(ModTags.SNACKS)
				.addTag(ModTags.MEALS)
				.addTag(ModTags.SWEETS)
				.addTag(CommonTags.FOODS_LEAFY_GREEN)
				.addTag(CommonTags.FOODS_DOUGH)
				.addTag(CommonTags.FOODS_PASTA)
				.addTag(CommonTags.FOODS_COOKED_EGG);

		// TODO: FOODS_MILK will be deprecated in 1.3, but is used here for now to not break add-on compat.
		tag(ConventionalItemTags.MILK_DRINKS).add(ModItems.MILK_BOTTLE.get()).addTag(CommonTags.FOODS_MILK);

		tag(ConventionalItemTags.VEGETABLE_FOODS).add(ModItems.ONION.get(), ModItems.TOMATO.get());
		tag(ConventionalItemTags.COOKIE_FOODS).add(ModItems.HONEY_COOKIE.get(), ModItems.SWEET_BERRY_COOKIE.get());
		tag(CommonTags.FOODS_DOUGH).addTag(CommonTags.FOODS_DOUGH_WHEAT);
		tag(ConventionalItemTags.RAW_MEAT_FOODS).addTag(CommonTags.FOODS_RAW_CHICKEN).addTag(CommonTags.FOODS_RAW_PORK).addTag(CommonTags.FOODS_RAW_BEEF).addTag(CommonTags.FOODS_RAW_MUTTON);
		tag(ConventionalItemTags.RAW_FISH_FOODS).addTag(CommonTags.FOODS_RAW_COD).addTag(CommonTags.FOODS_RAW_SALMON);
		tag(ConventionalItemTags.COOKED_MEAT_FOODS).addTag(CommonTags.FOODS_COOKED_CHICKEN).addTag(CommonTags.FOODS_COOKED_PORK).addTag(CommonTags.FOODS_COOKED_BEEF).addTag(CommonTags.FOODS_COOKED_MUTTON);
		tag(ConventionalItemTags.COOKED_FISH_FOODS).addTag(CommonTags.FOODS_COOKED_COD).addTag(CommonTags.FOODS_COOKED_SALMON);
		tag(ConventionalItemTags.FOOD_POISONING_FOODS).add(
				ModItems.WHEAT_DOUGH.get(),
				ModItems.RAW_PASTA.get(),
				ModItems.CHICKEN_CUTS.get(),
				ModItems.NETHER_SALAD.get()
		);
		tag(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
				.add(ModItems.APPLE_PIE.get())
				.add(ModItems.SWEET_BERRY_CHEESECAKE.get())
				.add(ModItems.CHOCOLATE_PIE.get())
				.add(ModItems.ROAST_CHICKEN_BLOCK.get())
				.add(ModItems.HONEY_GLAZED_HAM_BLOCK.get())
				.add(ModItems.SHEPHERDS_PIE_BLOCK.get())
				.add(ModItems.STUFFED_PUMPKIN_BLOCK.get())
				.add(ModItems.RICE_ROLL_MEDLEY_BLOCK.get());
		valueLookupBuilder(ConventionalItemTags.SOUP_FOODS)
				.add(ModItems.BONE_BROTH.get())
				.add(ModItems.BEEF_STEW.get())
				.add(ModItems.VEGETABLE_SOUP.get())
				.add(ModItems.CHICKEN_SOUP.get())
				.add(ModItems.FISH_STEW.get())
				.add(ModItems.PUMPKIN_SOUP.get())
				.add(ModItems.BAKED_COD_STEW.get())
				.add(ModItems.NOODLE_SOUP.get());
		tag(ConventionalItemTags.PIE_FOODS)
				.add(ModItems.APPLE_PIE_SLICE.get())
				.add(ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get())
				.add(ModItems.CHOCOLATE_PIE_SLICE.get());

		tag(ConventionalItemTags.TOOLS).addTag(CommonTags.TOOLS_KNIFE);
		tag(ConventionalItemTags.SEEDS).add(ModItems.CABBAGE_SEEDS.get(), ModItems.RICE.get(), ModItems.TOMATO_SEEDS.get());
		valueLookupBuilder(ConventionalItemTags.CROPS)
				.forceAddTag(CommonTags.CROPS_CABBAGE)
				.forceAddTag(CommonTags.CROPS_ONION)
				.forceAddTag(CommonTags.CROPS_RICE)
				.forceAddTag(CommonTags.CROPS_TOMATO);
		valueLookupBuilder(ConventionalItemTags.STORAGE_BLOCKS)
				.forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_CARROT)
				.forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_POTATO)
				.forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_BEETROOT)
				.forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_CABBAGE)
				.forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_TOMATO)
				.forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_ONION)
				.forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_RICE)
				.forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_RICE_PANICLE)
				.forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_STRAW);
	}

	public void registerCommonTags() {
		// TODO: Remove on 1.3
		tag(CommonTags.FOODS_MILK).add(Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get());

		tag(CommonTags.CROPS_CABBAGE).add(ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get());
		tag(CommonTags.CROPS_ONION).add(ModItems.ONION.get());
		tag(CommonTags.CROPS_TOMATO).add(ModItems.TOMATO.get());
		tag(CommonTags.CROPS_RICE).add(ModItems.RICE.get());

		valueLookupBuilder(CommonTags.FOODS_CABBAGE).add(ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get());
		valueLookupBuilder(CommonTags.FOODS_TOMATO).add(ModItems.TOMATO.get());
		valueLookupBuilder(CommonTags.FOODS_ONION).add(ModItems.ONION.get());

		tag(CommonTags.FOODS_DOUGH_WHEAT).add(ModItems.WHEAT_DOUGH.get());
		tag(CommonTags.CROPS_GRAIN).add(Items.WHEAT, ModItems.RICE.get());
		tag(CommonTags.FOODS_PASTA).add(ModItems.RAW_PASTA.get());
		tag(CommonTags.FOODS_LEAFY_GREEN).addTag(CommonTags.FOODS_CABBAGE);

		valueLookupBuilder(CommonTags.FOODS_RAW_BACON).add(ModItems.BACON.get());
		valueLookupBuilder(CommonTags.FOODS_RAW_BEEF).add(Items.BEEF, ModItems.MINCED_BEEF.get());
		valueLookupBuilder(CommonTags.FOODS_RAW_CHICKEN).add(Items.CHICKEN, ModItems.CHICKEN_CUTS.get());
		valueLookupBuilder(CommonTags.FOODS_RAW_PORK).add(Items.PORKCHOP).forceAddTag(CommonTags.FOODS_RAW_BACON);
		valueLookupBuilder(CommonTags.FOODS_RAW_MUTTON).add(Items.MUTTON, ModItems.MUTTON_CHOPS.get());
		valueLookupBuilder(CommonTags.FOODS_RAW_COD).add(Items.COD, ModItems.COD_SLICE.get());
		valueLookupBuilder(CommonTags.FOODS_RAW_SALMON).add(Items.SALMON, ModItems.SALMON_SLICE.get());
		// Refabricated: You are unable to remove contents from tags. We'll do this.
		valueLookupBuilder(CommonTags.FOODS_SAFE_RAW_FISH)
				.add(Items.COD)
				.add(Items.SALMON)
				.add(Items.TROPICAL_FISH);

		valueLookupBuilder(CommonTags.FOODS_COOKED_BACON).add(ModItems.COOKED_BACON.get());
		valueLookupBuilder(CommonTags.FOODS_COOKED_BEEF).add(Items.COOKED_BEEF, ModItems.BEEF_PATTY.get());
		valueLookupBuilder(CommonTags.FOODS_COOKED_CHICKEN).add(Items.COOKED_CHICKEN, ModItems.COOKED_CHICKEN_CUTS.get());
		valueLookupBuilder(CommonTags.FOODS_COOKED_PORK).add(Items.COOKED_PORKCHOP).forceAddTag(CommonTags.FOODS_COOKED_BACON);
		valueLookupBuilder(CommonTags.FOODS_COOKED_MUTTON).add(Items.COOKED_MUTTON, ModItems.COOKED_MUTTON_CHOPS.get());
		valueLookupBuilder(CommonTags.FOODS_COOKED_COD).add(Items.COOKED_COD, ModItems.COOKED_COD_SLICE.get());
		valueLookupBuilder(CommonTags.FOODS_COOKED_SALMON).add(Items.COOKED_SALMON, ModItems.COOKED_SALMON_SLICE.get());
		valueLookupBuilder(CommonTags.FOODS_COOKED_EGG).add(ModItems.FRIED_EGG.get());

		valueLookupBuilder(CommonTags.STORAGE_BLOCKS_ITEM_CARROT).add(ModItems.CARROT_CRATE.get());
		valueLookupBuilder(CommonTags.STORAGE_BLOCKS_ITEM_POTATO).add(ModItems.POTATO_CRATE.get());
		valueLookupBuilder(CommonTags.STORAGE_BLOCKS_ITEM_BEETROOT).add(ModItems.BEETROOT_CRATE.get());
		valueLookupBuilder(CommonTags.STORAGE_BLOCKS_ITEM_CABBAGE).add(ModItems.CABBAGE_CRATE.get());
		valueLookupBuilder(CommonTags.STORAGE_BLOCKS_ITEM_TOMATO).add(ModItems.TOMATO_CRATE.get());
		valueLookupBuilder(CommonTags.STORAGE_BLOCKS_ITEM_ONION).add(ModItems.ONION_CRATE.get());
		valueLookupBuilder(CommonTags.STORAGE_BLOCKS_ITEM_RICE).add(ModItems.RICE_BAG.get());
		valueLookupBuilder(CommonTags.STORAGE_BLOCKS_ITEM_RICE_PANICLE).add(ModItems.RICE_BALE.get());
		valueLookupBuilder(CommonTags.STORAGE_BLOCKS_ITEM_STRAW).add(ModItems.STRAW_BALE.get());

		valueLookupBuilder(CommonTags.TOOLS_KNIFE).add(ModItems.FLINT_KNIFE.get(), ModItems.COPPER_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.GOLDEN_KNIFE.get(), ModItems.NETHERITE_KNIFE.get());
	}

	public void registerCompatibilityTags() {
		valueLookupBuilder(CompatibilityTags.CREATE_UPRIGHT_ON_BELT)
				.addTag(ModTags.MEALS)
				.addTag(ModTags.DRINKS)
				.addTag(ModTags.FEASTS)
				.add(ModItems.TOMATO_SAUCE.get())
				.add(ModItems.DOG_FOOD.get())
				.add(ModItems.FRUIT_SALAD.get())
				.add(ModItems.NETHER_SALAD.get())
				.add(ModItems.PIE_CRUST.get())
				.add(ModItems.APPLE_PIE.get())
				.add(ModItems.SWEET_BERRY_CHEESECAKE.get())
				.add(ModItems.CHOCOLATE_PIE.get());

		valueLookupBuilder(CompatibilityTags.CREATE_CA_PLANT_FOODS)
				.add(ModItems.PUMPKIN_SLICE.get())
				.add(ModItems.ROTTEN_TOMATO.get())
				.add(ModItems.RICE_PANICLE.get());
		valueLookupBuilder(CompatibilityTags.CREATE_CA_PLANTS)
				.add(ModItems.SANDY_SHRUB.get())
				.add(ModItems.BROWN_MUSHROOM_COLONY.get())
				.add(ModItems.RED_MUSHROOM_COLONY.get());

		valueLookupBuilder(CompatibilityTags.ORIGINS_MEAT)
				.add(ModItems.FRIED_EGG.get())
				.add(ModItems.COD_SLICE.get())
				.add(ModItems.COOKED_COD_SLICE.get())
				.add(ModItems.SALMON_SLICE.get())
				.add(ModItems.COOKED_SALMON_SLICE.get())
				.add(ModItems.BACON_AND_EGGS.get());

		valueLookupBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS)
				.add(ModItems.CABBAGE_SEEDS.get())
				.add(ModItems.ONION.get())
				.add(ModItems.RICE.get());
		valueLookupBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS)
				.add(ModItems.ONION.get());
		valueLookupBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS)
				.add(ModItems.TOMATO_SEEDS.get())
				.add(ModItems.RICE.get());
		valueLookupBuilder(CompatibilityTags.SERENE_SEASONS_WINTER_CROPS)
				.add(ModItems.CABBAGE_SEEDS.get());

		valueLookupBuilder(CompatibilityTags.TINKERS_CONSTRUCT_SEEDS)
				.add(ModItems.ONION.get());
	}

	private TagAppender<Item, Item> tag(TagKey<Item> tag) {
		return valueLookupBuilder(tag);
	}
}
