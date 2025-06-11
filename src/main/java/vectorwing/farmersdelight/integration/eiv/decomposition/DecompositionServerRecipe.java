package vectorwing.farmersdelight.integration.eiv.decomposition;

import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import net.minecraft.nbt.CompoundTag;
import vectorwing.farmersdelight.FarmersDelight;

public class DecompositionServerRecipe implements IEivServerRecipe {


        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final EivRecipeType<DecompositionServerRecipe> TYPE = EivRecipeType.register(
                FarmersDelight.res("decomposition"),
                () -> new DecompositionServerRecipe()
        );

        @Override
        public void writeToTag(CompoundTag tag) {

        }

        @Override
        public void loadFromTag(CompoundTag tag) {

        }

        @Override
        public EivRecipeType<? extends DecompositionServerRecipe> getRecipeType() {
            return TYPE;
        }
}
