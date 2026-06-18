package vectorwing.farmersdelight.integration.rei.display;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.common.displays.crafting.CraftingDisplay;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;

import java.util.List;
import java.util.Optional;

public class CuttingDisplay extends BasicDisplay implements CraftingDisplay {
    private EntryIngredient tool;
    private List<Pair<EntryIngredient, Float>> chanceResults;

    private static final StreamCodec<RegistryFriendlyByteBuf, Pair<EntryIngredient, Float>> CHANCE_RESULTS_STREAM_CODEC = StreamCodec.composite(
            EntryIngredient.streamCodec(), Pair::getFirst,
            ByteBufCodecs.FLOAT, Pair::getSecond,
            Pair::new
    );

    public static final DisplaySerializer<CuttingDisplay> SERIALIZER = DisplaySerializer.of(
            RecordCodecBuilder.mapCodec(inst -> inst.group(
                    EntryIngredient.codec().listOf().fieldOf("inputs").forGetter(CuttingDisplay::getInputEntries),
                    EntryIngredient.codec().listOf().fieldOf("outputs").forGetter(CuttingDisplay::getOutputEntries),
                    Identifier.CODEC.optionalFieldOf("location").forGetter(CuttingDisplay::getDisplayLocation),
                    EntryIngredient.codec().fieldOf("tool").forGetter(CuttingDisplay::getTool),
                    Codec.pair(EntryIngredient.codec(), Codec.FLOAT).listOf().fieldOf("chance_results").forGetter(CuttingDisplay::getRollableOutputs)
            ).apply(inst, CuttingDisplay::new)),
            StreamCodec.composite(
                    EntryIngredient.streamCodec().apply(ByteBufCodecs.list()), CuttingDisplay::getInputEntries,
                    EntryIngredient.streamCodec().apply(ByteBufCodecs.list()), CuttingDisplay::getOutputEntries,
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC), CuttingDisplay::getDisplayLocation,
                    EntryIngredient.streamCodec(), CuttingDisplay::getTool,
                    CHANCE_RESULTS_STREAM_CODEC.apply(ByteBufCodecs.list()), CuttingDisplay::getRollableOutputs,
                    CuttingDisplay::new
            ));

    public CuttingDisplay(RecipeHolder<CuttingBoardRecipe> recipe) {
        this(List.of(EntryIngredients.ofIngredient(recipe.value().getInput())),
                recipe.value().getRollableResults().stream().map(result -> EntryIngredients.of(result.stack())).toList(),
                Optional.of(recipe.id().identifier()),
                EntryIngredients.ofIngredient(recipe.value().getTool()),
                recipe.value().getRollableResults().stream().map(result -> Pair.of(EntryIngredients.of(result.stack()), result.chance())).toList()
        );
    }

    public CuttingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<Identifier> location, EntryIngredient tool, List<Pair<EntryIngredient, Float>> chanceResults) {
        super(inputs, outputs, location);
        this.tool = tool;
        this.chanceResults = chanceResults;
    }

    public EntryIngredient getTool() {
        return tool;
    }
    public List<Pair<EntryIngredient, Float>> getRollableOutputs() {
        return chanceResults;
    }


    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICategoryIdentifiers.CUTTING;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public boolean isShapeless() {
        return false;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }
}
