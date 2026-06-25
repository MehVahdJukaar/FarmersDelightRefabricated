package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.refabricated.FDRefabricatedTags;

import java.util.concurrent.CompletableFuture;

public class EntityTags extends FabricTagProvider.EntityTypeTagProvider
{
	public EntityTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		valueLookupBuilder(ModTags.EntityTypes.DOG_FOOD_USERS).add(EntityType.WOLF);
		valueLookupBuilder(ModTags.EntityTypes.HORSE_FEED_USERS).add(
				EntityType.HORSE,
				EntityType.SKELETON_HORSE,
				EntityType.ZOMBIE_HORSE,
				EntityType.DONKEY,
				EntityType.MULE,
				EntityType.LLAMA);
		valueLookupBuilder(ModTags.EntityTypes.HORSE_FEED_TEMPTED).add(
				EntityType.HORSE,
				EntityType.DONKEY,
				EntityType.MULE);
		valueLookupBuilder(FDRefabricatedTags.EntityTypes.DROPS_LEATHER)
			.add(
				EntityType.COW,
				EntityType.MOOSHROOM,
				EntityType.HORSE,
				EntityType.DONKEY,
				EntityType.MULE,
				EntityType.LLAMA,
				EntityType.TRADER_LLAMA
			);
	}
}
