package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;

public class ModMenuTypes
{
	public static final LazyRegistrar<MenuType<?>> MENU_TYPES = LazyRegistrar.create(BuiltInRegistries.MENU, FarmersDelight.MODID);

	public static final RegistryObject<MenuType<CookingPotMenu>> COOKING_POT = MENU_TYPES
			.register("cooking_pot", () -> new ExtendedScreenHandlerType<>(CookingPotMenu::new));
}
