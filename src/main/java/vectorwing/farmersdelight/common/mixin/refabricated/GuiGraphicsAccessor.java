package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiGraphics.class)
public interface GuiGraphicsAccessor {

    @Accessor("scissorStack")
    GuiGraphics.ScissorStack fdrf$getScissorStack();
}
