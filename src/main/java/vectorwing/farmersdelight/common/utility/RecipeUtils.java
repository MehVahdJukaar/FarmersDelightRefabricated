package vectorwing.farmersdelight.common.utility;

import net.minecraft.resources.Identifier;
import vectorwing.farmersdelight.FarmersDelight;

public class RecipeUtils
{
	public static Identifier FDLocation(String name) {
		return Identifier.fromNamespaceAndPath(FarmersDelight.MODID, name);
	}
}
