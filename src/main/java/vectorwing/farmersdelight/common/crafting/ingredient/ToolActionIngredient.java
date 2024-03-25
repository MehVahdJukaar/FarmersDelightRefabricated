package vectorwing.farmersdelight.common.crafting.ingredient;

import com.google.common.base.Suppliers;
import com.google.gson.JsonObject;
import io.github.fabricators_of_create.porting_lib.tool.ToolAction;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredient;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredientSerializer;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.List;
import java.util.function.Supplier;

;

@MethodsReturnNonnullByDefault
public class ToolActionIngredient implements CustomIngredient {
    public static final Serializer SERIALIZER = new Serializer();
    public static final ResourceLocation SERIALIZER_ID = FarmersDelight.res("tool_action");

    public final ToolAction toolAction;
    private final Supplier<List<ItemStack>> matchingStacks;

    public static void register() {
        CustomIngredientSerializer.register(SERIALIZER);
    }

    /**
     * Ingredient that checks if the given stack can perform a ToolAction from Forge.
     */
    public ToolActionIngredient(ToolAction toolAction) {
        this.matchingStacks = Suppliers.memoize(() -> BuiltInRegistries.ITEM.stream()
                .map(ItemStack::new)
                .filter(stack -> stack.canPerformAction(toolAction))
                .toList());
        this.toolAction = toolAction;
    }

    @Override
    public List<ItemStack> getMatchingStacks() {
        return matchingStacks.get();
    }

    // no need to compare tags? I think
    @Override
    public boolean requiresTesting() {
        return false;
    }

    @Override
    public CustomIngredientSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public boolean test(@Nullable ItemStack stack) {
        return stack != null && stack.canPerformAction(toolAction);
    }

    public static class Serializer implements CustomIngredientSerializer<ToolActionIngredient> {

        @Override
        public ResourceLocation getIdentifier() {
            return SERIALIZER_ID;
        }

        @Override
        public ToolActionIngredient read(JsonObject json) {
            return new ToolActionIngredient(ToolAction.get(json.get("action").getAsString()));
        }

        @Override
        public void write(JsonObject json, ToolActionIngredient ingredient) {
            json.addProperty("action", ingredient.toolAction.name());
        }

        @Override
        public ToolActionIngredient read(FriendlyByteBuf buf) {
            return new ToolActionIngredient(ToolAction.get(buf.readUtf()));
        }

        @Override
        public void write(FriendlyByteBuf buffer, ToolActionIngredient ingredient) {
            buffer.writeUtf(ingredient.toolAction.name());
        }
    }
}
