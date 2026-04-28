package vectorwing.farmersdelight.data.recipe;

import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import vectorwing.farmersdelight.common.crafting.CookingPotBookCategory;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.ConventionalTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

public class CookingRecipes
{
	public static final int FAST_COOKING = 100;      // 5 seconds
	public static final int NORMAL_COOKING = 200;    // 10 seconds
	public static final int SLOW_COOKING = 400;      // 20 seconds

	public static final float SMALL_EXP = 0.35F;
	public static final float MEDIUM_EXP = 1.0F;
	public static final float LARGE_EXP = 2.0F;

	public static void register(HolderLookup.Provider registryLookup, RecipeOutput output) {
		HolderGetter<Item> holderGetter = registryLookup.lookupOrThrow(Registries.ITEM);
		cookMiscellaneous(holderGetter, output);
		cookMinecraftSoups(holderGetter, output);
		cookMeals(holderGetter, output);
	}

	private static void cookMiscellaneous(HolderGetter<Item> holderGetter, RecipeOutput output) {
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.HOT_COCOA.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(ConventionalItemTags.MILK_DRINKS)
				.addIngredient(Items.SUGAR)
				.addIngredient(Items.COCOA_BEANS)
				.addIngredient(Items.COCOA_BEANS)
				.unlockedByAnyIngredient(Items.COCOA_BEANS, Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get())
				.setRecipeBookCategory(CookingPotBookCategory.DRINKS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.APPLE_CIDER.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(Items.APPLE)
				.addIngredient(Items.APPLE)
				.addIngredient(Items.SUGAR)
				.unlockedByItems("has_apple", Items.APPLE)
				.setRecipeBookCategory(CookingPotBookCategory.DRINKS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.TOMATO_SAUCE.get(), 1, FAST_COOKING, SMALL_EXP)
				.addIngredient(CommonTags.Items.CROPS_TOMATO)
				.addIngredient(CommonTags.Items.CROPS_TOMATO)
				.unlockedByItems("has_tomato", ModItems.TOMATO.get())
				.setRecipeBookCategory(CookingPotBookCategory.MISC)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.DOG_FOOD.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(Items.ROTTEN_FLESH)
				.addIngredient(Items.BONE_MEAL)
				.addIngredient(ConventionalItemTags.RAW_MEAT_FOODS)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.unlockedByAnyIngredient(Items.ROTTEN_FLESH, Items.BONE_MEAL, ModItems.RICE.get())
				.setRecipeBookCategory(CookingPotBookCategory.MISC)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.GLOW_BERRY_CUSTARD.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(Items.GLOW_BERRIES)
				.addIngredient(ConventionalItemTags.MILK_DRINKS)
				.addIngredient(ConventionalItemTags.EGGS)
				.addIngredient(Items.SUGAR)
				.unlockedByAnyIngredient(Items.GLOW_BERRIES, Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get())
				.setRecipeBookCategory(CookingPotBookCategory.MISC)
				.build(output);
	}

	private static void cookMinecraftSoups(HolderGetter<Item> holderGetter, RecipeOutput output) {
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, Items.MUSHROOM_STEW, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
				.addIngredient(Items.BROWN_MUSHROOM)
				.addIngredient(Items.RED_MUSHROOM)
				.unlockedByAnyIngredient(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, Items.BEETROOT_SOUP, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
				.addIngredient(Items.BEETROOT)
				.addIngredient(Items.BEETROOT)
				.addIngredient(Items.BEETROOT)
				.unlockedByItems("has_beetroot", Items.BEETROOT)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, Items.RABBIT_STEW, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
				.addIngredient(ConventionalItemTags.POTATO_CROPS)
				.addIngredient(Items.RABBIT)
				.addIngredient(ConventionalItemTags.CARROT_CROPS)
				.addIngredient(Ingredient.of(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM))
				.unlockedByAnyIngredient(Items.RABBIT, Items.BROWN_MUSHROOM, Items.RED_MUSHROOM, Items.CARROT, Items.BAKED_POTATO)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
	}

	private static void cookMeals(HolderGetter<Item> holderGetter, RecipeOutput output) {
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.BAKED_COD_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_RAW_COD)
				.addIngredient(Items.POTATO)
				.addIngredient(ConventionalItemTags.EGGS)
				.addIngredient(CommonTags.Items.CROPS_TOMATO)
				.unlockedByAnyIngredient(Items.COD, Items.POTATO, ModItems.TOMATO.get(), Items.EGG, Items.BLUE_EGG, Items.BROWN_EGG)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.BEEF_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_RAW_BEEF)
				.addIngredient(Items.CARROT)
				.addIngredient(Items.POTATO)
				.unlockedByAnyIngredient(Items.BEEF, Items.CARROT, Items.POTATO)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.BONE_BROTH.get(), 1, NORMAL_COOKING, SMALL_EXP)
				.addIngredient(ConventionalItemTags.BONES)
				.addIngredient(DefaultCustomIngredients.any(
						Ingredient.of(Items.GLOW_BERRIES),
						Ingredient.of(holderGetter.getOrThrow(ConventionalItemTags.MUSHROOMS)),
						Ingredient.of(Items.HANGING_ROOTS),
						Ingredient.of(Items.GLOW_LICHEN)
				))
				.unlockedByItems("has_bone", Items.BONE)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.CABBAGE_ROLLS.get(), 1, FAST_COOKING, SMALL_EXP)
				.addIngredient(CommonTags.Items.CROPS_CABBAGE)
				.addIngredient(ModTags.CABBAGE_ROLL_INGREDIENTS)
				.unlockedByAnyIngredient(ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get())
				.setRecipeBookCategory(CookingPotBookCategory.MISC)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.CHICKEN_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_RAW_CHICKEN)
				.addIngredient(Items.CARROT)
				.addIngredient(CommonTags.Items.FOODS_LEAFY_GREEN)
				.addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
				.unlockedByAnyIngredient(Items.CHICKEN, Items.CARROT)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.COOKED_RICE.get(), 1, FAST_COOKING, SMALL_EXP)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.unlockedByItems("has_rice", ModItems.RICE.get())
				.setRecipeBookCategory(CookingPotBookCategory.MISC)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.DUMPLINGS.get(), 2, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_DOUGH)
				.addIngredient(CommonTags.Items.CROPS_CABBAGE)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.addIngredient(DefaultCustomIngredients.any(
						Ingredient.of(holderGetter.getOrThrow(CommonTags.FOODS_RAW_CHICKEN)),
						Ingredient.of(holderGetter.getOrThrow(CommonTags.FOODS_RAW_PORK)),
						Ingredient.of(holderGetter.getOrThrow(CommonTags.FOODS_RAW_BEEF)),
						Ingredient.of(Items.BROWN_MUSHROOM)
				))
				.unlockedByAnyIngredient(ModItems.WHEAT_DOUGH.get(), ModItems.CABBAGE.get(), ModItems.ONION.get())
				.setRecipeBookCategory(CookingPotBookCategory.MISC)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.FISH_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_SAFE_RAW_FISH)
				.addIngredient(ModItems.TOMATO_SAUCE.get())
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.unlockedByAnyIngredient(Items.SALMON, Items.COD, Items.TROPICAL_FISH, ModItems.TOMATO_SAUCE.get(), ModItems.ONION.get())
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.FRIED_RICE.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.addIngredient(ConventionalItemTags.EGGS)
				.addIngredient(Items.CARROT)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.unlockedByAnyIngredient(ModItems.RICE.get(), Items.EGG, Items.BROWN_EGG, Items.BLUE_EGG, Items.CARROT, ModItems.ONION.get())
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.MUSHROOM_RICE.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(Items.BROWN_MUSHROOM)
				.addIngredient(Items.RED_MUSHROOM)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.addIngredient(Ingredient.of(Items.CARROT, Items.POTATO))
				.unlockedByAnyIngredient(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, ModItems.RICE.get())
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.NOODLE_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.addIngredient(CommonTags.Items.FOODS_COOKED_EGG)
				.addIngredient(Items.DRIED_KELP)
				.addIngredient(CommonTags.Items.FOODS_RAW_PORK)
				.unlockedByAnyIngredient(ModItems.RAW_PASTA.get(), Items.DRIED_KELP, Items.PORKCHOP)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.PASTA_WITH_MEATBALLS.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(ModItems.MINCED_BEEF.get())
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.addIngredient(ModItems.TOMATO_SAUCE.get())
				.unlockedByAnyIngredient(ModItems.RAW_PASTA.get(), Items.BEEF, ModItems.TOMATO_SAUCE.get())
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.PASTA_WITH_MUTTON_CHOP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_RAW_MUTTON)
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.addIngredient(ModItems.TOMATO_SAUCE.get())
				.unlockedByAnyIngredient(ModItems.RAW_PASTA.get(), Items.MUTTON, ModItems.TOMATO_SAUCE.get())
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.PUMPKIN_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(ModItems.PUMPKIN_SLICE.get())
				.addIngredient(CommonTags.Items.FOODS_LEAFY_GREEN)
				.addIngredient(CommonTags.Items.FOODS_RAW_PORK)
				.addIngredient(ConventionalItemTags.MILK_DRINKS)
				.unlockedByAnyIngredient(Items.PUMPKIN, ModItems.PUMPKIN_SLICE.get(), Items.PORKCHOP, Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get())
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.RATATOUILLE.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.CROPS_TOMATO)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.addIngredient(Items.BEETROOT)
				.addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
				.unlockedByAnyIngredient(ModItems.TOMATO.get(), ModItems.ONION.get(), Items.BEETROOT)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.SQUID_INK_PASTA.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_SAFE_RAW_FISH)
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.addIngredient(CommonTags.Items.CROPS_TOMATO)
				.addIngredient(Items.INK_SAC)
				.unlockedByAnyIngredient(ModItems.RAW_PASTA.get(), Items.INK_SAC, ModItems.TOMATO.get())
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.STUFFED_PUMPKIN_BLOCK.get(), 1, SLOW_COOKING, LARGE_EXP, Items.PUMPKIN)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.addIngredient(Items.BROWN_MUSHROOM)
				.addIngredient(Items.POTATO)
				.addIngredient(ConventionalItemTags.BERRY_FOODS)
				.addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
				.unlockedByItems("has_pumpkin", Blocks.PUMPKIN)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.VEGETABLE_NOODLES.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(Items.CARROT)
				.addIngredient(Items.BROWN_MUSHROOM)
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.addIngredient(CommonTags.Items.FOODS_LEAFY_GREEN)
				.addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
				.unlockedByAnyIngredient(ModItems.RAW_PASTA.get(), Items.BROWN_MUSHROOM, Items.CARROT)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
		CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.VEGETABLE_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(Items.CARROT)
				.addIngredient(Items.POTATO)
				.addIngredient(Items.BEETROOT)
				.addIngredient(CommonTags.Items.FOODS_LEAFY_GREEN)
				.unlockedByAnyIngredient(Items.CARROT, ModItems.ONION.get(), Items.BEETROOT)
				.setRecipeBookCategory(CookingPotBookCategory.MEALS)
				.build(output);
	}
}
