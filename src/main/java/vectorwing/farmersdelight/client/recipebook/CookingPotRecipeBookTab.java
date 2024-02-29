package vectorwing.farmersdelight.client.recipebook;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import java.util.EnumSet;

public enum CookingPotRecipeBookTab
{
	MEALS("meals"),
	DRINKS("drinks"),
	MISC("misc");

	public final String name;

	CookingPotRecipeBookTab(String name) {
		this.name = name;
	}

	public static CookingPotRecipeBookTab findByName(String name) {
		for (CookingPotRecipeBookTab value : values()) {
			if (value.name.equals(name)) {
				return value;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
