package net.medievalweapons.compat;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.medievalweapons.entity.renderer.FranciscaEntityRenderer;
import net.medievalweapons.entity.renderer.JavelinEntityRenderer;

@Environment(EnvType.CLIENT)
public class CompatRender {

    public static void loadRenderer() {
        // TODO: Better End (BCLib) compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (8 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        // TODO: Better Nether (BCLib) compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (8 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        // TODO: DragonLoot compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (4 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        if (CompatItems.isMythicMetalsLoaded) {
            EntityRendererRegistry.register(CompatEntities.ADAMANTITE_FRANCISCA, FranciscaEntityRenderer::new);
            EntityRendererRegistry.register(CompatEntities.ADAMANTITE_JAVELIN, JavelinEntityRenderer::new);
        }
        if (CompatItems.isParadiseLostLoaded) {
            EntityRendererRegistry.register(CompatEntities.OLVITE_FRANCISCA, FranciscaEntityRenderer::new);
            EntityRendererRegistry.register(CompatEntities.OLVITE_JAVELIN, JavelinEntityRenderer::new);
        }
        // TODO: Oh The Biomes You'll Go compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (4 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        // TODO: Additional Additions compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (6 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        if (CompatItems.isMythicUpgradesLoaded) {
            EntityRendererRegistry.register(CompatEntities.RUBY_NETHERITE_FRANCISCA, FranciscaEntityRenderer::new);
            EntityRendererRegistry.register(CompatEntities.RUBY_NETHERITE_JAVELIN, JavelinEntityRenderer::new);
            EntityRendererRegistry.register(CompatEntities.SAPPHIRE_NETHERITE_FRANCISCA, FranciscaEntityRenderer::new);
            EntityRendererRegistry.register(CompatEntities.SAPPHIRE_NETHERITE_JAVELIN, JavelinEntityRenderer::new);
        }
        if (CompatItems.isDeeperDarkerLoaded) {
            EntityRendererRegistry.register(CompatEntities.WARDEN_FRANCISCA, FranciscaEntityRenderer::new);
            EntityRendererRegistry.register(CompatEntities.WARDEN_JAVELIN, JavelinEntityRenderer::new);
        }
        // TODO: EarlyStage compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (6 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        // TODO: Oblivion compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (8 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
    }
}