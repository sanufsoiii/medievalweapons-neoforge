package net.medievalweapons.init;

import net.medievalweapons.MedievalMain;
import net.medievalweapons.compat.CompatItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.AddPackFindersEvent;

public class CompatInit {

    public static final boolean isBetterCombatLoaded = ModList.get().isLoaded("bettercombat");
    // Optional: only consulted behind this guard, so net.levelz classes are never loaded when the mod is absent.
    public static final boolean isLevelZLoaded = ModList.get().isLoaded("levelz");

    // Fabric: ResourceManagerHelper.registerBuiltinResourcePack(id, container, DEFAULT_ENABLED) in onInitialize().
    // NeoForge: built-in packs are offered through AddPackFindersEvent (mod bus) whenever a pack repository is built.
    public static void init(IEventBus modBus) {
        modBus.addListener(AddPackFindersEvent.class, CompatInit::onAddPackFinders);
    }

    private static void onAddPackFinders(AddPackFindersEvent event) {
        // The nested packs of the original jar contain only data (recipes, weapon attributes) in this port;
        // their models and translations are All Rights Reserved and come from the original jar (port/assets).
        if (event.getPackType() != PackType.SERVER_DATA) {
            return;
        }
        // TODO: Additional Additions compat pack excluded: the NeoForge build of Additional Additions has none of the
        // materials and items this pack uses (see _excluded/compat-unavailable/resourcepacks).
        // TODO: Better End compat pack excluded: the mod has no NeoForge 1.21.1 build.
        // TODO: Better Nether compat pack excluded: the mod has no NeoForge 1.21.1 build.
        // TODO: Oh The Biomes You'll Go compat pack excluded: the mod has no NeoForge 1.21.1 build.
        if (CompatItems.isDeeperDarkerLoaded) {
            addBuiltinPack(event, "deeperdarker_medievalweapons_compat");
        }
        // TODO: DragonLoot compat pack excluded: the mod has no NeoForge 1.21.1 build.
        // TODO: EarlyStage compat pack excluded: the mod has no NeoForge 1.21.1 build.
        if (CompatItems.isMythicMetalsLoaded) {
            addBuiltinPack(event, "mythicmetals_medievalweapons_compat");
        }
        if (CompatItems.isMythicUpgradesLoaded) {
            addBuiltinPack(event, "mythicupgrades_medievalweapons_compat");
        }
        // TODO: Oblivion compat pack excluded: the mod has no NeoForge 1.21.1 build.
        if (CompatItems.isParadiseLostLoaded) {
            addBuiltinPack(event, "paradise_lost_medievalweapons_compat");
        }
    }

    private static void addBuiltinPack(AddPackFindersEvent event, String name) {
        // PackSource.BUILT_IN is added to new and existing worlds automatically, like Fabric's DEFAULT_ENABLED.
        event.addPackFinders(MedievalMain.identifierOf("resourcepacks/" + name), PackType.SERVER_DATA, Component.literal("medievalweapons/" + name), PackSource.BUILT_IN, false,
                Pack.Position.TOP);
    }

}
