package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.entity.RottenTomatoEntity;

public class ModEntityTypes
{
	public static final LazyRegistrar<EntityType<?>> ENTITIES = LazyRegistrar.create(BuiltInRegistries.ENTITY_TYPE, FarmersDelight.MODID);

	public static final RegistryObject<EntityType<RottenTomatoEntity>> ROTTEN_TOMATO = ENTITIES.register("rotten_tomato", () -> (
			EntityType.Builder.<RottenTomatoEntity>of(RottenTomatoEntity::new, MobCategory.MISC)
					.sized(0.25F, 0.25F)
					.clientTrackingRange(4)
					.updateInterval(10)
					.build("rotten_tomato")));
}
