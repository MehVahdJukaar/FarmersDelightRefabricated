package vectorwing.farmersdelight.data;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.*;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import vectorwing.farmersdelight.data.advancement.FDAdvancementGenerator;
import vectorwing.farmersdelight.common.registry.ModBiomeModifiers;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.world.WildCropGeneration;
import vectorwing.farmersdelight.data.loot.FDBlockLoot;
import vectorwing.farmersdelight.data.loot.FDChestLoot;
import vectorwing.farmersdelight.data.tools.StructureUpdater;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataGenerators implements DataGeneratorEntrypoint
{
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(DynamicRegistryProvider::new);
		BlockTags blockTags = pack.addProvider(BlockTags::new);
		pack.addProvider((output, registriesFuture) -> new ItemTags(output, registriesFuture, blockTags));
		pack.addProvider(EntityTags::new);
		pack.addProvider(DamageTypeTags::new);
		pack.addProvider(EnchantmentTags::new);
		pack.addProvider(Recipes::new);
		pack.addProvider(FDAdvancementGenerator::new);
		pack.addProvider(FDBlockLoot::new);
//		pack.addProvider((output, registriesFuture) -> new StructureUpdater(output, "structures/village/houses", FarmersDelight.MODID));
	}


	private static class DynamicRegistryProvider extends FabricDynamicRegistryProvider {

		public DynamicRegistryProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
			super(output, lookup);
		}

		@Override
		protected void configure(HolderLookup.Provider registries, Entries entries) {
			ModEnchantments.bootstrap(createContext(registries, entries));
		}

		private static <T> BootstrapContext<T> createContext(HolderLookup.Provider registries, Entries entries) {
			return new BootstrapContext<>() {
				@Override
				public Holder.Reference<T> register(ResourceKey<T> resourceKey, T object, Lifecycle lifecycle) {
					return (Holder.Reference<T>) entries.add(resourceKey, object);
				}

				@Override
				public <S> HolderGetter<S> lookup(ResourceKey<? extends Registry<? extends S>> resourceKey) {
					return registries.lookupOrThrow(resourceKey);
				}
			};
		}

		@Override
		public @NotNull String getName() {
			return "Dynamic Registries";
		}
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.DAMAGE_TYPE, bootstrapContext -> bootstrapContext.register(ModDamageTypes.STOVE_BURN,
			new DamageType("farmersdelight.stove", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1F, DamageEffects.BURNING)));
		registryBuilder.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);
	}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, WildCropGeneration::bootstrapConfiguredFeatures)
			.add(Registries.PLACED_FEATURE, WildCropGeneration::bootstrapPlacedFeatures)
			.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrapBiomeModifiers)
			.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrapDamageTypes)
			.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);
		DatapackBuiltinEntriesProvider datapackProvider = new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), registrySetBuilder, Set.of(FarmersDelight.MODID));
		CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
		generator.addProvider(event.includeServer(), datapackProvider);

		BlockTags blockTags = new BlockTags(output, lookupProvider, helper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new ItemTags(output, lookupProvider, blockTags.contentsGetter(), helper));
		generator.addProvider(event.includeServer(), new EntityTags(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new DamageTypeTags(output, lookupProvider, FarmersDelight.MODID, helper));
		generator.addProvider(event.includeServer(), new EnchantmentTags(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new Recipes(output, lookupProvider));
		generator.addProvider(event.includeServer(), new LootModifiers(output, lookupProvider));
		generator.addProvider(event.includeServer(), new DataMaps(output, lookupProvider));
		generator.addProvider(event.includeServer(), new Advancements(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(), List.of(
			new LootTableProvider.SubProviderEntry(FDBlockLoot::new, LootContextParamSets.BLOCK),
			new LootTableProvider.SubProviderEntry(FDChestLoot::new, LootContextParamSets.CHEST)
		), lookupProvider));
		generator.addProvider(event.includeServer(), new StructureUpdater("structures/village/houses", FarmersDelight.MODID, helper, output));

		BlockStates blockStates = new BlockStates(output, helper);
		generator.addProvider(event.includeClient(), blockStates);
		generator.addProvider(event.includeClient(), new ItemModels(output, blockStates.models().existingFileHelper));
		generator.addProvider(event.includeClient(), new SoundDefinitions(output, helper));
	}
}
