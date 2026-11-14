package net.medievalweapons.init;

import net.medievalweapons.MedievalMain;
import net.medievalweapons.compat.CompatRender;
import net.medievalweapons.entity.FranciscaEntity;
import net.medievalweapons.entity.JavelinEntity;
import net.medievalweapons.entity.renderer.FranciscaEntityRenderer;
import net.medievalweapons.entity.renderer.JavelinEntityRenderer;
import net.medievalweapons.init.ParticleInit.HealingAuraParticle;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.HashMap;
import java.util.Map;

public class RenderInit {

    public static final Map<DyeColor, RenderType> CUSTOM_GLINT = customGlints();

    // Fabric: EntityRendererRegistry / ParticleFactoryRegistry calls in onInitializeClient().
    // NeoForge: the same registrations inside EntityRenderersEvent.RegisterRenderers and RegisterParticleProvidersEvent (mod bus).
    public static void init(IEventBus modBus) {
        modBus.addListener(EntityRenderersEvent.RegisterRenderers.class, RenderInit::registerRenderers);
        modBus.addListener(RegisterParticleProvidersEvent.class, RenderInit::registerParticles);
    }

    @SuppressWarnings("unchecked")
    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<ResourceLocation, EntityType> entry : EntityInit.ENTITY_TYPES.entrySet()) {
            if (entry.getKey().getPath().contains("javelin")) {
                event.registerEntityRenderer((EntityType<JavelinEntity>) entry.getValue(), JavelinEntityRenderer::new);
            } else if (entry.getKey().getPath().contains("francisca")) {
                event.registerEntityRenderer((EntityType<FranciscaEntity>) entry.getValue(), FranciscaEntityRenderer::new);
            }
        }
        // Compat
        CompatRender.loadRenderer(event);
    }

    private static void registerParticles(RegisterParticleProvidersEvent event) {
        // Particle
        event.registerSpriteSet(ParticleInit.HEALING_AURA_PARTICLE, HealingAuraParticle.Factory::new);
    }

    private static Map<DyeColor, RenderType> customGlints() {
        Map<DyeColor, RenderType> map = new HashMap<>();

        for (DyeColor color : DyeColor.values()) {
            final ResourceLocation glintId = MedievalMain.identifierOf("textures/glint/" + color.getName() + "_item_glint.png");
            map.put(color, RenderType.create(color.getName() + "_glint", DefaultVertexFormat.POSITION_TEX,
                    VertexFormat.Mode.QUADS, 256, RenderType.CompositeState.builder().setShaderState(RenderStateShard.RENDERTYPE_GLINT_SHADER)
                            .setTextureState(new RenderStateShard.TextureStateShard(glintId, true, false))
                            .setWriteMaskState(RenderStateShard.COLOR_WRITE).setCullState(RenderStateShard.NO_CULL).setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST)
                            .setTransparencyState(RenderStateShard.GLINT_TRANSPARENCY).setTexturingState(RenderStateShard.GLINT_TEXTURING).createCompositeState(false)));
        }

        return map;
    }

}