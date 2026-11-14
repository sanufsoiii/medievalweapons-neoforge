package net.medievalweapons.port.assets;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Shown on the title screen when the original jar is not in resourcepacks/. Opens pages only on click. */
public class MissingAssetsScreen extends Screen {

    private static final Logger LOGGER = LoggerFactory.getLogger("medievalweapons");
    private static final String CURSEFORGE_URL = "https://www.curseforge.com/minecraft/mc-mods/medievalweapons/files";
    private static final String MODRINTH_URL = "https://modrinth.com/mod/medievalweapons/version/1.7.0+1.21.1";
    private static final int BUTTON_WIDTH = 260;
    private static final int BUTTON_HEIGHT = 20;
    private static final int STEP = 24;

    private final Screen parent;
    private MultiLineLabel body = MultiLineLabel.EMPTY;
    private boolean notFound = false;
    private int bodyY;
    private int notFoundY;

    public MissingAssetsScreen(Screen parent) {
        super(Component.translatable("medievalweapons.port.assets.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int left = centerX - BUTTON_WIDTH / 2;
        this.body = MultiLineLabel.create(this.font, Component.translatable("medievalweapons.port.assets.text"), Math.min(this.width - 40, 360));
        int buttonsHeight = 5 * STEP + 24 + 14;
        int textHeight = this.body.getLineCount() * this.font.lineHeight;
        int top = Math.max(10, (this.height - (20 + textHeight + 12 + buttonsHeight)) / 2);
        this.bodyY = top + 20;
        int y = this.bodyY + textHeight + 12;

        this.addRenderableWidget(Button.builder(Component.translatable("medievalweapons.port.assets.curseforge"),
                b -> Util.getPlatform().openUri(URI.create(CURSEFORGE_URL))).bounds(left, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());
        y += STEP;
        this.addRenderableWidget(Button.builder(Component.translatable("medievalweapons.port.assets.modrinth"),
                b -> Util.getPlatform().openUri(URI.create(MODRINTH_URL))).bounds(left, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());
        y += STEP;
        this.addRenderableWidget(Button.builder(Component.translatable("medievalweapons.port.assets.open_folder"),
                b -> openFolder()).bounds(left, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());
        y += STEP;
        this.addRenderableWidget(Button.builder(Component.translatable("medievalweapons.port.assets.recheck"),
                b -> recheck()).bounds(left, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());
        y += STEP;
        this.addRenderableWidget(Button.builder(Component.translatable("medievalweapons.port.assets.continue"),
                b -> onClose()).bounds(left, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());
        y += STEP + 2;
        this.addRenderableWidget(Checkbox.builder(Component.translatable("medievalweapons.port.assets.dont_show"), this.font)
                .pos(left, y)
                .selected(AssetNoticeSettings.dontShowAgain())
                .onValueChange((box, value) -> AssetNoticeSettings.setDontShowAgain(value))
                .build());
        this.notFoundY = y + 24;
    }

    private void openFolder() {
        Path dir = OriginalAssetsLocator.resourcePacksDir();
        try {
            Files.createDirectories(dir);
            Util.getPlatform().openPath(dir);
        } catch (Exception e) {
            LOGGER.warn("[medievalweapons-port] Could not open {}", dir, e);
        }
    }

    private void recheck() {
        if (OriginalAssetsLocator.rescan().isPresent()) {
            this.notFound = false;
            this.minecraft.getResourcePackRepository().reload();
            this.minecraft.reloadResourcePacks();
            this.minecraft.setScreen(this.parent instanceof TitleScreen ? this.parent : new TitleScreen());
        } else {
            this.notFound = true;
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, this.bodyY - 20, 0xFFFFAA00);
        this.body.renderCentered(graphics, this.width / 2, this.bodyY);
        if (this.notFound) {
            graphics.drawCenteredString(this.font, Component.translatable("medievalweapons.port.assets.not_found"), this.width / 2, this.notFoundY, 0xFFFF5555);
        }
    }

    @Override
    public void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderMenuBackground(graphics);
        graphics.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.parent);
    }
}
