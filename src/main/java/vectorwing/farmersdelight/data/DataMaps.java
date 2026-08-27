package vectorwing.farmersdelight.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import vectorwing.farmersdelight.common.datamap.MushroomColony;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModDataMaps;

/// There's something deeply funny about using Fabric's data generator for NeoForge data.
public class DataMaps implements DataProvider {
	private final FabricDataOutput output;

	public DataMaps(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		this.output = output;
	}

	private static final List<Consumer<BiConsumer<String, JsonElement>>> SUBMITTERS = List.of(
		DataMaps::colonies
	);

	private static void colonies(BiConsumer<String, JsonElement> consumer) {
		var map = new HashMap<Holder<Block>, MushroomColony>();
		map.put(Blocks.BROWN_MUSHROOM.builtInRegistryHolder(), new MushroomColony(ModBlocks.BROWN_MUSHROOM_COLONY.get()));
		map.put(Blocks.RED_MUSHROOM.builtInRegistryHolder(), new MushroomColony(ModBlocks.RED_MUSHROOM_COLONY.get()));
        consumer.accept("block/" + ModDataMaps.MUSHROOM_COLONIES.id().getPath(), ModDataMaps.MUSHROOM_COLONIES.mapCodec().encodeStart(JsonOps.INSTANCE, map).getOrThrow());
	}

	private static void collect(BiConsumer<String, JsonElement> consumer) {
		for (final var submitter : SUBMITTERS) {
			submitter.accept(consumer);
		}
	}

	@Override
	public CompletableFuture<?> run(CachedOutput cache) {
		final var elements = new HashMap<String, JsonElement>();

		collect((name, elem) -> {
			if (elements.put(name, elem) != null) {
				throw new IllegalArgumentException("An element with name " + name + " has already been added.");
			}
		});

		final var paths = this.output.createPathProvider(PackOutput.Target.DATA_PACK, "data_maps");

		return CompletableFuture.allOf(
			elements.entrySet().stream().map(x ->
					DataProvider.saveStable(
						cache,
						x.getValue(),
						paths.json(ResourceLocation.fromNamespaceAndPath("farmersdelight", x.getKey()))
					)
				)
				.toArray(CompletableFuture[]::new)
		);
	}

	@Override
	public String getName() {
		return "Farmer's Delight Data Maps";
	}
}