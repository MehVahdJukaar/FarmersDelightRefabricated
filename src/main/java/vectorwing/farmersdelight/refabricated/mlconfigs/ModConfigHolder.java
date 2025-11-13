package vectorwing.farmersdelight.refabricated.mlconfigs;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public abstract class ModConfigHolder {

    private static final Map<Identifier, ModConfigHolder> CONFIG_STORAGE = new ConcurrentHashMap<>(); //wack. multithreading mod loading

    public static void addTrackedSpec(ModConfigHolder spec) {
        var old = CONFIG_STORAGE.put(spec.getId(), spec);
        if (old != null) {
            throw new IllegalStateException("Duplicate config type for with id " + spec.getId());
        }
    }

    public static Collection<ModConfigHolder> getTrackedSpecs() {
        return CONFIG_STORAGE.values();
    }

    @Nullable
    public static ModConfigHolder getConfigSpec(Identifier configId) {
        return CONFIG_STORAGE.get(configId);
    }

    private final Identifier configId;
    private final String fileName;
    private final Component readableName;
    private final Path filePath;
    private final ConfigType type;
    @Nullable
    private final Runnable changeCallback;

    protected ModConfigHolder(Identifier id, String fileExtension, Path configDirectory, ConfigType type, @Nullable Runnable changeCallback) {
        this.configId = id;
        this.fileName = id.getNamespace() + "-" + id.getPath() + "." + fileExtension;
        this.filePath = configDirectory.resolve(fileName);
        this.type = type;
        this.changeCallback = changeCallback;
        this.readableName = Component.literal(getReadableName(id.toDebugFileName() + "_configs"));

        ModConfigHolder.addTrackedSpec(this);
    }

    public static String getReadableName(String name) {
        return Arrays.stream((name).replace(":", "_").split("_"))
                .map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public Component getReadableName() {
        return readableName;
    }

    protected void onRefresh() {
        if (this.changeCallback != null) {
            this.changeCallback.run();
        }
    }

    public boolean isLoaded() {
        return true;
    }

    public abstract void forceLoad();

    public ConfigType getConfigType() {
        return type;
    }

    public String getModId() {
        return configId.getNamespace();
    }

    public Identifier getId() {
        return configId;
    }

    public boolean isSynced() {
        return this.type.isSynced();
    }

    public String getFileName() {
        return fileName;
    }

    public Path getFullPath() {
        return filePath;
    }

    public abstract void loadFromBytes(InputStream stream);

    @Nullable
    @Environment(EnvType.CLIENT)
    public Screen makeScreen(Screen parent) {
        return makeScreen(parent, null);
    }

    @Nullable
    @Environment(EnvType.CLIENT)
    public abstract Screen makeScreen(Screen parent, @Nullable Identifier background);

    //serverside method
    public abstract boolean hasConfigScreen();

    //send configs from server -> client
    public void syncConfigsToPlayer(ServerPlayer player) {
        /*
        if (this.isSynced()) {
            try {
                final byte[] configData = Files.readAllBytes(this.getFullPath());
                NetworkHelper.sendToClientPlayer(player, new ClientBoundSyncConfigsMessage(configData, this.getId()));
            } catch (IOException e) {
                Moonlight.LOGGER.error("Failed to sync common configs {}", this.getFileName(), e);
            }
        } else throw new UnsupportedOperationException("Tried to sync a config of type " + this.getConfigType());
         */
    }


    //called on server. sync server -> all clients
    public void sendSyncedConfigsToAllPlayers() {
        /*
        if (this.isSynced()) {
            MinecraftServer currentServer = PlatHelper.getCurrentServer();
            if (currentServer != null) {
                PlayerList playerList = currentServer.getPlayerList();
                for (ServerPlayer player : playerList.getPlayers()) {
                    syncConfigsToPlayer(player);
                }
            }
        } else throw new UnsupportedOperationException("Tried to sync a config of type " + this.getConfigType());
         */
    }

    public static class ConfigLoadingException extends RuntimeException {
        public ConfigLoadingException(ModConfigHolder config, Exception cause) {
            super("Failed to load config file " + config.getFileName() + " of type " + config.getConfigType() + " for mod " + config.getModId() + ". Try deleting it", cause);
        }
    }
}
