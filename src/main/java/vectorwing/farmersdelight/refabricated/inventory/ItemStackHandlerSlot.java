package vectorwing.farmersdelight.refabricated.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleItemStorage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.item.ItemStack;

public class ItemStackHandlerSlot extends SingleItemStorage {
    public ItemStackHandlerSlot() {}

    @Override
    protected long getCapacity(ItemVariant variant) {
        return 64;
    }

    @Override
    public void writeNbt(CompoundTag nbt, HolderLookup.Provider provider) {
        nbt.merge((CompoundTag) ItemStack.CODEC.encodeStart(RegistryOps.create(NbtOps.INSTANCE, provider), variant.toStack((int)amount)).getOrThrow());
    }

    @Override
    public void readNbt(CompoundTag nbt, HolderLookup.Provider provider) {
        ItemStack stack = ItemStack.CODEC.decode(RegistryOps.create(NbtOps.INSTANCE, provider), nbt).getOrThrow().getFirst();
        variant = ItemVariant.of(stack);
        amount = stack.getCount();
    }
}
