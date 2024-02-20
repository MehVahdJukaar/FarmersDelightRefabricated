package vectorwing.farmersdelight.common.event;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModItems;


public class VillagerEvents
{

	public static void addTrades(){
		if (Configuration.FARMERS_BUY_FD_CROPS.get()) {
			TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, (trades) -> {
				trades.add(emeraldForItemsTrade(ModItems.ONION.get(), 26, 16, 2));
				trades.add(emeraldForItemsTrade(ModItems.TOMATO.get(), 26, 16, 2));
			});

			TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, (trades) -> {
				trades.add(emeraldForItemsTrade(ModItems.CABBAGE.get(), 16, 16, 5));
				trades.add(emeraldForItemsTrade(ModItems.RICE.get(), 20, 16, 5));
			});
		}
		if (Configuration.WANDERING_TRADER_SELLS_FD_ITEMS.get()) {
			TradeOfferHelper.registerWanderingTraderOffers(0, (trades) -> {
				trades.add(itemForEmeraldTrade(ModItems.CABBAGE_SEEDS.get(), 1, 12));
				trades.add(itemForEmeraldTrade(ModItems.TOMATO_SEEDS.get(), 1, 12));
				trades.add(itemForEmeraldTrade(ModItems.RICE.get(), 1, 12));
				trades.add(itemForEmeraldTrade(ModItems.ONION.get(), 1, 12));
			});
		}
	}

	public static VillagerTrades.ItemListing emeraldForItemsTrade(ItemLike item, int count, int maxTrades, int xp) {
		return new BasicItemListing(new ItemStack(item, count), new ItemStack(Items.EMERALD), maxTrades, xp, 0.05F);
	}

	public static VillagerTrades.ItemListing itemForEmeraldTrade(ItemLike item, int maxTrades, int xp) {
		return new BasicItemListing(1, new ItemStack(item), maxTrades, xp, 0.05F);
	}
}
