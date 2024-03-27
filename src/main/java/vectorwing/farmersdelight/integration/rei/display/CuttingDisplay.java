package vectorwing.farmersdelight.integration.rei.display;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.Pair;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;

import java.util.List;
import java.util.Optional;

public class CuttingDisplay extends BasicDisplay {
    private EntryIngredient tool;
    private List<Pair<EntryIngredient, Float>> chanceResults;

    public CuttingDisplay(CuttingBoardRecipe recipe) {
        this(EntryIngredients.ofIngredients(recipe.getIngredients()), recipe.getRollableResults().stream().map(result -> EntryIngredients.of(result.stack())).toList(), Optional.of(recipe.getId()), EntryIngredients.ofIngredient(recipe.getTool()), recipe.getRollableResults().stream().map(result -> Pair.of(EntryIngredients.of(result.stack()), result.chance())).toList());
    }

    public CuttingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location, CompoundTag tag) {
        this(inputs, outputs, location, EntryIngredient.of(EntryStack.read(tag.getCompound("tool"))), deserializeChanceResults(tag));
    }

    public CuttingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location, EntryIngredient tool, List<Pair<EntryIngredient, Float>> chanceResults) {
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


    private static List<Pair<EntryIngredient, Float>> deserializeChanceResults(CompoundTag tag) {
        ImmutableList.Builder<Pair<EntryIngredient, Float>> builder = new ImmutableList.Builder<>();
        ListTag innerTag = tag.getList("chance_results", Tag.TAG_COMPOUND);
        for (int i = 0; i < innerTag.size(); ++i) {
            CompoundTag entry = innerTag.getCompound(i);
            builder.add(Pair.of(EntryIngredient.of(EntryStack.read(entry.getCompound("stack"))), entry.getFloat("chance")));
        }
        return builder.build();
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICategoryIdentifiers.CUTTING;
    }

    public static Serializer<CuttingDisplay> serializer() {
        return Serializer.of(CuttingDisplay::new, (display, tag) -> {
            display.tool = EntryIngredient.of(EntryStack.read(tag.getCompound("tool")));
            display.chanceResults = deserializeChanceResults(tag);
        });
    }
}
