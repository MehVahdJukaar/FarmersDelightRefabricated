package vectorwing.farmersdelight.refabricated;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import vectorwing.farmersdelight.common.loot.modifier.AddItemModifier;
import vectorwing.farmersdelight.common.loot.modifier.FDAddTableLootModifier;

import java.util.List;

public class LootModificationEvents {

    public static void init(){
        //man this wont cut it....
        LootTableEvents.MODIFY.register(LootModificationEvents::modifyTable);
    }

    private static void modifyTable(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, HolderLookup.Provider registries){

    }

    private static final List<LootModifier> LOOT_MODIFIERS = List.of(
            new AddItemModifier(),
            new AddItemModifier(),
            new FDAddTableLootModifier()
    );

    public static void onLootEvent() {
        //iterate over mods and apply

    }

}
