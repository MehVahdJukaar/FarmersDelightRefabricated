package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import vectorwing.farmersdelight.refabricated.access.SkilletEntityRenderState;

@Mixin(HumanoidRenderState.class)
public class HumanoidRenderStateMixin implements SkilletEntityRenderState {
    @Unique
    private long fdrf$skilletFlipTimestamp = -1;

    @Override
    public long fdrf$getSkilletFlipTimestamp() {
        return fdrf$skilletFlipTimestamp;
    }

    @Override
    public void fdrf$setSkilletFlipTimestamp(long value) {
        fdrf$skilletFlipTimestamp = value;
    }
}
