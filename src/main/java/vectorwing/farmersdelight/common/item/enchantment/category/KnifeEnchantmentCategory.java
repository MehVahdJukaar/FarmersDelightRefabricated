package vectorwing.farmersdelight.common.item.enchantment.category;

import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.item.KnifeItem;

/**
 * @see vectorwing.farmersdelight.common.registry.ModEnchantments#KNIFE
 */
@SuppressWarnings("unused")
public class KnifeEnchantmentCategory extends AbstractEnchantmentCategory {

    @Override
    public boolean canEnchant(Item item) {
        return item instanceof KnifeItem;
    }

}
