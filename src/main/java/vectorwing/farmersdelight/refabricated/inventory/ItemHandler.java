package vectorwing.farmersdelight.refabricated.inventory;

import it.unimi.dsi.fastutil.ints.IntList;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.world.item.ItemStack;

public interface ItemHandler extends SlottedStorage<ItemVariant> {
	int getSlotCount();
	int getSlotLimit(int slot);

	ItemStack getStackInSlot(int slot);
	ItemStack insertItem(int slot, ItemStack stack, boolean simulate);
	ItemStack extractItem(int slot, int amount, boolean simulate);
	void setStackInSlot(int slot, ItemStack stack);

	boolean isItemValid(int slot, ItemStack stack);

	/**
	 * Only necessary if you are using RecipeWrapper.
	 * @return The indexes of all input slots.
	 */
	default IntList getInputSlotIndexes() {
		return IntList.of();
	}

	@Deprecated(forRemoval = true, since = "3.1.0")
	default void commitModifiedStacks() {}

	@Deprecated(forRemoval = true, since = "3.1.0")
	default ItemStack removeItem(int slot, int amount) {
		return extractItem(slot, amount, false);
	}

	@Deprecated(forRemoval = true, since = "3.1.0")
	default long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
		return getSlot(slot).insert(resource, maxAmount, transaction);
	}
	@Deprecated(forRemoval = true, since = "3.1.0")
	default long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
		return getSlot(slot).extract(resource, maxAmount, transaction);
	}
}