package vectorwing.farmersdelight.common.registry;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regMenu;

public class ModMenuTypes {
    public static final Supplier<MenuType<CookingPotMenu>> COOKING_POT = regMenu("cooking_pot", () -> new ExtendedScreenHandlerType<>(CookingPotMenu::new, BlockPos.STREAM_CODEC));

    public static void touch() {

    }
}
