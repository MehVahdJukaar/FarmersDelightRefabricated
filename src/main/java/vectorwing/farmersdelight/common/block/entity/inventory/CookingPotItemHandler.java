package vectorwing.farmersdelight.common.block.entity.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleItemStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandler;

import java.util.Iterator;

public class CookingPotItemHandler implements ItemHandler {
	private static final int SLOTS_INPUT = 6;
	private static final int SLOT_CONTAINER_INPUT = 7;
	private static final int SLOT_MEAL_OUTPUT = 8;
	private final ItemStackHandler itemHandler;
	private final Direction side;

	public CookingPotItemHandler(ItemStackHandler itemHandler, @Nullable Direction side) {
		this.itemHandler = itemHandler;
		this.side = side;
	}

	public boolean isItemValid(int slot, @NotNull ItemStack stack) {
		return itemHandler.isItemValid(slot, stack);
	}

	@Override
	public int getSlotCount() {
		return itemHandler.getSlotCount();
	}

	@Override
	public SingleSlotStorage<ItemVariant> getSlot(int slot) {
		return itemHandler.getSlot(slot);
	}

	@NotNull
	public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT ? itemHandler.insertItem(slot, stack, simulate) : stack;
		}
		return slot == SLOT_CONTAINER_INPUT ? itemHandler.insertItem(slot, stack, simulate) : stack;
	}

	@NotNull
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT ? itemHandler.removeItem(slot, amount, simulate) : ItemStack.EMPTY;
		}
		return slot == SLOT_MEAL_OUTPUT ? itemHandler.removeItem(slot, amount, simulate) : ItemStack.EMPTY;
	}

	@Override
	public int getSlotLimit(int slot) {
		return itemHandler.getSlotLimit(slot);
	}

	// Fabric
	@Override
	public @NotNull ItemStack getStackInSlot(int slot) {
		return itemHandler.getStackInSlot(slot);
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		this.itemHandler.setStackInSlot(slot, stack);
	}

	@Override
	public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		StoragePreconditions.notBlankNotNegative(resource, maxAmount);
		long inserted = 0;
		Iterator<SingleItemStorage> itr = itemHandler.getInsertableSlotsFor(resource);
		while (itr.hasNext()) {
			SingleItemStorage slot = itr.next();
			inserted += slot.insert(resource, maxAmount - inserted, transaction);
			if (inserted >= maxAmount)
				break;
		}
		return inserted;
	}

	@Override
	public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		StoragePreconditions.notBlankNotNegative(resource, maxAmount);
		long extracted = 0;
		for (SingleItemStorage slot : itemHandler.getSlotsContaining(resource.getItem())) {
			extracted += slot.extract(resource, maxAmount - extracted, transaction);
			if (extracted >= maxAmount)
				break;
		}
		return extracted;
	}

	private boolean isValidInputSlot(int slot, boolean output) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT;
		}
		return output ? slot == SLOT_MEAL_OUTPUT : slot == SLOT_CONTAINER_INPUT;
	}

	@Override
	public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT ? itemHandler.insertSlot(slot, resource, maxAmount, transaction) : 0;
		}
		return slot == SLOT_CONTAINER_INPUT ? itemHandler.insertSlot(slot, resource, maxAmount, transaction) : 0;
	}

	@Override
	public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT ? itemHandler.extractSlot(slot, resource, maxAmount, transaction) : 0;
		}
		return slot == SLOT_MEAL_OUTPUT ? itemHandler.extractSlot(slot, resource, maxAmount, transaction) : 0;
	}

	@Override
	public Iterator<StorageView<ItemVariant>> iterator() {
		if (side == null || side.equals(Direction.UP))
			return (Iterator) itemHandler.getSlots().subList(0, SLOTS_INPUT).iterator();
		return itemHandler.getSlots().get(SLOT_MEAL_OUTPUT).iterator();
	}
}