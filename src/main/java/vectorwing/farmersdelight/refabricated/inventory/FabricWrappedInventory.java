package vectorwing.farmersdelight.refabricated.inventory;

import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.CrashReport;
import net.minecraft.ReportedException;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class FabricWrappedInventory implements ItemHandler {
    private final List<ItemHandlerStackWrapper> fabricWrappers;

    public FabricWrappedInventory(int size) {
        fabricWrappers = Lists.newArrayListWithCapacity(size);
        for (int i = 0; i < size; ++i) {
            fabricWrappers.add(new ItemHandlerStackWrapper(this, i));
        }
    }

    @Override
    public SingleSlotStorage<ItemVariant> getSlot(int slot) {
        return fabricWrappers.get(slot);
    }

    @Override
    public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
        long amount = 0;
        try {
            for (ItemHandlerStackWrapper wrapper : fabricWrappers) {
                amount += wrapper.insert(resource, maxAmount - amount, transaction);
                if (amount == maxAmount)
                    return maxAmount;
            }
        } catch (Exception ex) {
            CrashReport report = CrashReport.forThrowable(ex, "Inserting resources into slots");
            report.addCategory("Slotted insertion details")
                    .setDetail("Slots", () -> Objects.toString(fabricWrappers, null))
                    .setDetail("Resource", () -> Objects.toString(resource, null))
                    .setDetail("Max amount", maxAmount)
                    .setDetail("Transaction", transaction);
            throw new ReportedException(report);
        }
        return amount;
    }

    @Override
    public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
        long amount = 0;
        try {
            for (ItemHandlerStackWrapper wrapper : fabricWrappers) {
                amount += wrapper.extract(resource, maxAmount - amount, transaction);
                if (amount == maxAmount)
                    return maxAmount;
            }
        } catch (Exception ex) {
            CrashReport report = CrashReport.forThrowable(ex, "Inserting resources into slots");
            report.addCategory("Slotted insertion details")
                    .setDetail("Slots", () -> Objects.toString(fabricWrappers, null))
                    .setDetail("Resource", () -> Objects.toString(resource, null))
                    .setDetail("Max amount", maxAmount)
                    .setDetail("Transaction", transaction);
            throw new ReportedException(report);
        }
        return amount;
    }

    @Override
    public @NotNull Iterator<StorageView<ItemVariant>> iterator() {
        return getSlots().stream()
                .map(storageViews -> (StorageView<ItemVariant>)storageViews)
                .iterator();
    }
}
