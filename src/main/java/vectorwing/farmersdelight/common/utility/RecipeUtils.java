package vectorwing.farmersdelight.common.utility;

import com.google.common.base.Preconditions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import vectorwing.farmersdelight.FarmersDelight;

public class RecipeUtils
{
	// Copyright (c) 2014-2015 mezz
	public static ItemStack getResultItem(Recipe<?> recipe) {
        RegistryAccess registryAccess = Preconditions.checkNotNull(Minecraft.getInstance().level,
                "level must not be null.").registryAccess();
		return recipe.getResultItem(registryAccess);
	}

	public static ResourceLocation FDLocation(String name) {
		return ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, name);
	}
}
