package net.medievalweapons.port;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import net.medievalweapons.MedievalMain;
import net.medievalweapons.init.ItemInit;
import net.medievalweapons.init.ParticleInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client self-check of the port, run on the title screen only in runClientSmoke (-Dmedievalweapons.port.exitOnTitle=true).
 * Covers the platform seams the title screen alone does not exercise: mixin targets that are not class-loaded yet,
 * entity renderers, item model properties and the particle provider.
 */
public final class ClientSelfCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedievalMain.MOD_ID);

    // Targets of every mixin in medievalweapons.mixins.json (common and client). Loading them applies the mixins;
    // with "defaultRequire": 1 a failed injection throws here instead of later in game.
    private static final List<String> MIXIN_TARGETS = List.of(
            "net.minecraft.world.entity.AreaEffectCloud",
            "net.minecraft.world.item.BowItem",
            "net.minecraft.world.item.enchantment.EnchantmentHelper",
            "net.minecraft.world.item.enchantment.Enchantment",
            "net.minecraft.world.entity.LivingEntity",
            "net.minecraft.world.entity.Mob",
            "net.minecraft.world.entity.player.Player",
            "net.minecraft.server.ServerAdvancementManager",
            "net.minecraft.tags.TagLoader",
            "net.minecraft.client.player.AbstractClientPlayer",
            "net.minecraft.client.model.HumanoidModel",
            "net.minecraft.client.renderer.RenderBuffers",
            "net.minecraft.client.gui.GuiGraphics",
            "net.minecraft.client.gui.Gui",
            "net.minecraft.client.renderer.entity.ItemRenderer",
            "net.minecraft.client.Minecraft",
            "net.minecraft.world.entity.projectile.AbstractArrow");

    private ClientSelfCheck() {
    }

    public static void run() {
        boolean ok = true;

        int loaded = 0;
        for (String target : MIXIN_TARGETS) {
            try {
                Class.forName(target, true, ClientSelfCheck.class.getClassLoader());
                loaded++;
            } catch (Throwable t) {
                ok = false;
                LOGGER.error("[medievalweapons-port] CLIENT_SELF_CHECK_FAIL mixin target {}", target, t);
            }
        }

        int types = 0;
        int renderers = 0;
        try {
            Map<?, ?> rendererMap = (Map<?, ?>) field(EntityRenderDispatcher.class, "renderers").get(Minecraft.getInstance().getEntityRenderDispatcher());
            for (EntityType<?> type : BuiltInRegistries.ENTITY_TYPE) {
                if (MedievalMain.MOD_ID.equals(BuiltInRegistries.ENTITY_TYPE.getKey(type).getNamespace())) {
                    types++;
                    if (rendererMap.get(type) != null) {
                        renderers++;
                    } else {
                        LOGGER.error("[medievalweapons-port] CLIENT_SELF_CHECK_FAIL no renderer for {}", BuiltInRegistries.ENTITY_TYPE.getKey(type));
                    }
                }
            }
        } catch (Throwable t) {
            ok = false;
            LOGGER.error("[medievalweapons-port] CLIENT_SELF_CHECK_FAIL renderers", t);
        }
        ok &= types > 0 && types == renderers;

        ItemStack longBow = new ItemStack(ItemInit.LONG_BOW_ITEM);
        ItemStack recurveBow = new ItemStack(ItemInit.RECURVE_BOW_ITEM);
        ItemStack longSword = new ItemStack(BuiltInRegistries.ITEM.get(MedievalMain.identifierOf("iron_long_sword")));
        boolean properties = ItemProperties.getProperty(longBow, ResourceLocation.parse("pull")) != null
                && ItemProperties.getProperty(longBow, ResourceLocation.parse("pulling")) != null
                && ItemProperties.getProperty(recurveBow, ResourceLocation.parse("pull")) != null
                && ItemProperties.getProperty(recurveBow, ResourceLocation.parse("pulling")) != null
                && ItemProperties.getProperty(longSword, MedievalMain.identifierOf("offhand")) != null
                && ItemProperties.getProperty(longSword, ResourceLocation.parse("throwing")) != null
                && ItemProperties.getProperty(longSword, MedievalMain.identifierOf("gui")) != null;
        if (properties) {
            // "gui" = 1 for seed 0 (GUI, forced by DrawContextMixin), 0 otherwise; the value must be clamped to 0..1.
            float gui = ItemProperties.getProperty(longSword, MedievalMain.identifierOf("gui")).call(longSword, null, null, 0);
            float world = ItemProperties.getProperty(longSword, MedievalMain.identifierOf("gui")).call(longSword, null, null, 42);
            properties = gui == 1.0F && world == 0.0F;
        }
        if (!properties) {
            LOGGER.error("[medievalweapons-port] CLIENT_SELF_CHECK_FAIL item properties");
        }
        ok &= properties;

        boolean particle = false;
        try {
            Map<?, ?> providers = (Map<?, ?>) field(ParticleEngine.class, "providers").get(Minecraft.getInstance().particleEngine);
            particle = providers.get(BuiltInRegistries.PARTICLE_TYPE.getKey(ParticleInit.HEALING_AURA_PARTICLE)) != null;
        } catch (Throwable t) {
            LOGGER.error("[medievalweapons-port] CLIENT_SELF_CHECK_FAIL particle provider", t);
        }
        if (!particle) {
            LOGGER.error("[medievalweapons-port] CLIENT_SELF_CHECK_FAIL no provider for {}", BuiltInRegistries.PARTICLE_TYPE.getKey(ParticleInit.HEALING_AURA_PARTICLE));
        }
        ok &= particle;

        LOGGER.info("[medievalweapons-port] CLIENT_SELF_CHECK {} mixinTargets={}/{} renderers={}/{} itemProperties={} particleProvider={}", ok ? "OK" : "FAIL",
                loaded, MIXIN_TARGETS.size(), renderers, types, properties, particle);
    }

    private static Field field(Class<?> owner, String name) throws NoSuchFieldException {
        Field field = owner.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }
}
