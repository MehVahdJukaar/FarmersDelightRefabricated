package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.renderer.item.CuboidItemModelWrapper;
import net.minecraft.client.renderer.item.ModelRenderProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CuboidItemModelWrapper.class)
public interface CuboidItemModelWrapperAccessor {
    @Accessor("properties")
    ModelRenderProperties fdrf$getProperties();

    @Accessor("properties")
    @Mutable
    void fdrf$setProperties(ModelRenderProperties value);
}
