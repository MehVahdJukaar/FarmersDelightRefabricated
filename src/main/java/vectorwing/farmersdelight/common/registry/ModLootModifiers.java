package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.Codec;
import io.github.fabricators_of_create.porting_lib.loot.IGlobalLootModifier;
import io.github.fabricators_of_create.porting_lib.loot.PortingLibLoot;
import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.loot.modifier.AddItemModifier;
import vectorwing.farmersdelight.common.loot.modifier.AddLootTableModifier;
import vectorwing.farmersdelight.common.loot.modifier.PastrySlicingModifier;
import vectorwing.farmersdelight.common.loot.modifier.ReplaceItemModifier;

import java.util.function.Supplier;

public class ModLootModifiers
{
	public static final LazyRegistrar<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = LazyRegistrar.create(PortingLibLoot.GLOBAL_LOOT_MODIFIER_SERIALIZERS_KEY, FarmersDelight.MODID);

	public static final Supplier<Codec<? extends IGlobalLootModifier>> ADD_ITEM = LOOT_MODIFIERS.register("add_item", AddItemModifier.CODEC);
    public static final Supplier<Codec<? extends IGlobalLootModifier>> REPLACE_ITEM = LOOT_MODIFIERS.register("replace_item", ReplaceItemModifier.CODEC);
	public static final Supplier<Codec<? extends IGlobalLootModifier>> ADD_LOOT_TABLE = LOOT_MODIFIERS.register("add_loot_table", AddLootTableModifier.CODEC);
	public static final Supplier<Codec<? extends IGlobalLootModifier>> PASTRY_SLICING = LOOT_MODIFIERS.register("pastry_slicing", PastrySlicingModifier.CODEC);
}
