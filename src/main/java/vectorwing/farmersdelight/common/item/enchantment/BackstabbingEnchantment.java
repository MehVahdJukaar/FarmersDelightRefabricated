package vectorwing.farmersdelight.common.item.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.mutable.MutableFloat;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

public class BackstabbingEnchantment
{
	/**
	 * Determines whether the attacker is facing a 90-100 degree cone behind the target's looking direction.
	 */
	public static boolean isLookingBehindTarget(LivingEntity target, Vec3 attackerLocation) {
		if (attackerLocation != null) {
			Vec3 lookingVector = target.getViewVector(1.0F);
			Vec3 attackAngleVector = attackerLocation.subtract(target.position()).normalize();
			attackAngleVector = new Vec3(attackAngleVector.x, 0.0D, attackAngleVector.z);
			return attackAngleVector.dot(lookingVector) < -0.5D;
		}
		return false;
	}

	public static float getBackstabbingDamagePerLevel(float amount, int level) {
		float multiplier = ((level * 0.2F) + 1.2F);
		return amount * multiplier;
	}

	public static class BackstabbingEvent
	{
		/*
		 * Moved impl to LivingEntityMixin because PortingLib does not support
		 * stacking values within their LivingHurtEvent equivalent.
		 */
		@SuppressWarnings("unused")
		public static float onKnifeBackstab(LivingEntity entity, DamageSource source, float amount) {
			Entity attacker = source.getEntity();
			if (attacker instanceof LivingEntity living && isLookingBehindTarget(entity, source.getSourcePosition())) {
				Level level = attacker.level();
				if (level instanceof ServerLevel serverLevel) {
					ItemStack weapon = living.getWeaponItem(); // since you play a sound on success, we record the original to do a change check later
					MutableFloat dmg = new MutableFloat(amount);
					EnchantmentHelper.runIterationOnItem(weapon, (enchantment, powerLevel) -> {
						enchantment.value().modifyDamageFilteredValue(ModDataComponents.BACKSTABBING.get(), serverLevel, powerLevel, weapon, attacker, source, dmg);
					});

					if (amount != dmg.getValue()) {
						amount = dmg.getValue();
						serverLevel.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.BLOCKS, 1.0F, 1.0F);
					}
				}
			}
			return amount;
		}
	}
}
