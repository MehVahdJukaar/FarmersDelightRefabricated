package vectorwing.farmersdelight.common.registry;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModSounds {

    static final List<Supplier<?>> SOUNDS = new ArrayList<>();
    @NotNull
    private static <T extends SoundEvent> Supplier<T> register(String id, Supplier<T> supplier) {
        var v = Suppliers.memoize(() ->
                Registry.register(BuiltInRegistries.SOUND_EVENT, FarmersDelight.res(id), supplier.get()));
        SOUNDS.add(v);
        return v;
    }

    public static void init(){
        SOUNDS.forEach(Supplier::get);
        SOUNDS.clear();
    }

    // Stove
    public static final Supplier<SoundEvent> BLOCK_STOVE_CRACKLE = register("block.stove.crackle",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "block.stove.crackle")));

    // Cooking Pot
    public static final Supplier<SoundEvent> BLOCK_COOKING_POT_BOIL = register("block.cooking_pot.boil",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "block.cooking_pot.boil")));
    public static final Supplier<SoundEvent> BLOCK_COOKING_POT_BOIL_SOUP = register("block.cooking_pot.boil_soup",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "block.cooking_pot.boil_soup")));

    // Cutting Board
    public static final Supplier<SoundEvent> BLOCK_CUTTING_BOARD_KNIFE = register("block.cutting_board.knife",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "block.cutting_board.knife")));

    // Cabinet
    public static final Supplier<SoundEvent> BLOCK_CABINET_OPEN = register("block.cabinet.open",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "block.cabinet.open")));
    public static final Supplier<SoundEvent> BLOCK_CABINET_CLOSE = register("block.cabinet.close",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "block.cabinet.close")));

    // Skillet
    public static final Supplier<SoundEvent> BLOCK_SKILLET_SIZZLE = register("block.skillet.sizzle",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "block.skillet.sizzle")));
    public static final Supplier<SoundEvent> BLOCK_SKILLET_ADD_FOOD = register("block.skillet.add_food",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "block.skillet.add_food")));
    public static final Supplier<SoundEvent> ITEM_SKILLET_ATTACK_STRONG = register("item.skillet.attack.strong",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "item.skillet.attack.strong")));
    public static final Supplier<SoundEvent> ITEM_SKILLET_ATTACK_WEAK = register("item.skillet.attack.weak",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "item.skillet.attack.weak")));

    // Tomato Bush
    public static final Supplier<SoundEvent> ITEM_TOMATO_PICK_FROM_BUSH = register("block.tomato_bush.pick_tomatoes",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "block.tomato_bush.pick_tomatoes")));

    public static final Supplier<SoundEvent> ENTITY_ROTTEN_TOMATO_THROW = register("entity.rotten_tomato.throw",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "entity.rotten_tomato.throw")));
    public static final Supplier<SoundEvent> ENTITY_ROTTEN_TOMATO_HIT = register("entity.rotten_tomato.hit",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmersDelight.MODID, "entity.rotten_tomato.hit")));


}
