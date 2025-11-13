package vectorwing.farmersdelight.data.tools;

/*
 *  BluSunrize
 *  Copyright (c) 2021
 *
 *  This code is licensed under "Blu's License of Common Sense".
 *  Class written by malte0811 and BluSunrize, and used with malte's permission.
 */

import com.google.common.hash.Hashing;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerUpper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.SharedConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

// Don't think this one will work.
public class StructureUpdater implements DataProvider
{
	private final Identifier id;
	private final FabricDataOutput output;
	private final PackResources resources;

	public StructureUpdater(
			FabricDataOutput output,
			String basePath, String modid
	) {
		this.id = Identifier.fromNamespaceAndPath(modid, basePath);
		this.output = output;
		this.resources = new PathPackResources(
				new PackLocationInfo("fdrf/temp", Component.literal("temp"), PackSource.BUILT_IN, Optional.empty()),
				output.createRegistryElementsPathProvider(Registries.STRUCTURE).file(id, ".json")
		);
	}

	@Override
	public CompletableFuture<?> run(@NotNull CachedOutput cache) {
		try {

			Resource resource = new Resource(resources, resources.getResource(PackType.SERVER_DATA, id));
			process(id, resource, cache);
			return CompletableFuture.completedFuture(null);
		}
		catch (IOException x) {
			return CompletableFuture.failedFuture(x);
		}
	}

	private void process(Identifier loc, Resource resource, CachedOutput cache) throws IOException {
		CompoundTag inputNBT = NbtIo.readCompressed(resource.open(), NbtAccounter.unlimitedHeap());
		CompoundTag converted = updateNBT(inputNBT);
		if (!converted.equals(inputNBT)) {
			Class<? extends DataFixer> fixerClass = DataFixers.getDataFixer().getClass();
			if (!fixerClass.equals(DataFixerUpper.class))
				throw new RuntimeException("Structures are not up to date, but unknown data fixer is in use: " + fixerClass.getName());
			writeNBTTo(loc, converted, cache);
		}
	}

	private void writeNBTTo(Identifier loc, CompoundTag data, CachedOutput cache) throws IOException {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		NbtIo.writeCompressed(data, bytearrayoutputstream);
		byte[] bytes = bytearrayoutputstream.toByteArray();
		Path outputPath = output.getOutputFolder().resolve("data/" + loc.getNamespace() + "/" + loc.getPath());
		cache.writeIfNeeded(outputPath, bytes, Hashing.sha1().hashBytes(bytes));
	}

	private static CompoundTag updateNBT(CompoundTag nbt) {
		final CompoundTag updatedNBT = DataFixTypes.STRUCTURE.updateToCurrentVersion(
				DataFixers.getDataFixer(), nbt, nbt.getIntOr("DataVersion", SharedConstants.getCurrentVersion().dataVersion().version())
		);
		StructureTemplate template = new StructureTemplate();
		template.load(BuiltInRegistries.BLOCK.filterFeatures(FeatureFlags.DEFAULT_FLAGS), updatedNBT);
		return template.save(new CompoundTag());
	}

	@NotNull
	@Override
	public String getName() {
		return "Update structure files in " + id.toString();
	}
}