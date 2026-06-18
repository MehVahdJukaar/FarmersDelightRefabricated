package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.concurrent.CompletableFuture;

public class EnchantmentTags extends FabricTagsProvider<Enchantment>
{
	public EnchantmentTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, Registries.ENCHANTMENT, provider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(net.minecraft.tags.EnchantmentTags.NON_TREASURE).add(ModEnchantments.BACKSTABBING);
	}
}
