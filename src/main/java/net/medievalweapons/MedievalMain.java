package net.medievalweapons;

import net.medievalweapons.init.*;
import net.medievalweapons.network.MedievalServerPacket;
import net.medievalweapons.port.ServerSmoke;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(MedievalMain.MOD_ID)
public class MedievalMain {

    // All credits go to Plasma Studios: https://youtu.be/Uc7YMW3AKpg?t=1152
    // Thanks for the awesome series
    // Check it out here: https://youtu.be/yCNUP2NAt-A

    public static final String MOD_ID = "medievalweapons";

    private static boolean registered = false;

    public MedievalMain(IEventBus modBus) {
        // Config first: ItemInit reads ConfigInit.CONFIG in its static initializers.
        ConfigInit.init();
        CompatInit.init(modBus);

        // Vanilla registries are unfrozen for the whole RegisterEvent phase. The *Init classes register their content
        // from static fields and init() (Fabric style), so they are class-loaded inside that phase only.
        modBus.addListener(RegisterEvent.class, MedievalMain::onRegister);
        // Method references only: calling a static method of ItemInit here would class-load it outside RegisterEvent.
        modBus.addListener(BuildCreativeModeTabContentsEvent.class, ItemInit::addItemGroupEntries);
        MedievalServerPacket.init(modBus);

        // Port self-check for the dedicated server, active only with -Dmedievalweapons.port.serverSmoke=true.
        if (Boolean.getBoolean("medievalweapons.port.serverSmoke")) {
            ServerSmoke.register(NeoForge.EVENT_BUS);
        }
    }

    private static void onRegister(RegisterEvent event) {
        if (registered) {
            return;
        }
        registered = true;
        // Same order as the original onInitialize() (ConfigInit and CompatInit run in the constructor,
        // MedievalServerPacket registers through RegisterPayloadHandlersEvent).
        ItemInit.init();
        EffectInit.init();
        EntityInit.init();
        ParticleInit.init();
        SoundInit.init();
        TagInit.init();
    }

    public static ResourceLocation identifierOf(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}

// You are LOVED!!!
// Jesus loves you unconditionally!
