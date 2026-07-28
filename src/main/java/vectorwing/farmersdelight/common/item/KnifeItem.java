package vectorwing.farmersdelight.common.item;

import com.google.common.collect.Sets;
import io.github.fabricators_of_create.porting_lib.enchant.CustomEnchantingBehaviorItem;
import io.github.fabricators_of_create.porting_lib.tool.ToolAction;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.refabricated.ItemAbility;

import java.util.Set;

public class KnifeItem extends DiggerItem implements CustomEnchantingBehaviorItem
{
	/**
	 * This action is used on cutting recipes which need a knife.
	 */
	public static final ToolAction KNIFE_DIG = ToolAction.get("knife_dig");
	/**
	 * This action is used in gameplay interactions where something is harvested.
	 */
	public static final ItemAbility KNIFE_HARVEST = ItemAbility.KNIFE_HARVEST;

	public static final Set<ItemAbility> KNIFE_ACTIONS = Set.of(ItemAbility.SHEARS_CARVE, ItemAbility.SWORD_DIG, ItemAbility.KNIFE_DIG, ItemAbility.KNIFE_HARVEST);

	public KnifeItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
		super(attackDamage, attackSpeed, tier, ModTags.Blocks.MINEABLE_WITH_KNIFE, properties);
	}

	public static void init() {
		UseBlockCallback.EVENT.register(KnifeItem.KnifeEvents::onCakeInteraction);
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
		return !player.isCreative();
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(1, attacker, (user) -> user.broadcastBreakEvent(EquipmentSlot.MAINHAND));
		return true;
	}

	public static class KnifeEvents
	{
		/*
		 * Moved impl to LivingEntityMixin because PortingLib does not support
		 * stacking values within their LivingKnockbackEvent equivalent.
		 */
		public static double onKnifeKnockback(double strength, LivingEntity entity) {
			LivingEntity attacker = entity.getKillCredit();
			ItemStack toolStack = attacker != null ? attacker.getItemInHand(InteractionHand.MAIN_HAND) : ItemStack.EMPTY;
			if (toolStack.getItem() instanceof KnifeItem) {
				strength = strength - 0.1F;
			}
			return strength;
		}

		public static InteractionResult onCakeInteraction(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
			ItemStack heldStack = player.getItemInHand(hand);

			if (!ItemUtils.isKnife(heldStack)) {
				return InteractionResult.PASS;
			}

			BlockPos pos = player.blockPosition();
			BlockState state = level.getBlockState(pos);
			Block block = state.getBlock();

			if (state.is(ModTags.Blocks.DROPS_CAKE_SLICE)) {
				level.setBlock(pos, Blocks.CAKE.defaultBlockState().setValue(CakeBlock.BITES, 1), 3);
				Block.dropResources(state, level, pos);
				ItemUtils.spawnItemEntity(level, new ItemStack(ModItems.CAKE_SLICE.get()),
					pos.getX(), pos.getY() + 0.2, pos.getZ() + 0.5,
					-0.05, 0, 0);
				level.playSound(null, pos, ModSounds.BLOCK_FOOD_SLICE.get(), SoundSource.PLAYERS, 0.8F, 0.8F);

				player.awardStat(Stats.ITEM_USED.get(heldStack.getItem()));
				return InteractionResult.SUCCESS;
			}

			if (block == Blocks.CAKE) {
				int bites = state.getValue(CakeBlock.BITES);
				if (bites < 6) {
					level.setBlock(pos, state.setValue(CakeBlock.BITES, bites + 1), 3);
				} else {
					level.removeBlock(pos, false);
				}
				ItemUtils.spawnItemEntity(level, new ItemStack(ModItems.CAKE_SLICE.get()),
					pos.getX() + (bites * 0.1), pos.getY() + 0.2, pos.getZ() + 0.5,
					-0.05, 0, 0);
				level.playSound(null, pos, ModSounds.BLOCK_FOOD_SLICE.get(), SoundSource.PLAYERS, 0.8F, 0.8F);

				player.awardStat(Stats.ITEM_USED.get(heldStack.getItem()));
				return InteractionResult.SUCCESS;
			}
			return InteractionResult.PASS;
		}
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.world.item.enchantment.Enchantment enchantment) {
		Set<Enchantment> ALLOWED_ENCHANTMENTS = Sets.newHashSet(Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.KNOCKBACK, Enchantments.FIRE_ASPECT, Enchantments.MOB_LOOTING);
		if (ALLOWED_ENCHANTMENTS.contains(enchantment)) {
			return true;
		}
		Set<Enchantment> DENIED_ENCHANTMENTS = Sets.newHashSet(Enchantments.BLOCK_FORTUNE);
		if (DENIED_ENCHANTMENTS.contains(enchantment)) {
			return false;
		}
		return enchantment.category.canEnchant(stack.getItem());
	}
}