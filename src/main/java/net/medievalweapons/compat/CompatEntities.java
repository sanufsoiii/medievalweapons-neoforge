package net.medievalweapons.compat;

import net.medievalweapons.MedievalMain;
import net.medievalweapons.entity.*;
import net.medievalweapons.init.EntityInit;
import net.medievalweapons.item.FranciscaItem;
import net.medievalweapons.item.JavelinItem;
import net.minecraft.world.entity.EntityType;

public class CompatEntities {
    // DragonLoot
    public static EntityType<JavelinEntity> DRAGON_JAVELIN;
    public static EntityType<FranciscaEntity> DRAGON_FRANCISCA;

    // Better Nether
    public static EntityType<FranciscaEntity> NETHER_RUBY_FRANCISCA;
    public static EntityType<JavelinEntity> NETHER_RUBY_JAVELIN;
    public static EntityType<FranciscaEntity> CINCINNASITE_FRANCISCA;
    public static EntityType<JavelinEntity> CINCINNASITE_JAVELIN;
    public static EntityType<FranciscaEntity> CINCINNASITE_DIAMOND_FRANCISCA;
    public static EntityType<JavelinEntity> CINCINNASITE_DIAMOND_JAVELIN;

    // Better End
    public static EntityType<FranciscaEntity> TERMINITE_FRANCISCA;
    public static EntityType<JavelinEntity> TERMINITE_JAVELIN;
    public static EntityType<FranciscaEntity> AETERNIUM_FRANCISCA;
    public static EntityType<JavelinEntity> AETERNIUM_JAVELIN;
    public static EntityType<FranciscaEntity> THALLASIUM_FRANCISCA;
    public static EntityType<JavelinEntity> THALLASIUM_JAVELIN;

    // Mythic Metals
    public static EntityType<JavelinEntity> ADAMANTITE_JAVELIN;
    public static EntityType<FranciscaEntity> ADAMANTITE_FRANCISCA;

    // BYG
    public static EntityType<JavelinEntity> PENDORITE_JAVELIN;
    public static EntityType<FranciscaEntity> PENDORITE_FRANCISCA;

    // Additional Additions
    public static EntityType<JavelinEntity> ROSE_GOLD_JAVELIN;
    public static EntityType<FranciscaEntity> ROSE_GOLD_FRANCISCA;
    public static EntityType<JavelinEntity> GILDED_NETHERITE_JAVELIN;
    public static EntityType<FranciscaEntity> GILDED_NETHERITE_FRANCISCA;

    // Paradise Lost
    public static EntityType<JavelinEntity> OLVITE_JAVELIN;
    public static EntityType<FranciscaEntity> OLVITE_FRANCISCA;

    // Mythic Upgrades
    public static EntityType<JavelinEntity> RUBY_NETHERITE_JAVELIN;
    public static EntityType<FranciscaEntity> RUBY_NETHERITE_FRANCISCA;
    public static EntityType<JavelinEntity> SAPPHIRE_NETHERITE_JAVELIN;
    public static EntityType<FranciscaEntity> SAPPHIRE_NETHERITE_FRANCISCA;

    // Deeper and Darker
    public static EntityType<JavelinEntity> WARDEN_JAVELIN;
    public static EntityType<FranciscaEntity> WARDEN_FRANCISCA;

    // EarlyStage
    public static EntityType<JavelinEntity> STEEL_JAVELIN;
    public static EntityType<FranciscaEntity> STEEL_FRANCISCA;
    public static EntityType<JavelinEntity> DAMASCUS_STEEL_JAVELIN;
    public static EntityType<FranciscaEntity> DAMASCUS_STEEL_FRANCISCA;

    // Oblivion
    public static EntityType<JavelinEntity> FIERY_NETHERITE_JAVELIN;
    public static EntityType<FranciscaEntity> FIERY_NETHERITE_FRANCISCA;
    public static EntityType<JavelinEntity> SCARLET_JAVELIN;
    public static EntityType<FranciscaEntity> SCARLET_FRANCISCA;
    public static EntityType<JavelinEntity> SOLARITE_JAVELIN;
    public static EntityType<FranciscaEntity> SOLARITE_FRANCISCA;

