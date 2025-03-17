package vectorwing.farmersdelight.common.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.FabricUtils.regSound;

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
	public static final Supplier<SoundEvent> BLOCK_CUTTING_BOARD_KNIFE = regSound("block.cutting_board.knife",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.cutting_board.knife")));

	// Cabinet
	public static final Supplier<SoundEvent> BLOCK_CABINET_OPEN = regSound("block.cabinet.open",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.cabinet.open")));
	public static final Supplier<SoundEvent> BLOCK_CABINET_CLOSE = regSound("block.cabinet.close",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.cabinet.close")));

	// Skillet
	public static final Supplier<SoundEvent> BLOCK_SKILLET_SIZZLE = regSound("block.skillet.sizzle",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.skillet.sizzle")));
	public static final Supplier<SoundEvent> BLOCK_SKILLET_ADD_FOOD = regSound("block.skillet.add_food",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.skillet.add_food")));
	public static final Supplier<SoundEvent> ITEM_SKILLET_ATTACK_STRONG = regSound("item.skillet.attack.strong",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "item.skillet.attack.strong")));
	public static final Supplier<SoundEvent> ITEM_SKILLET_ATTACK_WEAK = regSound("item.skillet.attack.weak",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "item.skillet.attack.weak")));

	// Tomato Bush
	public static final Supplier<SoundEvent> ITEM_TOMATO_PICK_FROM_BUSH = regSound("block.tomato_bush.pick_tomatoes",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block.tomato_bush.pick_tomatoes")));

	public static final Supplier<SoundEvent> ENTITY_ROTTEN_TOMATO_THROW = regSound("entity.rotten_tomato.throw",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "entity.rotten_tomato.throw")));
	public static final Supplier<SoundEvent> ENTITY_ROTTEN_TOMATO_HIT = regSound("entity.rotten_tomato.hit",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "entity.rotten_tomato.hit")));

	public static void touch() {

	}
}
