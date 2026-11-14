package net.medievalweapons.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.medievalweapons.item.RapierItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

@Mixin(Mob.class)
public abstract class MobEntityMixin extends LivingEntity {

    public MobEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public void knockback(double strength, double x, double z) {
        if (this.getLastHurtByMob() == null || this.getLastHurtByMob().getMainHandItem().isEmpty() || !(this.getLastHurtByMob().getMainHandItem().getItem() instanceof RapierItem)) {
            super.knockback(strength, x, z);
        }
    }

}
