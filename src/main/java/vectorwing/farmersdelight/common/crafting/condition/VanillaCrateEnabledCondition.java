package vectorwing.farmersdelight.common.crafting.condition;

import com.mojang.serialization.MapCodec;
import io.github.fabricators_of_create.porting_lib.conditions.ICondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;

public class VanillaCrateEnabledCondition implements ICondition
{
	public static final MapCodec<VanillaCrateEnabledCondition> CODEC = MapCodec.unit(new VanillaCrateEnabledCondition());
	public static final ResourceConditionType<VanillaCrateEnabledCondition> TYPE = ResourceConditionType.create(FarmersDelight.res("vanilla_crates_enabled"), CODEC);

	public VanillaCrateEnabledCondition() {
	}

	@Override
	public boolean test(HolderLookup.@Nullable Provider provider, IContext iContext) {
		return Configuration.ENABLE_VANILLA_CROP_CRATES.get();
	}

	@Override
	public ResourceConditionType<?> getType() {
		return TYPE;
	}
}