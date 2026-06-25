package vectorwing.farmersdelight.common.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.gamerules.GameRules;
import vectorwing.farmersdelight.common.mixin.refabricated.FoodDataAccessor;

public class NourishmentEffect extends MobEffect
{
	/**
	 * This effect prevents hunger loss by constantly setting the exhaustion level to zero.
	 * If the player can spend saturation to heal damage, the effect pauses to let them do so.
	 * Slow healing won't consume hunger, making it happen indefinitely. A mixin allows the player to always eat when under this effect to compensate.
	 */
	public NourishmentEffect() {
		super(MobEffectCategory.BENEFICIAL, 0xF3B300);
	}

	public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity entity, int amplifier) {

		if (entity instanceof Player player) {
			FoodData foodData = player.getFoodData();
            boolean isPlayerHealingWithHunger =
                    serverLevel.getGameRules().get(GameRules.NATURAL_HEALTH_REGENERATION)
                            && player.isHurt()
                            && foodData.getFoodLevel() >= 18;
            if (!isPlayerHealingWithHunger) {
                float exhaustion = ((FoodDataAccessor)foodData).fdrf$getExhaustionLevel();
                float reduction = Math.min(exhaustion, 4.0F);
                if (exhaustion > 0.0F) {
                    player.causeFoodExhaustion(-reduction);
                }
            }
		}

		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}
