package vectorwing.farmersdelight.refabricated;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public enum ItemAbility implements StringRepresentable {
    //just contains stuff fd has. Could have been hardcoded, Hoping that keeping like this will make it easier to merge
    SHEARS_CARVE,SWORD_DIG; //just add needed ones, same names as neo so we can keep the recipe as is

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public static final Codec<ItemAbility> CODEC = StringRepresentable.fromEnum(ItemAbility::values);

    public boolean canPerformAction(@Nullable ItemStack stack) {
       //item ability -> tag
//TODO
    }
}
