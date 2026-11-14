package net.medievalweapons.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.sugar.Local;

import net.medievalweapons.init.ConfigInit;
import net.medievalweapons.init.ParticleInit;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;

@Mixin(AreaEffectCloud.class)
public abstract class AreaEffectCloudEntityMixin {
    @Shadow
    private LivingEntity owner;

    // Port: the original captures the locals with CAPTURE_FAILSOFT as (boolean, float, List, List, Iterator, LivingEntity),
    // which does not match the locals of AreaEffectCloud#tick in NeoForge's recompiled Minecraft (a second boolean sits
    // between the float and the first List) and fails the injection. MixinExtras @Local takes the same LivingEntity: it is
    // the only LivingEntity local at this call (the entity the instant effect is applied to).
    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;applyInstantenousEffect(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/LivingEntity;ID)V"), cancellable = true)
    private void tickMixin(CallbackInfo info, @Local LivingEntity livingEntity) {
        if (!ConfigInit.CONFIG.healing_staff_owner_healing && livingEntity.equals(owner) && getParticle().equals(ParticleInit.HEALING_AURA_PARTICLE)) {
            info.cancel();
        }

    }

    @Shadow
    public ParticleOptions getParticle() {
        return null;
    }

}
