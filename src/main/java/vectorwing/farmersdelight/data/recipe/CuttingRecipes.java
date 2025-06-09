package vectorwing.farmersdelight.data.recipe;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.refabricated.ItemAbility;

public class CuttingRecipes
{
	public static void register(HolderLookup.Provider provider, RecipeOutput output) {
		HolderGetter<Item> holderGetter = provider.lookupOrThrow(Registries.ITEM);
		// Knife
		cuttingAnimalItems(holderGetter, output);
		cuttingVegetables(holderGetter, output);
		cuttingFoods(holderGetter, output);
		cuttingFlowers(holderGetter, output);

		// Pickaxe
		salvagingMinerals(output);

		// Axe
		strippingWood(holderGetter, output);
		salvagingWoodenFurniture(holderGetter, output);

		// Shovel
		diggingSediments(holderGetter, output);

		// Shears
		salvagingUsingShears(holderGetter, output);
	}

	private static void cuttingAnimalItems(HolderGetter<Item> holderGetter, RecipeOutput output) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BEEF), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.MINCED_BEEF.get(), 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.PORKCHOP), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.BACON.get(), 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CHICKEN), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.CHICKEN_CUTS.get(), 2)
				.addResult(Items.BONE_MEAL)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.COOKED_CHICKEN), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.COOKED_CHICKEN_CUTS.get(), 2)
				.addResult(Items.BONE_MEAL)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.COD), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.COD_SLICE.get(), 2)
				.addResult(Items.BONE_MEAL)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.COOKED_COD), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.COOKED_COD_SLICE.get(), 2)
				.addResult(Items.BONE_MEAL)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.SALMON), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.SALMON_SLICE.get(), 2)
				.addResult(Items.BONE_MEAL)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.COOKED_SALMON), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.COOKED_SALMON_SLICE.get(), 2)
				.addResult(Items.BONE_MEAL)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.HAM.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.PORKCHOP, 2)
				.addResult(Items.BONE)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.SMOKED_HAM.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.COOKED_PORKCHOP, 2)
				.addResult(Items.BONE)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.MUTTON), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.MUTTON_CHOPS.get(), 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.COOKED_MUTTON), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.COOKED_MUTTON_CHOPS.get(), 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.INK_SAC), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.BLACK_DYE, 2)
				.build(output);
	}

	private static void cuttingVegetables(HolderGetter<Item> holderGetter, RecipeOutput output) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CABBAGE.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.CABBAGE_LEAF.get(), 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.RICE_PANICLE.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.RICE.get(), 1)
				.addResult(ModItems.STRAW.get())
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.MELON), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.MELON_SLICE, 9)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.PUMPKIN), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.PUMPKIN_SLICE.get(), 4)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BROWN_MUSHROOM_COLONY.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.BROWN_MUSHROOM, 5)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.RED_MUSHROOM_COLONY.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.RED_MUSHROOM, 5)
				.build(output);
	}

	private static void cuttingFoods(HolderGetter<Item> holderGetter, RecipeOutput output) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(holderGetter.getOrThrow(CommonTags.FOODS_DOUGH)), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.RAW_PASTA.get(), 1)
				.build(output, ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "tag_dough"));
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.KELP_ROLL.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.KELP_ROLL_SLICE.get(), 3)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CAKE), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.CAKE_SLICE.get(), 7)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.APPLE_PIE.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.APPLE_PIE_SLICE.get(), 4)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.SWEET_BERRY_CHEESECAKE.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get(), 4)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CHOCOLATE_PIE.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.CHOCOLATE_PIE_SLICE.get(), 4)
				.build(output);
	}

	private static void cuttingFlowers(HolderGetter<Item> holderGetter, RecipeOutput output) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.WITHER_ROSE), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.BLACK_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CORNFLOWER), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.BLUE_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BLUE_ORCHID), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.LIGHT_BLUE_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.AZURE_BLUET), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.LIGHT_GRAY_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.OXEYE_DAISY), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.LIGHT_GRAY_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.WHITE_TULIP), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.LIGHT_GRAY_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.ALLIUM), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.MAGENTA_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.ORANGE_TULIP), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.ORANGE_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.PINK_TULIP), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.PINK_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.RED_TULIP), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.RED_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POPPY), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.RED_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.LILY_OF_THE_VALLEY), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.WHITE_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.DANDELION), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.YELLOW_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.TORCHFLOWER), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.ORANGE_DYE, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_BEETROOTS.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.BEETROOT_SEEDS, 1)
				.addResult(Items.RED_DYE)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_CABBAGES.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.CABBAGE_SEEDS.get(), 1)
				.addResultWithChance(Items.YELLOW_DYE, 0.5F, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_CARROTS.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.CARROT, 1)
				.addResultWithChance(Items.LIGHT_GRAY_DYE, 0.5F, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_ONIONS.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.ONION.get(), 1)
				.addResult(Items.MAGENTA_DYE, 2)
				.addResultWithChance(Items.LIME_DYE, 0.1F)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_POTATOES.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), Items.POTATO, 1)
				.addResultWithChance(Items.PURPLE_DYE, 0.5F, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_RICE.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.RICE.get(), 1)
				.addResultWithChance(ModItems.STRAW.get(), 0.5F)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_TOMATOES.get()), Ingredient.of(holderGetter.getOrThrow(CommonTags.TOOLS_KNIFE)), ModItems.TOMATO_SEEDS.get(), 1)
				.addResultWithChance(ModItems.TOMATO.get(), 0.2F)
				.addResultWithChance(Items.GREEN_DYE, 0.1F)
				.build(output);
	}

	private static void salvagingMinerals(RecipeOutput output) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BRICKS), new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(), Items.BRICK, 4)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.NETHER_BRICKS), new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(), Items.NETHER_BRICK, 4)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.STONE), new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(), Items.COBBLESTONE, 1)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.DEEPSLATE), new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(), Items.COBBLED_DEEPSLATE, 1)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.QUARTZ_BLOCK), new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(), Items.QUARTZ, 4)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.AMETHYST_BLOCK), new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(), Items.AMETHYST_SHARD, 4)
				.build(output);
	}

	private static void strippingWood(HolderGetter<Item> holderGetter, RecipeOutput output) {
		stripLogForBark(holderGetter, output, Items.OAK_LOG, Items.STRIPPED_OAK_LOG);
		stripLogForBark(holderGetter, output, Items.OAK_WOOD, Items.STRIPPED_OAK_WOOD);
		stripLogForBark(holderGetter, output, Items.SPRUCE_LOG, Items.STRIPPED_SPRUCE_LOG);
		stripLogForBark(holderGetter, output, Items.SPRUCE_WOOD, Items.STRIPPED_SPRUCE_WOOD);
		stripLogForBark(holderGetter, output, Items.BIRCH_LOG, Items.STRIPPED_BIRCH_LOG);
		stripLogForBark(holderGetter, output, Items.BIRCH_WOOD, Items.STRIPPED_BIRCH_WOOD);
		stripLogForBark(holderGetter, output, Items.JUNGLE_LOG, Items.STRIPPED_JUNGLE_LOG);
		stripLogForBark(holderGetter, output, Items.JUNGLE_WOOD, Items.STRIPPED_JUNGLE_WOOD);
		stripLogForBark(holderGetter, output, Items.ACACIA_LOG, Items.STRIPPED_ACACIA_LOG);
		stripLogForBark(holderGetter, output, Items.ACACIA_WOOD, Items.STRIPPED_ACACIA_WOOD);
		stripLogForBark(holderGetter, output, Items.DARK_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG);
		stripLogForBark(holderGetter, output, Items.DARK_OAK_WOOD, Items.STRIPPED_DARK_OAK_WOOD);
		stripLogForBark(holderGetter, output, Items.MANGROVE_LOG, Items.STRIPPED_MANGROVE_LOG);
		stripLogForBark(holderGetter, output, Items.MANGROVE_WOOD, Items.STRIPPED_MANGROVE_WOOD);
		stripLogForBark(holderGetter, output, Items.CHERRY_LOG, Items.STRIPPED_CHERRY_LOG);
		stripLogForBark(holderGetter, output, Items.CHERRY_WOOD, Items.STRIPPED_CHERRY_WOOD);
		stripLogForBark(holderGetter, output, Items.PALE_OAK_LOG, Items.STRIPPED_PALE_OAK_LOG);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAMBOO_BLOCK), new ItemAbilityIngredient(ItemAbility.AXE_STRIP).toVanilla(), Items.STRIPPED_BAMBOO_BLOCK)
				.addResult(ModItems.STRAW.get())
				.addSound(SoundEvents.AXE_STRIP).build(output);
		stripLogForBark(holderGetter, output, Items.CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM);
		stripLogForBark(holderGetter, output, Items.CRIMSON_HYPHAE, Items.STRIPPED_CRIMSON_HYPHAE);
		stripLogForBark(holderGetter, output, Items.WARPED_STEM, Items.STRIPPED_WARPED_STEM);
		stripLogForBark(holderGetter, output, Items.WARPED_HYPHAE, Items.STRIPPED_WARPED_HYPHAE);
	}

	private static void salvagingWoodenFurniture(HolderGetter<Item> holderGetter, RecipeOutput output) {
		salvagePlankFromFurniture(holderGetter, output, Items.OAK_PLANKS, Items.OAK_DOOR, Items.OAK_TRAPDOOR, Items.OAK_SIGN, Items.OAK_HANGING_SIGN);
		salvagePlankFromFurniture(holderGetter, output, Items.SPRUCE_PLANKS, Items.SPRUCE_DOOR, Items.SPRUCE_TRAPDOOR, Items.SPRUCE_SIGN, Items.SPRUCE_HANGING_SIGN);
		salvagePlankFromFurniture(holderGetter, output, Items.BIRCH_PLANKS, Items.BIRCH_DOOR, Items.BIRCH_TRAPDOOR, Items.BIRCH_SIGN, Items.BIRCH_HANGING_SIGN);
		salvagePlankFromFurniture(holderGetter, output, Items.JUNGLE_PLANKS, Items.JUNGLE_DOOR, Items.JUNGLE_TRAPDOOR, Items.JUNGLE_SIGN, Items.JUNGLE_HANGING_SIGN);
		salvagePlankFromFurniture(holderGetter, output, Items.ACACIA_PLANKS, Items.ACACIA_DOOR, Items.ACACIA_TRAPDOOR, Items.ACACIA_SIGN, Items.ACACIA_HANGING_SIGN);
		salvagePlankFromFurniture(holderGetter, output, Items.DARK_OAK_PLANKS, Items.DARK_OAK_DOOR, Items.DARK_OAK_TRAPDOOR, Items.DARK_OAK_SIGN, Items.DARK_OAK_HANGING_SIGN);
		salvagePlankFromFurniture(holderGetter, output, Items.MANGROVE_PLANKS, Items.MANGROVE_DOOR, Items.MANGROVE_TRAPDOOR, Items.MANGROVE_SIGN, Items.MANGROVE_HANGING_SIGN);
		salvagePlankFromFurniture(holderGetter, output, Items.CHERRY_PLANKS, Items.CHERRY_DOOR, Items.CHERRY_TRAPDOOR, Items.CHERRY_SIGN, Items.CHERRY_HANGING_SIGN);
		salvagePlankFromFurniture(holderGetter, output, Items.BAMBOO_PLANKS, Items.BAMBOO_DOOR, Items.BAMBOO_TRAPDOOR, Items.BAMBOO_SIGN, Items.BAMBOO_HANGING_SIGN);
		salvagePlankFromFurniture(holderGetter, output, Items.CRIMSON_PLANKS, Items.CRIMSON_DOOR, Items.CRIMSON_TRAPDOOR, Items.CRIMSON_SIGN, Items.CRIMSON_HANGING_SIGN);
		salvagePlankFromFurniture(holderGetter, output, Items.WARPED_PLANKS, Items.WARPED_DOOR, Items.WARPED_TRAPDOOR, Items.WARPED_SIGN, Items.WARPED_HANGING_SIGN);
	}

	private static void diggingSediments(HolderGetter<Item> holderGetter, RecipeOutput output) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CLAY), new ItemAbilityIngredient(ItemAbility.SHOVEL_DIG).toVanilla(), Items.CLAY_BALL, 4)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.GRAVEL), new ItemAbilityIngredient(ItemAbility.SHOVEL_DIG).toVanilla(), Items.GRAVEL, 1)
				.addResultWithChance(Items.FLINT, 0.1F)
				.build(output);
	}

	private static void salvagingUsingShears(HolderGetter<Item> holderGetter, RecipeOutput output) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.SADDLE), Ingredient.of(holderGetter.getOrThrow(ConventionalItemTags.SHEAR_TOOLS)), Items.LEATHER, 2)
				.addResultWithChance(Items.IRON_NUGGET, 0.5F, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.LEATHER_HORSE_ARMOR), Ingredient.of(holderGetter.getOrThrow(ConventionalItemTags.SHEAR_TOOLS)), Items.LEATHER, 2)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.LEATHER_HELMET), Ingredient.of(holderGetter.getOrThrow(ConventionalItemTags.SHEAR_TOOLS)), Items.LEATHER, 1)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.LEATHER_CHESTPLATE), Ingredient.of(holderGetter.getOrThrow(ConventionalItemTags.SHEAR_TOOLS)), Items.LEATHER, 1)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.LEATHER_LEGGINGS), Ingredient.of(holderGetter.getOrThrow(ConventionalItemTags.SHEAR_TOOLS)), Items.LEATHER, 1)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.LEATHER_BOOTS), Ingredient.of(holderGetter.getOrThrow(ConventionalItemTags.SHEAR_TOOLS)), Items.LEATHER, 1)
				.build(output);
	}


	/**
	 * Generates an axe-cutting recipe for each furniture, resulting in one plank of the given type.
	 */
	private static void salvagePlankFromFurniture(HolderGetter<Item> holderGetter, RecipeOutput output, ItemLike plank, ItemLike door, ItemLike trapdoor, ItemLike sign, ItemLike hangingSign) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(door), new ItemAbilityIngredient(ItemAbility.AXE_DIG).toVanilla(), plank).build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(trapdoor), new ItemAbilityIngredient(ItemAbility.AXE_DIG).toVanilla(), plank).build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(sign), new ItemAbilityIngredient(ItemAbility.AXE_DIG).toVanilla(), plank).build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(hangingSign), new ItemAbilityIngredient(ItemAbility.AXE_DIG).toVanilla(), plank).build(output);
	}

	/**
	 * Generates an axe-stripping recipe for the pair of given logs, with custom sound and a Tree Bark result attached.
	 */
	private static void stripLogForBark(HolderGetter<Item> holderGetter, RecipeOutput output, ItemLike log, ItemLike strippedLog) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(log), new ItemAbilityIngredient(ItemAbility.AXE_STRIP).toVanilla(), strippedLog)
				.addResult(ModItems.TREE_BARK.get())
				.addSound(SoundEvents.AXE_STRIP).build(output);
	}
}
