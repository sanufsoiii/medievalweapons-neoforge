package net.medievalweapons.mixin;

import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

import net.medievalweapons.init.ConfigInit;
import net.medievalweapons.init.ParticleInit;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;

@Mixin(AreaEffectCloud.class)
public abstract class AreaEffectCloudEntityMixin {
    @Shadow
    private LivingEntity owner;

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;applyInstantenousEffect(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/LivingEntity;ID)V"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void tickMixin(CallbackInfo info, boolean bl, float f, List<MobEffectInstance> list, List<LivingEntity> list2, Iterator<LivingEntity> var6, LivingEntity livingEntity) {
        if (!ConfigInit.CONFIG.healing_staff_owner_healing && livingEntity.equals(owner) && getParticle().equals(ParticleInit.HEALING_AURA_PARTICLE)) {
            info.cancel();
        }

    }

    @Shadow
    public ParticleOptions getParticle() {
        return null;
    }

}
