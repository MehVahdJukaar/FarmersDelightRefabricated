package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * These methods should really be public.
 * Thanks Mojang.
 */
@Mixin(GhostSlots.class)
public interface GhostSlotsInvoker {
    @Invoker("setInput")
    void fdrf$setInput(Slot slot, ContextMap contextMap, SlotDisplay slotDisplay);

    @Invoker("setResult")
    void fdrf$setResult(Slot slot, ContextMap contextMap, SlotDisplay slotDisplay);
}
