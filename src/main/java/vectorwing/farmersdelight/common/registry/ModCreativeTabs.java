package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;

public class ModCreativeTabs
{
	public static final LazyRegistrar<CreativeModeTab> CREATIVE_TABS = LazyRegistrar.create(Registries.CREATIVE_MODE_TAB, FarmersDelight.MODID);

	public static final RegistryObject<CreativeModeTab> TAB_FARMERS_DELIGHT = CREATIVE_TABS.register(FarmersDelight.MODID,
			() -> FabricItemGroup.builder()
					.title(Component.translatable("itemGroup.farmersdelight"))
					.icon(() -> new ItemStack(ModBlocks.STOVE.get()))
					.displayItems((parameters, output) -> ModItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
					.build());
}
