package vectorwing.farmersdelight.refabricated.datamap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModDataMaps;

import java.util.Map;

public class RefabricatedDataMapReloadListener extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
	public static final ResourceLocation ID = FarmersDelight.res("reload_listener");

	public RefabricatedDataMapReloadListener(Gson gson, String directory) {
		super(gson, directory);
	}

	public static void init() {
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new RefabricatedDataMapReloadListener(new Gson(), "data_maps/block"));
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> preparations, ResourceManager resourceManager, ProfilerFiller profiler) {
		ModDataMaps.MUSHROOM_COLONIES.map.clear();
		preparations.forEach((id, je) -> {
			if (id.equals(ModDataMaps.MUSHROOM_COLONIES.id())) {
				ModDataMaps.MUSHROOM_COLONIES.map.putAll(ModDataMaps.MUSHROOM_COLONIES.blockCodec().parse(JsonOps.INSTANCE, je).getOrThrow());
			}
		});
	}

	@Override
	public ResourceLocation getFabricId() {
		return ID;
	}
}
