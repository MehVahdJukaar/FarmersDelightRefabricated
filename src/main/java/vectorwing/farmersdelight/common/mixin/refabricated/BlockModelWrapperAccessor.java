package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.item.ModelRenderProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockModelWrapper.class)
public interface BlockModelWrapperAccessor {
    @Accessor("properties")
    ModelRenderProperties fdrf$getProperties();
}
