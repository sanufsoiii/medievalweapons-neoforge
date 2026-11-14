package net.medievalweapons.port.assets;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.resources.IoSupplier;

/**
 * Read-only view of the original jar (or of one nested pack folder inside it, via {@code prefix}): client assets of the
 * allowed namespaces only, never any server data.
 */
public class OriginalAssetsPackResources extends FilePackResources {

    // medievalweapons: textures, models, sounds, lang, particles, Better Combat attack animations;
    // emi: the original's EMI recipe defaults (assets/emi/recipe/defaults/medievalweapons.json).
    static final Set<String> NAMESPACES = Set.of("medievalweapons", "emi");

    public OriginalAssetsPackResources(PackLocationInfo info, SharedZipFileAccess zip, String prefix) {
        super(info, zip, prefix);
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        if (type != PackType.CLIENT_RESOURCES) {
            return Set.of();
        }
        Set<String> result = new HashSet<>(super.getNamespaces(type));
        result.retainAll(NAMESPACES);
        return result;
    }

    @Override
    public IoSupplier<InputStream> getResource(PackType type, ResourceLocation location) {
        if (type != PackType.CLIENT_RESOURCES || !NAMESPACES.contains(location.getNamespace())) {
            return null;
        }
        return super.getResource(type, location);
    }

    @Override
    public void listResources(PackType type, String namespace, String path, ResourceOutput output) {
        if (type != PackType.CLIENT_RESOURCES || !NAMESPACES.contains(namespace)) {
            return;
        }
        super.listResources(type, namespace, path, output);
    }

    @Override
    public IoSupplier<InputStream> getRootResource(String... elements) {
        // Neither the jar root nor the nested packs' pack.mcmeta/pack.png are exposed.
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getMetadataSection(MetadataSectionSerializer<T> serializer) {
        if (serializer == PackMetadataSection.TYPE) {
            return (T) new PackMetadataSection(Component.translatable("medievalweapons.port.assets.pack_description"),
                    SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
        }
        return null;
    }
}
