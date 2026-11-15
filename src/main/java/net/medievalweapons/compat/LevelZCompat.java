package net.medievalweapons.compat;

import net.levelz.util.BonusHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;

// Optional LevelZ integration. Every call site is guarded by CompatInit.isLevelZLoaded, so this class (and with it
// net.levelz.util.BonusHelper) is only loaded when LevelZ is present. Same call as the original:
//   if (CompatInit.isLevelZLoaded && owner instanceof Player playerEntity) { BonusHelper.bowBonus(playerEntity, this); }
// Signature from the NeoForge LevelZ port (net.levelz.util.BonusHelper#bowBonus(LivingEntity, Projectile)).
public final class LevelZCompat {

    private LevelZCompat() {
    }

    public static void bowBonus(LivingEntity owner, Projectile projectile) {
        BonusHelper.bowBonus(owner, projectile);
    }
}
