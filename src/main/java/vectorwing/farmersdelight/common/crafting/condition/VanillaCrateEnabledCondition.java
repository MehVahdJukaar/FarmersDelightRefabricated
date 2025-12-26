package vectorwing.farmersdelight.common.crafting.condition;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.resources.ResourceLocation;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;

public class VanillaCrateEnabledCondition implements ConditionJsonProvider
{
	public static final ResourceLocation ID = new ResourceLocation(FarmersDelight.MODID, "vanilla_crates_enabled");
	private final ResourceLocation location;

	public VanillaCrateEnabledCondition(ResourceLocation location) {
		this.location = location;
	}

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
		private final ResourceLocation location;

		public Serializer() {
			this.location = new ResourceLocation(FarmersDelight.MODID, "vanilla_crates_enabled");
		}

		public ResourceLocation getID() {
			return this.location;
		}

		public VanillaCrateEnabledCondition read(JsonObject json) {
			return new VanillaCrateEnabledCondition(this.location);
		}

		public void write(JsonObject json, VanillaCrateEnabledCondition value) {
		}
	}
}