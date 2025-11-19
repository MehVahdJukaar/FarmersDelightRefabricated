package vectorwing.farmersdelight.common.event;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModItems;

public class VillagerEvents
{
	public static void init() {
		onVillagerTrades();
		onWandererTrades();
	}

	public static void onVillagerTrades() {
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, (trades) -> {
			trades.add(emeraldForItemsTrade(ModItems.ONION.get(), 26, 16, 2, Configuration.FARMERS_BUY_FD_CROPS));
			trades.add(emeraldForItemsTrade(ModItems.TOMATO.get(), 26, 16, 2, Configuration.FARMERS_BUY_FD_CROPS));
		});

		TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, (trades) -> {
			trades.add(emeraldForItemsTrade(ModItems.CABBAGE.get(), 16, 16, 5, Configuration.FARMERS_BUY_FD_CROPS));
			trades.add(emeraldForItemsTrade(ModItems.RICE.get(), 20, 16, 5, Configuration.FARMERS_BUY_FD_CROPS));
		});
	}

	/**
	 * Increased the trade amount of the Wandering Trader's trades.
	 * Additionally removed Onions from the trade pool because the WT only sells seeds.
	 */
	public static void onWandererTrades() {
			TradeOfferHelper.registerWanderingTraderOffers(trades -> {
				trades.addOffersToPool(TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,
						itemForEmeraldTrade(ModItems.CABBAGE_SEEDS.get(), 12, 1, Configuration.WANDERING_TRADER_SELLS_FD_ITEMS),
						itemForEmeraldTrade(ModItems.TOMATO_SEEDS.get(), 12, 1, Configuration.WANDERING_TRADER_SELLS_FD_ITEMS),
						itemForEmeraldTrade(ModItems.RICE.get(), 12, 1, Configuration.WANDERING_TRADER_SELLS_FD_ITEMS));
			});
	}

	public static VillagerTrades.ItemListing emeraldForItemsTrade(ItemLike item, int count, int maxTrades, int xp, Supplier<Boolean> predicate) {
		return new FDItemListing(new VillagerTrades.EmeraldForItems(item, count, maxTrades, xp), predicate);
	}

	public static VillagerTrades.ItemListing itemForEmeraldTrade(ItemLike item, int maxTrades, int xp, Supplier<Boolean> predicate) {
		return new FDItemListing(new VillagerTrades.ItemsForEmeralds(new ItemStack(item), 1, 1, maxTrades, xp, 0.05F), predicate);
	}

	private static record FDItemListing(VillagerTrades.ItemListing listing, Supplier<Boolean> predicate) implements VillagerTrades.ItemListing {

		@Override
		public MerchantOffer getOffer(ServerLevel serverLevel, Entity trader, RandomSource random) {
			if (!predicate.get()) return null;
			return listing.getOffer(serverLevel, trader, random);
		}
	}
}
