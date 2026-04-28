package vectorwing.farmersdelight.refabricated;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagFile;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TagLoader;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TagUtils {
    private static ResourceManager resourceManager;
    // Vanilla loads tags after Loot Tables are loaded, so we need to do something about that.
    private static List<Holder<Block>> earlyDropsCakeTag;
    private static List<Holder<EntityType<?>>> earlyDropsLeatherTag;

    // This exists so we don't modify literally every loot table in the game just to add loot to a few
    public static boolean isCandleDropsCakeSliceTag(Holder<Block> block, HolderLookup<Block> lookup) {
        if (earlyDropsCakeTag == null) {
            TagLoader<Holder<Block>> loader = new TagLoader<>((rl, bl) -> lookup.get(ResourceKey.create(Registries.BLOCK, rl)), "tags/block");
            Map<Identifier, List<TagLoader.EntryWithSource>> dropsLeatherMap = loadTag(ModTags.DROPS_CAKE_SLICE);
            Map<Identifier, List<Holder<Block>>> loaded = loader.build(dropsLeatherMap);
            earlyDropsCakeTag = loaded.get(ModTags.DROPS_CAKE_SLICE.location());
            if (earlyDropsCakeTag == null)
                earlyDropsCakeTag = List.of();
        }

        return earlyDropsCakeTag.contains(block);
    }

    // This exists so we don't modify literally every loot table in the game just to add loot to a few
    public static boolean isDropsLeatherTag(Holder<EntityType<?>> entityType, HolderLookup<EntityType<?>> lookup) {
        if (earlyDropsLeatherTag == null) {
            TagLoader<Holder<EntityType<?>>> loader = new TagLoader<>(rl -> lookup.get(ResourceKey.create(Registries.ENTITY_TYPE, rl)), "tags/entity_type");
            var dropsLeatherMap = loadTag(FDRefabricatedTags.EntityTypes.DROPS_LEATHER);
            Map<ResourceLocation, Collection<Holder<EntityType<?>>>> loaded = loader.build(dropsLeatherMap);
            earlyDropsLeatherTag = loaded.get(FDRefabricatedTags.EntityTypes.DROPS_LEATHER.location());
            if (earlyDropsLeatherTag == null)
                earlyDropsLeatherTag = List.of();
        }

        return earlyDropsLeatherTag.contains(entityType);
    }

    public static <T> Map<Identifier, List<TagLoader.EntryWithSource>> loadTag(TagKey<T> tagKey) {
        Map<Identifier, List<TagLoader.EntryWithSource>> map = Maps.newHashMap();
        String tagRegistryLocation = (tagKey.registry().identifier().getNamespace().equals(Identifier.DEFAULT_NAMESPACE) ? "" : tagKey.registry().identifier().getNamespace() + "/")  + tagKey.registry().identifier().getPath();
        Identifier jsonPath = Identifier.fromNamespaceAndPath(tagKey.location().getNamespace(), "tags/" +
                tagRegistryLocation + "/" + tagKey.location().getPath() + ".json");

        for (Resource entry : resourceManager.getResourceStack(jsonPath)) {
            loadIndividualTag(tagRegistryLocation, jsonPath, entry, map);
        }

        return map;
    }

    private static void loadIndividualTag(String tagRegistryLocation, Identifier fileLocation, Resource resource, Map<Identifier, List<TagLoader.EntryWithSource>> map) {
        FileToIdConverter converter = FileToIdConverter.json("tags/" + tagRegistryLocation);
        Identifier fileToId = converter.fileToId(fileLocation);
        try (Reader reader = resource.openAsReader()) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            List<TagLoader.EntryWithSource> list = map.getOrDefault(fileToId, new ArrayList<>());
            TagFile tagFile = TagFile.CODEC.parse(new Dynamic<>(JsonOps.INSTANCE, jsonElement)).getOrThrow();
            if (tagFile.replace()) {
                list.clear();
            }

            tagFile.entries().forEach((tagEntry) -> {
                // Return value is unused, this was the easiest way to determine whether this was a tag or not.
                tagEntry.verifyIfPresent(Identifier -> {
                    list.add(new TagLoader.EntryWithSource(tagEntry, resource.sourcePackId()));
                    return false;
                }, Identifier -> {
                    for (Resource innerEntry : resourceManager.getResourceStack(converter.idToFile(Identifier))) {
                        loadIndividualTag(tagRegistryLocation, Identifier, innerEntry, map);
                    }
                    list.add(new TagLoader.EntryWithSource(tagEntry, resource.sourcePackId()));
                    return false;
                });
            });
            map.putIfAbsent(fileToId, list);
        } catch (Exception ignored) {
            // The game should throw an exception itself upon failure.

        }
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