package vectorwing.farmersdelight.common.block.entity;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.crafting.RecipeAccess;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipeInput;
import vectorwing.farmersdelight.common.registry.*;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandler;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

import java.util.List;
import java.util.Optional;

public class CuttingBoardBlockEntity extends SyncedBlockEntity
{
	private final ItemStackHandler inventory;
	private final RecipeManager.CachedCheck<CuttingBoardRecipeInput, CuttingBoardRecipe> quickCheck;
	private boolean isItemCarvingBoard;

	public CuttingBoardBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntityTypes.CUTTING_BOARD.get(), pos, state);
		inventory = createHandler();
		isItemCarvingBoard = false;
		quickCheck = RecipeManager.createCheck(ModRecipeTypes.CUTTING.get());
	}

    public static void init() {
        ItemStorage.SIDED.registerForBlockEntity(CuttingBoardBlockEntity::getStorage, ModBlockEntityTypes.CUTTING_BOARD.get());
    }

    public Storage<ItemVariant> getStorage(@Nullable Direction side) {
        return getInventory();
    }

	@Override
	public void loadAdditional(@NonNull ValueInput input) {
		super.loadAdditional(input);
		isItemCarvingBoard = input.getBooleanOr("IsItemCarved", false);
		inventory.deserialize(input.childOrEmpty("Inventory"));
	}

	@Override
	public void saveAdditional(@NonNull ValueOutput output) {
		super.saveAdditional(output);
		inventory.serialize(output.child("Inventory"));
		output.putBoolean("IsItemCarved", isItemCarvingBoard);
	}

	@Override
	public void preRemoveSideEffects(@NonNull BlockPos pos, @NonNull BlockState state) {
        if (level != null) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), getStoredItem());
        }
        super.preRemoveSideEffects(pos, state);
	}

	public boolean processStoredItemUsingTool(ItemStack toolStack, @Nullable Player player) {
		if (!(level instanceof ServerLevel serverLevel)) {
			RecipeAccess recipeAccess = level.recipeAccess();
			return recipeAccess.propertySet(ModRecipePropertySets.CUTTING_BOARD_INPUT).test(getStoredItem()) &&
				recipeAccess.propertySet(ModRecipePropertySets.CUTTING_BOARD_TOOL).test(toolStack);
		}

		if (isItemCarvingBoard) return false;

		Optional<RecipeHolder<CuttingBoardRecipe>> matchingRecipe = getMatchingRecipe(toolStack, player, serverLevel);

		matchingRecipe.ifPresent(recipe -> {
			List<ItemStack> results = recipe.value().rollResults(level.getRandom(), EnchantmentHelper.getItemEnchantmentLevel(level.holderLookup(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), toolStack));
			for (ItemStack resultStack : results) {
				Direction direction = getBlockState().getValue(CuttingBoardBlock.FACING).getCounterClockWise();
				ItemUtils.spawnItemEntity(level, resultStack.copy(),
						worldPosition.getX() + 0.5 + (direction.getStepX() * 0.2), worldPosition.getY() + 0.2, worldPosition.getZ() + 0.5 + (direction.getStepZ() * 0.2),
						direction.getStepX() * 0.2F, 0.0F, direction.getStepZ() * 0.2F);
			}
			toolStack.hurtAndBreak(1, serverLevel, (ServerPlayer) player, (item) -> {});
			if (player != null) {
				player.awardStat(Stats.ITEM_USED.get(toolStack.getItem()));
			}
			spawnCuttingParticles(serverLevel, getBlockPos(), getStoredItem());
			playProcessingSound(recipe.value().getSoundEvent().orElse(null), toolStack, getStoredItem());
			inventory.extractItem(0, 1, false);
			if (player instanceof ServerPlayer) {
				ModAdvancements.USE_CUTTING_BOARD.get().trigger((ServerPlayer) player);
				if (!getStoredItem().isEmpty()) {
					player.sendOverlayMessage(TextUtils.block("cutting_board.remaining_items", getStoredItem().getCount()));
				} else {
					player.sendOverlayMessage(Component.empty());
				}
			}
		});

		return matchingRecipe.isPresent();
	}

	private Optional<RecipeHolder<CuttingBoardRecipe>> getMatchingRecipe(ItemStack toolStack, @Nullable Player player, ServerLevel serverLevel) {
		Optional<RecipeHolder<CuttingBoardRecipe>> recipe = quickCheck.getRecipeFor(new CuttingBoardRecipeInput(getStoredItem(), toolStack), serverLevel);
		if (recipe.isPresent()) {
			if (recipe.get().value().getTool().test(toolStack)) {
				return recipe;
			} else if (player != null) {
				player.sendOverlayMessage(TextUtils.block("cutting_board.invalid_tool"));
			}
		} else if (player != null) {
			player.sendOverlayMessage(TextUtils.block("cutting_board.invalid_item"));
		}

		return Optional.empty();
	}

	public void spawnCuttingParticles(ServerLevel level, BlockPos pos, ItemStack stack) {
		level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack.getItem()), pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, 5, 0.1, 0.1, 0.1, 0.05D);
	}

	public void playProcessingSound(@Nullable SoundEvent sound, ItemStack tool, ItemStack boardItem) {
		if (sound != null) {
			playSound(sound, 1.0F, 1.0F);
		} else if (tool.is(ConventionalItemTags.SHEAR_TOOLS)) {
			playSound(SoundEvents.SHEEP_SHEAR, 1.0F, 1.0F);
		} else if (tool.is(CommonTags.Items.TOOLS_KNIFE)) {
			playSound(ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get(), 0.8F, 1.0F);
		} else if (boardItem.getItem() instanceof BlockItem blockItem) {
			Block block = blockItem.getBlock();
			SoundType soundType = block.defaultBlockState().getSoundType();
			playSound(soundType.getBreakSound(), 1.0F, 0.8F);
		} else {
			playSound(SoundEvents.WOOD_BREAK, 1.0F, 0.8F);
		}
	}

	public void playSound(SoundEvent sound, float volume, float pitch) {
		if (level != null)
			level.playSound(null, worldPosition.getX() + 0.5F, worldPosition.getY() + 0.5F, worldPosition.getZ() + 0.5F, sound, SoundSource.BLOCKS, volume, pitch);
	}

	public boolean canAddItem(ItemStack addedStack) {
		if (isItemCarvingBoard || addedStack.isEmpty()) {
			return false;
		}
		return inventory.insertItem(0, addedStack.copy(), true).getCount() != addedStack.getCount();
	}

	public ItemStack addItem(ItemStack addedStack) {
		if (!isItemCarvingBoard) {
			return inventory.insertItem(0, addedStack.copy(), false);
		}
		return addedStack;
	}

	public ItemStack removeItem() {
		isItemCarvingBoard = false;
		return inventory.extractItem(0, getMaxStackSize(), false);
	}

	public boolean carveToolOnBoard(ItemStack toolStack) {
		if (toolStack.has(DataComponents.TOOL) || toolStack.getItem() instanceof TridentItem || toolStack.getItem() instanceof ShearsItem) {
			if (addItem(toolStack) == ItemStack.EMPTY) {
				isItemCarvingBoard = true;
				return true;
			}
		}
		return false;
	}

	public ItemHandler getInventory() {
		return inventory;
	}

	public ItemStack getStoredItem() {
		return inventory.getStackInSlot(0);
	}

	public int getMaxStackSize() {
		return inventory.getSlotLimit(0);
	}

	public boolean isEmpty() {
		return inventory.getStackInSlot(0).isEmpty();
	}

	public boolean isItemCarvingBoard() {
		return isItemCarvingBoard;
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
	}

	private ItemStackHandler createHandler() {
		return new ItemStackHandler()
		{
			@Override
			protected void onContentsChanged(int slot) {
				inventoryChanged();
			}
		};
	}
}