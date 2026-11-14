package net.medievalweapons.port.assets;

import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.neoforged.fml.loading.FMLPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Stores the "don't show again" flag in config/medievalweapons-port-assets.json (separate from the mod's own config). */
public final class AssetNoticeSettings {

    private static final Logger LOGGER = LoggerFactory.getLogger("medievalweapons");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String KEY = "dont_show_again";

    private AssetNoticeSettings() {
    }

    private static Path file() {
        return FMLPaths.CONFIGDIR.get().resolve("medievalweapons-port-assets.json");
    }

    public static boolean dontShowAgain() {
        Path file = file();
        if (!Files.isRegularFile(file)) {
            return false;
        }
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);
            return json != null && json.has(KEY) && json.get(KEY).getAsBoolean();
        } catch (Exception e) {
            LOGGER.warn("[medievalweapons-port] Could not read {}", file, e);
            return false;
        }
    }

    public static void setDontShowAgain(boolean value) {
        Path file = file();
        JsonObject json = new JsonObject();
        json.addProperty(KEY, value);
        try {
            Files.createDirectories(file.getParent());
            try (Writer writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                GSON.toJson(json, writer);
            }
        } catch (Exception e) {
            LOGGER.warn("[medievalweapons-port] Could not write {}", file, e);
        }
    }
}
