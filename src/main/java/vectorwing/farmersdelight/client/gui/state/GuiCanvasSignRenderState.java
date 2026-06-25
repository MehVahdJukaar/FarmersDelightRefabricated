package vectorwing.farmersdelight.client.gui.state;

import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.world.item.DyeColor;
import org.jspecify.annotations.Nullable;

public record GuiCanvasSignRenderState(Model signModel,
                                       @Nullable DyeColor dye,
                                       int x0,
                                       int y0,
                                       int x1,
                                       int y1,
                                       float scale,
                                       @Nullable ScreenRectangle scissorArea,
                                       @Nullable ScreenRectangle bounds) implements PictureInPictureRenderState {
	public GuiCanvasSignRenderState(Model model, @Nullable DyeColor dye, int i, int j, int k, int l, float f, @Nullable ScreenRectangle screenRectangle) {
        this(model, dye, i, j, k, l, f, screenRectangle, PictureInPictureRenderState.getBounds(i, j, k, l, screenRectangle));
    }
}