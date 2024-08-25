package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.MapCodec;
import io.github.fabricators_of_create.porting_lib.conditions.ICondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import vectorwing.farmersdelight.common.crafting.condition.VanillaCrateEnabledCondition;

import java.util.function.Supplier;

public class ModConditionCodecs
{
	/**
	 * Refabricated: Deprecated: Use {@link VanillaCrateEnabledCondition#TYPE}
	 */
	@Deprecated
	public static final Supplier<MapCodec<? extends ICondition>> VANILLA_CRATE_ENABLED = () -> VanillaCrateEnabledCondition.CODEC;

	public static void register() {
		ResourceConditions.register(VanillaCrateEnabledCondition.TYPE);
	}
}
