package vectorwing.farmersdelight.data.recipe;

import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.DoughRecipe;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;
import vectorwing.farmersdelight.common.crafting.condition.VanillaCrateEnabledCondition;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.RecipeUtils;

import java.util.function.BiFunction;

// Extend FabricRecipeProvider to allow conditional recipes.
public class CraftingRecipes {

	public static void register(HolderLookup.Provider registryLookup, RecipeOutput output, BiFunction<RecipeOutput, ResourceCondition, RecipeOutput> conditionApplier) {
		HolderGetter<Item> holderGetter = registryLookup.lookupOrThrow(Registries.ITEM);
		recipesVanillaAlternatives(holderGetter, output);
		recipesBlocks(holderGetter, output, conditionApplier);
		recipesCanvasSigns(holderGetter, output);
		recipesTools(holderGetter, output);
		recipesMaterials(holderGetter, output);
		recipesFoodstuffs(holderGetter, output);
		recipesFoodBlocks(holderGetter, output);
		recipesCraftedMeals(holderGetter, output);
		SpecialRecipeBuilder.special(FoodServingRecipe::new).save(output, "food_serving");
		SpecialRecipeBuilder.special(DoughRecipe::new).save(output, ResourceKey.create(Registries.RECIPE, FarmersDelight.id("wheat_dough_from_water")));
	}

