package vectorwing.farmersdelight.common.crafting.condition;

import net.minecraft.resources.ResourceLocation;
import vectorwing.farmersdelight.FarmersDelight;

public class VanillaCrateEnabledCondition
{
	public static final ResourceLocation ID = new ResourceLocation(FarmersDelight.MODID, "vanilla_crates_enabled");

	/*
	private final ResourceLocation location;

	public VanillaCrateEnabledCondition(ResourceLocation location) {
		this.location = location;
	}

	public ResourceLocation getID() {
		return this.location;
	}

	public boolean test() {
		return Configuration.ENABLE_VANILLA_CROP_CRATES.get();
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
	 */
}