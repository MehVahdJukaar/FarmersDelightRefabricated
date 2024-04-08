package vectorwing.farmersdelight;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class FarmersDelightASM implements Runnable {
    public static final String KNIFE_ENCHANTMENT_CATEGORY = "FARMERS_DELIGHT_KNIFE";

    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();
        String enchantmentTarget = remapper.mapClassName("intermediary", "net.minecraft.class_1886");
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass(KNIFE_ENCHANTMENT_CATEGORY, "vectorwing.farmersdelight.common.item.enchantment.category.KnifeEnchantmentCategory").build();
    }

}
