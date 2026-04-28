package vectorwing.farmersdelight.common.block.entity.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
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
import java.util.stream.Stream;

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
	public int getSlotLimit(int slot) {
		return itemHandler.getSlotLimit(slot);
	}
}

	@Override
	public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		StoragePreconditions.notBlankNotNegative(resource, maxAmount);
		long extracted = 0;
        for (Iterator<SingleStackStorage> it = getSlotsContaining(resource); it.hasNext(); ) {
			SingleStackStorage slot = it.next();
            extracted += slot.extract(resource, maxAmount - extracted, transaction);
            if (extracted >= maxAmount)
                break;
        }
		return extracted;
	}

	@Override
	public Iterator<StorageView<ItemVariant>> iterator() {
		if (side == null || side.equals(Direction.UP))
			return itemHandler.getSlots().subList(0, SLOTS_INPUT).stream().map(storageView -> (StorageView<ItemVariant>)storageView).iterator();
		return Stream.of(itemHandler.getSlots().get(SLOT_MEAL_OUTPUT)).map(storageView -> (StorageView<ItemVariant>)storageView).iterator();
	}

	private Iterator<SingleStackStorage> getInsertableSlotsFor(ItemVariant resource) {
		var slots = (side == null || side.equals(Direction.UP)) ?
				itemHandler.getSlots().subList(0, SLOTS_INPUT) :
				List.of(itemHandler.getSlots().get(SLOT_CONTAINER_INPUT));
		return slots.stream()
				.filter(views -> views.isResourceBlank() || views.getResource().equals(resource))
				.map(storageView -> (SingleStackStorage)storageView)
				.iterator();
	}

	private Iterator<SingleStackStorage> getSlotsContaining(ItemVariant resource) {
		var slots = (side == null || side.equals(Direction.UP)) ?
				itemHandler.getSlots().subList(0, SLOTS_INPUT) :
				List.of(itemHandler.getSlots().get(SLOT_MEAL_OUTPUT));
		return slots.stream()
				.filter(views -> views.getResource().equals(resource))
				.map(storageView -> (SingleStackStorage)storageView)
				.iterator();
	}
}