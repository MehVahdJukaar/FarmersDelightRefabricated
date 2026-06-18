package vectorwing.farmersdelight.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.entity.RottenTomatoEntity;
import vectorwing.farmersdelight.common.references.ModEntityTypeIds;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regEntity;

public class ModEntityTypes {
    public static final Supplier<EntityType<RottenTomatoEntity>> ROTTEN_TOMATO = regEntity(ModEntityTypeIds.ROTTEN_TOMATO, () -> (
            EntityType.Builder.<RottenTomatoEntity>of(RottenTomatoEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, FarmersDelight.id("rotten_tomato")))));

	public static void touch() {

	}
}
