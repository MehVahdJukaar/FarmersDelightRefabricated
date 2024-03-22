package vectorwing.farmersdelight.common.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vectorwing.farmersdelight.common.item.enchantment.BackstabbingEnchantment;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @ModifyVariable(method = "actuallyHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getDamageAfterArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F"), argsOnly = true)
    private float handleBackstabbingDamage(float original, DamageSource damageSource) {
        return BackstabbingEnchantment.BackstabbingEvent.onKnifeBackstab((LivingEntity)(Object)this, damageSource, original);
    }
}
