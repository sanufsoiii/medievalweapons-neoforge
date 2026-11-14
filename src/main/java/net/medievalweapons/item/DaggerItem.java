package net.medievalweapons.item;

import net.medievalweapons.MedievalMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class DaggerItem extends SwordItem {

    public static final ResourceLocation ATTACK_BONUS_MODIFIER_ID = MedievalMain.identifierOf("sneaking_attack_bonus");
    private static final AttributeModifier ATTACK_BONUS_MODIFIER;

    public DaggerItem(Tier material, Properties settings) {
        super(material, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!world.isClientSide() && entity instanceof Player player) {
            Boolean extra = player.isShiftKeyDown() || player.hasEffect(MobEffects.INVISIBILITY);
            if (selected && extra && !player.getAttributes().hasModifier(Attributes.ATTACK_DAMAGE, ATTACK_BONUS_MODIFIER_ID)) {
                AttributeInstance entityAttributeInstance = player.getAttribute(Attributes.ATTACK_DAMAGE);
                entityAttributeInstance.addTransientModifier(ATTACK_BONUS_MODIFIER);
            } else if (player.getAttributes().hasModifier(Attributes.ATTACK_DAMAGE, ATTACK_BONUS_MODIFIER_ID) && !extra) {
                player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ATTACK_BONUS_MODIFIER_ID);
            }
        }
    }

    static {
        ATTACK_BONUS_MODIFIER = new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, 2.0D, AttributeModifier.Operation.ADD_VALUE);
    }

}
