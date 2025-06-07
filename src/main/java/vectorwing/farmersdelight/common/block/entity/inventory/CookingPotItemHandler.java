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
import java.util.List;

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
	public @NotNull ItemStack getStackInSlot(int slot) {
		return itemHandler.getStackInSlot(slot);
	}

	@Override
	public int getSlotLimit(int slot) {
		return itemHandler.getSlotLimit(slot);
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		this.itemHandler.setStackInSlot(slot, stack);
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
			return slot < SLOTS_INPUT ? itemHandler.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
		}
		return slot == SLOT_MEAL_OUTPUT ? itemHandler.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
	}

	@Override
	public SingleSlotStorage<ItemVariant> getSlot(int slot) {
		return itemHandler.getSlot(slot);
	}
	@Override
	public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		StoragePreconditions.notBlankNotNegative(resource, maxAmount);
		long inserted = 0;;
		for (Iterator<SingleItemStorage> it = getInsertableSlotsFor(resource); it.hasNext(); ) {
			SingleItemStorage slot = it.next();
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
        for (Iterator<SingleItemStorage> it = getSlotsContaining(resource); it.hasNext(); ) {
            SingleItemStorage slot = it.next();
            extracted += slot.extract(resource, maxAmount - extracted, transaction);
            if (extracted >= maxAmount)
                break;
        }
		return extracted;
	}

	@Override
	public Iterator<StorageView<ItemVariant>> iterator() {
		if (side == null || side.equals(Direction.UP))
			return (Iterator) itemHandler.getSlots().subList(0, SLOTS_INPUT).iterator();
		return (Iterator) List.of(itemHandler.getSlots().get(SLOT_MEAL_OUTPUT)).iterator();
	}

	private Iterator<SingleItemStorage> getInsertableSlotsFor(ItemVariant resource) {
		var slots = (side == null || side.equals(Direction.UP)) ?
				itemHandler.getSlots().subList(0, SLOTS_INPUT) :
				List.of(itemHandler.getSlots().get(SLOT_MEAL_OUTPUT));
		return (Iterator) slots.stream()
				.filter(views -> views.isResourceBlank() || views.getResource().equals(resource))
				.iterator();
	}

	private Iterator<SingleItemStorage> getSlotsContaining(ItemVariant resource) {
		var slots = (side == null || side.equals(Direction.UP)) ?
				itemHandler.getSlots().subList(0, SLOTS_INPUT) :
				List.of(itemHandler.getSlots().get(SLOT_MEAL_OUTPUT));
		return (Iterator) slots.stream()
				.filter(views -> views.getResource().equals(resource))
				.iterator();
	}
}