package vectorwing.farmersdelight.common.crafting.condition;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.resources.ResourceLocation;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;

public class VanillaCrateEnabledCondition implements ConditionJsonProvider
{
	private static final ResourceLocation ID = new ResourceLocation(FarmersDelight.MODID, "vanilla_crates_enabled");
	public static final VanillaCrateEnabledCondition INSTANCE = new VanillaCrateEnabledCondition();

	public boolean test() {
		return Configuration.ENABLE_VANILLA_CROP_CRATES.get();
	}

    @Override
    public ResourceLocation getConditionId() {
        return ID;
    }

    @Override
    public void writeParameters(JsonObject object) {

    }

    public static class Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		public ResourceLocation getID() {
			return ID;
		}

		public VanillaCrateEnabledCondition read(JsonObject json) {
			return VanillaCrateEnabledCondition.INSTANCE;
		}

		public void write(JsonObject json, VanillaCrateEnabledCondition value) {
		}
	}
}