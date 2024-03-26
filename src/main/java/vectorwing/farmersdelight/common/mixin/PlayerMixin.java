package vectorwing.farmersdelight.common.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.item.SkilletItem;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow public abstract float getAttackStrengthScale(float adjustTicks);

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;resetAttackStrengthTicker()V"))
    private void captureAttackStrengthScale(Entity target, CallbackInfo ci) {
        SkilletItem.SkilletEvents.attackPower = this.getAttackStrengthScale(0.0F);
    }

    @Inject(method = "hurt", at = @At("HEAD"))
    private void handleSkilletAttackSound(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        SkilletItem.SkilletEvents.playSkilletAttackSound(this, source);
    }
}
