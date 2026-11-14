package net.medievalweapons.compat;

import net.levelz.util.BonusHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;

// TODO: LevelZ is being ported to NeoForge 1.21.1 separately (~/Developer/minecraft_moding/levelz-neoforge).
// This class is not compiled until that port exists as a dependency. Call sites in the original:
//   FranciscaEntity#onHitEntity and JavelinEntity#onHitEntity:
//     if (CompatInit.isLevelZLoaded && owner instanceof Player playerEntity) { BonusHelper.bowBonus(playerEntity, this); }
// Port shape: keep the call site behind ModList.get().isLoaded("levelz") (CompatInit.isLevelZLoaded) and call
// LevelZCompat.bowBonus(player, this) so that net.levelz classes are only loaded when LevelZ is present.
// Signature from the Fabric LevelZ 2.0.6+1.21.1 jar (javap, intermediary class_1309/class_1676 mapped to Mojang):
//   public static void net.levelz.util.BonusHelper.bowBonus(LivingEntity, Projectile)
// Re-check it against the NeoForge LevelZ port when linking.
public final class LevelZCompat {

    private LevelZCompat() {
    }

    public static void bowBonus(LivingEntity owner, Projectile projectile) {
        BonusHelper.bowBonus(owner, projectile);
    }
}
