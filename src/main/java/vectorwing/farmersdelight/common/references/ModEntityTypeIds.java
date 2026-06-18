package vectorwing.farmersdelight.common.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import vectorwing.farmersdelight.FarmersDelight;

public class ModEntityTypeIds {
	public static final ResourceKey<EntityType<?>> ROTTEN_TOMATO = key("rotten_tomato");

	private static ResourceKey<EntityType<?>> key(final String name) {
		return ResourceKey.create(Registries.ENTITY_TYPE, FarmersDelight.id(name));
	}
}
