package vectorwing.farmersdelight.data.builder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.CookingPotBookCategory;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.util.LinkedHashMap;
import java.util.Map;

@MethodsReturnNonnullByDefault
public class CookingPotRecipeBuilder implements RecipeBuilder
{
	private final HolderGetter<Item> items;
	private CookingPotBookCategory category;
	private final NonNullList<Ingredient> ingredients = NonNullList.create();
	private final Item result;
	private final ItemStack resultStack;
	private final int cookingTime;
	private final float experience;
	private final ItemStack container;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	public CookingPotRecipeBuilder(HolderGetter<Item> items, ItemLike result, int count, int cookingTime, float experience, @Nullable ItemLike container) {
		this(items, new ItemStack(result, count), cookingTime, experience, container);
	}

	public CookingPotRecipeBuilder(HolderGetter<Item> items, ItemStack resultIn, int cookingTime, float experience, @Nullable ItemLike container) {
		this.items = items;
		this.result = resultIn.getItem();
		this.resultStack = resultIn;
		this.cookingTime = cookingTime;
		this.experience = experience;
		this.container = container != null ? new ItemStack(container) : ItemStack.EMPTY;
		this.category = null;
	}

	public static CookingPotRecipeBuilder cookingPotRecipe(HolderGetter<Item> items, ItemLike mainResult, int count, int cookingTime, float experience) {
		return new CookingPotRecipeBuilder(items, mainResult, count, cookingTime, experience, null);
	}

	public static CookingPotRecipeBuilder cookingPotRecipe(HolderGetter<Item> items, ItemLike mainResult, int count, int cookingTime, float experience, ItemLike container) {
		return new CookingPotRecipeBuilder(items, mainResult, count, cookingTime, experience, container);
	}

	public CookingPotRecipeBuilder addIngredient(TagKey<Item> tagIn) {
		return addIngredient(Ingredient.of(items.getOrThrow(tagIn)));
	}

	public CookingPotRecipeBuilder addIngredient(ItemLike itemIn) {
		return addIngredient(itemIn, 1);
	}

	public CookingPotRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
		for (int i = 0; i < quantity; ++i) {
			addIngredient(Ingredient.of(itemIn));
		}
		return this;
	}

	public CookingPotRecipeBuilder addIngredient(Ingredient ingredientIn) {
		return addIngredient(ingredientIn, 1);
	}

	public CookingPotRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
		for (int i = 0; i < quantity; ++i) {
			ingredients.add(ingredientIn);
		}
		return this;
	}

	@Override
	public RecipeBuilder group(@org.jetbrains.annotations.Nullable String p_176495_) {
		return this;
	}

	public CookingPotRecipeBuilder setRecipeBookCategory(CookingPotBookCategory category) {
		this.category = category;
		return this;
	}

	@Override
	public Item getResult() {
		return this.result;
	}

	@Override
	public CookingPotRecipeBuilder unlockedBy(String criterionName, Criterion<?> criterionTrigger) {
		this.criteria.put(criterionName, criterionTrigger);
		return this;
	}

	public CookingPotRecipeBuilder unlockedByItems(String criterionName, ItemLike... items) {
		return unlockedBy(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(items));
	}

	public CookingPotRecipeBuilder unlockedByAnyIngredient(ItemLike... itemCollection) {
		this.criteria.put("has_any_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, itemCollection).build()));
		return this;
	}

	public void build(RecipeOutput output) {
		ResourceLocation location = BuiltInRegistries.ITEM.getKey(result);
		build(output, ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, location.getPath()));
	}

	public void build(RecipeOutput outputIn, String save) {
		ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(result);
		if ((ResourceLocation.parse(save)).equals(resourcelocation)) {
			throw new IllegalStateException("Cooking Recipe " + save + " should remove its 'save' argument");
		} else {
			build(outputIn, ResourceLocation.parse(save));
		}
	}

	public void build(RecipeOutput output, ResourceLocation location) {
		save(output, ResourceKey.create(Registries.RECIPE, location.withPrefix("cooking/")));
	}

	@Override
	public void save(RecipeOutput output, ResourceKey<Recipe<?>> resourceKey) {
		Advancement.Builder advancementBuilder = output.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
				.rewards(AdvancementRewards.Builder.recipe(resourceKey))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancementBuilder::addCriterion);
		CookingPotRecipe recipe = new CookingPotRecipe(
				"",
				this.category,
				this.ingredients,
				this.resultStack,
				this.container,
				this.experience,
				this.cookingTime
		);
		output.accept(resourceKey, recipe, advancementBuilder.build(resourceKey.location().withPrefix("recipes/")));
	}
}
