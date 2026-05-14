package vectorwing.farmersdelight.common.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.refabricated.ItemAbility;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandler;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Optional;

/**
 * Util for handling ItemStacks and inventories containing them.
 */
public class ItemUtils
{
	/**
	 * Shorthand method for checking if the given stack either has a required ToolAction, or is otherwise part of a given tag.
	 * @param toolAction The ToolAction to check for
	 * @param fallbackTag An item tag to check for, if the given ToolAction is absent
	 * @return true if either condition matches
	 */
	public static boolean isValidTool(ItemStack stack, ItemAbility toolAction, TagKey<Item> fallbackTag) {
		return toolAction.canPerformAction(stack) || stack.is(fallbackTag);
	}

	public static boolean isKnife(ItemStack stack) {
		return isValidTool(stack, KnifeItem.KNIFE_HARVEST, ModTags.Items.KNIVES);
	}

	public static void dropItems(Level level, BlockPos pos, ItemStackHandler inventory) {
        for (int slot = 0; slot < inventory.getSlotCount(); slot++)
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(),
                    inventory.extractItem(slot, inventory.getSlotLimit(slot), false));
	}

	public static void clearItems(ItemStackHandler inventory) {
		for (int i = 0; i < inventory.getSlotCount(); i++) {
			inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
	}

	public static boolean doesInventoryHaveItems(ItemHandler inventory) {
        for (int i = 0; i < inventory.getSlotCount(); ++i) {
            if (!inventory.getStackInSlot(i).isEmpty())
                return true;
        }
        return false;
	}

	public static void spawnItemEntity(Level level, ItemStack stack, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
		ItemEntity entity = new ItemEntity(level, x, y, z, stack);
		entity.setDeltaMovement(xMotion, yMotion, zMotion);
		level.addFreshEntity(entity);
	}

	/**
	 * Checks if the enchantment is registered, and if so, gets that enchantment's level on the passed stack. Defaults to 0 in all edge cases.
	 * @param enchantment The ResourceKey for the enchantment
	 * @param registries The provider for registry lookup
	 * @param stack The stack to be queried
	 * @return The enchantment's level, if the stack is enchanted with it. Returns 0 if not, or if the enchantment is disabled.
	 */
	public static int getValidatedEnchantmentLevel(ResourceKey<Enchantment> enchantment, HolderLookup.Provider registries, ItemStack stack) {
		Optional<Holder.Reference<Enchantment>> fortune = registries.lookupOrThrow(Registries.ENCHANTMENT).get( enchantment);
		return fortune.map(e->ItemUtils.getEnchantmentLevel(stack, e)).orElse(0);
	}

	public static int getEnchantmentLevel(ItemStack stack, Holder<Enchantment> enchantment) {
		ItemEnchantments itemenchantments = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
		return itemenchantments.getLevel(enchantment);
	}
}
