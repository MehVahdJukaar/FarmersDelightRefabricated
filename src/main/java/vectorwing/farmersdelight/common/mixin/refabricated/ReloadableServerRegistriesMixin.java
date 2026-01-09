package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.storage.loot.LootDataType;
import net.minecraft.world.level.storage.loot.Validatable;
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
public class ReloadableServerRegistriesMixin {
    @Inject(method = "lambda$reload$0", at = @At(value = "HEAD"))
    private static <T extends Validatable> void fdrf$setLootTableAccess(RegistryOps registryOps, ResourceManager resourceManager, Executor executor, LootDataType<T> lootDataType, CallbackInfoReturnable<CompletableFuture> cir) {
        if (lootDataType != LootDataType.TABLE)
            return;

        TagUtils.setLootTableResourceManager(resourceManager);
    }

    @Inject(method = "lambda$reload$1", at = @At("RETURN"))
    private static void fdrf$clearLootTableAccess(LayeredRegistryAccess layeredRegistryAccess, HolderLookup.Provider provider, List list, CallbackInfoReturnable<ReloadableServerRegistries.LoadResult> cir) {
        TagUtils.resetEarlyTagCollections();
    }
}