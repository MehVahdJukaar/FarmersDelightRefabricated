package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class VillagerTags extends FabricTagsProvider<VillagerTrade>
{
    public VillagerTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.builder(VillagerTradeTags.FARMER_LEVEL_1)
                .addOptional(VillagerTrades.FARMER_1_ONION_EMERALD)
                .addOptional(VillagerTrades.FARMER_1_TOMATO_EMERALD);
        this.builder(VillagerTradeTags.FARMER_LEVEL_2)
                .addOptional(VillagerTrades.FARMER_2_CABBAGE_EMERALD)
                .addOptional(VillagerTrades.FARMER_2_RICE_EMERALD);
        this.builder(VillagerTradeTags.WANDERING_TRADER_COMMON)
                .addOptional(VillagerTrades.WANDERING_TRADER_CABBAGE_EMERALD)
                .addOptional(VillagerTrades.WANDERING_TRADER_RICE_EMERALD).addOptional(VillagerTrades.WANDERING_TRADER_TOMATO_EMERALD)
                .addOptional(VillagerTrades.WANDERING_TRADER_ONION_EMERALD);
	}
}