    public static void loadEntities() {
        // TODO: Better End (BCLib) compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (15 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatEntities.java.
        // TODO: Better Nether (BCLib) compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (15 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatEntities.java.
        // TODO: DragonLoot compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (7 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatEntities.java.
        if (CompatItems.isMythicMetalsLoaded) {
            ADAMANTITE_JAVELIN = EntityInit.register("adamantite_javelin", EntityInit.createJavelin((JavelinItem) CompatItems.ADAMANTITE_JAVELIN_ITEM));
            ADAMANTITE_FRANCISCA = EntityInit.register("adamantite_francisca", EntityInit.createFrancisca((FranciscaItem) CompatItems.ADAMANTITE_FRANCISCA_ITEM));

            EntityInit.ENTITY_TYPES.put(MedievalMain.identifierOf("adamantite_francisca"), ADAMANTITE_FRANCISCA);
            EntityInit.ENTITY_TYPES.put(MedievalMain.identifierOf("adamantite_javelin"), ADAMANTITE_JAVELIN);
        }
        if (CompatItems.isParadiseLostLoaded) {
            OLVITE_JAVELIN = EntityInit.register("olvite_javelin", EntityInit.createJavelin((JavelinItem) CompatItems.OLVITE_JAVELIN_ITEM));
            OLVITE_FRANCISCA = EntityInit.register("olvite_francisca", EntityInit.createFrancisca((FranciscaItem) CompatItems.OLVITE_FRANCISCA_ITEM));

            EntityInit.ENTITY_TYPES.put(MedievalMain.identifierOf("olvite_francisca"), OLVITE_FRANCISCA);
            EntityInit.ENTITY_TYPES.put(MedievalMain.identifierOf("olvite_javelin"), OLVITE_JAVELIN);
        }
        // TODO: Oh The Biomes You'll Go compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (7 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatEntities.java.
        // TODO: Additional Additions compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (11 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatEntities.java.
        if (CompatItems.isMythicUpgradesLoaded) {
            RUBY_NETHERITE_JAVELIN = EntityInit.register("ruby_netherite_javelin", EntityInit.createJavelin((JavelinItem) CompatItems.RUBY_NETHERITE_JAVELIN_ITEM));
            RUBY_NETHERITE_FRANCISCA = EntityInit.register("ruby_netherite_francisca", EntityInit.createFrancisca((FranciscaItem) CompatItems.RUBY_NETHERITE_FRANCISCA_ITEM));
            SAPPHIRE_NETHERITE_JAVELIN = EntityInit.register("sapphire_netherite_javelin", EntityInit.createJavelin((JavelinItem) CompatItems.SAPPHIRE_NETHERITE_JAVELIN_ITEM));
            SAPPHIRE_NETHERITE_FRANCISCA = EntityInit.register("sapphire_netherite_francisca", EntityInit.createFrancisca((FranciscaItem) CompatItems.SAPPHIRE_NETHERITE_FRANCISCA_ITEM));

            EntityInit.ENTITY_TYPES.put(MedievalMain.identifierOf("ruby_netherite_francisca"), RUBY_NETHERITE_FRANCISCA);
            EntityInit.ENTITY_TYPES.put(MedievalMain.identifierOf("ruby_netherite_javelin"), RUBY_NETHERITE_JAVELIN);
            EntityInit.ENTITY_TYPES.put(MedievalMain.identifierOf("sapphire_netherite_francisca"), SAPPHIRE_NETHERITE_FRANCISCA);
            EntityInit.ENTITY_TYPES.put(MedievalMain.identifierOf("sapphire_netherite_javelin"), SAPPHIRE_NETHERITE_JAVELIN);
        }
        if (CompatItems.isDeeperDarkerLoaded) {
            WARDEN_JAVELIN = EntityInit.register("warden_javelin", EntityInit.createJavelin((JavelinItem) CompatItems.WARDEN_JAVELIN_ITEM));
            WARDEN_FRANCISCA = EntityInit.register("warden_francisca", EntityInit.createFrancisca((FranciscaItem) CompatItems.WARDEN_FRANCISCA_ITEM));

            EntityInit.ENTITY_TYPES.put(MedievalMain.identifierOf("warden_francisca"), WARDEN_FRANCISCA);
            EntityInit.ENTITY_TYPES.put(MedievalMain.identifierOf("warden_javelin"), WARDEN_JAVELIN);
        }
        // TODO: EarlyStage compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (11 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatEntities.java.
        // TODO: Oblivion compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (15 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatEntities.java.
    }
}