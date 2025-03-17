package vectorwing.farmersdelight.common.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regTab;

public class ModCreativeTabs {
    public static final Supplier<CreativeModeTab> TAB_FARMERS_DELIGHT = regTab(FarmersDelight.MODID,
            () -> FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.farmersdelight"))
                    .icon(() -> new ItemStack(ModBlocks.STOVE.get()))
                    .displayItems((parameters, output) -> ModItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
                    .build());
}
