package vectorwing.farmersdelight.refabricated.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;

//this can probably be just an INV delegate
public abstract class ItemStackHandlerContainer {
    public ItemStack getStackInSlot(int mealDisplaySlot) {
        return null;
    }

    public int getSlotLimit(int slot) {
    }

    protected abstract int getStackLimit(int slot, ItemVariant resource);

    protected abstract void onContentsChanged(int slot);

    public int getSlotCount() {
    }

    public @NotNull ItemStack removeItem(int slot, int amount) {
    }

    public void setStackInSlot(int slot, ItemStack stack) {
    }

    public Collection<Object> getSlotsContaining(Item item) {
    }

    public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
    }

    public Iterator<StorageView<ItemVariant>> iterator() {
    }

    public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
        return 0;
    }
}
