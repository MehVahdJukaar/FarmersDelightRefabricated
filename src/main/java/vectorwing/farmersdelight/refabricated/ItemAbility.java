package vectorwing.farmersdelight.refabricated;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.tag.CommonTags;

import java.util.Locale;

public enum ItemAbility implements StringRepresentable {
	//just contains stuff fd has. Could have been hardcoded, Hoping that keeping like this will make it easier to merge
	SWORD_DIG, SHOVEL_DIG, PICKAXE_DIG,
	SHEARS_CARVE, SHEARS_DIG, SHEARS_HARVEST,
	AXE_DIG, AXE_STRIP,
	HOE_DIG,
	KNIFE_DIG, KNIFE_HARVEST; //just add needed ones, same names as neo so we can keep the recipe as is

	@Override
	public String getSerializedName() {
		return this.name().toLowerCase(Locale.ROOT);
	}

	public static final Codec<ItemAbility> CODEC = StringRepresentable.fromEnum(ItemAbility::values);

	public boolean canPerformAction(@NotNull ItemStack stack) {
		//item ability -> tag

		return switch (this) {
			case SHEARS_CARVE, SHEARS_HARVEST, SHEARS_DIG -> stack.is(ConventionalItemTags.SHEARS);
			case SWORD_DIG -> stack.is(ItemTags.SWORDS);
			case SHOVEL_DIG -> stack.is(ItemTags.SHOVELS);
			case PICKAXE_DIG -> stack.is(ItemTags.PICKAXES);
			case AXE_DIG, AXE_STRIP -> stack.is(ItemTags.AXES);
			case HOE_DIG -> stack.is(ItemTags.HOES);
			case KNIFE_DIG, KNIFE_HARVEST -> stack.is(CommonTags.Items.TOOLS_KNIVES);
		};
	}
}