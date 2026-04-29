package vectorwing.farmersdelight.refabricated.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import vectorwing.farmersdelight.refabricated.FDRefabricatedTags;

import java.util.concurrent.CompletableFuture;

public class RefabricatedMobEffectTags extends FabricTagsProvider<MobEffect>
{
	public RefabricatedMobEffectTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, Registries.MOB_EFFECT, lookupProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		builder(FDRefabricatedTags.MobEffects.MILK_BOTTLE_IGNORED);
		builder(FDRefabricatedTags.MobEffects.HOT_COCOA_IGNORED);
	}
}
