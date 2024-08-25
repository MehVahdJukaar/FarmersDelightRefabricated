package vectorwing.farmersdelight.common.utility;

import io.github.fabricators_of_create.porting_lib.util.DeferredHolder;
import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

import java.util.function.UnaryOperator;

public class RegistryUtils
{
	public static EnchantmentEffectComponents createEnchantmentEffectComponents(String modid) {
		return new EnchantmentEffectComponents(modid);
	}

	public static class EnchantmentEffectComponents extends DeferredRegister<DataComponentType<?>>
	{
		protected EnchantmentEffectComponents(String namespace) {
			super(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, namespace);
		}

		public <D> DeferredHolder<DataComponentType<?>, DataComponentType<D>> registerComponentType(String name, UnaryOperator<DataComponentType.Builder<D>> builder) {
			return this.register(name, () -> ((DataComponentType.Builder)builder.apply(DataComponentType.builder())).build());
		}
	}
}
