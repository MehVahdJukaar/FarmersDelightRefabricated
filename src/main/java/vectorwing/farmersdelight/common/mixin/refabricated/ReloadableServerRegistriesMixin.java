package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.refabricated.RefabricatedEarlyTagUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @see RefabricatedEarlyTagUtils
 */
@Mixin(ReloadableServerRegistries.class)
public class ReloadableServerRegistriesMixin {
    @Inject(method = "reload", at = @At(value = "HEAD"))
    private static void fdrf$setLootTableAccess(LayeredRegistryAccess<RegistryLayer> context, List<Registry.PendingTags<?>> updatedContextTags, ResourceManager resourceManager, Executor executor, CallbackInfoReturnable<CompletableFuture<ReloadableServerRegistries.LoadResult>> cir) {
        RefabricatedEarlyTagUtils.setLootTableResourceManager(resourceManager);
    }

    @Inject(method = "lambda$reload$0", at = @At("RETURN"))
    private static void fdrf$clearLootTableAccess(LayeredRegistryAccess layeredRegistryAccess, HolderLookup.Provider provider, RegistryAccess.Frozen newlyLoadedRegistries, CallbackInfoReturnable<ReloadableServerRegistries.LoadResult> cir) {
        RefabricatedEarlyTagUtils.resetEarlyTagCollections();
    }
}