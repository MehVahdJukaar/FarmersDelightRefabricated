package vectorwing.farmersdelight.common.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipe.CookingPotRecipeDisplay;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeBookCategories;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.refabricated.inventory.RecipeWrapper;

import java.util.List;
import java.util.Optional;

public class CookingPotRecipe implements Recipe<RecipeWrapper>
{
	private final String group;
	private final CookingPotBookCategory category;
	private final List<Ingredient> inputItems;
	private final ItemStackTemplate result;
    @Nullable
	private final ItemStackTemplate container;
	private final float experience;
	private final int cookTime;

	private PlacementInfo placementInfo;

	public CookingPotRecipe(String group, @Nullable CookingPotBookCategory tab, List<Ingredient> inputItems, ItemStackTemplate output, Optional<ItemStackTemplate> container, float experience, int cookTime) {
		this.group = group;
		this.category = tab;
		this.inputItems = inputItems;
		this.result = output;
        this.container = container.orElse(null);
		this.experience = experience;
		this.cookTime = cookTime;
	}

	public List<Ingredient> input() {
		return inputItems;
	}

	public ItemStackTemplate result() {
		return result;
	}

	@Nullable
	public CookingPotBookCategory category() {
		return this.category;
	}

	public ItemStackTemplate container() {
		if (container == null) {
            ItemStackTemplate template = result.create().getCraftingRemainder();
            if (template != null) {
                return template;
            }
            Item item = CookingPotBlockEntity.INGREDIENT_REMAINDER_OVERRIDES.get(result.item().value());
            if (item != null) {
                return new ItemStackTemplate(item);
            }
        }
        return container;
	}

	public Optional<ItemStackTemplate> containerOverride() {
		return Optional.ofNullable(container);
	}

	@Override
	public ItemStack assemble(RecipeWrapper inv) {
		return this.result.create();
	}

	@Override
	public boolean showNotification() {
		return false;
	}

	@Override
	public String group() {
		return this.group;
	}

	@Override
	public RecipeSerializer<? extends Recipe<RecipeWrapper>> getSerializer() {
		return ModRecipeSerializers.COOKING.get();
	}

	@Override
	public RecipeType<? extends Recipe<RecipeWrapper>> getType() {
		return ModRecipeTypes.COOKING.get();
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new CookingPotRecipeDisplay(this.input().stream().map(Ingredient::display).toList(),
				container == null ? Optional.empty() : Optional.of(new SlotDisplay.ItemStackSlotDisplay(new ItemStackTemplate(container.item(), container.count(), container.components()))),
				new SlotDisplay.ItemStackSlotDisplay(this.result()),
				new SlotDisplay.ItemSlotDisplay(ModItems.COOKING_POT.get()),
				cookTime,
				experience));
	}

	@Override
	public PlacementInfo placementInfo() {
		if (placementInfo == null) {
			placementInfo = PlacementInfo.create(inputItems);
		}

		return placementInfo;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return switch (category()) {
			case MEALS -> ModRecipeBookCategories.COOKING_MEALS.get();
			case DRINKS -> ModRecipeBookCategories.COOKING_DRINKS.get();
			case MISC -> ModRecipeBookCategories.COOKING_MISC.get();
		};
	}

	public float getExperience() {
		return this.experience;
	}

	public int getCookTime() {
		return this.cookTime;
	}

	@Override
	public boolean matches(RecipeWrapper inv, Level level) {
		return inv.ingredientAmount() == this.inputItems.size() && inv.stackedContents().canCraft(this, null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CookingPotRecipe that = (CookingPotRecipe) o;

		if (Float.compare(that.getExperience(), getExperience()) != 0) return false;
		if (getCookTime() != that.getCookTime()) return false;
		if (!group().equals(that.group())) return false;
		if (category != that.category) return false;
		if (!inputItems.equals(that.inputItems)) return false;
		if (!result.equals(that.result)) return false;
		return container.equals(that.container);
	}

	@Override
	public int hashCode() {
		int result = group().hashCode();
		result = 31 * result + (category() != null ? category().hashCode() : 0);
		result = 31 * result + inputItems.hashCode();
		result = 31 * result + this.result.hashCode();
		result = 31 * result + container.hashCode();
		result = 31 * result + (getExperience() != 0.0f ? Float.floatToIntBits(getExperience()) : 0);
		result = 31 * result + getCookTime();
		return result;
	}

	public static class Serializer
	{
		private static final MapCodec<CookingPotRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
				Codec.STRING.optionalFieldOf("group", "").forGetter(CookingPotRecipe::group),
				CookingPotBookCategory.CODEC.optionalFieldOf("recipe_book_tab", CookingPotBookCategory.MISC).forGetter(CookingPotRecipe::category),
				Ingredient.CODEC.listOf(1, 6).fieldOf("ingredients").forGetter(CookingPotRecipe::input),
				ItemStackTemplate.CODEC.fieldOf("result").forGetter(r -> r.result),
				ItemStackTemplate.CODEC.optionalFieldOf("container").forGetter(CookingPotRecipe::containerOverride),
				Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(CookingPotRecipe::getExperience),
				Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(CookingPotRecipe::getCookTime)
		).apply(inst, CookingPotRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, CookingPotRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

		public Serializer() {
		}

//		@Override
		public static MapCodec<CookingPotRecipe> codec() {
			return CODEC;
		}

//		@Override
		public static StreamCodec<RegistryFriendlyByteBuf, CookingPotRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static CookingPotRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
			String groupIn = buffer.readUtf();
			CookingPotBookCategory tabIn = CookingPotBookCategory.STREAM_CODEC.decode(buffer);
			List<Ingredient> inputItemsIn = Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()).decode(buffer);
			ItemStackTemplate outputIn = ItemStackTemplate.STREAM_CODEC.decode(buffer);
			Optional<ItemStackTemplate> container = ByteBufCodecs.optional(ItemStackTemplate.STREAM_CODEC).decode(buffer);
			float experienceIn = buffer.readFloat();
			int cookTimeIn = buffer.readVarInt();
			return new CookingPotRecipe(groupIn, tabIn, inputItemsIn, outputIn, container, experienceIn, cookTimeIn);
		}

		private static void toNetwork(RegistryFriendlyByteBuf buffer, CookingPotRecipe recipe) {
			buffer.writeUtf(recipe.group);
            CookingPotBookCategory.STREAM_CODEC.encode(buffer, recipe.category);
			Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()).encode(buffer, recipe.inputItems);
			ItemStackTemplate.STREAM_CODEC.encode(buffer, recipe.result);
			ByteBufCodecs.optional(ItemStackTemplate.STREAM_CODEC).encode(buffer, Optional.ofNullable(recipe.container));
			buffer.writeFloat(recipe.experience);
			buffer.writeVarInt(recipe.cookTime);
		}
	}
}
