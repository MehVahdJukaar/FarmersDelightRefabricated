package vectorwing.farmersdelight.common.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

public class VillagerEvents
{
	public static void init() {
        onVillagerTrades();
        onWandererTrades();
	}

	public static void onVillagerTrades() {
		if (!Configuration.ENABLE_FARMERS_BUY_FD_CROPS.get()) return;

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, (trades) -> {
            trades.add(emeraldForItemsTrade(ModItems.ONION.get(), 26, 16, 2, Configuration.ENABLE_FARMERS_BUY_FD_CROPS));
            trades.add(emeraldForItemsTrade(ModItems.TOMATO.get(), 26, 16, 2, Configuration.ENABLE_FARMERS_BUY_FD_CROPS));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, (trades) -> {
            trades.add(emeraldForItemsTrade(ModItems.CABBAGE.get(), 16, 16, 5, Configuration.ENABLE_FARMERS_BUY_FD_CROPS));
            trades.add(emeraldForItemsTrade(ModItems.RICE.get(), 20, 16, 5, Configuration.ENABLE_FARMERS_BUY_FD_CROPS));
        });
    }

	public static void onWandererTrades() {
		TradeOfferHelper.registerWanderingTraderOffers(1, (trades) -> {
			if (Configuration.ENABLE_WANDERING_TRADER_SELLS_FD_ITEMS.get()) {

				trades.add(itemForEmeraldTrade(ModItems.CABBAGE_SEEDS.get(), 1, 12, Configuration.ENABLE_WANDERING_TRADER_SELLS_FD_ITEMS));
				trades.add(itemForEmeraldTrade(ModItems.TOMATO_SEEDS.get(), 1, 12, Configuration.ENABLE_WANDERING_TRADER_SELLS_FD_ITEMS));
				trades.add(itemForEmeraldTrade(ModItems.RICE.get(), 1, 12, Configuration.ENABLE_WANDERING_TRADER_SELLS_FD_ITEMS));
				trades.add(itemForEmeraldTrade(ModItems.ONION.get(), 1, 12, Configuration.ENABLE_WANDERING_TRADER_SELLS_FD_ITEMS));
			}
		});
	}

	public static VillagerTrades.ItemListing emeraldForItemsTrade(ItemLike item, int count, int maxTrades, int xp, Supplier<Boolean> predicate) {
		return new FDItemListing(new VillagerTrades.EmeraldForItems(item, count, maxTrades, xp), predicate);
	}

	public static VillagerTrades.ItemListing itemForEmeraldTrade(ItemLike item, int maxTrades, int xp, Supplier<Boolean> predicate) {
		return new FDItemListing(new VillagerTrades.ItemsForEmeralds(new ItemStack(item), 1, 1, maxTrades, xp, 0.05F), predicate);
	}

    private record FDItemListing(VillagerTrades.ItemListing listing, Supplier<Boolean> predicate) implements VillagerTrades.ItemListing {
        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            if (!predicate.get())
                return null;

            return listing.getOffer(trader, random);
        }
    }
}
