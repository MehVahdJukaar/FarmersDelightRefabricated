package vectorwing.farmersdelight.refabricated;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public abstract class LootModifier {
    //dummy class
    public LootModifier(@Nullable LootItemCondition[] conditionsIn) {
    }

    protected abstract @NonNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext);
}
