package vectorwing.farmersdelight.refabricated;

import vectorwing.farmersdelight.common.loot.modifier.AddItemModifier;
import vectorwing.farmersdelight.common.loot.modifier.FDAddTableLootModifier;

import java.util.List;

public class LootModStuff {

    private static final List<LootModifier> LOOT_MODIFIERS = List.of(
            new AddItemModifier(),
            new AddItemModifier(),
            new FDAddTableLootModifier()
    );

    public static void onLootEvent() {
        //iterate over mods and apply

    }

}
