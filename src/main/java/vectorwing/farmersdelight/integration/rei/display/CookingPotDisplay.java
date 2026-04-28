//
//package vectorwing.farmersdelight.integration.rei.display;
//
//import com.mojang.serialization.Codec;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import me.shedaniel.rei.api.common.category.CategoryIdentifier;
//import me.shedaniel.rei.api.common.display.Display;
//import me.shedaniel.rei.api.common.display.DisplaySerializer;
//import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
//import me.shedaniel.rei.api.common.entry.EntryIngredient;
//import me.shedaniel.rei.api.common.util.EntryIngredients;
//import me.shedaniel.rei.plugin.common.displays.crafting.CraftingDisplay;
//import net.minecraft.network.codec.ByteBufCodecs;
//import net.minecraft.network.codec.StreamCodec;
//import net.minecraft.resources.Identifier;
//import net.minecraft.world.item.crafting.RecipeHolder;
//import org.jspecify.annotations.Nullable;
//import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
//import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;
//
//import java.util.List;
//import java.util.Optional;
//
//public class CookingPotDisplay extends BasicDisplay implements CraftingDisplay {
//    private EntryIngredient container;
//    private int cookTime;
//    private float experience;
//
//    public static final DisplaySerializer<CookingPotDisplay> SERIALIZER = DisplaySerializer.of(
//            RecordCodecBuilder.mapCodec(inst -> inst.group(
//                    EntryIngredient.codec().listOf().fieldOf("inputs").forGetter(CookingPotDisplay::getInputEntries),
//                    EntryIngredient.codec().listOf().fieldOf("outputs").forGetter(CookingPotDisplay::getOutputEntries),
//                    Identifier.CODEC.optionalFieldOf("location").forGetter(CookingPotDisplay::getDisplayLocation),
//                    EntryIngredient.codec().fieldOf("container").forGetter(CookingPotDisplay::getOutputContainer),
//                    Codec.INT.fieldOf("cook_time").forGetter(CookingPotDisplay::getCookTime),
//                    Codec.FLOAT.fieldOf("experience").forGetter(CookingPotDisplay::getExperience)
//            ).apply(inst, CookingPotDisplay::new)),
//            StreamCodec.composite(
//                    EntryIngredient.streamCodec().apply(ByteBufCodecs.list()), CookingPotDisplay::getInputEntries,
//                    EntryIngredient.streamCodec().apply(ByteBufCodecs.list()), CookingPotDisplay::getOutputEntries,
//                    ByteBufCodecs.optional(Identifier.STREAM_CODEC), CookingPotDisplay::getDisplayLocation,
//                    EntryIngredient.streamCodec(), CookingPotDisplay::getOutputContainer,
//                    ByteBufCodecs.INT, CookingPotDisplay::getCookTime,
//                    ByteBufCodecs.FLOAT, CookingPotDisplay::getExperience,
//                    CookingPotDisplay::new
//            ));
//
//    public CookingPotDisplay(RecipeHolder<CookingPotRecipe> recipe) {
//        this(EntryIngredients.ofIngredients(recipe.value().input()), List.of(EntryIngredients.of(recipe.value().result())), Optional.of(recipe.id().identifier()), EntryIngredients.of(recipe.value().container()), recipe.value().getCookTime(), recipe.value().getExperience());
//    }
//
//    public CookingPotDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<Identifier> location, EntryIngredient container, int cookTime, float experience) {
//        super(inputs, outputs, location);
//        this.container = container;
//        this.cookTime = cookTime;
//        this.experience = experience;
//    }
//
//    public EntryIngredient getOutputContainer() {
//        return container;
//    }
//
//    public int getCookTime() {
//        return cookTime;
//    }
//
//    public float getExperience() {
//        return experience;
//    }
//
//    @Override
//    public CategoryIdentifier<?> getCategoryIdentifier() {
//        return REICategoryIdentifiers.COOKING;
//    }
//
//    @Override
//    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
//        return SERIALIZER;
//    }
//
//    @Override
//    public boolean isShapeless() {
//        return true;
//    }
//
//    @Override
//    public int getWidth() {
//        return 3;
//    }
//
//    @Override
//    public int getHeight() {
//        return 2;
//    }
//}
//
