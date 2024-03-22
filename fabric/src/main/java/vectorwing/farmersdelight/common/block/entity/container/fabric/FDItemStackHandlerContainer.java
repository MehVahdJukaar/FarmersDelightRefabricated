package vectorwing.farmersdelight.common.block.entity.container.fabric;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemHandlerHelper;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A implementation of ItemStackHandlerContainer which implements
 * specific Farmer's Delight required methods.
 */
public class FDItemStackHandlerContainer extends ItemStackHandlerContainer {

    public FDItemStackHandlerContainer() {
        super();
    }

    public FDItemStackHandlerContainer(int size) {
        super(size);
    }

    /**
     * An item insertion method that returns what you'd expect out of the container
     * upon inserting the stack.
     */
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (indexInvalid(slot))
            return stack;

        ItemStack existing = this.getItem(slot);
        int limit = Math.min(this.getSlotLimit(slot), stack.getMaxStackSize());

        if (!existing.isEmpty()) {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate) {
            if (existing.isEmpty()) {
                this.setItem(slot, stack);
            } else {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
    }

}
