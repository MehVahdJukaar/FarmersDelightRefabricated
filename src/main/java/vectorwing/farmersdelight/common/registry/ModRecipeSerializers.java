package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.DoughRecipe;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regRecipeSerializer;

public class ModRecipeSerializers {
    public static final Supplier<RecipeSerializer<CookingPotRecipe>> COOKING = regRecipeSerializer("cooking",()-> new RecipeSerializer<>() {
	    @Override
		public MapCodec<CookingPotRecipe> codec() {
			return CookingPotRecipe.Serializer.codec();
		}

	    @Override
		public StreamCodec<RegistryFriendlyByteBuf, CookingPotRecipe> streamCodec() {
			return CookingPotRecipe.Serializer.streamCodec();
		}
    });
    public static final Supplier<RecipeSerializer<CuttingBoardRecipe>> CUTTING = regRecipeSerializer("cutting", ()-> new RecipeSerializer<>() {
	    @Override
		public MapCodec<CuttingBoardRecipe> codec() {
			return CuttingBoardRecipe.Serializer.codec();
		}

	    @Override
		public StreamCodec<RegistryFriendlyByteBuf, CuttingBoardRecipe> streamCodec() {
			return CuttingBoardRecipe.Serializer.streamCodec();
		}
    });

	public static final Supplier<RecipeSerializer<FoodServingRecipe>> FOOD_SERVING =
            regRecipeSerializer("food_serving", () -> FoodServingRecipe.SERIALIZER);
	public static final Supplier<RecipeSerializer<DoughRecipe>> DOUGH =
            regRecipeSerializer("dough", () -> DoughRecipe.SERIALIZER);

	public static void touch() {

	}
}
