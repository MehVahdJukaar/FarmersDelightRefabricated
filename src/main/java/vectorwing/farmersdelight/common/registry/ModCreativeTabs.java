package vectorwing.farmersdelight.common.registry;

import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.function.Supplier;

public class ModCreativeTabs
{
	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FarmersDelight.MODID);

	public static final Supplier<CreativeModeTab> TAB_FARMERS_DELIGHT = CREATIVE_TABS.register(FarmersDelight.MODID,
			() -> FabricItemGroup.builder()
					.title(Component.translatable("itemGroup.farmersdelight"))
					.icon(() -> new ItemStack(ModBlocks.STOVE.get()))
					.displayItems((parameters, output) -> ModItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
					.build());
}
