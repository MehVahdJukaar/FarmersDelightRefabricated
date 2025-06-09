package vectorwing.farmersdelight.common.utility;

/**
 * Util for helping with rendering elements across the mod.
 */
public class ClientRenderUtils
{
	public static boolean isCursorInsideBounds(int iconX, int iconY, int iconWidth, int iconHeight, double cursorX, double cursorY) {
		return iconX <= cursorX && cursorX < iconX + iconWidth && iconY <= cursorY && cursorY < iconY + iconHeight;
	}
}
