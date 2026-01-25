package vectorwing.farmersdelight.common.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.List;
import java.util.Optional;

@MethodsReturnNonnullByDefault
public class SmokerCookFunction extends LootItemConditionalFunction
{
	public static final Identifier ID = Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "smoker_cook");
	public static final MapCodec<SmokerCookFunction> CODEC = RecordCodecBuilder.mapCodec(
			p_298131_ -> commonFields(p_298131_).apply(p_298131_, SmokerCookFunction::new)
	);

	protected SmokerCookFunction(List<LootItemCondition> conditionsIn) {
		super(conditionsIn);
	}

    @Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		if (stack.isEmpty()) {
			return stack;
		} else {
			Optional<RecipeHolder<SmokingRecipe>> recipe = context.getLevel().recipeAccess().getRecipeFor(RecipeType.SMOKING, new SingleRecipeInput(stack), context.getLevel());
			if (recipe.isPresent()) {
				ItemStack result = recipe.get().value().assemble(new SingleRecipeInput(stack)).copy();
				result.setCount(result.getCount() * stack.getCount());
				return result;
			} else {
				return stack;
			}
		}
	}

    @Override
    public MapCodec<? extends LootItemConditionalFunction> codec() {
        return CODEC;
    }
}
