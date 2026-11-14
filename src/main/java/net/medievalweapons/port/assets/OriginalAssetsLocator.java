package net.medievalweapons.port.assets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

import net.neoforged.fml.loading.FMLPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Finds the original MedievalWeapons jar that the player put into {@code <gameDir>/resourcepacks/}.
 * Only that folder is scanned (no recursion, no network). The result is cached until {@link #rescan()}.
 */
public final class OriginalAssetsLocator {

    private static final Logger LOGGER = LoggerFactory.getLogger("medievalweapons");
    static final String MARKER = "assets/medievalweapons/textures/item/iron_long_sword.png";

    private static boolean scanned = false;
    private static Path cached = null;

    private OriginalAssetsLocator() {
    }

    public static synchronized Optional<Path> find() {
        if (!scanned) {
            scanned = true;
            cached = scan();
            if (cached != null) {
                LOGGER.info("[medievalweapons-port] ORIGINAL_ASSETS_FOUND {}", cached.getFileName());
            } else {
                LOGGER.info("[medievalweapons-port] ORIGINAL_ASSETS_MISSING");
            }
        }
        return Optional.ofNullable(cached);
    }

    public static synchronized Optional<Path> rescan() {
        scanned = false;
        cached = null;
        return find();
    }

    public static Path resourcePacksDir() {
        return FMLPaths.GAMEDIR.get().resolve("resourcepacks");
    }

    private static Path scan() {
        Path dir = resourcePacksDir();
        if (!Files.isDirectory(dir)) {
            return null;
        }
        List<Path> matches = new ArrayList<>();
        try (Stream<Path> files = Files.list(dir)) {
            for (Path file : (Iterable<Path>) files::iterator) {
                String name = file.getFileName().toString().toLowerCase();
                if (Files.isRegularFile(file) && (name.endsWith(".jar") || name.endsWith(".zip")) && hasMarker(file)) {
                    matches.add(file);
                }
            }
        } catch (IOException | RuntimeException e) {
            LOGGER.warn("[medievalweapons-port] Could not list {}", dir, e);
            return null;
        }
        if (matches.isEmpty()) {
            return null;
        }
        matches.sort(Comparator.comparing(OriginalAssetsLocator::modifiedTime).reversed());
        for (int i = 1; i < matches.size(); i++) {
            LOGGER.info("[medievalweapons-port] Ignoring older original jar {}", matches.get(i).getFileName());
        }
        return matches.get(0);
    }

    private static boolean hasMarker(Path file) {
        try (ZipFile zip = new ZipFile(file.toFile())) {
            return zip.getEntry(MARKER) != null;
        } catch (IOException | RuntimeException e) {
            LOGGER.warn("[medievalweapons-port] Could not read {}", file.getFileName(), e);
            return false;
        }
    }

    private static FileTime modifiedTime(Path file) {
        try {
            return Files.getLastModifiedTime(file);
        } catch (IOException e) {
            return FileTime.fromMillis(0L);
        }
    }
}
