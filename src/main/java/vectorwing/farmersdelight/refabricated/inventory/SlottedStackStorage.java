package vectorwing.farmersdelight.refabricated.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface SlottedStackStorage {
    int getSlotCount();

    SingleSlotStorage<ItemVariant> getSlot(int slot);

    int getSlotLimit(int slot);

    // Fabric
    @NotNull ItemStack getStackInSlot(int slot);

    void setStackInSlot(int slot, ItemStack stack);

    long insert(ItemVariant resource, long maxAmount, TransactionContext transaction);

    long extract(ItemVariant resource, long maxAmount, TransactionContext transaction);

    long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction);

    long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction);

    Iterator<StorageView<ItemVariant>> iterator();
}
