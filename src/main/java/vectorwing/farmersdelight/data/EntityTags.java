package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityTypeIds;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.refabricated.FDRefabricatedTags;

import java.util.concurrent.CompletableFuture;

public class EntityTags extends FabricTagsProvider.EntityTypeTagsProvider
{
	public EntityTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		builder(ModTags.EntityTypes.DOG_FOOD_USERS).add(EntityTypeIds.WOLF);
		builder(ModTags.EntityTypes.HORSE_FEED_USERS).add(
				EntityTypeIds.HORSE,
				EntityTypeIds.SKELETON_HORSE,
				EntityTypeIds.ZOMBIE_HORSE,
				EntityTypeIds.DONKEY,
				EntityTypeIds.MULE,
				EntityTypeIds.LLAMA);
		builder(ModTags.EntityTypes.HORSE_FEED_TEMPTED).add(
				EntityTypeIds.HORSE,
				EntityTypeIds.DONKEY,
				EntityTypeIds.MULE);
		builder(FDRefabricatedTags.EntityTypes.DROPS_LEATHER)
			.add(
				EntityTypeIds.COW,
				EntityTypeIds.MOOSHROOM,
				EntityTypeIds.HORSE,
				EntityTypeIds.DONKEY,
				EntityTypeIds.MULE,
				EntityTypeIds.LLAMA,
				EntityTypeIds.TRADER_LLAMA
			);
	}
}
