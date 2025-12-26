package vectorwing.farmersdelight.common.crafting.ingredient;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.fabricators_of_create.porting_lib.tool.ToolAction;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredient;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredientSerializer;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.List;

@MethodsReturnNonnullByDefault
public class ToolActionIngredient extends Ingredient implements CustomIngredient
{
	public static final Serializer SERIALIZER = new Serializer();

	public final ToolAction toolAction;

	/**
	 * Ingredient that checks if the given stack can perform a ToolAction from Forge.
	 */
	public ToolActionIngredient(ToolAction toolAction) {
		super(BuiltInRegistries.ITEM.stream()
				.map(ItemStack::new)
				.filter(stack -> stack.canPerformAction(toolAction))
				.map(Ingredient.ItemValue::new));
		this.toolAction = toolAction;
	}

    public static void register() {
        CustomIngredientSerializer.register(SERIALIZER);
    }

    @Override
    public boolean requiresTesting() {
        return false;
    }

    @Override
	public boolean test(@Nullable ItemStack stack) {
		return stack != null && stack.canPerformAction(toolAction);
	}

    @Override
    public List<ItemStack> getMatchingStacks() {
        return List.of();
    }

    @Override
    public CustomIngredientSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
	public JsonElement toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("type", SERIALIZER.getIdentifier().toString());
		json.addProperty("action", toolAction.name());
		return json;
	}

	public static class Serializer implements CustomIngredientSerializer<ToolActionIngredient>
	{
        @Override
        public ResourceLocation getIdentifier() {
            return FarmersDelight.res("tool_action");
        }

		@Override
		public ToolActionIngredient read(JsonObject json) {
			return new ToolActionIngredient(ToolAction.get(json.get("action").getAsString()));
		}

		@Override
		public ToolActionIngredient read(FriendlyByteBuf buffer) {
			return new ToolActionIngredient(ToolAction.get(buffer.readUtf()));
		}

        @Override
        public void write(JsonObject json, ToolActionIngredient ingredient) {
            json.addProperty("action", ingredient.toolAction.name());
        }

        @Override
		public void write(FriendlyByteBuf buffer, ToolActionIngredient ingredient) {
			buffer.writeUtf(ingredient.toolAction.name());
		}
	}
}
