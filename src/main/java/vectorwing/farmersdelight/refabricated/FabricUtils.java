
package vectorwing.farmersdelight.refabricated;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

// put general fabric stuff here
public class FabricUtils {

    public static <T>Supplier<T> register(String name, Supplier<T> supplier, Registry<T> reg) {
        T object = supplier.get();
        Registry.register(reg, name, object);
        return () -> object;
    }

    public static Supplier<Block> regBlock(String name, Supplier<Block> supplier) {
        return register(name, supplier, BuiltInRegistries.BLOCK);
    }
}
