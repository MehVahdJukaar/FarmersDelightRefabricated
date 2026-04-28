package vectorwing.farmersdelight.common.loot.modifier;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jspecify.annotations.NonNull;
import vectorwing.farmersdelight.refabricated.LootModifier;

public class ReplaceItemModifier extends LootModifier
{

	private final Item removedItem;
	private final Item addedItem;
	private final int addedCount;

	/**
	 * This loot modifier removes all instances of the specified item, replacing it by another specified addition.
	 */
	public ReplaceItemModifier(LootItemCondition[] conditions, Item removedItem, Item addedItem, int addedCount) {
		super(conditions);
		this.removedItem = removedItem;
		this.addedItem = addedItem;
		this.addedCount = addedCount;
	}

	@Override
	protected @NonNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		ItemStack addedStack = new ItemStack(addedItem, addedCount);

		generatedLoot.forEach((item) -> {
			if (item.is(removedItem)) {
				generatedLoot.remove(item);
			}
		});

		if (addedStack.getCount() < addedStack.getMaxStackSize()) {
			generatedLoot.add(addedStack);
		} else {
			int i = addedStack.getCount();

			while (i > 0) {
				ItemStack subStack = addedStack.copy();
				subStack.setCount(Math.min(addedStack.getMaxStackSize(), i));
				i -= subStack.getCount();
				generatedLoot.add(subStack);
			}
		}

		return generatedLoot;
	}

}
