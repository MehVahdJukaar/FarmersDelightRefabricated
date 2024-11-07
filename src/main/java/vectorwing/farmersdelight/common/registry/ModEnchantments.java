package vectorwing.farmersdelight.common.registry;

import com.chocohead.mm.api.ClassTinkerers;
import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.FarmersDelightASM;
import vectorwing.farmersdelight.common.item.enchantment.BackstabbingEnchantment;

import java.util.function.Supplier;

public class ModEnchantments
{
	public static final LazyRegistrar<Enchantment> ENCHANTMENTS = LazyRegistrar.create(BuiltInRegistries.ENCHANTMENT, FarmersDelight.MODID);

	public static final Supplier<Enchantment> BACKSTABBING = ENCHANTMENTS.register("backstabbing",
			() -> new BackstabbingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));
}
