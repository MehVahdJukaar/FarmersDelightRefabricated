package vectorwing.farmersdelight.integration.rei.display;

import com.mojang.serialization.MapCodec;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;

import java.util.List;
import java.util.Optional;

public class DecompositionDisplay extends BasicDisplay {
    public static final DisplaySerializer<? extends Display> SERIALIZER = DisplaySerializer.of(
            MapCodec.unit(new DecompositionDisplay()),
            StreamCodec.unit(new DecompositionDisplay())
    );

    public DecompositionDisplay() {
        super(List.of(EntryIngredients.of(ModItems.ORGANIC_COMPOST.get())), List.of(EntryIngredients.of(ModItems.RICH_SOIL.get())));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICategoryIdentifiers.DECOMPOSITION;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }

}
