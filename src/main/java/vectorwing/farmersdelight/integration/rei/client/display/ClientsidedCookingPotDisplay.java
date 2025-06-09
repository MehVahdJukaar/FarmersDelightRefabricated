package vectorwing.farmersdelight.integration.rei.client.display;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.client.displays.ClientsidedRecipeBookDisplay;
import me.shedaniel.rei.plugin.common.displays.crafting.CraftingDisplay;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipe.CookingPotRecipeDisplay;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;

import java.util.List;
import java.util.Optional;

public class ClientsidedCookingPotDisplay extends BasicDisplay implements CraftingDisplay, ClientsidedRecipeBookDisplay {
    private final Optional<RecipeDisplayId> id;
    private final EntryIngredient container;
    private final int cookTime;
    private final float experience;

    public static final DisplaySerializer<ClientsidedCookingPotDisplay> SERIALIZER = DisplaySerializer.of(
            RecordCodecBuilder.mapCodec(inst -> inst.group(
                    EntryIngredient.codec().listOf().fieldOf("inputs").forGetter(ClientsidedCookingPotDisplay::getInputEntries),
                    EntryIngredient.codec().listOf().fieldOf("outputs").forGetter(ClientsidedCookingPotDisplay::getOutputEntries),
                    Codec.INT.xmap(RecipeDisplayId::new, RecipeDisplayId::index).optionalFieldOf("id").forGetter(ClientsidedCookingPotDisplay::recipeDisplayId),
                    EntryIngredient.codec().fieldOf("container").forGetter(ClientsidedCookingPotDisplay::getOutputContainer),
                    Codec.INT.fieldOf("cook_time").forGetter(ClientsidedCookingPotDisplay::getCookTime),
                    Codec.FLOAT.fieldOf("experience").forGetter(ClientsidedCookingPotDisplay::getExperience)
            ).apply(inst, ClientsidedCookingPotDisplay::new)),
            StreamCodec.composite(
                    EntryIngredient.streamCodec().apply(ByteBufCodecs.list()), ClientsidedCookingPotDisplay::getInputEntries,
                    EntryIngredient.streamCodec().apply(ByteBufCodecs.list()), ClientsidedCookingPotDisplay::getOutputEntries,
                    ByteBufCodecs.optional(RecipeDisplayId.STREAM_CODEC), ClientsidedCookingPotDisplay::recipeDisplayId,
                    EntryIngredient.streamCodec(), ClientsidedCookingPotDisplay::getOutputContainer,
                    ByteBufCodecs.INT, ClientsidedCookingPotDisplay::getCookTime,
                    ByteBufCodecs.FLOAT, ClientsidedCookingPotDisplay::getExperience,
                    ClientsidedCookingPotDisplay::new
            )
    );

    public ClientsidedCookingPotDisplay(CookingPotRecipeDisplay recipe, Optional<RecipeDisplayId> id) {
        this(
                EntryIngredients.ofSlotDisplays(recipe.ingredients()),
                List.of(EntryIngredients.ofSlotDisplay(recipe.result())),
                id,
                EntryIngredients.ofSlotDisplay(recipe.container()),
                recipe.duration(),
                recipe.experience()
        );
    }

    public ClientsidedCookingPotDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<RecipeDisplayId> id,
                                        EntryIngredient container, int cookTime, float experience) {
        super(inputs, outputs);
        this.id = id;
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
    public boolean isShapeless() {
        return true;
    }

    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 2;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICategoryIdentifiers.COOKING;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public Optional<RecipeDisplayId> recipeDisplayId() {
        return id;
    }
}
