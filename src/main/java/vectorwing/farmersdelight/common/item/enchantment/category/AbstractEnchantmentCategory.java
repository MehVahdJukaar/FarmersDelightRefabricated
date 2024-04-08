package vectorwing.farmersdelight.common.item.enchantment.category;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * <p>Pseudo class for Fabric-ASM, not needed in mixins.json.</p>
 * <p>Extends this class and add the subclass as a {@link EnchantmentCategory} using {@link com.chocohead.mm.api.ClassTinkerers#enumBuilder(String)}.</p>
 * @see vectorwing.farmersdelight.FarmersDelightASM
 */
@SuppressWarnings("all")
@Mixin(EnchantmentCategory.class)
public abstract class AbstractEnchantmentCategory {

    @Shadow
    public abstract boolean canEnchant(Item item);

}
