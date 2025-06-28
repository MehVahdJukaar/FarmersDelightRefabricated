package vectorwing.farmersdelight.common.block.entity.inventory;

import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.block.entity.Basket;
import vectorwing.farmersdelight.refabricated.inventory.InvWrapper;

public class BasketInvWrapper extends InvWrapper {
    protected final Basket basket;

    public BasketInvWrapper(Basket basket) {
        super(basket);
        this.basket = basket;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (simulate) {
            return super.insertItem(slot, stack, true);
        } else {
            boolean wasEmpty = basket.isEmpty();
            int originalCount = stack.getCount();
            stack = super.insertItem(slot, stack, false);
            if (wasEmpty && originalCount > stack.getCount()) {
                if (!basket.isOnCustomCooldown()) {
                    basket.setCooldown(8);
                }
            }
            return stack;
        }
    }
}
