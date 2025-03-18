package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.storage.loot.LootDataType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.refabricated.TagUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @see TagUtils
 */
@Mixin(ReloadableServerRegistries.class)
public class Mixin_ReloadableServerRegistries {
    @Inject(method = "method_58276", at = @At(value = "HEAD"))
    private static <T> void enchiridion$setLootTableAccess(RegistryOps registryOps, ResourceManager resourceManager, Executor executor, LootDataType<T> lootDataType, CallbackInfoReturnable<CompletableFuture> cir) {
        if (lootDataType != LootDataType.TABLE)
            return;

        TagUtils.setLootTableResourceManager(resourceManager);
    }

    @Inject(method = "method_58288", at = @At("RETURN"))
    private static void enchiridion$clearLootTableAccess(LayeredRegistryAccess layeredRegistryAccess, List list, CallbackInfoReturnable<LayeredRegistryAccess> cir) {
        TagUtils.setLootTableResourceManager(null);
        TagUtils.resetEarlyTagCollections();
    }
}