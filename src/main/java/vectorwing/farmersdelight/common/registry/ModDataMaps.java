package vectorwing.farmersdelight.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.datamap.CraftRemainderOverride;
import vectorwing.farmersdelight.common.datamap.MushroomColony;
import vectorwing.farmersdelight.common.utility.RecipeUtils;
import vectorwing.farmersdelight.refabricated.datamap.RefabricatedDataMapType;

public class ModDataMaps
{
	public static final RefabricatedDataMapType<Block, MushroomColony> MUSHROOM_COLONIES = new RefabricatedDataMapType<>(
		RecipeUtils.FDLocation("mushroom_colonies"), Registries.BLOCK, MushroomColony.CODEC, MushroomColony.MUSHROOM_COLONY_CODEC, false);
	public static final RefabricatedDataMapType<Item, CraftRemainderOverride> CRAFT_REMAINDER_OVERRIDES = new RefabricatedDataMapType<>(
		RecipeUtils.FDLocation("craft_remainder_overrides"), Registries.ITEM, CraftRemainderOverride.CODEC, CraftRemainderOverride.CRAFT_REMAINDER_CODEC, false);

}
