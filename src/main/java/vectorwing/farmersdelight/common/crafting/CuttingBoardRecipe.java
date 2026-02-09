package vectorwing.farmersdelight.common.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.registry.ModRecipeBookCategories;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CuttingBoardRecipe implements Recipe<CuttingBoardRecipeInput>
{
	public static final int MAX_RESULTS = 4;

	private final String group;
	private final Ingredient input;
	private final Ingredient tool;
	private final List<ChanceResult> results;
    @Nullable
	private final Holder<SoundEvent> soundEvent;

	public CuttingBoardRecipe(String group, Ingredient input, Ingredient tool, List<ChanceResult> results, @Nullable Holder<SoundEvent> soundEvent) {
		this.group = group;
		this.input = input;
		this.tool = tool;
		this.results = results;
		this.soundEvent = soundEvent;
	}

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private CuttingBoardRecipe(String group, Ingredient input, Ingredient tool, List<ChanceResult> results, Optional<Holder<SoundEvent>> soundEvent) {
        this(group, input, tool, results, soundEvent.orElse(null));
    }

	@Override
	public boolean matches(CuttingBoardRecipeInput input, Level level) {
		return this.input.test(input.item()) && this.tool.test(input.tool());
	}

	@Override
	public ItemStack assemble(CuttingBoardRecipeInput inv) {
		return this.results.get(0).stack().copy();
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public boolean showNotification() {
		return false;
	}

	@Override
	public String group() {
		return this.group;
	}

	public Ingredient getInput() {
		return input;
	}

	public Ingredient getTool() {
		return this.tool;
	}

	public List<ItemStack> getResults() {
		return getRollableResults().stream()
				.map(ChanceResult::stack)
				.collect(Collectors.toList());
	}

	public List<ChanceResult> getRollableResults() {
		return this.results;
	}

	public List<ItemStack> rollResults(RandomSource rand, int fortuneLevel) {
		List<ItemStack> results = new ArrayList<>();
		List<ChanceResult> rollableResults = getRollableResults();
		for (ChanceResult output : rollableResults) {
			ItemStack stack = output.rollOutput(rand, fortuneLevel);
			if (!stack.isEmpty())
				results.add(stack);
		}
		return results;
	}

	public Optional<SoundEvent> getSoundEvent() {
		return getSoundEventHolder().map(Holder::value);
	}

    public Optional<Holder<SoundEvent>> getSoundEventHolder() {
        return Optional.ofNullable(this.soundEvent);
    }

	@Override
	public RecipeSerializer<CuttingBoardRecipe> getSerializer() {
		return ModRecipeSerializers.CUTTING.get();
	}

	@Override
	public RecipeType<CuttingBoardRecipe> getType() {
		return ModRecipeTypes.CUTTING.get();
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.NOT_PLACEABLE;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return ModRecipeBookCategories.CUTTING.get();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CuttingBoardRecipe that = (CuttingBoardRecipe) o;

		if (!group().equals(that.group())) return false;
		if (!input.equals(that.input)) return false;
		if (!getTool().equals(that.getTool())) return false;
		if (!getResults().equals(that.getResults())) return false;
		return Objects.equals(soundEvent, that.soundEvent);
	}

	@Override
	public int hashCode() {
        return Objects.hash(group, input, tool, results, soundEvent);
	}

	public static class Serializer
	{
		public static final StreamCodec<RegistryFriendlyByteBuf, CuttingBoardRecipe> STREAM_CODEC =
				StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

		private static final MapCodec<CuttingBoardRecipe> CODEC = RecordCodecBuilder.mapCodec(
				inst -> inst.group(Codec.STRING.optionalFieldOf("group", "").forGetter(CuttingBoardRecipe::group),
								// List::getFirst does not compile...
								Ingredient.CODEC.listOf(1, 1).fieldOf("ingredients").xmap(List::getFirst, List::of).forGetter(cuttingBoardRecipe -> cuttingBoardRecipe.input),
								Ingredient.CODEC.fieldOf("tool").forGetter(CuttingBoardRecipe::getTool),
								ChanceResult.CODEC.listOf(1, MAX_RESULTS).fieldOf("result").forGetter(CuttingBoardRecipe::getRollableResults),
								SoundEvent.CODEC.optionalFieldOf("sound").forGetter(CuttingBoardRecipe::getSoundEventHolder))
						.apply(inst, CuttingBoardRecipe::new));

		public Serializer() {
		}

		public static CuttingBoardRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
			String groupIn = buffer.readUtf(32767);
			Ingredient inputItemIn = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			Ingredient toolIn = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			List<ChanceResult> resultsIn = ChanceResult.STREAM_CODEC.apply(ByteBufCodecs.list()).decode(buffer);
			Optional<Holder<SoundEvent>> soundEventIn = SoundEvent.STREAM_CODEC.apply(ByteBufCodecs::optional).decode(buffer);

			return new CuttingBoardRecipe(groupIn, inputItemIn, toolIn, resultsIn, soundEventIn.orElse(null));
		}

		public static void toNetwork(RegistryFriendlyByteBuf buffer, CuttingBoardRecipe recipe) {
			buffer.writeUtf(recipe.group);
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.input);
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.tool);
			ChanceResult.STREAM_CODEC.apply(ByteBufCodecs.list()).encode(buffer, recipe.results);
            SoundEvent.STREAM_CODEC.apply(ByteBufCodecs::optional).encode(buffer, Optional.ofNullable(recipe.soundEvent));
		}

//		@Override
		public static MapCodec<CuttingBoardRecipe> codec() {
			return CODEC;
		}

//		@Override
		public static StreamCodec<RegistryFriendlyByteBuf, CuttingBoardRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
