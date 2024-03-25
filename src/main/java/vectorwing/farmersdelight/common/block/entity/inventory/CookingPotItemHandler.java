package vectorwing.farmersdelight.common.block.entity.inventory;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerSlot;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import io.github.fabricators_of_create.porting_lib.util.DualSortedSetIterator;
import it.unimi.dsi.fastutil.objects.ObjectAVLTreeSet;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.utility.ItemUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class CookingPotItemHandler implements SlottedStackStorage {
	private static final int SLOTS_INPUT = 6;
	private static final int SLOT_CONTAINER_INPUT = 7;
	private static final int SLOT_MEAL_OUTPUT = 8;
	private final ItemStackHandlerContainer itemHandler;
	private final Direction side;

	public CookingPotItemHandler(ItemStackHandlerContainer itemHandler, @Nullable Direction side) {
		this.itemHandler = itemHandler;
		this.side = side;
	}

	public boolean isItemValid(int slot, @NotNull ItemStack stack) {
		return isItemValid(slot, ItemVariant.of(stack), 1);
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
			return slot < SLOTS_INPUT ? ItemUtils.insertItem(itemHandler, slot, stack, simulate) : stack;
		} else {
			return slot == SLOT_CONTAINER_INPUT ? ItemUtils.insertItem(itemHandler, slot, stack, simulate) : stack;
		}
	}

	@NotNull
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT ? itemHandler.removeItem(slot, amount) : ItemStack.EMPTY;
		} else {
			return slot == SLOT_MEAL_OUTPUT ? itemHandler.removeItem(slot, amount) : ItemStack.EMPTY;
		}
	}

	@Override
	public int getSlotLimit(int slot) {
		return itemHandler.getSlotLimit(slot);
	}

	// Fabric
	@Override
	@NotNull
	public ItemStack getStackInSlot(int slot) {
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
		Iterator<ItemStackHandlerSlot> itr = getInsertableSlotsFor(resource);
		while (itr.hasNext()) {
			ItemStackHandlerSlot slot = itr.next();
			inserted += slot.insert(resource, maxAmount - inserted, transaction);
			if (inserted >= maxAmount)
				break;
		}
		return inserted;
	}

	@Override
	public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		return 0;
	}


	private Iterator<ItemStackHandlerSlot> getInsertableSlotsFor(ItemVariant resource) {
		SortedSet<ItemStackHandlerSlot> slots = getSlotsContaining(resource.getItem());
		SortedSet<ItemStackHandlerSlot> emptySlots = getSlotsContaining(Items.AIR);
		if (slots.isEmpty()) {
			return emptySlots.isEmpty() ? Collections.emptyIterator() : emptySlots.iterator();
		} else {
			return emptySlots.isEmpty() ? slots.iterator() : new DualSortedSetIterator<>(slots, emptySlots);
		}
	}

	private SortedSet<ItemStackHandlerSlot> getSlotsContaining(Item item) {
		return itemHandler.getSlotsContaining(item).stream().filter(storageViews -> isValidSlot(storageViews.getIndex())).collect(Collectors.toCollection(() -> new ObjectAVLTreeSet<>(Comparator.comparingInt(ItemStackHandlerSlot::getIndex))));
	}

	private boolean isValidSlot(int slot) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT;
		} else {
			return slot == SLOT_CONTAINER_INPUT;
		}
	}

	@Override
	public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT ? itemHandler.insertSlot(slot, resource, maxAmount, transaction) : 0;
		} else {
			return slot == SLOT_CONTAINER_INPUT ? itemHandler.insertSlot(slot, resource, maxAmount, transaction) : 0;
		}
	}

	@Override
	public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
		if (side == null || side.equals(Direction.UP)) {
			return slot < SLOTS_INPUT ? itemHandler.extractSlot(slot, resource, maxAmount, transaction) : 0;
		} else {
			return slot == SLOT_CONTAINER_INPUT ? itemHandler.extractSlot(slot, resource, maxAmount, transaction) : 0;
		}
	}
}
