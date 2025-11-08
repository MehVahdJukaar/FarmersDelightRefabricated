package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.CompatibilityTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ItemTags extends FabricTagProvider.ItemTagProvider
{
	public ItemTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> provider, BlockTagProvider blockTagProvider) {
		super(output, provider, blockTagProvider);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		copy(ModTags.WILD_CROPS, ModTags.WILD_CROPS_ITEM);
		copy(BlockTags.SMALL_FLOWERS, net.minecraft.tags.ItemTags.SMALL_FLOWERS);

		this.registerMinecraftTags();
		this.registerModTags();
		this.registerNeoForgeTags();
		this.registerCommonTags();
		this.registerCompatibilityTags();
	}

	private void registerMinecraftTags() {
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.BREAKS_DECORATED_POTS).addTag(ModTags.KNIVES);
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.TALL_FLOWERS).add(ModItems.WILD_RICE.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.PIGLIN_LOVED).add(ModItems.GOLDEN_KNIFE.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.SIGNS).addTag(ModTags.CANVAS_SIGNS);
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.HANGING_SIGNS).addTag(ModTags.HANGING_CANVAS_SIGNS);
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.VILLAGER_PLANTABLE_SEEDS)
				.add(ModItems.CABBAGE_SEEDS.get())
				.add(ModItems.TOMATO_SEEDS.get())
				.add(ModItems.ONION.get());

		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.FIRE_ASPECT_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.SWORD_ENCHANTABLE).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.MINING_ENCHANTABLE).addTag(ModTags.KNIVES);
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.MINING_LOOT_ENCHANTABLE).addTag(ModTags.KNIVES);

		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.MEAT)
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
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.CHICKEN_FOOD)
				.add(ModItems.CABBAGE_SEEDS.get())
				.add(ModItems.TOMATO_SEEDS.get())
				.add(ModItems.RICE.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.PIG_FOOD)
				.add(ModItems.CABBAGE.get())
				.add(ModItems.TOMATO.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.WOLF_FOOD)
				.add(ModItems.BEEF_STEW.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.RABBIT_FOOD)
				.add(ModItems.CABBAGE.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.PARROT_FOOD)
				.add(ModItems.CABBAGE_SEEDS.get())
				.add(ModItems.TOMATO_SEEDS.get())
				.add(ModItems.RICE.get());
		getOrCreateTagBuilder(net.minecraft.tags.ItemTags.HORSE_TEMPT_ITEMS)
				.add(ModItems.HORSE_FEED.get());
	}

	private void registerModTags() {
		getOrCreateTagBuilder(ModTags.MEALS).add(
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
		getOrCreateTagBuilder(ModTags.DRINKS).add(
				ModItems.MILK_BOTTLE.get(),
				ModItems.APPLE_CIDER.get(),
				ModItems.MELON_JUICE.get(),
				ModItems.HOT_COCOA.get()
		);
		getOrCreateTagBuilder(ModTags.FEASTS).add(
				ModItems.ROAST_CHICKEN_BLOCK.get(),
				ModItems.STUFFED_PUMPKIN_BLOCK.get(),
				ModItems.SHEPHERDS_PIE_BLOCK.get(),
				ModItems.HONEY_GLAZED_HAM_BLOCK.get(),
				ModItems.RICE_ROLL_MEDLEY_BLOCK.get()
		);
		getOrCreateTagBuilder(ModTags.KNIVES).add(ModItems.FLINT_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.GOLDEN_KNIFE.get(), ModItems.NETHERITE_KNIFE.get());
		getOrCreateTagBuilder(ModTags.KNIFE_ENCHANTABLE).forceAddTag(ModTags.KNIVES);
		getOrCreateTagBuilder(ModTags.STRAW_HARVESTERS).forceAddTag(ModTags.KNIVES);
		getOrCreateTagBuilder(ModTags.CABBAGE_ROLL_INGREDIENTS).forceAddTag(CommonTags.FOODS_RAW_PORK).forceAddTag(CommonTags.FOODS_SAFE_RAW_FISH).forceAddTag(CommonTags.FOODS_RAW_CHICKEN).forceAddTag(CommonTags.FOODS_RAW_BEEF).forceAddTag(CommonTags.FOODS_RAW_MUTTON).forceAddTag(ConventionalItemTags.EGGS).forceAddTag(ConventionalItemTags.MUSHROOMS).add(Items.CARROT, Items.POTATO, Items.BEETROOT);
		getOrCreateTagBuilder(ModTags.CANVAS_SIGNS)
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
		getOrCreateTagBuilder(ModTags.HANGING_CANVAS_SIGNS)
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
		getOrCreateTagBuilder(ModTags.WOODEN_CABINETS)
				.add(ModItems.OAK_CABINET.get())
				.add(ModItems.SPRUCE_CABINET.get())
				.add(ModItems.BIRCH_CABINET.get())
				.add(ModItems.JUNGLE_CABINET.get())
				.add(ModItems.ACACIA_CABINET.get())
				.add(ModItems.DARK_OAK_CABINET.get())
				.add(ModItems.MANGROVE_CABINET.get())
				.add(ModItems.CHERRY_CABINET.get())
				.add(ModItems.BAMBOO_CABINET.get())
				.add(ModItems.CRIMSON_CABINET.get())
				.add(ModItems.WARPED_CABINET.get());
		getOrCreateTagBuilder(ModTags.CABINETS).forceAddTag(ModTags.WOODEN_CABINETS);
		getOrCreateTagBuilder(ModTags.OFFHAND_EQUIPMENT).add(Items.SHIELD);
		getOrCreateRawBuilder(ModTags.OFFHAND_EQUIPMENT)
				.addOptionalElement(ResourceLocation.parse("create:extendo_grip"));
		getOrCreateTagBuilder(ModTags.SERVING_CONTAINERS).add(Items.BOWL, Items.GLASS_BOTTLE, Items.BUCKET);
		getOrCreateTagBuilder(ModTags.FLAT_ON_CUTTING_BOARD).add(Items.TRIDENT, Items.SPYGLASS);
		getOrCreateRawBuilder(ModTags.FLAT_ON_CUTTING_BOARD)
				.addOptionalElement(ResourceLocation.parse("supplementaries:quiver"))
				.addOptionalElement(ResourceLocation.parse("autumnity:turkey"))
				.addOptionalElement(ResourceLocation.parse("autumnity:cooked_turkey"));
	}

	@SuppressWarnings("unchecked")
	private void registerNeoForgeTags() {
		getOrCreateTagBuilder(ConventionalItemTags.CROPS)
				.addTag(CommonTags.CROPS_GRAIN);
		getOrCreateTagBuilder(ConventionalItemTags.FOODS)
				.addTag(CommonTags.FOODS_LEAFY_GREEN)
				.addTag(CommonTags.FOODS_DOUGH)
				.addTag(CommonTags.FOODS_PASTA)
				.addTag(CommonTags.FOODS_COOKED_EGG)
				.addTag(CommonTags.FOODS_MILK);
		getOrCreateTagBuilder(ConventionalItemTags.VEGETABLE_FOODS).add(ModItems.ONION.get(), ModItems.TOMATO.get());
		getOrCreateTagBuilder(ConventionalItemTags.COOKIE_FOODS).add(ModItems.HONEY_COOKIE.get(), ModItems.SWEET_BERRY_COOKIE.get());
		getOrCreateTagBuilder(ConventionalItemTags.RAW_MEAT_FOODS).forceAddTag(CommonTags.FOODS_RAW_CHICKEN).forceAddTag(CommonTags.FOODS_RAW_PORK).forceAddTag(CommonTags.FOODS_RAW_BEEF).forceAddTag(CommonTags.FOODS_RAW_MUTTON);
		getOrCreateTagBuilder(ConventionalItemTags.RAW_FISH_FOODS).forceAddTag(CommonTags.FOODS_RAW_COD).forceAddTag(CommonTags.FOODS_RAW_SALMON);
		getOrCreateTagBuilder(ConventionalItemTags.COOKED_MEAT_FOODS).forceAddTag(CommonTags.FOODS_COOKED_CHICKEN).forceAddTag(CommonTags.FOODS_COOKED_PORK).forceAddTag(CommonTags.FOODS_COOKED_BEEF).forceAddTag(CommonTags.FOODS_COOKED_MUTTON);
		getOrCreateTagBuilder(ConventionalItemTags.COOKED_FISH_FOODS).forceAddTag(CommonTags.FOODS_COOKED_COD).forceAddTag(CommonTags.FOODS_COOKED_SALMON);
		getOrCreateTagBuilder(ConventionalItemTags.FOOD_POISONING_FOODS).add(ModItems.CHICKEN_CUTS.get());
		getOrCreateTagBuilder(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
				.add(ModItems.APPLE_PIE.get())
				.add(ModItems.SWEET_BERRY_CHEESECAKE.get())
				.add(ModItems.CHOCOLATE_PIE.get())
				.add(ModItems.ROAST_CHICKEN_BLOCK.get())
				.add(ModItems.HONEY_GLAZED_HAM_BLOCK.get())
				.add(ModItems.SHEPHERDS_PIE_BLOCK.get())
				.add(ModItems.STUFFED_PUMPKIN_BLOCK.get())
				.add(ModItems.RICE_ROLL_MEDLEY_BLOCK.get());
		getOrCreateTagBuilder(ConventionalItemTags.SOUP_FOODS)
				.add(ModItems.BONE_BROTH.get())
				.add(ModItems.BEEF_STEW.get())
				.add(ModItems.VEGETABLE_SOUP.get())
				.add(ModItems.CHICKEN_SOUP.get())
				.add(ModItems.FISH_STEW.get())
				.add(ModItems.PUMPKIN_SOUP.get())
				.add(ModItems.BAKED_COD_STEW.get())
				.add(ModItems.NOODLE_SOUP.get());

		getOrCreateTagBuilder(ConventionalItemTags.TOOLS).forceAddTag(CommonTags.TOOLS_KNIFE);
		getOrCreateTagBuilder(ConventionalItemTags.SEEDS).add(ModItems.CABBAGE_SEEDS.get(), ModItems.RICE.get(), ModItems.TOMATO_SEEDS.get());
		getOrCreateTagBuilder(ConventionalItemTags.CROPS).forceAddTag(CommonTags.CROPS_CABBAGE).forceAddTag(CommonTags.CROPS_ONION).forceAddTag(CommonTags.CROPS_RICE).forceAddTag(CommonTags.CROPS_TOMATO);
		getOrCreateTagBuilder(ConventionalItemTags.STORAGE_BLOCKS).forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_CARROT).forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_POTATO).forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_BEETROOT).forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_CABBAGE).forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_TOMATO).forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_ONION).forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_RICE).forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_RICE_PANICLE).forceAddTag(CommonTags.STORAGE_BLOCKS_ITEM_STRAW);
	}

	public void registerCommonTags() {
		getOrCreateTagBuilder(CommonTags.CROPS_CABBAGE).add(ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get());
		getOrCreateTagBuilder(CommonTags.CROPS_ONION).add(ModItems.ONION.get());
		getOrCreateTagBuilder(CommonTags.CROPS_TOMATO).add(ModItems.TOMATO.get());
		getOrCreateTagBuilder(CommonTags.CROPS_RICE).add(ModItems.RICE.get());

		getOrCreateTagBuilder(CommonTags.FOODS_CABBAGE).add(ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get());
		getOrCreateTagBuilder(CommonTags.FOODS_TOMATO).add(ModItems.TOMATO.get());
		getOrCreateTagBuilder(CommonTags.FOODS_ONION).add(ModItems.ONION.get());

		getOrCreateTagBuilder(CommonTags.FOODS_DOUGH).add(ModItems.WHEAT_DOUGH.get());
		getOrCreateTagBuilder(CommonTags.CROPS_GRAIN).add(Items.WHEAT, ModItems.RICE.get());
		getOrCreateTagBuilder(CommonTags.FOODS_MILK).add(Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get());
		getOrCreateTagBuilder(CommonTags.FOODS_PASTA).add(ModItems.RAW_PASTA.get());
		getOrCreateTagBuilder(CommonTags.FOODS_LEAFY_GREEN).forceAddTag(CommonTags.FOODS_CABBAGE);

		getOrCreateTagBuilder(CommonTags.FOODS_RAW_BACON).add(ModItems.BACON.get());
		getOrCreateTagBuilder(CommonTags.FOODS_RAW_BEEF).add(Items.BEEF, ModItems.MINCED_BEEF.get());
		getOrCreateTagBuilder(CommonTags.FOODS_RAW_CHICKEN).add(Items.CHICKEN, ModItems.CHICKEN_CUTS.get());
		getOrCreateTagBuilder(CommonTags.FOODS_RAW_PORK).add(Items.PORKCHOP).forceAddTag(CommonTags.FOODS_RAW_BACON);
		getOrCreateTagBuilder(CommonTags.FOODS_RAW_MUTTON).add(Items.MUTTON, ModItems.MUTTON_CHOPS.get());
		getOrCreateTagBuilder(CommonTags.FOODS_RAW_COD).add(Items.COD, ModItems.COD_SLICE.get());
		getOrCreateTagBuilder(CommonTags.FOODS_RAW_SALMON).add(Items.SALMON, ModItems.SALMON_SLICE.get());
		// Refabricated: You are unable to remove contents from tags. We'll do this.
		getOrCreateTagBuilder(CommonTags.FOODS_SAFE_RAW_FISH)
				.add(Items.COD)
				.add(Items.SALMON)
				.add(Items.TROPICAL_FISH);

		getOrCreateTagBuilder(CommonTags.FOODS_COOKED_BACON).add(ModItems.COOKED_BACON.get());
		getOrCreateTagBuilder(CommonTags.FOODS_COOKED_BEEF).add(Items.COOKED_BEEF, ModItems.BEEF_PATTY.get());
		getOrCreateTagBuilder(CommonTags.FOODS_COOKED_CHICKEN).add(Items.COOKED_CHICKEN, ModItems.COOKED_CHICKEN_CUTS.get());
		getOrCreateTagBuilder(CommonTags.FOODS_COOKED_PORK).add(Items.COOKED_PORKCHOP).forceAddTag(CommonTags.FOODS_COOKED_BACON);
		getOrCreateTagBuilder(CommonTags.FOODS_COOKED_MUTTON).add(Items.COOKED_MUTTON, ModItems.COOKED_MUTTON_CHOPS.get());
		getOrCreateTagBuilder(CommonTags.FOODS_COOKED_COD).add(Items.COOKED_COD, ModItems.COOKED_COD_SLICE.get());
		getOrCreateTagBuilder(CommonTags.FOODS_COOKED_SALMON).add(Items.COOKED_SALMON, ModItems.COOKED_SALMON_SLICE.get());
		getOrCreateTagBuilder(CommonTags.FOODS_COOKED_EGG).add(ModItems.FRIED_EGG.get());

		getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_CARROT).add(ModItems.CARROT_CRATE.get());
		getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_POTATO).add(ModItems.POTATO_CRATE.get());
		getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_BEETROOT).add(ModItems.BEETROOT_CRATE.get());
		getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_CABBAGE).add(ModItems.CABBAGE_CRATE.get());
		getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_TOMATO).add(ModItems.TOMATO_CRATE.get());
		getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_ONION).add(ModItems.ONION_CRATE.get());
		getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_RICE).add(ModItems.RICE_BAG.get());
		getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_RICE_PANICLE).add(ModItems.RICE_BALE.get());
		getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_STRAW).add(ModItems.STRAW_BALE.get());

		getOrCreateTagBuilder(CommonTags.TOOLS_KNIFE).add(ModItems.FLINT_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.GOLDEN_KNIFE.get(), ModItems.NETHERITE_KNIFE.get());
	}

	public void registerCompatibilityTags() {
		getOrCreateTagBuilder(CompatibilityTags.CREATE_UPRIGHT_ON_BELT)
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

		getOrCreateTagBuilder(CompatibilityTags.CREATE_CA_PLANT_FOODS)
				.add(ModItems.PUMPKIN_SLICE.get())
				.add(ModItems.ROTTEN_TOMATO.get())
				.add(ModItems.RICE_PANICLE.get());
		getOrCreateTagBuilder(CompatibilityTags.CREATE_CA_PLANTS)
				.add(ModItems.SANDY_SHRUB.get())
				.add(ModItems.BROWN_MUSHROOM_COLONY.get())
				.add(ModItems.RED_MUSHROOM_COLONY.get());

		getOrCreateTagBuilder(CompatibilityTags.ORIGINS_MEAT)
				.add(ModItems.FRIED_EGG.get())
				.add(ModItems.COD_SLICE.get())
				.add(ModItems.COOKED_COD_SLICE.get())
				.add(ModItems.SALMON_SLICE.get())
				.add(ModItems.COOKED_SALMON_SLICE.get())
				.add(ModItems.BACON_AND_EGGS.get());

		getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS)
				.add(ModItems.CABBAGE_SEEDS.get())
				.add(ModItems.ONION.get())
				.add(ModItems.RICE.get());
		getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS)
				.add(ModItems.ONION.get());
		getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS)
				.add(ModItems.TOMATO_SEEDS.get())
				.add(ModItems.RICE.get());
		getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_WINTER_CROPS)
				.add(ModItems.CABBAGE_SEEDS.get());

		getOrCreateTagBuilder(CompatibilityTags.TINKERS_CONSTRUCT_SEEDS)
				.add(ModItems.ONION.get());
	}
}
