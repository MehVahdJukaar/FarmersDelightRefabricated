package vectorwing.farmersdelight.refabricated.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleItemStorage;

public class ItemStackHandlerSlot extends SingleItemStorage {
    public ItemStackHandlerSlot() {}

    @Override
    protected long getCapacity(ItemVariant variant) {
        return 64;
    }
}
