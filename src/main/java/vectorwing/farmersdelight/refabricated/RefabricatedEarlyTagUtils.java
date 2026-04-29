package vectorwing.farmersdelight.refabricated;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TagLoader;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.refabricated.duck.TagLoaderSpecificResourceDuck;

import java.util.*;

// Average Chrys witch-craft class.
public class RefabricatedEarlyTagUtils {
    private static ResourceManager resourceManager;
    // Vanilla loads tags after Loot Tables are loaded, so we need to do something about that.
    private static Collection<Holder<Block>> earlyDropsCakeTag;
    private static Collection<Holder<EntityType<?>>> earlyDropsLeatherTag;

    // This exists so we don't modify literally every loot table in the game just to add loot to a few
    public static boolean isCandleDropsCakeSliceTag(Holder<Block> block, HolderLookup<Block> lookup) {
        if (earlyDropsCakeTag == null) {
            TagLoader<Holder<Block>> loader = new TagLoader<>((rl, bl) -> lookup.get(ResourceKey.create(Registries.BLOCK, rl)), "tags/block");

			var loadedTag = loadTagEarly(loader, ModTags.Blocks.DROPS_CAKE_SLICE);

            earlyDropsCakeTag = loadedTag.get(ModTags.Blocks.DROPS_CAKE_SLICE.location());
            if (earlyDropsCakeTag == null) {
				earlyDropsCakeTag = Collections.emptySet();
			}
        }

        return earlyDropsCakeTag.contains(block);
    }

    // This exists so we don't modify literally every loot table in the game just to add loot to a few
    public static boolean isDropsLeatherTag(Holder<EntityType<?>> entityType, HolderLookup<EntityType<?>> lookup) {
        if (earlyDropsLeatherTag == null) {
            TagLoader<Holder<EntityType<?>>> loader = new TagLoader<>((id, required) -> lookup.get(ResourceKey.create(Registries.ENTITY_TYPE, id)), "tags/entity_type");

			var loadedTag = loadTagEarly(loader, FDRefabricatedTags.EntityTypes.DROPS_LEATHER);

            earlyDropsLeatherTag = loadedTag.get(FDRefabricatedTags.EntityTypes.DROPS_LEATHER.location());
			if (earlyDropsCakeTag == null) {
				earlyDropsCakeTag = Collections.emptySet();
			}
        }

        return earlyDropsLeatherTag.contains(entityType);
    }

	// Do it this way to ensure that 'fabric:remove' values are handled correctly.
    private static <T> Map<Identifier, List<Holder<T>>> loadTagEarly(TagLoader<Holder<T>> tagLoader, TagKey<T> tagKey) {
        String tagRegistryLocation = (tagKey.registry().identifier().getNamespace().equals(Identifier.DEFAULT_NAMESPACE) ? "" : tagKey.registry().identifier().getNamespace() + "/")  + tagKey.registry().identifier().getPath();
		Identifier tagPath = Identifier.fromNamespaceAndPath(tagKey.location().getNamespace(), "tags/" +
                tagRegistryLocation + "/" + tagKey.location().getPath() + ".json");

		((TagLoaderSpecificResourceDuck) tagLoader).fdrf$setSingleTag(tagPath);
		Map<Identifier, List<TagLoader.EntryWithSource>> loaded = tagLoader.load(resourceManager);
		return tagLoader.build(loaded);
	}

    public static void setLootTableResourceManager(ResourceManager manager) {
        resourceManager = manager;
    }

    public static void resetEarlyTagCollections() {
        resourceManager = null;
        earlyDropsCakeTag = null;
        earlyDropsLeatherTag = null;
    }
}