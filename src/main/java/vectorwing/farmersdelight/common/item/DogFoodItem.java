package vectorwing.farmersdelight.common.item;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.MathUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;
import java.util.function.Consumer;

public class DogFoodItem extends ConsumableItem
{
	public static final List<MobEffectInstance> EFFECTS = List.of(
			new MobEffectInstance(MobEffects.SPEED, 6000, 0),
			new MobEffectInstance(MobEffects.STRENGTH, 6000, 0),
			new MobEffectInstance(MobEffects.RESISTANCE, 6000, 0));

	public DogFoodItem(Properties properties) {
		super(properties);
	}

	public static void init(){
		UseEntityCallback.EVENT.register(DogFoodEvent::onDogFoodApplied);
	}

	public static class DogFoodEvent
	{
		public static InteractionResult onDogFoodApplied(Player player, Level level, InteractionHand hand, Entity target,
											@Nullable EntityHitResult entityHitResult) {
			if (player.isSpectator())
				return InteractionResult.PASS;

			ItemStack itemStack = player.getItemInHand(hand);

			if (target instanceof LivingEntity entity && target.is(ModTags.EntityTypes.DOG_FOOD_USERS)) {
				boolean isTameable = entity instanceof TamableAnimal;

				if (entity.isAlive() && (!isTameable || ((TamableAnimal) entity).isTame()) && itemStack.getItem().equals(ModItems.DOG_FOOD.get())) {
					entity.setHealth(entity.getMaxHealth());
					for (MobEffectInstance effect : EFFECTS) {
						entity.addEffect(new MobEffectInstance(effect));
					}
					entity.level().playSound(null, target.blockPosition(), SoundEvents.GENERIC_EAT.value(), SoundSource.PLAYERS, 0.8F, 0.8F);

					for (int i = 0; i < 5; ++i) {
						double xSpeed = MathUtils.RAND.nextGaussian() * 0.02D;
						double ySpeed = MathUtils.RAND.nextGaussian() * 0.02D;
						double zSpeed = MathUtils.RAND.nextGaussian() * 0.02D;
						entity.level().addParticle(ModParticleTypes.STAR.get(), entity.getRandomX(1.0D), entity.getRandomY() + 0.5D, entity.getRandomZ(1.0D), xSpeed, ySpeed, zSpeed);
					}

					if (itemStack.getCraftingRemainder() != null && !player.isCreative()) {
						player.addItem(itemStack.getCraftingRemainder().create());
						itemStack.shrink(1);
					}

					return InteractionResult.SUCCESS;
				}
			}
			return InteractionResult.PASS;
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
		if (!Configuration.FOOD_EFFECT_TOOLTIP.get()) {
			return;
		}

		MutableComponent textWhenFeeding = TextUtils.getTranslation("tooltip.dog_food.when_feeding");
		tooltipAdder.accept(textWhenFeeding.withStyle(ChatFormatting.GRAY));

		for (MobEffectInstance effectInstance : EFFECTS) {
			MutableComponent effectDescription = Component.literal(" ");
			MutableComponent effectName = Component.translatable(effectInstance.getDescriptionId());
			effectDescription.append(effectName);
			MobEffect effect = effectInstance.getEffect().value();

			if (effectInstance.getAmplifier() > 0) {
				effectDescription.append(" ").append(Component.translatable("potion.potency." + effectInstance.getAmplifier()));
			}

			if (effectInstance.getDuration() > 20) {
				effectDescription.append(" (").append(MobEffectUtil.formatDuration(effectInstance, 1.0F, context.tickRate())).append(")");
			}

			tooltipAdder.accept(effectDescription.withStyle(effect.getCategory().getTooltipFormatting()));
		}
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
		if (target instanceof Wolf wolf) {
			if (wolf.isAlive() && wolf.isTame()) {
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}
}
