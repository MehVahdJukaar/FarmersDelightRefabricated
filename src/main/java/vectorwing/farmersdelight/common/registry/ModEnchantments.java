package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.enchantment.BackstabbingEnchantment;

public class ModEnchantments
{
    // cant do on fabric :P
	// public static final EnchantmentCategory KNIFE = EnchantmentCategory.create("knife", item -> item instanceof KnifeItem);

	public static final LazyRegistrar<Enchantment> ENCHANTMENTS = LazyRegistrar.create(BuiltInRegistries.ENCHANTMENT, FarmersDelight.MODID);

	public static final RegistryObject<Enchantment> BACKSTABBING = ENCHANTMENTS.register("backstabbing",
			() -> new BackstabbingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));
}
