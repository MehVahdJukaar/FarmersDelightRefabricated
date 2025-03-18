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
import net.minecraft.resources.ResourceLocation;
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
    private static Collection<Holder<Block>> earlyDropsCakeTag;
    private static Collection<Holder<EntityType<?>>> earlyDropsLeatherTag;

    private static final FileToIdConverter CONVERTER = FileToIdConverter.json("tags/entity_type");

    // This exists so we don't modify literally every loot table in the game just to add loot to a few
    public static boolean isCandleDropsCakeSliceTag(Holder<Block> block, HolderLookup<Block> lookup) {
        if (earlyDropsCakeTag == null) {
            TagLoader<Holder<Block>> loader = new TagLoader<>(rl -> lookup.get(ResourceKey.create(Registries.BLOCK, rl)), "tags/block");
            var dropsLeatherMap = loadTag(ModTags.DROPS_CAKE_SLICE);
            Map<ResourceLocation, Collection<Holder<Block>>> loaded = loader.build(dropsLeatherMap);
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
            var dropsLeatherMap = loadTag(ModTags.DROPS_LEATHER);
            Map<ResourceLocation, Collection<Holder<EntityType<?>>>> loaded = loader.build(dropsLeatherMap);
            earlyDropsLeatherTag = loaded.get(ModTags.DROPS_LEATHER.location());
            if (earlyDropsLeatherTag == null)
                earlyDropsLeatherTag = List.of();
        }

        return earlyDropsLeatherTag.contains(entityType);
    }

    private static <T> Map<ResourceLocation, List<TagLoader.EntryWithSource>> loadTag(TagKey<T> tagKey) {
        Map<ResourceLocation, List<TagLoader.EntryWithSource>> map = Maps.newHashMap();

        for(Map.Entry<ResourceLocation, List<Resource>> entry : resourceManager.listResourceStacks("tags/" + tagKey.registry().location() + "/" + tagKey.location().getPath() + ".json", resourceLocation -> tagKey.location().getNamespace().equals(resourceLocation.getNamespace())).entrySet()) {
            loadIndividualTag(entry, map);
        }

        return map;
    }

    private static void loadIndividualTag(Map.Entry<ResourceLocation, List<Resource>> entry, Map<ResourceLocation, List<TagLoader.EntryWithSource>> map) {
        ResourceLocation fileKey = entry.getKey();
        ResourceLocation fileToId = CONVERTER.fileToId(fileKey);

        for(Resource resource : entry.getValue()) {
            try (Reader reader = resource.openAsReader()) {
                JsonElement jsonElement = JsonParser.parseReader(reader);
                List<TagLoader.EntryWithSource> list = map.getOrDefault(fileToId, new ArrayList<>());
                TagFile tagFile = TagFile.CODEC.parse(new Dynamic<>(JsonOps.INSTANCE, jsonElement)).getOrThrow();
                if (tagFile.replace()) {
                    list.clear();
                }

                tagFile.entries().forEach((tagEntry) -> {
                    // Return value is unused, this was the easiest way to determine whether this was a tag or not.
                    tagEntry.verifyIfPresent(resourceLocation -> {
                        list.add(new TagLoader.EntryWithSource(tagEntry, resource.sourcePackId()));
                        return false;
                    }, resourceLocation -> {
                        for(Map.Entry<ResourceLocation, List<Resource>> innerEntry : resourceManager.listResourceStacks(CONVERTER.idToFile(resourceLocation).getPath(), resourceLocation1 -> resourceLocation1.getNamespace().equals(resourceLocation.getNamespace())).entrySet()) {
                            loadIndividualTag(innerEntry, map);
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
    }

    public static void setLootTableResourceManager(ResourceManager manager) {
        resourceManager = manager;
    }

    public static void resetEarlyTagCollections() {
        earlyDropsCakeTag = null;
        earlyDropsLeatherTag = null;
    }
}