package vectorwing.farmersdelight.integration.rrv.info;

import cc.cassian.rrv.api.recipe.ReliableServerRecipe;
import cc.cassian.rrv.api.recipe.ReliableServerRecipeType;
import net.minecraft.nbt.CompoundTag;
import vectorwing.farmersdelight.FarmersDelight;

public class InfoServerRecipe implements ReliableServerRecipe {


        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final ReliableServerRecipeType<InfoServerRecipe> TYPE = ReliableServerRecipeType.register(
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
        public ReliableServerRecipeType<? extends InfoServerRecipe> getRecipeType() {
            return TYPE;
        }
}
