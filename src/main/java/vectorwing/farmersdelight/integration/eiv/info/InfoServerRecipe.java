package vectorwing.farmersdelight.integration.eiv.info;

import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import net.minecraft.nbt.CompoundTag;
import vectorwing.farmersdelight.FarmersDelight;

public class InfoServerRecipe implements IEivServerRecipe {


        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final EivRecipeType<InfoServerRecipe> TYPE = EivRecipeType.register(
                FarmersDelight.res("info"),
                () -> new InfoServerRecipe()
        );

        @Override
        public void writeToTag(CompoundTag tag) {

        }

        @Override
        public void loadFromTag(CompoundTag tag) {

        }

        @Override
        public EivRecipeType<? extends InfoServerRecipe> getRecipeType() {
            return TYPE;
        }
}
