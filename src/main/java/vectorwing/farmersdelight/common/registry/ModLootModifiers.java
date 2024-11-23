package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.MapCodec;
import io.github.fabricators_of_create.porting_lib.loot.IGlobalLootModifier;
import io.github.fabricators_of_create.porting_lib.loot.PortingLibLoot;
import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.loot.modifier.AddItemModifier;
import vectorwing.farmersdelight.common.loot.modifier.FDAddTableLootModifier;
import vectorwing.farmersdelight.common.loot.modifier.PastrySlicingModifier;
import vectorwing.farmersdelight.common.loot.modifier.ReplaceItemModifier;

import java.util.function.Supplier;

public class ModLootModifiers
{
	public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(PortingLibLoot.GLOBAL_LOOT_MODIFIER_SERIALIZERS, FarmersDelight.MODID);

	public static final Supplier<MapCodec<AddItemModifier>> ADD_ITEM = LOOT_MODIFIERS.register("add_item", AddItemModifier.CODEC);
    public static final Supplier<MapCodec<ReplaceItemModifier>> REPLACE_ITEM = LOOT_MODIFIERS.register("replace_item", ReplaceItemModifier.CODEC);
	public static final Supplier<MapCodec<FDAddTableLootModifier>> ADD_LOOT_TABLE = LOOT_MODIFIERS.register("add_loot_table", FDAddTableLootModifier.CODEC);
	public static final Supplier<MapCodec<PastrySlicingModifier>> PASTRY_SLICING = LOOT_MODIFIERS.register("pastry_slicing", PastrySlicingModifier.CODEC);
}
