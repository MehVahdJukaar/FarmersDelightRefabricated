package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class EntityTags extends FabricTagsProvider.EntityTypeTagsProvider
{
	public EntityTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
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
	}
}
