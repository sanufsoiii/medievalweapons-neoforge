package net.medievalweapons;

import me.shedaniel.autoconfig.AutoConfig;
import net.medievalweapons.config.MedievalConfig;
import net.medievalweapons.init.ModelInit;
import net.medievalweapons.init.RenderInit;
import net.medievalweapons.network.MedievalClientPacket;
import net.medievalweapons.port.assets.AssetNotice;
import net.medievalweapons.port.assets.OriginalAssetsPackFinder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(value = MedievalMain.MOD_ID, dist = Dist.CLIENT)
public class MedievalClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedievalMain.MOD_ID);
    private static boolean titleReached = false;

    public MedievalClient(IEventBus modBus, ModContainer container) {
        // Fabric: ModMenu integration (see _excluded/modmenu). NeoForge: the mod list "Config" button.
        container.registerExtensionPoint(IConfigScreenFactory.class, (mc, parent) -> AutoConfig.getConfigScreen(MedievalConfig.class, parent).get());

        ModelInit.init(modBus);
        RenderInit.init(modBus);
        MedievalClientPacket.init();

        // Original ARR assets from the player's own jar in resourcepacks/ (never bundled, never downloaded).
        modBus.addListener(AddPackFindersEvent.class, OriginalAssetsPackFinder::onAddPackFinders);

        // Port self-check: log when the title screen is reached, optionally exit (runClientSmoke).
        NeoForge.EVENT_BUS.addListener(ScreenEvent.Opening.class, MedievalClient::onScreenOpening);
        NeoForge.EVENT_BUS.addListener(ScreenEvent.Opening.class, AssetNotice::onScreenOpening);
    }

    private static void onScreenOpening(ScreenEvent.Opening event) {
        if (titleReached || !(event.getNewScreen() instanceof TitleScreen)) {
            return;
        }
        titleReached = true;
        LOGGER.info("[medievalweapons-port] TITLE_SCREEN_REACHED");
        if (Boolean.getBoolean("medievalweapons.port.exitOnTitle")) {
            Thread exitThread = new Thread(() -> {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException ignored) {
                }
                Minecraft.getInstance().execute(() -> Minecraft.getInstance().stop());
            }, "medievalweapons-port-smoke-exit");
            exitThread.setDaemon(true);
            exitThread.start();
        }
    }

}
