package net.medievalweapons.port.assets;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.FilePackResources.SharedZipFileAccess;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.AddPackFindersEvent;

/**
 * Registers the player's original MedievalWeapons jar as always-on, top-most built-in resource packs (client only):
 * the jar root, plus the client half of the nested compat packs {@code resourcepacks/<mod>_medievalweapons_compat/}
 * for the compat mods that are loaded (Fabric registered those nested packs as built-in resource packs of the mod).
 */
public final class OriginalAssetsPackFinder {

    public static final String PACK_ID = "medievalweapons:original_assets";

    // modId -> nested pack folder in the original jar. Only the compat mods this port supports (see CompatInit).
    private static final Map<String, String> COMPAT_PACKS = new LinkedHashMap<>();

    static {
        COMPAT_PACKS.put("deeperdarker", "deeperdarker_medievalweapons_compat");
        COMPAT_PACKS.put("mythicmetals", "mythicmetals_medievalweapons_compat");
        COMPAT_PACKS.put("mythicupgrades", "mythicupgrades_medievalweapons_compat");
        COMPAT_PACKS.put("paradise_lost", "paradise_lost_medievalweapons_compat");
    }

    private OriginalAssetsPackFinder() {
    }

    public static void onAddPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() != PackType.CLIENT_RESOURCES) {
            return;
        }
        // Scanned inside loadPacks so that PackRepository.reload() picks up a jar added at runtime.
        event.addRepositorySource(consumer -> {
            Optional<Path> jar = OriginalAssetsLocator.find();
            if (jar.isEmpty()) {
                return;
            }
            Path file = jar.get();
            consumer.accept(createPack(PACK_ID, "", file, Component.translatable("medievalweapons.port.assets.pack_name")));
            for (Map.Entry<String, String> entry : COMPAT_PACKS.entrySet()) {
                if (ModList.get().isLoaded(entry.getKey())) {
                    consumer.accept(createPack(PACK_ID + "/" + entry.getValue(), "resourcepacks/" + entry.getValue(), file,
                            Component.translatable("medievalweapons.port.assets.compat_pack_name", entry.getKey())));
                }
            }
        });
    }

    private static Pack createPack(String id, String prefix, Path file, Component title) {
        PackLocationInfo info = new PackLocationInfo(id, title, PackSource.BUILT_IN, Optional.empty());
        Pack.ResourcesSupplier supplier = new Pack.ResourcesSupplier() {
            @Override
            public PackResources openPrimary(PackLocationInfo location) {
                return new OriginalAssetsPackResources(location, new SharedZipFileAccess(file.toFile()), prefix);
            }

            @Override
            public PackResources openFull(PackLocationInfo location, Pack.Metadata metadata) {
                return openPrimary(location);
            }
        };
        Pack.Metadata metadata = new Pack.Metadata(Component.translatable("medievalweapons.port.assets.pack_description"),
                PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of());
        return new Pack(info, supplier, metadata, new PackSelectionConfig(true, Pack.Position.TOP, true));
    }
}
