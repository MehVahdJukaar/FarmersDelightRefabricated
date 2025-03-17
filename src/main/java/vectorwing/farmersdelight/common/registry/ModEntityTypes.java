package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import vectorwing.farmersdelight.common.entity.RottenTomatoEntity;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regEntity;

public class ModEntityTypes {
    public static final Supplier<EntityType<RottenTomatoEntity>> ROTTEN_TOMATO = regEntity("rotten_tomato", () -> (
            EntityType.Builder.<RottenTomatoEntity>of(RottenTomatoEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build()));

	public static void touch() {

	}
}
