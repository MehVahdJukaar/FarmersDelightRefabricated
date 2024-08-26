package vectorwing.farmersdelight;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;

import java.util.Map;

//TODO: delete when porting lib updates since they will for sure have added this.
//This is hacky and tbh not even needed but hey
public class CompostableHelper extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
    public CompostableHelper() {
        super(new Gson(), "data_maps/item");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) {
        JsonElement je = object.get(ResourceLocation.fromNamespaceAndPath("neoforge", "compostables"));
        if (je != null) {
            var j = je.getAsJsonObject().get("values");
            for (var v : j.getAsJsonObject().asMap().entrySet()) {
                Item i = BuiltInRegistries.ITEM.get(ResourceLocation.tryParse(v.getKey().toString()));
                ComposterBlock.COMPOSTABLES.put(i, v.getValue().getAsJsonObject().get("chance").getAsFloat());
            }
        }
    }

    public static void init() {
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new CompostableHelper());
    }

    @Override
    public ResourceLocation getFabricId() {
        return FarmersDelight.res("compostable_helper");
    }
}
