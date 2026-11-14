package net.medievalweapons.compat;

import net.medievalweapons.entity.renderer.FranciscaEntityRenderer;
import net.medievalweapons.entity.renderer.JavelinEntityRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class CompatRender {

    public static void loadRenderer(EntityRenderersEvent.RegisterRenderers event) {
        // TODO: Better End (BCLib) compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (8 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        // TODO: Better Nether (BCLib) compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (8 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        // TODO: DragonLoot compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (4 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        if (CompatItems.isMythicMetalsLoaded) {
            event.registerEntityRenderer(CompatEntities.ADAMANTITE_FRANCISCA, FranciscaEntityRenderer::new);
            event.registerEntityRenderer(CompatEntities.ADAMANTITE_JAVELIN, JavelinEntityRenderer::new);
        }
        if (CompatItems.isParadiseLostLoaded) {
            event.registerEntityRenderer(CompatEntities.OLVITE_FRANCISCA, FranciscaEntityRenderer::new);
            event.registerEntityRenderer(CompatEntities.OLVITE_JAVELIN, JavelinEntityRenderer::new);
        }
        // TODO: Oh The Biomes You'll Go compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (4 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        // TODO: Additional Additions compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (6 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        if (CompatItems.isMythicUpgradesLoaded) {
            event.registerEntityRenderer(CompatEntities.RUBY_NETHERITE_FRANCISCA, FranciscaEntityRenderer::new);
            event.registerEntityRenderer(CompatEntities.RUBY_NETHERITE_JAVELIN, JavelinEntityRenderer::new);
            event.registerEntityRenderer(CompatEntities.SAPPHIRE_NETHERITE_FRANCISCA, FranciscaEntityRenderer::new);
            event.registerEntityRenderer(CompatEntities.SAPPHIRE_NETHERITE_JAVELIN, JavelinEntityRenderer::new);
        }
        if (CompatItems.isDeeperDarkerLoaded) {
            event.registerEntityRenderer(CompatEntities.WARDEN_FRANCISCA, FranciscaEntityRenderer::new);
            event.registerEntityRenderer(CompatEntities.WARDEN_JAVELIN, JavelinEntityRenderer::new);
        }
        // TODO: EarlyStage compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (6 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
        // TODO: Oblivion compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (8 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatRender.java.
    }
}