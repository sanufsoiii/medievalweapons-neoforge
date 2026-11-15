package net.medievalweapons.port.assets;

import net.minecraft.client.gui.screens.TitleScreen;
import net.neoforged.neoforge.client.event.ScreenEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Replaces the first TitleScreen of the session with {@link MissingAssetsScreen} when the original jar is missing. */
public final class AssetNotice {

    private static final Logger LOGGER = LoggerFactory.getLogger("medievalweapons");
    private static boolean handled = false;

    private AssetNotice() {
    }

    public static void onScreenOpening(ScreenEvent.Opening event) {
        if (handled || !(event.getNewScreen() instanceof TitleScreen title)) {
            return;
        }
        handled = true;
        if (OriginalAssetsLocator.find().isPresent() || AssetNoticeSettings.dontShowAgain()) {
            return;
        }
        if (Boolean.getBoolean("medievalweapons.port.exitOnTitle")) {
            // The smoke test cannot click buttons, so the screen is not opened: build it (same init path as the game uses)
            // and check that it comes out with its widgets, then report that the notice would have been shown.
            MissingAssetsScreen screen = new MissingAssetsScreen(title);
            net.minecraft.client.Minecraft minecraft = net.minecraft.client.Minecraft.getInstance();
            screen.init(minecraft, minecraft.getWindow().getGuiScaledWidth(), minecraft.getWindow().getGuiScaledHeight());
            LOGGER.info("[medievalweapons-port] ASSET_NOTICE_WOULD_SHOW widgets={}", screen.children().size());
            return;
        }
        LOGGER.info("[medievalweapons-port] ASSET_NOTICE_SHOWN");
        event.setNewScreen(new MissingAssetsScreen(title));
    }
}
