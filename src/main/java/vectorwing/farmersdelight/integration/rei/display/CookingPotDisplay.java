package vectorwing.farmersdelight.integration.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;

import java.util.List;
import java.util.Optional;

public class CookingPotDisplay extends BasicDisplay {
    private EntryIngredient container;
    private int cookTime;
    private float experience;

    public CookingPotDisplay(CookingPotRecipe recipe) {
        this(EntryIngredients.ofIngredients(recipe.getIngredients()), List.of(EntryIngredients.of(recipe.getResultItem(REGISTRY_ACCESS.get()))), Optional.of(recipe.getId()), EntryIngredients.of(recipe.getOutputContainer()), recipe.getCookTime(), recipe.getExperience());
    }

    public CookingPotDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location, CompoundTag tag) {
        this(inputs, outputs, location, EntryIngredient.of(EntryStack.read(tag.getCompound("container"))), tag.getInt("cook_time"), tag.getFloat("experience"));
    }

    public CookingPotDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location, EntryIngredient container, int cookTime, float experience) {
        super(inputs, outputs, location);
        this.container = container;
        this.cookTime = cookTime;
        this.experience = experience;
    }

    public EntryIngredient getOutputContainer() {
        return container;
    }

    public int getCookTime() {
        return cookTime;
    }

    public float getExperience() {
        return experience;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICategoryIdentifiers.COOKING;
    }

    public static Serializer<CookingPotDisplay> serializer() {
        return BasicDisplay.Serializer.of(CookingPotDisplay::new, (display, tag) -> {
            display.container = EntryIngredient.of(EntryStack.read(tag.getCompound("container")));
            display.cookTime = tag.getInt("cook_time");
            display.experience = tag.getFloat("experience");
        });
    }
}
