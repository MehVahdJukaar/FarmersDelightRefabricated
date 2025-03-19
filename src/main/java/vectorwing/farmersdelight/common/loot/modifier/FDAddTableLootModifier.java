package vectorwing.farmersdelight.common.loot.modifier;

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
        super(conditionsIn);
        this.lootTable = lootTable;
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (Configuration.GENERATE_FD_CHEST_LOOT.get()) {
            // Refabricated: The game will loop if we don't make a new context.
//            LootContext extraContext = new LootContext.Builder(((LootContextAccessor) context).getParams()).create(Optional.empty());
//            extraContext.setQueriedLootTableId(this.lootTable.location());
//            context.getResolver().get(Registries.LOOT_TABLE, this.lootTable).ifPresent((extraTable) -> {
//                extraTable.value().getRandomItemsRaw(extraContext, createStackSplitter(context.getLevel(), generatedLoot::add));
//            });
        }
        return generatedLoot;
    }

}
