package net.medievalweapons.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.medievalweapons.item.BigAxeItem;
import net.medievalweapons.item.HealingStaffItem;
import net.medievalweapons.item.HeroStaffItem;
import net.medievalweapons.item.HeroSwordItem;
import net.medievalweapons.item.LongSwordItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.ItemAbility;

/**
 * Port-only mixin (not in the original). The weapons that block with UseAnim.BLOCK report the SHIELD_BLOCK item ability
 * so that NeoForge's LivingEntity#isBlocking() accepts them (vanilla checks the use animation). NeoForge also uses that
 * ability in two more places where vanilla, and therefore the original on Fabric, checks for the shield item; this mixin
 * keeps the vanilla result there for these weapons only:
 * - Player#hurtCurrentlyUsedShield: vanilla damages the used item only if it is Items.SHIELD;
 * - Player#disableShield: vanilla puts Items.SHIELD on cooldown, not the used item.
 */
@Mixin(Player.class)
public abstract class PlayerShieldPortMixin {

    @WrapOperation(method = "hurtCurrentlyUsedShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;canPerformAction(Lnet/neoforged/neoforge/common/ItemAbility;)Z"))
    private boolean medievalweapons$vanillaShieldDamageCheck(ItemStack stack, ItemAbility itemAbility, Operation<Boolean> original) {
        if (medievalweapons$isBlockingWeapon(stack.getItem())) {
            return stack.is(Items.SHIELD);
        }
        return original.call(stack, itemAbility);
    }

    @WrapOperation(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;addCooldown(Lnet/minecraft/world/item/Item;I)V"))
    private void medievalweapons$vanillaDisableShieldCooldown(ItemCooldowns cooldowns, Item item, int ticks, Operation<Void> original) {
        original.call(cooldowns, medievalweapons$isBlockingWeapon(item) ? Items.SHIELD : item, ticks);
    }

    @Unique
    private static boolean medievalweapons$isBlockingWeapon(Item item) {
        return item instanceof LongSwordItem || item instanceof BigAxeItem || item instanceof HeroSwordItem || item instanceof HeroStaffItem
                || item instanceof HealingStaffItem;
    }
}
