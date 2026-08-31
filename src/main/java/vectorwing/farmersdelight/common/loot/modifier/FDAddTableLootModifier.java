package vectorwing.farmersdelight.common.loot.modifier;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.refabricated.LootModifier;

/**
 * Credits to Commoble for this implementation!
 */
public class FDAddTableLootModifier extends LootModifier {

    private final ResourceKey<LootTable> lootTable;

	public FDAddTableLootModifier(LootItemCondition[] conditionsIn, ResourceKey<LootTable> lootTable) {
		super(conditionsIn, lootTable);
		this.lootTable = lootTable;
	}

	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (Configuration.GENERATE_FD_CHEST_LOOT.get()) {
			// return super.doApply(generatedLoot, context);
		}
		return generatedLoot;
	}

//	@Override
	public MapCodec<?> codec() {
		return null;
	}
}
