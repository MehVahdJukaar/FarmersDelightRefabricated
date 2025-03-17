package vectorwing.farmersdelight.refabricated;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import vectorwing.farmersdelight.common.loot.modifier.AddItemModifier;
import vectorwing.farmersdelight.common.loot.modifier.FDAddTableLootModifier;

import java.util.List;

public class LootModStuff {

    public static void init(){
        //man this wont cut it....
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, supplier, setter) -> {
            //iterate over mods and apply
            for (LootModifier mod : LOOT_MODIFIERS) {
                mod.doApply().modifyLoot(resourceManager, lootManager, id, supplier, setter);
            }
        });
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
