package vectorwing.farmersdelight.common.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.enchantment.BackstabbingEnchantment;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyVariable(method = "hurt", at = @At("HEAD"), argsOnly = true)
    private float handleBackstabbingDamage(float original, DamageSource source) {
        if (original > 0) {
            SkilletItem.SkilletEvents.playSkilletAttackSound((LivingEntity)(Object)this, source);
            // You'd be multiplying with 0 if you were to do this with any value <= 0.
            return BackstabbingEnchantment.BackstabbingEvent.onKnifeBackstab((LivingEntity)(Object)this, source, original);
        }
        return original;
    }

    @ModifyVariable(method = "knockback", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private double handleKnifeKnockback(double strength) {
        return KnifeItem.KnifeEvents.onKnifeKnockback(strength, (LivingEntity)(Object)this);
    }
}