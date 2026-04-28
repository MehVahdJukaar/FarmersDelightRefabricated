package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagLoader;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.refabricated.duck.TagLoaderSpecificResourceDuck;

import java.util.List;
import java.util.Map;

@Mixin(TagLoader.class)
public class TagLoaderMixin implements TagLoaderSpecificResourceDuck {
	@Unique
	@Nullable
	private Identifier tagPath = null;

	@ModifyExpressionValue(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/resources/FileToIdConverter;listMatchingResourceStacks(Lnet/minecraft/server/packs/resources/ResourceManager;)Ljava/util/Map;"))
	private Map<Identifier, List<Resource>> fdrf$onlyLoadSingleTagIfSpecified(Map<Identifier, List<Resource>> original, @Local(argsOnly = true) ResourceManager resourceManager) {
		if (tagPath != null) {
			return Map.of(tagPath, resourceManager.getResourceStack(tagPath));
		}
		return original;
	}

	@Override
	public void fdrf$setSpecificResourceToLoad(Identifier tagPath) {
		this.tagPath = tagPath;
	}
}
