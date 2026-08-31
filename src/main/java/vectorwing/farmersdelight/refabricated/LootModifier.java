package vectorwing.farmersdelight.refabricated;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class LootModifier {
    //dummy class
    public LootModifier(@Nullable LootItemCondition[] conditionsIn) {
    }

	public LootModifier(LootItemCondition[] conditionsIn, ResourceKey<LootTable> lootTable) {

	}

	protected abstract @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext);
}
