package vectorwing.farmersdelight.common.registry;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regSound;

public class ModSounds
{
	// Stove
	public static final Supplier<SoundEvent> BLOCK_STOVE_CRACKLE = regSound("block.stove.crackle",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.stove.crackle")));

	// Cooking Pot
	public static final Supplier<SoundEvent> BLOCK_COOKING_POT_BOIL = regSound("block.cooking_pot.boil",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.cooking_pot.boil")));
	public static final Supplier<SoundEvent> BLOCK_COOKING_POT_BOIL_SOUP = regSound("block.cooking_pot.boil_soup",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.cooking_pot.boil_soup")));

	// Cutting Board
	public static final Supplier<SoundEvent> BLOCK_CUTTING_BOARD_PLACE = regSound("block.cutting_board.place_item",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.cutting_board.place_item")));
	public static final Supplier<SoundEvent> BLOCK_CUTTING_BOARD_REMOVE = regSound("block.cutting_board.remove_item",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.cutting_board.remove_item")));
	public static final Supplier<SoundEvent> BLOCK_CUTTING_BOARD_CARVE = regSound("block.cutting_board.carve_tool",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.cutting_board.carve_tool")));
	public static final Supplier<SoundEvent> BLOCK_CUTTING_BOARD_KNIFE = regSound("block.cutting_board.knife_cut",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.cutting_board.knife_cut")));

	// Rope Fence Gate
	public static final Supplier<SoundEvent> BLOCK_ROPE_FENCE_GATE_OPEN = regSound("block.rope_fence_gate.open",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.rope_fence_gate.open")));
	public static final Supplier<SoundEvent> BLOCK_ROPE_FENCE_GATE_CLOSE = regSound("block.rope_fence_gate.close",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.rope_fence_gate.close")));

	// Cabinet
	public static final Supplier<SoundEvent> BLOCK_CABINET_OPEN = regSound("block.cabinet.open",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "block.cabinet.open")));
	public static final Supplier<SoundEvent> BLOCK_CABINET_CLOSE = regSound("block.cabinet.close",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "block.cabinet.close")));

	// Skillet
	public static final Supplier<SoundEvent> BLOCK_SKILLET_SIZZLE = regSound("block.skillet.sizzle",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "block.skillet.sizzle")));
	public static final Supplier<SoundEvent> BLOCK_SKILLET_ADD_FOOD = regSound("block.skillet.add_food",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "block.skillet.add_food")));
	public static final Supplier<SoundEvent> ITEM_SKILLET_ATTACK_STRONG = regSound("item.skillet.attack.strong",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "item.skillet.attack.strong")));
	public static final Supplier<SoundEvent> ITEM_SKILLET_ATTACK_WEAK = regSound("item.skillet.attack.weak",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "item.skillet.attack.weak")));

	// Tomato Bush
    public static final Supplier<SoundEvent> ITEM_TOMATO_PICK_FROM_BUSH = regSound("block.tomatoes.pick_tomatoes",
            () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "block.tomatoes.pick_tomatoes")));

    // Food
	public static final Supplier<SoundEvent> BLOCK_FOOD_TAKE_PORTION = regSound("block.food.take_portion",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.food.take_portion")));
	public static final Supplier<SoundEvent> BLOCK_FOOD_SLICE = regSound("block.food.slice",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.food.slice")));

	public static final Supplier<SoundEvent> ENTITY_ROTTEN_TOMATO_THROW = regSound("entity.rotten_tomato.throw",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "entity.rotten_tomato.throw")));
	public static final Supplier<SoundEvent> ENTITY_ROTTEN_TOMATO_HIT = regSound("entity.rotten_tomato.hit",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "entity.rotten_tomato.hit")));

	public static void touch() {

	}
}
