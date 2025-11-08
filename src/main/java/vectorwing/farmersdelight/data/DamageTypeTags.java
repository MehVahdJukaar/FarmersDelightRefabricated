package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTags extends FabricTagProvider<DamageType>
{
	public DamageTypeTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, Registries.DAMAGE_TYPE, lookupProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		getOrCreateTagBuilder(net.minecraft.tags.DamageTypeTags.IS_FIRE).add(ModDamageTypes.STOVE_BURN);
        getOrCreateTagBuilder(net.minecraft.tags.DamageTypeTags.NO_KNOCKBACK).add(ModDamageTypes.STOVE_BURN);
        getOrCreateTagBuilder(net.minecraft.tags.DamageTypeTags.BURN_FROM_STEPPING).add(ModDamageTypes.STOVE_BURN);
        getOrCreateTagBuilder(net.minecraft.tags.DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES).add(ModDamageTypes.STOVE_BURN);
	}
}
