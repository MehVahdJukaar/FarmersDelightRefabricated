package vectorwing.farmersdelight.common.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipe.CookingPotRecipeDisplay;
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
	private final ItemStack result;
	private final ItemStack container;
	private final ItemStack containerOverride;
	private final float experience;
	private final int cookTime;

	private PlacementInfo placementInfo;

	public CookingPotRecipe(String group, @Nullable CookingPotBookCategory tab, List<Ingredient> inputItems, ItemStack output, ItemStack container, float experience, int cookTime) {
		this.group = group;
		this.category = tab;
		this.inputItems = inputItems;
		this.result = output;

		if (!container.isEmpty()) {
			this.container = container;
		} else if (!output.getRecipeRemainder().isEmpty()) {
			this.container = output.getRecipeRemainder();
		} else {
			this.container = ItemStack.EMPTY;
		}

		this.containerOverride = container;
		this.experience = experience;
		this.cookTime = cookTime;
	}

	public List<Ingredient> input() {
		return inputItems;
	}

	public ItemStack result() {
		return result;
	}

	@Nullable
	public CookingPotBookCategory category() {
		return this.category;
	}

	public ItemStack container() {
		return this.container;
	}

	public ItemStack containerOverride() {
		return this.containerOverride;
	}

	@Override
	public ItemStack assemble(RecipeWrapper inv, HolderLookup.Provider provider) {
		return this.result.copy();
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
				container.isEmpty() ? Optional.empty() : Optional.of(new SlotDisplay.ItemStackSlotDisplay(container)),
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

	public static class Serializer implements RecipeSerializer<CookingPotRecipe>
	{
		private static final MapCodec<CookingPotRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
				Codec.STRING.optionalFieldOf("group", "").forGetter(CookingPotRecipe::group),
				CookingPotBookCategory.CODEC.optionalFieldOf("category", CookingPotBookCategory.MISC).forGetter(CookingPotRecipe::category),
				Ingredient.CODEC.listOf(1, 6).fieldOf("ingredients").forGetter(CookingPotRecipe::input),
				ItemStack.STRICT_CODEC.fieldOf("result").forGetter(r -> r.result),
				ItemStack.STRICT_CODEC.optionalFieldOf("container", ItemStack.EMPTY).forGetter(CookingPotRecipe::containerOverride),
				Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(CookingPotRecipe::getExperience),
				Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(CookingPotRecipe::getCookTime)
		).apply(inst, CookingPotRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, CookingPotRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

		public Serializer() {
		}

		@Override
		public MapCodec<CookingPotRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, CookingPotRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static CookingPotRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
			String groupIn = buffer.readUtf();
			CookingPotBookCategory tabIn = CookingPotBookCategory.STREAM_CODEC.decode(buffer);
			List<Ingredient> inputItemsIn = Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(6)).decode(buffer);
			ItemStack outputIn = ItemStack.STREAM_CODEC.decode(buffer);
			ItemStack container = ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer);
			float experienceIn = buffer.readFloat();
			int cookTimeIn = buffer.readVarInt();
			return new CookingPotRecipe(groupIn, tabIn, inputItemsIn, outputIn, container, experienceIn, cookTimeIn);
		}

		private static void toNetwork(RegistryFriendlyByteBuf buffer, CookingPotRecipe recipe) {
			buffer.writeUtf(recipe.group);
			buffer.writeUtf(recipe.category != null ? recipe.category.toString() : "");
			Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(6)).encode(buffer, recipe.inputItems);
			ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
			ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, recipe.container);
			buffer.writeFloat(recipe.experience);
			buffer.writeVarInt(recipe.cookTime);
		}
	}
}
