package vectorwing.farmersdelight.integration.rrv.decomposition;

import cc.cassian.rrv.api.recipe.ReliableServerRecipe;
import cc.cassian.rrv.api.recipe.ReliableServerRecipeType;
import net.minecraft.nbt.CompoundTag;
import vectorwing.farmersdelight.FarmersDelight;

public class DecompositionServerRecipe implements ReliableServerRecipe {


        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final ReliableServerRecipeType<DecompositionServerRecipe> TYPE = ReliableServerRecipeType.register(
                FarmersDelight.id("decomposition"),
                () -> new DecompositionServerRecipe()
        );

        @Override
        public void writeToTag(CompoundTag tag) {

        }

        @Override
        public void loadFromTag(CompoundTag tag) {

        }

        @Override
        public ReliableServerRecipeType<? extends DecompositionServerRecipe> getRecipeType() {
            return TYPE;
        }
}
