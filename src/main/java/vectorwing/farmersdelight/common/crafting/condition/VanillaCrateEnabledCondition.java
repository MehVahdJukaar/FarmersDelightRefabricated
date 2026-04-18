package vectorwing.farmersdelight.common.crafting.condition;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;

public class VanillaCrateEnabledCondition implements ResourceCondition
{
	public static final MapCodec<VanillaCrateEnabledCondition> CODEC = MapCodec.unit(new VanillaCrateEnabledCondition());
	public static final VanillaCrateEnabledCondition INSTANCE = new VanillaCrateEnabledCondition();
	public static final ResourceConditionType<VanillaCrateEnabledCondition> TYPE = ResourceConditionType.create(FarmersDelight.res("vanilla_crates_enabled"), CODEC);

	public VanillaCrateEnabledCondition() {
	}

	public static void init() {
		ResourceConditions.register(TYPE);
	}

	@Override
	public ResourceConditionType<?> getType() {
		return TYPE;
	}

	@Override
	public boolean test(HolderLookup.@Nullable Provider registryLookup) {
		return Configuration.ENABLE_VANILLA_CROP_CRATES.get();
	}
}