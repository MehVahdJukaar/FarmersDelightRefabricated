package vectorwing.farmersdelight.refabricated.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.world.item.ItemStack;

public interface ItemHandler extends SlottedStorage<ItemVariant> {
    int getSlotCount();
    int getSlotLimit(int slot);

    ItemStack getStackInSlot(int slot);
    void commitModifiedStacks();
    void setStackInSlot(int slot, ItemStack stack);
    ItemStack removeItem(int slot, int amount);

    boolean isItemValid(int slot, ItemStack stack);

    long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction);
    long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction);
}
