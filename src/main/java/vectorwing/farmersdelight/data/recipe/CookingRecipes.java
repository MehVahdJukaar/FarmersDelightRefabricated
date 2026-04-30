package vectorwing.farmersdelight.data.recipe;

import mezz.jei.api.constants.Tags;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
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

	public static void register(RecipeOutput output) {
		cookMiscellaneous(output);
		cookMinecraftSoups(output);
		cookMeals(output);
	}

	private static void cookMiscellaneous(RecipeOutput output) {
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.HOT_COCOA.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(ConventionalTags.DRINKS_MILK)
				.addIngredient(Items.SUGAR)
				.addIngredient(Items.COCOA_BEANS)
				.addIngredient(Items.COCOA_BEANS)
				.unlockedByAnyIngredient(Items.COCOA_BEANS, Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.APPLE_CIDER.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(Items.APPLE)
				.addIngredient(Items.APPLE)
				.addIngredient(Items.SUGAR)
				.unlockedByItems("has_apple", Items.APPLE)
				.setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.TOMATO_SAUCE.get(), 1, FAST_COOKING, SMALL_EXP)
				.addIngredient(CommonTags.Items.CROPS_TOMATO)
				.addIngredient(CommonTags.Items.CROPS_TOMATO)
				.unlockedByItems("has_tomato", ModItems.TOMATO.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MISC)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.DOG_FOOD.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(Items.ROTTEN_FLESH)
				.addIngredient(Items.BONE_MEAL)
				.addIngredient(ConventionalItemTags.RAW_MEAT_FOODS)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.unlockedByAnyIngredient(Items.ROTTEN_FLESH, Items.BONE_MEAL, ModItems.RICE.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MISC)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.GLOW_BERRY_CUSTARD.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(Items.GLOW_BERRIES)
				.addIngredient(ConventionalTags.DRINKS_MILK)
				.addIngredient(ConventionalItemTags.EGGS)
				.addIngredient(Items.SUGAR)
				.unlockedByAnyIngredient(Items.GLOW_BERRIES, Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MISC)
				.save(output);
	}

	private static void cookMinecraftSoups(RecipeOutput output) {
		CookingPotRecipeBuilder.cookingPotRecipe(Items.MUSHROOM_STEW, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
				.addIngredient(Items.BROWN_MUSHROOM)
				.addIngredient(Items.RED_MUSHROOM)
				.unlockedByAnyIngredient(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.saveToFD(output);
		CookingPotRecipeBuilder.cookingPotRecipe(Items.BEETROOT_SOUP, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
				.addIngredient(ConventionalItemTags.BEETROOT_CROPS)
				.addIngredient(ConventionalItemTags.BEETROOT_CROPS)
				.addIngredient(ConventionalItemTags.BEETROOT_CROPS)
				.unlockedByItems("has_beetroot", Items.BEETROOT)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.saveToFD(output);
		CookingPotRecipeBuilder.cookingPotRecipe(Items.RABBIT_STEW, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
				.addIngredient(ConventionalItemTags.POTATO_CROPS)
				.addIngredient(Items.RABBIT)
				.addIngredient(ConventionalItemTags.CARROT_CROPS)
				.addIngredient(Ingredient.of(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM))
				.unlockedByAnyIngredient(Items.RABBIT, Items.BROWN_MUSHROOM, Items.RED_MUSHROOM, Items.CARROT, Items.POTATO)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.saveToFD(output);
	}

	private static void cookMeals(RecipeOutput output) {
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BAKED_COD_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_RAW_COD)
				.addIngredient(ConventionalItemTags.POTATO_CROPS)
				.addIngredient(ConventionalItemTags.EGGS)
				.addIngredient(CommonTags.Items.CROPS_TOMATO)
				.unlockedByAnyIngredient(Items.COD, Items.POTATO, ModItems.TOMATO.get(), Items.EGG)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BEEF_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_RAW_BEEF)
				.addIngredient(ConventionalItemTags.CARROT_CROPS)
				.addIngredient(ConventionalItemTags.POTATO_CROPS)
				.unlockedByAnyIngredient(Items.BEEF, Items.CARROT, Items.POTATO)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BONE_BROTH.get(), 1, NORMAL_COOKING, SMALL_EXP)
				.addIngredient(ConventionalItemTags.BONES)
				.addIngredient(DefaultCustomIngredients.any(
						Ingredient.of(Items.GLOW_BERRIES),
						Ingredient.of(ConventionalItemTags.MUSHROOMS),
						Ingredient.of(Items.HANGING_ROOTS),
						Ingredient.of(Items.GLOW_LICHEN)
				))
				.unlockedByItems("has_bone", Items.BONE)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CABBAGE_ROLLS.get(), 1, FAST_COOKING, SMALL_EXP)
				.addIngredient(CommonTags.Items.CROPS_CABBAGE)
				.addIngredient(DefaultCustomIngredients.any(
						Ingredient.of(ConventionalItemTags.RAW_MEAT_FOODS),
						Ingredient.of(CommonTags.Items.FOODS_SAFE_RAW_FISH),
						Ingredient.of(ConventionalItemTags.VEGETABLE_FOODS),
						Ingredient.of(ConventionalItemTags.MUSHROOMS)
				))
				.unlockedByAnyIngredient(ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MISC)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CHICKEN_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_RAW_CHICKEN)
				.addIngredient(ConventionalItemTags.CARROT_CROPS)
				.addIngredient(CommonTags.Items.FOODS_LEAFY_GREEN)
				.addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
				.unlockedByAnyIngredient(Items.CHICKEN, Items.CARROT)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COOKED_RICE.get(), 1, FAST_COOKING, SMALL_EXP)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.unlockedByItems("has_rice", ModItems.RICE.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MISC)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.DUMPLINGS.get(), 2, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_DOUGH)
				.addIngredient(CommonTags.Items.CROPS_CABBAGE)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.addIngredient(DefaultCustomIngredients.any(
						Ingredient.of(CommonTags.Items.FOODS_RAW_CHICKEN),
						Ingredient.of(CommonTags.Items.FOODS_RAW_PORK),
						Ingredient.of(CommonTags.Items.FOODS_RAW_BEEF),
						Ingredient.of(Items.BROWN_MUSHROOM)
				))
				.unlockedByAnyIngredient(ModItems.WHEAT_DOUGH.get(), ModItems.CABBAGE.get(), ModItems.ONION.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MISC)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FISH_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_SAFE_RAW_FISH)
				.addIngredient(ModItems.TOMATO_SAUCE.get())
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.unlockedByAnyIngredient(Items.SALMON, Items.COD, Items.TROPICAL_FISH, ModItems.TOMATO_SAUCE.get(), ModItems.ONION.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_RICE.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.addIngredient(ConventionalItemTags.EGGS)
				.addIngredient(ConventionalItemTags.CARROT_CROPS)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.unlockedByAnyIngredient(ModItems.RICE.get(), Items.EGG, Items.CARROT, ModItems.ONION.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.MUSHROOM_RICE.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(Items.BROWN_MUSHROOM)
				.addIngredient(Items.RED_MUSHROOM)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.addIngredient(Ingredient.of(Items.CARROT, Items.POTATO))
				.unlockedByAnyIngredient(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, ModItems.RICE.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.NOODLE_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.addIngredient(ConventionalItemTags.EGGS)
				.addIngredient(Items.DRIED_KELP)
				.addIngredient(CommonTags.Items.FOODS_RAW_PORK)
				.unlockedByAnyIngredient(ModItems.RAW_PASTA.get(), Items.DRIED_KELP, Items.PORKCHOP)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.ONION_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.addIngredient(ConventionalItemTags.BREAD_FOODS)
				.addIngredient(ConventionalItemTags.MILK_DRINKS)
				.unlockedByAnyIngredient(ModItems.ONION.get(), Items.BREAD, Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.PASTA_WITH_MEATBALLS.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(ModItems.MINCED_BEEF.get())
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.addIngredient(ModItems.TOMATO_SAUCE.get())
				.unlockedByAnyIngredient(ModItems.RAW_PASTA.get(), Items.BEEF, ModItems.TOMATO_SAUCE.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.PASTA_WITH_MUTTON_CHOP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_RAW_MUTTON)
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.addIngredient(ModItems.TOMATO_SAUCE.get())
				.unlockedByAnyIngredient(ModItems.RAW_PASTA.get(), Items.MUTTON, ModItems.TOMATO_SAUCE.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.PUMPKIN_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(ModItems.PUMPKIN_SLICE.get())
				.addIngredient(CommonTags.Items.FOODS_LEAFY_GREEN)
				.addIngredient(CommonTags.Items.FOODS_RAW_PORK)
				.addIngredient(ConventionalTags.DRINKS_MILK)
				.unlockedByAnyIngredient(Items.PUMPKIN, ModItems.PUMPKIN_SLICE.get(), Items.PORKCHOP, Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.RATATOUILLE.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.CROPS_TOMATO)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.addIngredient(ConventionalItemTags.BEETROOT_CROPS)
				.addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
				.unlockedByAnyIngredient(ModItems.TOMATO.get(), ModItems.ONION.get(), Items.BEETROOT)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SQUID_INK_PASTA.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(CommonTags.Items.FOODS_SAFE_RAW_FISH)
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.addIngredient(CommonTags.Items.CROPS_TOMATO)
				.addIngredient(Items.INK_SAC)
				.unlockedByAnyIngredient(ModItems.RAW_PASTA.get(), Items.INK_SAC, ModItems.TOMATO.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_PUMPKIN_BLOCK.get(), 1, SLOW_COOKING, LARGE_EXP, Items.PUMPKIN)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.addIngredient(Items.BROWN_MUSHROOM)
				.addIngredient(ConventionalItemTags.POTATO_CROPS)
				.addIngredient(ConventionalItemTags.BERRY_FOODS)
				.addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
				.unlockedByItems("has_pumpkin", Blocks.PUMPKIN)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.VEGETABLE_NOODLES.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(ConventionalItemTags.CARROT_CROPS)
				.addIngredient(ConventionalItemTags.MUSHROOMS)
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.addIngredient(CommonTags.Items.FOODS_LEAFY_GREEN)
				.addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
				.unlockedByAnyIngredient(ModItems.RAW_PASTA.get(), Items.BROWN_MUSHROOM, Items.CARROT)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
		CookingPotRecipeBuilder.cookingPotRecipe(ModItems.VEGETABLE_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
				.addIngredient(ConventionalItemTags.CARROT_CROPS)
				.addIngredient(ConventionalItemTags.POTATO_CROPS)
				.addIngredient(ConventionalItemTags.BEETROOT_CROPS)
				.addIngredient(CommonTags.Items.FOODS_LEAFY_GREEN)
				.unlockedByAnyIngredient(Items.CARROT, ModItems.ONION.get(), Items.BEETROOT)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);
	}
}
