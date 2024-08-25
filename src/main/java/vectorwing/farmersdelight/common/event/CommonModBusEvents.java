package vectorwing.farmersdelight.common.event;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.FoodValues;

public class CommonModBusEvents
{
	public static void init() {
		DefaultItemComponentEvents.MODIFY.register(CommonModBusEvents::onModifyDefaultComponents);
	}

	public static void onModifyDefaultComponents(DefaultItemComponentEvents.ModifyContext context) {
		if (Configuration.ENABLE_STACKABLE_SOUP_ITEMS.get()) {
			Configuration.SOUP_ITEM_LIST.get().forEach((key) -> {
				Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(key));
				context.modify(item, (builder) -> builder.set(DataComponents.MAX_STACK_SIZE, 16));
			});
		}
		if (Configuration.RABBIT_STEW_BUFF.get()) {
			context.modify(Items.RABBIT_STEW, (builder) -> builder.set(DataComponents.FOOD, FoodValues.RABBIT_STEW_BUFF));
		}
	}
}
