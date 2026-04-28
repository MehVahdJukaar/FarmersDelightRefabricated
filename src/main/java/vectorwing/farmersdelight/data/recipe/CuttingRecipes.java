package vectorwing.farmersdelight.data.recipe;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
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
    public static Ingredient KNIVES = matchesTool(KnifeItem.KNIFE_DIG, CommonTags.Items.TOOLS_KNIFE);
    public static Ingredient PICKAXES = matchesTool(ItemAbility.PICKAXE_DIG, ItemTags.PICKAXES);
    public static Ingredient AXES = matchesTool(ItemAbility.AXE_DIG, ItemTags.AXES);
    public static Ingredient AXES_STRIP = matchesTool(ItemAbility.AXE_STRIP, ItemTags.AXES);
    public static Ingredient SHOVELS = matchesTool(ItemAbility.SHOVEL_DIG, ItemTags.SHOVELS);
    public static Ingredient HOES = matchesTool(ItemAbility.HOE_DIG, ItemTags.HOES);
    public static Ingredient SHEARS = matchesTool(ItemAbility.SHEARS_DIG, ConventionalItemTags.SHEAR_TOOLS);

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

		// Hoe
		salvagingBlockFromVehicle(output);
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
				.build(output, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "tag_dough"));
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
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_TOMATOES.get()), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.TOMATO_SEEDS.get(), 1)
				.addResultWithChance(ModItems.TOMATO.get(), 0.2F)
				.addResultWithChance(Items.GREEN_DYE, 0.1F)
				.saveToFD(output);
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
		stripLogForBark(holderGetter, output, Items.PALE_OAK_WOOD, Items.STRIPPED_PALE_OAK_WOOD);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAMBOO_BLOCK), new ItemAbilityIngredient(ItemAbility.AXE_STRIP).toVanilla(), Items.STRIPPED_BAMBOO_BLOCK)
				.addResult(ModItems.STRAW.get())
				.addSound(SoundEvents.AXE_STRIP).build(output);
		stripLogForBark(holderGetter, output, Items.CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM);
		stripLogForBark(holderGetter, output, Items.CRIMSON_HYPHAE, Items.STRIPPED_CRIMSON_HYPHAE);
		stripLogForBark(holderGetter, output, Items.WARPED_STEM, Items.STRIPPED_WARPED_STEM);
		stripLogForBark(holderGetter, output, Items.WARPED_HYPHAE, Items.STRIPPED_WARPED_HYPHAE);
	}

	private static void salvagingWoodenFurniture(RecipeOutput output) {
		ssalvagePlankFromFurniture(output, WoodType.OAK,
                Items.OAK_PLANKS, Items.OAK_DOOR, Items.OAK_TRAPDOOR, Items.OAK_SIGN, Items.OAK_HANGING_SIGN, Items.OAK_FENCE, Items.OAK_FENCE_GATE,
                Items.OAK_PRESSURE_PLATE, Items.OAK_BUTTON, Items.OAK_BOAT, ModItems.OAK_CABINET.get());
        salvagePlankFromFurniture(output, WoodType.SPRUCE,
                Items.SPRUCE_PLANKS, Items.SPRUCE_DOOR, Items.SPRUCE_TRAPDOOR, Items.SPRUCE_SIGN, Items.SPRUCE_HANGING_SIGN, Items.SPRUCE_FENCE, Items.SPRUCE_FENCE_GATE,
                Items.SPRUCE_PRESSURE_PLATE, Items.SPRUCE_BUTTON, Items.SPRUCE_BOAT, ModItems.SPRUCE_CABINET.get());
        salvagePlankFromFurniture(output, WoodType.BIRCH,
                Items.BIRCH_PLANKS, Items.BIRCH_DOOR, Items.BIRCH_TRAPDOOR, Items.BIRCH_SIGN, Items.BIRCH_HANGING_SIGN, Items.BIRCH_FENCE, Items.BIRCH_FENCE_GATE,
                Items.BIRCH_PRESSURE_PLATE, Items.BIRCH_BUTTON, Items.BIRCH_BOAT, ModItems.BIRCH_CABINET.get());
        salvagePlankFromFurniture(output, WoodType.JUNGLE,
                Items.JUNGLE_PLANKS, Items.JUNGLE_DOOR, Items.JUNGLE_TRAPDOOR, Items.JUNGLE_SIGN, Items.JUNGLE_HANGING_SIGN, Items.JUNGLE_FENCE, Items.JUNGLE_FENCE_GATE,
                Items.JUNGLE_PRESSURE_PLATE, Items.JUNGLE_BUTTON, Items.JUNGLE_BOAT, ModItems.JUNGLE_CABINET.get());
        salvagePlankFromFurniture(output, WoodType.ACACIA,
                Items.ACACIA_PLANKS, Items.ACACIA_DOOR, Items.ACACIA_TRAPDOOR, Items.ACACIA_SIGN, Items.ACACIA_HANGING_SIGN, Items.ACACIA_FENCE, Items.ACACIA_FENCE_GATE,
                Items.ACACIA_PRESSURE_PLATE, Items.ACACIA_BUTTON, Items.ACACIA_BOAT, ModItems.ACACIA_CABINET.get());
        salvagePlankFromFurniture(output, WoodType.DARK_OAK,
                Items.DARK_OAK_PLANKS, Items.DARK_OAK_DOOR, Items.DARK_OAK_TRAPDOOR, Items.DARK_OAK_SIGN, Items.DARK_OAK_HANGING_SIGN, Items.DARK_OAK_FENCE, Items.DARK_OAK_FENCE_GATE,
                Items.DARK_OAK_PRESSURE_PLATE, Items.DARK_OAK_BUTTON, Items.DARK_OAK_BOAT, ModItems.DARK_OAK_CABINET.get());
        salvagePlankFromFurniture(output, WoodType.MANGROVE,
                Items.MANGROVE_PLANKS, Items.MANGROVE_DOOR, Items.MANGROVE_TRAPDOOR, Items.MANGROVE_SIGN, Items.MANGROVE_HANGING_SIGN, Items.MANGROVE_FENCE, Items.MANGROVE_FENCE_GATE,
                Items.MANGROVE_PRESSURE_PLATE, Items.MANGROVE_BUTTON, Items.MANGROVE_BOAT, ModItems.MANGROVE_CABINET.get());
        salvagePlankFromFurniture(output, WoodType.CHERRY,
                Items.CHERRY_PLANKS, Items.CHERRY_DOOR, Items.CHERRY_TRAPDOOR, Items.CHERRY_SIGN, Items.CHERRY_HANGING_SIGN, Items.CHERRY_FENCE, Items.CHERRY_FENCE_GATE,
                Items.CHERRY_PRESSURE_PLATE, Items.CHERRY_BUTTON, Items.CHERRY_BOAT, ModItems.CHERRY_CABINET.get());
        salvagePlankFromFurniture(output, WoodType.BAMBOO,
                Items.BAMBOO_PLANKS, Items.BAMBOO_DOOR, Items.BAMBOO_TRAPDOOR, Items.BAMBOO_SIGN, Items.BAMBOO_HANGING_SIGN, Items.BAMBOO_FENCE, Items.BAMBOO_FENCE_GATE,
                Items.BAMBOO_PRESSURE_PLATE, Items.BAMBOO_BUTTON, Items.BAMBOO_RAFT, ModItems.BAMBOO_CABINET.get());
        salvagePlankFromFurniture(output, WoodType.CRIMSON,
                Items.CRIMSON_PLANKS, Items.CRIMSON_DOOR, Items.CRIMSON_TRAPDOOR, Items.CRIMSON_SIGN, Items.CRIMSON_HANGING_SIGN, Items.CRIMSON_FENCE, Items.CRIMSON_FENCE_GATE,
                Items.CRIMSON_PRESSURE_PLATE, Items.CRIMSON_BUTTON, ModItems.CRIMSON_CABINET.get());
        salvagePlankFromFurniture(output, WoodType.WARPED,
                Items.WARPED_PLANKS, Items.WARPED_DOOR, Items.WARPED_TRAPDOOR, Items.WARPED_SIGN, Items.WARPED_HANGING_SIGN, Items.WARPED_FENCE, Items.WARPED_FENCE_GATE,
                Items.WARPED_PRESSURE_PLATE, Items.WARPED_BUTTON, ModItems.WARPED_CABINET.get());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WOODEN_BASKET.get()), AXES, ModItems.CANVAS.get())
                .addResult(Items.STICK)
                .saveToFD(output);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BAMBOO_BASKET.get()), AXES, ModItems.CANVAS.get())
                .addResult(Items.BAMBOO)
                .saveToFD(output);
    }

	private static void diggingSediments(HolderGetter<Item> holderGetter, RecipeOutput output) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CLAY), new ItemAbilityIngredient(ItemAbility.SHOVEL_DIG).toVanilla(), Items.CLAY_BALL, 4)
				.build(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.GRAVEL), new ItemAbilityIngredient(ItemAbility.SHOVEL_DIG).toVanilla(), Items.GRAVEL, 1)
				.addResultWithChance(Items.FLINT, 0.1F)
				.saveToFD(output);
	}

	private static void salvagingUsingShears(HolderGetter<Item> holderGetter, RecipeOutput output) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.SADDLE), SHEARS, Items.LEATHER, 2)
                .addResultWithChance(Items.IRON_NUGGET, 0.5F, 2)
                .save(output, salvagingRecipe("saddle"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.LEATHER_HORSE_ARMOR), SHEARS, Items.LEATHER, 2)
                .save(output, salvagingRecipe("leather_horse_armor"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS), SHEARS, Items.LEATHER, 1)
                .save(output, salvagingRecipe("leather_armor"));
    }

	private static void salvagingBlockFromVehicle(RecipeOutput output) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CHEST_MINECART), HOES, Items.MINECART)
			.addResult(Items.CHEST)
			.addSound(SoundEvents.METAL_BREAK)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.FURNACE_MINECART), HOES, Items.MINECART)
			.addResult(Items.FURNACE)
			.addSound(SoundEvents.METAL_BREAK)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.HOPPER_MINECART), HOES, Items.MINECART)
			.addResult(Items.HOPPER)
			.addSound(SoundEvents.METAL_BREAK)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.TNT_MINECART), HOES, Items.MINECART)
			.addResult(Items.TNT)
			.addSound(SoundEvents.METAL_BREAK)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.OAK_CHEST_BOAT), HOES, Items.OAK_BOAT)
			.addResult(Items.CHEST)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.SPRUCE_CHEST_BOAT), HOES, Items.SPRUCE_BOAT)
			.addResult(Items.CHEST)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BIRCH_CHEST_BOAT), HOES, Items.BIRCH_BOAT)
			.addResult(Items.CHEST)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.JUNGLE_CHEST_BOAT), HOES, Items.JUNGLE_BOAT)
			.addResult(Items.CHEST)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.ACACIA_CHEST_BOAT), HOES, Items.ACACIA_BOAT)
			.addResult(Items.CHEST)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.DARK_OAK_CHEST_BOAT), HOES, Items.DARK_OAK_BOAT)
			.addResult(Items.CHEST)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.MANGROVE_CHEST_BOAT), HOES, Items.MANGROVE_BOAT)
			.addResult(Items.CHEST)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CHERRY_CHEST_BOAT), HOES, Items.CHERRY_BOAT)
			.addResult(Items.CHEST)
			.salvaging()
			.saveToFD(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAMBOO_CHEST_RAFT), HOES, Items.BAMBOO_RAFT)
			.addResult(Items.CHEST)
			.salvaging()
			.saveToFD(output);
	}

	/**
	 * Generates an axe-cutting recipe for wooded furniture items, with a chance to recover one plank of the given type.
	 */
    private static void salvagePlankFromFurniture(RecipeOutput output, WoodType woodType, ItemLike plank, ItemLike... furniture) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(furniture), AXES, plank, 1, 0.75F)
                .save(output, salvagingRecipe(woodType.name() + "_furniture"));
    }

	/**
	 * Generates an axe-stripping recipe for the pair of given logs, with custom sound and a Tree Bark result attached.
	 */
	private static void stripLogForBark(HolderGetter<Item> holderGetter, RecipeOutput output, ItemLike log, ItemLike strippedLog) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(log), new ItemAbilityIngredient(ItemAbility.AXE_STRIP).toVanilla(), strippedLog)
				.addResult(ModItems.TREE_BARK.get())
				.addSound(SoundEvents.AXE_STRIP)
				.saveToFD(output);
	}

	private static Ingredient matchesTool(ItemAbility toolAction, TagKey<Item> fallbackTag) {
		return DefaultCustomIngredients.any(new ItemAbilityIngredient(toolAction).toVanilla(), Ingredient.of(fallbackTag));
	}

	private static ResourceLocation salvagingRecipe(String name) {
		return ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "salvaging/" + name);
	}
}
