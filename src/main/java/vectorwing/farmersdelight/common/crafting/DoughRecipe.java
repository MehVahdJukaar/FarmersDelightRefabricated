package vectorwing.farmersdelight.common.crafting;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;

public class DoughRecipe extends CustomRecipe
{
	public DoughRecipe(CraftingBookCategory category) {
		super(category);
	}

	@Override
	public boolean matches(CraftingInput container, Level level) {
		ItemStack wheatStack = ItemStack.EMPTY;
		ItemStack waterStack = ItemStack.EMPTY;

		for (int index = 0; index < container.size(); ++index) {
			ItemStack selectedStack = container.getItem(index);
			if (!selectedStack.isEmpty()) {
				if (selectedStack.is(Items.WHEAT)) {
					if (!wheatStack.isEmpty()) return false;
					wheatStack = selectedStack;
				} else {
					if (!selectedStack.is(ConventionalItemTags.WATER_BUCKETS)) {
						return false;
					}
					waterStack = selectedStack;
				}
			}
		}

		return !wheatStack.isEmpty() && !waterStack.isEmpty();
	}

	@Override
	public ItemStack assemble(CraftingInput container, HolderLookup.Provider registryAccess) {
		return new ItemStack(ModItems.WHEAT_DOUGH.get());
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingInput container) {
		NonNullList<ItemStack> remainders = NonNullList.withSize(container.size(), ItemStack.EMPTY);

		for (int index = 0; index < remainders.size(); ++index) {
			ItemStack selectedStack = container.getItem(index);
			if (selectedStack.is(ConventionalItemTags.WATER_BUCKETS)) {
				remainders.set(index, selectedStack.copy());
			}
		}

		return remainders;
	}

	@Override
	public RecipeSerializer<DoughRecipe> getSerializer() {
		return ModRecipeSerializers.DOUGH.get();
	}
}