	public static void canvasSignDyeing(HolderGetter<Item> holderGetter, RecipeOutput output, ItemLike canvasSign, ItemLike hangingCanvasSign, TagKey<Item> dyeTag) {
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.DECORATIONS, canvasSign, 1)
				.requires(ModTags.Items.CANVAS_SIGNS)
				.requires(dyeTag)
				.unlockedBy("has_canvas_sign", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS_SIGN.get()))
				.group("fd_canvas_sign")
				.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.DECORATIONS, hangingCanvasSign, 1)
				.requires(ModTags.Items.HANGING_CANVAS_SIGNS)
				.requires(dyeTag)
				.unlockedBy("has_hanging_canvas_sign", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HANGING_CANVAS_SIGN.get()))
				.group("fd_hanging_canvas_sign")
				.save(output);
	}
	/**
	 * The following recipes should ALWAYS define a custom save location.
	 * If not, they fall on the minecraft namespace, overriding vanilla recipes instead of being alternatives.
	 */
	private static void recipesVanillaAlternatives(HolderGetter<Item> holderGetter, RecipeOutput output) {
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.MISC, Items.PUMPKIN_SEEDS)
			.requires(ModItems.PUMPKIN_SLICE.get())
			.unlockedBy("has_pumpkin_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PUMPKIN_SLICE.get()))
			.save(output, fdLocation("pumpkin_seeds_from_slice"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, Items.SCAFFOLDING, 6)
			.pattern("b#b")
			.pattern("b b")
			.pattern("b b")
			.define('b', Items.BAMBOO)
			.define('#', ModItems.CANVAS.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.save(output, fdLocation("scaffolding_from_canvas"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.TOOLS, Items.LEAD)
			.pattern("ss ")
			.pattern("ss ")
			.pattern("  s")
			.define('s', ModItems.STRAW.get())
			.unlockedBy("has_straw", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRAW.get()))
			.save(output, fdLocation("lead_from_straw"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, Items.PAINTING)
			.pattern("sss")
			.pattern("scs")
			.pattern("sss")
			.define('s', Items.STICK)
			.define('c', ModItems.CANVAS.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.save(output, fdLocation("painting_from_canvas"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, Items.PUMPKIN)
			.pattern("##")
			.pattern("##")
			.define('#', ModItems.PUMPKIN_SLICE.get())
			.unlockedBy("has_pumpkin_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PUMPKIN_SLICE.get()))
			.save(output, fdLocation("pumpkin_from_slices"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, Items.CAKE)
			.pattern("mmm")
			.pattern("ses")
			.pattern("www")
			.define('m', ConventionalItemTags.MILK_DRINKS)
			.define('s', Items.SUGAR)
			.define('e', ItemTags.EGGS)
			.define('w', ConventionalItemTags.WHEAT_CROPS)
			.unlockedBy("has_milk_bottle", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MILK_BOTTLE.get()))
			.group("cake")
			.save(output, fdLocation("cake_from_milk_bottle"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, Items.CAKE)
			.requires(ModItems.CAKE_SLICE.get())
			.requires(ModItems.CAKE_SLICE.get())
			.requires(ModItems.CAKE_SLICE.get())
			.requires(ModItems.CAKE_SLICE.get())
			.requires(ModItems.CAKE_SLICE.get())
			.requires(ModItems.CAKE_SLICE.get())
			.requires(ModItems.CAKE_SLICE.get())
			.unlockedBy("has_cake_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_SLICE.get()))
			.group("cake")
			.save(output, fdLocation("cake_from_slices"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.MISC, Items.BOOK)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(ModItems.CANVAS.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.save(output, fdLocation("book_from_canvas"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.MISC, Items.MILK_BUCKET)
			.requires(Items.BUCKET)
			.requires(ModItems.MILK_BOTTLE.get())
			.requires(ModItems.MILK_BOTTLE.get())
			.requires(ModItems.MILK_BOTTLE.get())
			.requires(ModItems.MILK_BOTTLE.get())
			.unlockedBy("has_milk_bottle", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MILK_BOTTLE.get()))
			.save(output, fdLocation("milk_bucket_from_bottles"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.MISC, Items.PAPER)
			.requires(ModItems.TREE_BARK.get())
			.requires(ModItems.TREE_BARK.get())
			.requires(ModItems.TREE_BARK.get())
			.unlockedBy("has_tree_bark", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TREE_BARK.get()))
			.save(output, fdLocation("paper_from_tree_bark"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.BUILDING_BLOCKS, Items.PACKED_MUD, 2)
			.requires(ModItems.STRAW.get())
			.requires(Items.MUD)
			.requires(Items.MUD)
			.unlockedBy("has_straw", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRAW.get()))
			.save(output, fdLocation("packed_mud_from_straw"));
	}

	private static void recipesCanvasSigns(HolderGetter<Item> holderGetter, RecipeOutput output) {
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModItems.CANVAS_SIGN.get(), 3)
			.pattern("w#w")
			.pattern("w#w")
			.pattern(" / ")
			.define('w', ItemTags.PLANKS)
			.define('#', ModItems.CANVAS.get())
			.define('/', Items.STICK)
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModItems.HANGING_CANVAS_SIGN.get(), 6)
			.pattern("X X")
			.pattern("w#w")
			.pattern("w#w")
			.define('X', Items.IRON_CHAIN)
			.define('w', ItemTags.LOGS)
			.define('#', ModItems.CANVAS.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.save(output);

		canvasSignDyeing(holderGetter, output, ModItems.WHITE_CANVAS_SIGN.get(), ModItems.WHITE_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.WHITE_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.ORANGE_CANVAS_SIGN.get(), ModItems.ORANGE_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.ORANGE_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.MAGENTA_CANVAS_SIGN.get(), ModItems.MAGENTA_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.MAGENTA_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.LIGHT_BLUE_CANVAS_SIGN.get(), ModItems.LIGHT_BLUE_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.LIGHT_BLUE_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.YELLOW_CANVAS_SIGN.get(), ModItems.YELLOW_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.YELLOW_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.LIME_CANVAS_SIGN.get(), ModItems.LIME_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.LIME_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.PINK_CANVAS_SIGN.get(), ModItems.PINK_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.PINK_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.GRAY_CANVAS_SIGN.get(), ModItems.GRAY_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.GRAY_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.LIGHT_GRAY_CANVAS_SIGN.get(), ModItems.LIGHT_GRAY_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.LIGHT_GRAY_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.CYAN_CANVAS_SIGN.get(), ModItems.CYAN_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.CYAN_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.PURPLE_CANVAS_SIGN.get(), ModItems.PURPLE_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.PURPLE_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.BLUE_CANVAS_SIGN.get(), ModItems.BLUE_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.BLUE_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.BROWN_CANVAS_SIGN.get(), ModItems.BROWN_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.BROWN_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.GREEN_CANVAS_SIGN.get(), ModItems.GREEN_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.GREEN_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.RED_CANVAS_SIGN.get(), ModItems.RED_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.RED_DYES);
		canvasSignDyeing(holderGetter, output, ModItems.BLACK_CANVAS_SIGN.get(), ModItems.BLACK_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.BLACK_DYES);
	}

	private static void recipesBlocks(HolderGetter<Item> holderGetter, RecipeOutput output,  BiFunction<RecipeOutput, ResourceCondition, RecipeOutput> conditionApplier) {
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.STOVE.get())
			.pattern("iii")
			.pattern("B B")
			.pattern("BCB")
			.define('i', ConventionalItemTags.IRON_INGOTS)
			.define('B', Blocks.BRICKS)
			.define('C', Blocks.CAMPFIRE)
			.unlockedBy("has_campfire", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CAMPFIRE))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.COOKING_POT.get())
			.pattern("bSb")
			.pattern("iWi")
			.pattern("iii")
			.define('b', Items.BRICK)
			.define('i', ConventionalItemTags.IRON_INGOTS)
			.define('S', Items.WOODEN_SHOVEL)
			.define('W', ConventionalItemTags.WATER_BUCKETS)
			.unlockedBy("has_iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.WOODEN_BASKET.get())
			.pattern("/ /")
			.pattern("# #")
			.pattern("/#/")
			.define('/', Items.STICK)
			.define('#', ModItems.CANVAS.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.BAMBOO_BASKET.get())
			.pattern("/ /")
			.pattern("# #")
			.pattern("/#/")
			.define('/', Items.BAMBOO)
			.define('#', ModItems.CANVAS.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.CUTTING_BOARD.get())
			.pattern("/##")
			.pattern("/##")
			.define('/', Items.STICK)
			.define('#', ItemTags.PLANKS)
			.unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.SKILLET.get())
			.pattern(" ##")
			.pattern(" ##")
			.pattern("/  ")
			.define('/', Items.BRICK)
			.define('#', ConventionalItemTags.IRON_INGOTS)
			.unlockedBy("has_brick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICK))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.OAK_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.OAK_SLAB)
			.define('D', Items.OAK_TRAPDOOR)
			.unlockedBy("has_oak_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.BIRCH_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.BIRCH_SLAB)
			.define('D', Items.BIRCH_TRAPDOOR)
			.unlockedBy("has_birch_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BIRCH_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.SPRUCE_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.SPRUCE_SLAB)
			.define('D', Items.SPRUCE_TRAPDOOR)
			.unlockedBy("has_spruce_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPRUCE_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.JUNGLE_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.JUNGLE_SLAB)
			.define('D', Items.JUNGLE_TRAPDOOR)
			.unlockedBy("has_jungle_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUNGLE_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.ACACIA_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.ACACIA_SLAB)
			.define('D', Items.ACACIA_TRAPDOOR)
			.unlockedBy("has_acacia_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.DARK_OAK_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.DARK_OAK_SLAB)
			.define('D', Items.DARK_OAK_TRAPDOOR)
			.unlockedBy("has_dark_oak_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.MANGROVE_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.MANGROVE_SLAB)
			.define('D', Items.MANGROVE_TRAPDOOR)
			.unlockedBy("has_mangrove_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MANGROVE_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.CHERRY_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.CHERRY_SLAB)
			.define('D', Items.CHERRY_TRAPDOOR)
			.unlockedBy("has_cherry_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CHERRY_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.PALE_OAK_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.PALE_OAK_SLAB)
			.define('D', Items.PALE_OAK_TRAPDOOR)
			.unlockedBy("has_pale_oak_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PALE_OAK_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.BAMBOO_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.BAMBOO_SLAB)
			.define('D', Items.BAMBOO_TRAPDOOR)
			.unlockedBy("has_bamboo_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAMBOO_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.CRIMSON_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.CRIMSON_SLAB)
			.define('D', Items.CRIMSON_TRAPDOOR)
			.unlockedBy("has_crimson_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRIMSON_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModBlocks.WARPED_CABINET.get())
			.pattern("___")
			.pattern("D D")
			.pattern("___")
			.define('_', Items.WARPED_SLAB)
			.define('D', Items.WARPED_TRAPDOOR)
			.unlockedBy("has_warped_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WARPED_TRAPDOOR))
			.group("fd_cabinet")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModItems.ROPE.get(), 4)
			.pattern("s")
			.pattern("s")
			.define('s', ModItems.STRAW.get())
			.unlockedBy("has_straw", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRAW.get()))
			.group("fd_rope")
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.DECORATIONS, ModItems.ROPE.get(), 4)
			.requires(ModItems.SAFETY_NET.get())
			.unlockedBy("has_safety_net", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SAFETY_NET.get()))
			.group("fd_rope")
			.save(output, fdLocation("rope_from_safety_net"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModItems.SAFETY_NET.get(), 1)
			.pattern("rr")
			.pattern("rr")
			.define('r', ModItems.ROPE.get())
			.unlockedBy("has_rope", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ROPE.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModItems.ROPE_FENCE.get(), 3)
			.pattern("r/r")
			.pattern("r/r")
			.define('/', Items.STICK)
			.define('r', ModItems.ROPE.get())
			.unlockedBy("has_rope", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ROPE.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, ModItems.ROPE_FENCE_GATE.get())
			.pattern("/r/")
			.pattern("/r/")
			.define('/', Items.STICK)
			.define('r', ModItems.ROPE.get())
			.unlockedBy("has_rope", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ROPE.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.BEETROOT_CRATE.get())
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', Items.BEETROOT)
			.unlockedBy("has_beetroot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BEETROOT))
			.save(conditionApplier.apply(output, VanillaCrateEnabledCondition.INSTANCE), fdLocation("beetroot_crate"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.CARROT_CRATE.get())
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', Items.CARROT)
			.unlockedBy("has_carrot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CARROT))
			.save(conditionApplier.apply(output, VanillaCrateEnabledCondition.INSTANCE), fdLocation("carrot_crate"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.POTATO_CRATE.get())
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', Items.POTATO)
			.unlockedBy("has_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POTATO))
			.save(conditionApplier.apply(output, VanillaCrateEnabledCondition.INSTANCE), fdLocation("potato_crate"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.CABBAGE_CRATE.get(), 1)
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', ModItems.CABBAGE.get())
			.unlockedBy("has_cabbage", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CABBAGE.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.TOMATO_CRATE.get(), 1)
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', ModItems.TOMATO.get())
			.unlockedBy("has_tomato", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TOMATO.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.ONION_CRATE.get(), 1)
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', ModItems.ONION.get())
			.unlockedBy("has_onion", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ONION.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.RICE_BALE.get(), 1)
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', ModItems.RICE_PANICLE.get())
			.unlockedBy("has_rice_panicle", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RICE_PANICLE.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.RICE_BAG.get(), 1)
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', ModItems.RICE.get())
			.unlockedBy("has_rice", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RICE.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.STRAW_BALE.get(), 1)
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', ModItems.STRAW.get())
			.unlockedBy("has_straw", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRAW.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.DECORATIONS, ModItems.CANVAS_RUG.get(), 2)
			.requires(ModItems.CANVAS.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.MISC, ModItems.CANVAS.get(), 1)
			.requires(ModItems.CANVAS_RUG.get())
			.requires(ModItems.CANVAS_RUG.get())
			.unlockedBy("has_canvas_rug", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS_RUG.get()))
			.group("fd_canvas")
			.save(output, fdLocation("canvas_from_canvas_rug"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.ORGANIC_COMPOST.get(), 1)
			.requires(Items.DIRT)
			.requires(Items.ROTTEN_FLESH)
			.requires(Items.ROTTEN_FLESH)
			.requires(ModItems.STRAW.get())
			.requires(ModItems.STRAW.get())
			.requires(Items.BONE_MEAL)
			.requires(Items.BONE_MEAL)
			.requires(Items.BONE_MEAL)
			.requires(Items.BONE_MEAL)
			.unlockedBy("has_rotten_flesh", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ROTTEN_FLESH))
			.unlockedBy("has_straw", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRAW.get()))
			.group("fd_organic_compost")
			.save(output, fdLocation("organic_compost_from_rotten_flesh"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.ORGANIC_COMPOST.get(), 1)
			.requires(Items.DIRT)
			.requires(ModItems.STRAW.get())
			.requires(ModItems.STRAW.get())
			.requires(Items.BONE_MEAL)
			.requires(Items.BONE_MEAL)
			.requires(ModItems.TREE_BARK.get())
			.requires(ModItems.TREE_BARK.get())
			.requires(ModItems.TREE_BARK.get())
			.requires(ModItems.TREE_BARK.get())
			.unlockedBy("has_tree_bark", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TREE_BARK.get()))
			.unlockedBy("has_straw", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRAW.get()))
			.group("fd_organic_compost")
			.save(output, fdLocation("organic_compost_from_tree_bark"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.TATAMI.get(), 2)
			.pattern("cs")
			.pattern("sc")
			.define('c', ModItems.CANVAS.get())
			.define('s', ModItems.STRAW.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.group("fd_tatami")
			.save(output);

		// BREAKING DOWN
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.DECORATIONS, ModItems.FULL_TATAMI_MAT.get(), 2)
			.requires(ModItems.TATAMI.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.group("fd_full_tatami_mat")
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.DECORATIONS, ModItems.HALF_TATAMI_MAT.get(), 2)
			.requires(ModItems.FULL_TATAMI_MAT.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.save(output);

		// COMBINING BACK
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.DECORATIONS, ModItems.FULL_TATAMI_MAT.get(), 1)
			.requires(ModItems.HALF_TATAMI_MAT.get())
			.requires(ModItems.HALF_TATAMI_MAT.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.group("fd_full_tatami_mat")
			.save(output, fdLocation("full_tatami_mat_from_halves"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.BUILDING_BLOCKS, ModItems.TATAMI.get(), 1)
			.requires(ModItems.FULL_TATAMI_MAT.get())
			.requires(ModItems.FULL_TATAMI_MAT.get())
			.unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
			.group("fd_tatami")
			.save(output, fdLocation("tatami_block_from_full"));
	}

	private static void recipesTools(HolderGetter<Item> holderGetter, RecipeOutput output) {
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.COMBAT, ModItems.FLINT_KNIFE.get())
			.pattern("m")
			.pattern("s")
			.define('m', Items.FLINT)
			.define('s', Items.STICK)
			.unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.COMBAT, ModItems.COPPER_KNIFE.get())
			.pattern("m")
			.pattern("s")
			.define('m', ConventionalItemTags.COPPER_INGOTS)
			.define('s', Items.STICK)
			.unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.COMBAT, ModItems.IRON_KNIFE.get())
			.pattern("m")
			.pattern("s")
			.define('m', ConventionalItemTags.IRON_INGOTS)
			.define('s', Items.STICK)
			.unlockedBy("has_iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.COMBAT, ModItems.DIAMOND_KNIFE.get())
			.pattern("m")
			.pattern("s")
			.define('m', Items.DIAMOND)
			.define('s', Items.STICK)
			.unlockedBy("has_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.COMBAT, ModItems.GOLDEN_KNIFE.get())
			.pattern("m")
			.pattern("s")
			.define('m', Items.GOLD_INGOT)
			.define('s', Items.STICK)
			.unlockedBy("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
			.save(output);
		SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ModItems.DIAMOND_KNIFE.get()), Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.COMBAT, ModItems.NETHERITE_KNIFE.get())
			.unlocks("has_netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
			.save(output, FarmersDelight.MODID + ":netherite_knife_smithing");
	}

	private static void recipesMaterials(HolderGetter<Item> holderGetter, RecipeOutput output) {
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, ModItems.CANVAS.get())
			.pattern("##")
			.pattern("##")
			.define('#', ModItems.STRAW.get())
			.unlockedBy("has_straw", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRAW.get()))
			.group("fd_canvas")
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, Items.CARROT, 9)
			.requires(ModItems.CARROT_CRATE.get())
			.unlockedBy("has_carrot_crate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARROT_CRATE.get()))
			.save(output, fdLocation("carrot_from_crate"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, Items.POTATO, 9)
			.requires(ModItems.POTATO_CRATE.get())
			.unlockedBy("has_potato_crate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POTATO_CRATE.get()))
			.save(output, fdLocation("potato_from_crate"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, Items.BEETROOT, 9)
			.requires(ModItems.BEETROOT_CRATE.get())
			.unlockedBy("has_beetroot_crate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BEETROOT_CRATE.get()))
			.save(output, fdLocation("beetroot_from_crate"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.CABBAGE.get(), 9)
			.requires(ModItems.CABBAGE_CRATE.get())
			.unlockedBy("has_cabbage_crate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CABBAGE_CRATE.get()))
			.group("fd_cabbage")
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.TOMATO.get(), 9)
			.requires(ModItems.TOMATO_CRATE.get())
			.unlockedBy("has_tomato_crate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TOMATO_CRATE.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.ONION.get(), 9)
			.requires(ModItems.ONION_CRATE.get())
			.unlockedBy("has_onion_crate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ONION_CRATE.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.RICE_PANICLE.get(), 9)
			.requires(ModItems.RICE_BALE.get())
			.unlockedBy("has_rice_bale", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RICE_BALE.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.RICE.get(), 9)
			.requires(ModItems.RICE_BAG.get())
			.unlockedBy("has_rice_bag", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RICE_BAG.get()))
			.group("fd_rice")
			.save(output, FarmersDelight.MODID + ":rice_from_bag");
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.MISC, ModItems.STRAW.get(), 9)
			.requires(ModItems.STRAW_BALE.get())
			.unlockedBy("has_straw_bale", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRAW_BALE.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.RICE.get())
			.requires(ModItems.RICE_PANICLE.get())
			.unlockedBy("has_rice_panicle", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RICE_PANICLE.get()))
			.group("fd_rice")
			.save(output);
	}

	private static void recipesFoodstuffs(HolderGetter<Item> holderGetter, RecipeOutput output) {
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.MISC, ModItems.TOMATO_SEEDS.get())
			.requires(Ingredient.of(ModItems.TOMATO.get(), ModItems.ROTTEN_TOMATO.get()))
			.unlockedBy("has_tomato", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TOMATO.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.MILK_BOTTLE.get(), 4)
			.requires(Items.MILK_BUCKET)
			.requires(Items.GLASS_BOTTLE)
			.requires(Items.GLASS_BOTTLE)
			.requires(Items.GLASS_BOTTLE)
			.requires(Items.GLASS_BOTTLE)
			.unlockedBy("has_milk_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MILK_BUCKET))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.MELON_JUICE.get(), 1)
			.requires(Items.MELON_SLICE)
			.requires(Items.MELON_SLICE)
			.requires(Items.SUGAR)
			.requires(Items.MELON_SLICE)
			.requires(Items.MELON_SLICE)
			.requires(Items.GLASS_BOTTLE)
			.unlockedBy("has_melon_slice", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON_SLICE))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.WHEAT_DOUGH.get(), 3)
			.requires(ConventionalItemTags.WHEAT_CROPS)
			.requires(ConventionalItemTags.WHEAT_CROPS)
			.requires(ConventionalItemTags.WHEAT_CROPS)
			.requires(ItemTags.EGGS)
			.group("fd_dough")
			.unlockedBy("has_wheat", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHEAT))
			.save(output, fdLocation("wheat_dough_from_egg"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, ModItems.PIE_CRUST.get(), 1)
			.pattern("wMw")
			.pattern(" w ")
			.define('w', ConventionalItemTags.WHEAT_CROPS)
			.define('M', ConventionalItemTags.MILK_DRINKS)
			.unlockedBy("has_wheat", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHEAT))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.SWEET_BERRY_COOKIE.get(), 8)
			.requires(Items.SWEET_BERRIES)
			.requires(ConventionalItemTags.WHEAT_CROPS)
			.requires(ConventionalItemTags.WHEAT_CROPS)
			.unlockedBy("has_sweet_berries", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SWEET_BERRIES))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.HONEY_COOKIE.get(), 8)
			.requires(Items.HONEY_BOTTLE)
			.requires(ConventionalItemTags.WHEAT_CROPS)
			.requires(ConventionalItemTags.WHEAT_CROPS)
			.unlockedBy("has_honey_bottle", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEY_BOTTLE))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.CABBAGE.get())
			.requires(ModItems.CABBAGE_LEAF.get())
			.requires(ModItems.CABBAGE_LEAF.get())
			.unlockedBy("has_cabbage_leaf", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CABBAGE_LEAF.get()))
			.group("fd_cabbage")
			.save(output, fdLocation("cabbage_from_leaves"));
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.HORSE_FEED.get(), 1)
			.requires(Ingredient.of(Items.HAY_BLOCK, ModItems.RICE_BALE.get()))
			.requires(Items.APPLE)
			.requires(Items.APPLE)
			.requires(Items.GOLDEN_CARROT)
			.unlockedBy("has_golden_carrot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_CARROT))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, ModItems.MELON_POPSICLE.get(), 1)
			.pattern(" mm")
			.pattern("imm")
			.pattern("-i ")
			.define('m', Items.MELON_SLICE)
			.define('i', Items.ICE)
			.define('-', Items.STICK)
			.unlockedBy("has_melon", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON_SLICE))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.FRUIT_SALAD.get(), 1)
			.requires(Items.APPLE)
			.requires(Items.MELON_SLICE)
			.requires(Items.MELON_SLICE)
			.requires(ConventionalItemTags.BERRY_FOODS)
			.requires(ConventionalItemTags.BERRY_FOODS)
			.requires(ModItems.PUMPKIN_SLICE.get())
			.requires(Items.BOWL)
			.unlockedBy("has_fruits", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON_SLICE, Items.SWEET_BERRIES, Items.APPLE, ModItems.PUMPKIN_SLICE.get()))
			.save(output);
	}

	private static void recipesFoodBlocks(HolderGetter<Item> holderGetter, RecipeOutput output) {
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, ModItems.APPLE_PIE.get(), 1)
			.pattern("###")
			.pattern("aaa")
			.pattern("xOx")
			.define('#', ConventionalItemTags.WHEAT_CROPS)
			.define('a', Items.APPLE)
			.define('x', Items.SUGAR)
			.define('O', ModItems.PIE_CRUST.get())
			.unlockedBy("has_pie_crust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PIE_CRUST.get()))
			.group("fd_apple_pie")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, ModItems.APPLE_PIE.get(), 1)
			.pattern("##")
			.pattern("##")
			.define('#', ModItems.APPLE_PIE_SLICE.get())
			.unlockedBy("has_apple_pie_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.APPLE_PIE_SLICE.get()))
			.group("fd_apple_pie")
			.save(output, fdLocation("apple_pie_from_slices"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, ModItems.SWEET_BERRY_CHEESECAKE.get(), 1)
			.pattern("sss")
			.pattern("sss")
			.pattern("mOm")
			.define('s', Items.SWEET_BERRIES)
			.define('m', ConventionalItemTags.MILK_DRINKS)
			.define('O', ModItems.PIE_CRUST.get())
			.unlockedBy("has_pie_crust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PIE_CRUST.get()))
			.group("fd_sweet_berry_cheesecake")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, ModItems.SWEET_BERRY_CHEESECAKE.get(), 1)
			.pattern("##")
			.pattern("##")
			.define('#', ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get())
			.unlockedBy("has_sweet_berry_cheesecake_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get()))
			.group("fd_sweet_berry_cheesecake")
			.save(output, fdLocation("sweet_berry_cheesecake_from_slices"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, ModItems.CHOCOLATE_PIE.get(), 1)
			.pattern("ccc")
			.pattern("mmm")
			.pattern("xOx")
			.define('c', Items.COCOA_BEANS)
			.define('m', ConventionalItemTags.MILK_DRINKS)
			.define('x', Items.SUGAR)
			.define('O', ModItems.PIE_CRUST.get())
			.unlockedBy("has_pie_crust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PIE_CRUST.get()))
			.group("fd_chocolate_pie")
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, ModItems.CHOCOLATE_PIE.get(), 1)
			.pattern("##")
			.pattern("##")
			.define('#', ModItems.CHOCOLATE_PIE_SLICE.get())
			.unlockedBy("has_chocolate_pie_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CHOCOLATE_PIE_SLICE.get()))
			.group("fd_chocolate_pie")
			.save(output, fdLocation("chocolate_pie_from_slices"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, Items.PUMPKIN_PIE, 2)
			.pattern("cec")
			.pattern("csc")
			.pattern(" O ")
			.define('c', ModItems.PUMPKIN_SLICE.get())
			.define('e', ItemTags.EGGS)
			.define('s', Items.SUGAR)
			.define('O', ModItems.PIE_CRUST.get())
			.unlockedBy("has_pie_crust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PIE_CRUST.get()))
			.group("fd_pumpkin_pie")
			.save(output, fdLocation("pumpkin_pie_from_pie_crust"));
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, Items.PUMPKIN_PIE, 1)
			.pattern("##")
			.pattern("##")
			.define('#', ModItems.PUMPKIN_PIE_SLICE.get())
			.unlockedBy("has_pumpkin_pie_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PUMPKIN_PIE_SLICE.get()))
			.group("fd_pumpkin_pie")
			.save(output, fdLocation("pumpkin_pie_from_slices"));
	}

	private static void recipesCraftedMeals(HolderGetter<Item> holderGetter, RecipeOutput output) {
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.MIXED_SALAD.get())
			.requires(CommonTags.Items.FOODS_LEAFY_GREEN)
			.requires(CommonTags.Items.CROPS_TOMATO)
			.requires(ConventionalItemTags.BEETROOT_CROPS)
			.requires(Items.BOWL)
			.unlockedBy("has_bowl", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BOWL))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.NETHER_SALAD.get())
			.requires(Items.CRIMSON_FUNGUS)
			.requires(Items.WARPED_FUNGUS)
			.requires(Items.BOWL)
			.unlockedBy("has_bowl", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BOWL))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.BARBECUE_STICK.get())
			.requires(CommonTags.Items.CROPS_TOMATO)
			.requires(CommonTags.Items.CROPS_ONION)
			.requires(ConventionalItemTags.COOKED_MEAT_FOODS)
			.requires(Items.STICK)
			.unlockedBy("has_tomato", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TOMATO.get()))
			.unlockedBy("has_onion", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ONION.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.EGG_SANDWICH.get())
			.requires(ConventionalItemTags.BREAD_FOODS)
			.requires(CommonTags.Items.FOODS_COOKED_EGG)
			.requires(CommonTags.Items.FOODS_COOKED_EGG)
			.unlockedBy("has_fried_egg", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FRIED_EGG.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.CHICKEN_SANDWICH.get())
			.requires(ConventionalItemTags.BREAD_FOODS)
			.requires(CommonTags.Items.FOODS_COOKED_CHICKEN)
			.requires(CommonTags.Items.FOODS_LEAFY_GREEN)
			.requires(ConventionalItemTags.CARROT_CROPS)
			.unlockedBy("has_cooked_chicken", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_CHICKEN))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.HAMBURGER.get())
			.requires(ConventionalItemTags.BREAD_FOODS)
			.requires(ModItems.BEEF_PATTY.get())
			.requires(CommonTags.Items.FOODS_LEAFY_GREEN)
			.requires(CommonTags.Items.CROPS_TOMATO)
			.requires(CommonTags.Items.CROPS_ONION)
			.unlockedBy("has_beef_patty", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BEEF_PATTY.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.BACON_SANDWICH.get())
			.requires(ConventionalItemTags.BREAD_FOODS)
			.requires(CommonTags.Items.FOODS_COOKED_BACON)
			.requires(CommonTags.Items.FOODS_LEAFY_GREEN)
			.requires(CommonTags.Items.CROPS_TOMATO)
			.unlockedBy("has_bacon", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COOKED_BACON.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.MUTTON_WRAP.get())
			.requires(ConventionalItemTags.BREAD_FOODS)
			.requires(CommonTags.Items.FOODS_COOKED_MUTTON)
			.requires(CommonTags.Items.FOODS_LEAFY_GREEN)
			.requires(CommonTags.Items.CROPS_ONION)
			.unlockedBy("has_mutton", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_MUTTON))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.STUFFED_POTATO.get())
			.requires(Items.BAKED_POTATO)
			.requires(CommonTags.Items.FOODS_COOKED_BEEF)
			.requires(ConventionalItemTags.MILK_DRINKS)
			.unlockedBy("has_baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.SALMON_ROLL.get(), 2)
			.requires(ModItems.SALMON_SLICE.get())
			.requires(ModItems.SALMON_SLICE.get())
			.requires(ModItems.COOKED_RICE.get())
			.unlockedBy("has_salmon_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SALMON_SLICE.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.COD_ROLL.get(), 2)
			.requires(ModItems.COD_SLICE.get())
			.requires(ModItems.COD_SLICE.get())
			.requires(ModItems.COOKED_RICE.get())
			.unlockedBy("has_cod_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COD_SLICE.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, ModItems.KELP_ROLL.get(), 1)
			.pattern("RXR")
			.pattern("###")
			.define('#', Items.DRIED_KELP)
			.define('R', ModItems.COOKED_RICE.get())
			.define('X', ConventionalItemTags.VEGETABLE_FOODS)
			.unlockedBy("has_dried_kelp", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DRIED_KELP))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.GRILLED_SALMON.get())
			.requires(CommonTags.Items.FOODS_COOKED_SALMON)
			.requires(Items.SWEET_BERRIES)
			.requires(Items.BOWL)
			.requires(CommonTags.Items.CROPS_CABBAGE)
			.requires(CommonTags.Items.CROPS_ONION)
			.unlockedBy("has_salmon", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SALMON))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.STEAK_AND_POTATOES.get())
			.requires(Items.BAKED_POTATO)
			.requires(Items.COOKED_BEEF)
			.requires(Items.BOWL)
			.requires(CommonTags.Items.CROPS_ONION)
			.requires(ModItems.COOKED_RICE.get())
			.unlockedBy("has_baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.ROASTED_MUTTON_CHOPS.get())
			.requires(ModItems.COOKED_MUTTON_CHOPS.get())
			.requires(ConventionalItemTags.BEETROOT_CROPS)
			.requires(Items.BOWL)
			.requires(ModItems.COOKED_RICE.get())
			.requires(CommonTags.Items.CROPS_TOMATO)
			.unlockedBy("has_mutton", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_MUTTON))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.BACON_AND_EGGS.get())
			.requires(CommonTags.Items.FOODS_COOKED_BACON)
			.requires(CommonTags.Items.FOODS_COOKED_BACON)
			.requires(Items.BOWL)
			.requires(CommonTags.Items.FOODS_COOKED_EGG)
			.requires(CommonTags.Items.FOODS_COOKED_EGG)
			.unlockedBy("has_bacon", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COOKED_BACON.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.ROAST_CHICKEN_BLOCK.get())
			.requires(CommonTags.Items.CROPS_ONION)
			.requires(ItemTags.EGGS)
			.requires(ConventionalItemTags.BREAD_FOODS)
			.requires(ConventionalItemTags.CARROT_CROPS)
			.requires(Items.COOKED_CHICKEN)
			.requires(Items.BAKED_POTATO)
			.requires(ConventionalItemTags.CARROT_CROPS)
			.requires(Items.BOWL)
			.requires(Items.BAKED_POTATO)
			.unlockedBy("has_cooked_chicken", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_CHICKEN))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.SHEPHERDS_PIE_BLOCK.get())
			.requires(Items.BAKED_POTATO)
			.requires(ConventionalItemTags.MILK_DRINKS)
			.requires(Items.BAKED_POTATO)
			.requires(CommonTags.Items.FOODS_COOKED_MUTTON)
			.requires(CommonTags.Items.FOODS_COOKED_MUTTON)
			.requires(CommonTags.Items.FOODS_COOKED_MUTTON)
			.requires(CommonTags.Items.CROPS_ONION)
			.requires(Items.BOWL)
			.requires(CommonTags.Items.CROPS_ONION)
			.unlockedBy("has_cooked_mutton", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_MUTTON))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.HONEY_GLAZED_HAM_BLOCK.get())
			.requires(Items.SWEET_BERRIES)
			.requires(Items.HONEY_BOTTLE)
			.requires(Items.SWEET_BERRIES)
			.requires(Items.SWEET_BERRIES)
			.requires(ModItems.SMOKED_HAM.get())
			.requires(Items.SWEET_BERRIES)
			.requires(ModItems.COOKED_RICE.get())
			.requires(Items.BOWL)
			.requires(ModItems.COOKED_RICE.get())
			.unlockedBy("has_smoked_ham", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMOKED_HAM.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.GLEAMING_SALAD_BLOCK.get())
			.requires(Items.GLOW_BERRIES)
			.requires(Items.HONEY_BOTTLE)
			.requires(Items.GLOW_BERRIES)
			.requires(CommonTags.Items.CROPS_TOMATO)
			.requires(Items.GOLDEN_CARROT)
			.requires(ConventionalItemTags.BEETROOT_CROPS)
			.requires(ModItems.CABBAGE.get())
			.requires(Items.BOWL)
			.requires(ModItems.CABBAGE.get())
			.unlockedBy("has_glow_berries", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GLOW_BERRY_CUSTARD.get()))
			.save(output);
		ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.RICE_ROLL_MEDLEY_BLOCK.get())
			.requires(ModItems.KELP_ROLL_SLICE.get())
			.requires(ModItems.KELP_ROLL_SLICE.get())
			.requires(ModItems.KELP_ROLL_SLICE.get())
			.requires(ModItems.SALMON_ROLL.get())
			.requires(ModItems.SALMON_ROLL.get())
			.requires(ModItems.SALMON_ROLL.get())
			.requires(ModItems.COD_ROLL.get())
			.requires(Items.BOWL)
			.requires(ModItems.COD_ROLL.get())
			.unlockedBy("has_rice_roll", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SALMON_ROLL.get(), ModItems.COD_ROLL.get(), ModItems.KELP_ROLL_SLICE.get()))
			.save(output);
	}

	private static ResourceKey<Recipe<?>> fdLocation(String path) {
		return ResourceKey.create(Registries.RECIPE, RecipeUtils.FDLocation(path));
	}
}
