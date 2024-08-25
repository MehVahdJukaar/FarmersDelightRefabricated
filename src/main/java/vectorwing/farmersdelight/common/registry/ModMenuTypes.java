package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;

import java.util.function.Supplier;

public class ModMenuTypes
{
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, FarmersDelight.MODID);

	public static final Supplier<MenuType<CookingPotMenu>> COOKING_POT = MENU_TYPES
				.register("cooking_pot", () -> new ExtendedScreenHandlerType<>(CookingPotMenu::new, BlockPos.STREAM_CODEC));
}
