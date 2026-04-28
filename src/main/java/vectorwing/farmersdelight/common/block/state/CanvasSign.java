package vectorwing.farmersdelight.common.block.state;

import net.minecraft.world.item.DyeColor;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;

;

public interface CanvasSign
{
	/**
	 * Returns this sign's background dye dye. If null, the sign is uncolored (beige).
	 */
	@Nullable
	DyeColor getBackgroundColor();

	/**
	 * Checks a config to determine if the background dye is considered "dark".
	 */
	default boolean isDarkBackground() {
		DyeColor backgroundDye = this.getBackgroundColor();
		return backgroundDye != null && Configuration.CANVAS_SIGN_DARK_BACKGROUND_LIST.get().contains(backgroundDye.getName());
	}
}
