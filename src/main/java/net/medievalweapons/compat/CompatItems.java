package net.medievalweapons.compat;

import com.kyanite.deeperdarker.util.DDTiers;
import com.mythicmetals.item.tools.MythicToolMaterials;
import net.id.paradise_lost.item.tool.ParadiseLostToolMaterials;
import net.medievalweapons.MedievalMain;
import net.medievalweapons.init.ItemInit;
import net.medievalweapons.item.*;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.neoforged.fml.ModList;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CompatItems {

    private static final ModList modList = ModList.get();

    public static Boolean isDragonLootLoaded = modList.isLoaded("dragonloot");
    public static Boolean isBetterEndLoaded = modList.isLoaded("betterend");
    public static Boolean isBetterNetherLoaded = modList.isLoaded("betternether");
    public static Boolean isMythicMetalsLoaded = modList.isLoaded("mythicmetals");
    public static Boolean isParadiseLostLoaded = modList.isLoaded("paradise_lost");
    public static Boolean isBYGLoaded = modList.isLoaded("byg");
    public static Boolean isAdditionalAdditionsLoaded = modList.isLoaded("additionaladditions");
    public static Boolean isMythicUpgradesLoaded = modList.isLoaded("mythicupgrades");
    public static Boolean isDeeperDarkerLoaded = modList.isLoaded("deeperdarker");
    public static Boolean isEarlyStageLoaded = modList.isLoaded("earlystage");
    public static Boolean isOblivionLoaded = modList.isLoaded("oblivion");

    public static final Map<String, Set<String>> MATERIALS_BY_MOD = new LinkedHashMap<>();

    static {
        MATERIALS_BY_MOD.put("betterend", Set.of("terminite", "aeternium", "thallasium"));
        MATERIALS_BY_MOD.put("betternether", Set.of("nether_ruby", "cincinnasite", "cincinnasite_diamond"));
        MATERIALS_BY_MOD.put("dragonloot", Set.of("dragon"));
        MATERIALS_BY_MOD.put("mythicmetals", Set.of("adamantite"));
        MATERIALS_BY_MOD.put("paradise_lost", Set.of("olvite"));
        MATERIALS_BY_MOD.put("byg", Set.of("pendorite"));
        MATERIALS_BY_MOD.put("additionaladditions", Set.of("gilded_netherite", "rose_gold"));
        MATERIALS_BY_MOD.put("mythicupgrades", Set.of("ruby_netherite", "sapphire_netherite"));
        MATERIALS_BY_MOD.put("deeperdarker", Set.of("warden"));
        MATERIALS_BY_MOD.put("earlystage", Set.of("steel", "damascus_steel"));
        MATERIALS_BY_MOD.put("oblivion", Set.of("solarite", "fiery_netherite", "scarlet"));
    }

    // DragonLoot
    public static Item DRAGON_FRANCISCA_ITEM;
    public static Item DRAGON_JAVELIN_ITEM;

    // Better Nether
    public static Item NETHER_RUBY_FRANCISCA_ITEM;
    public static Item NETHER_RUBY_JAVELIN_ITEM;
    public static Item CINCINNASITE_FRANCISCA_ITEM;
    public static Item CINCINNASITE_JAVELIN_ITEM;
    public static Item CINCINNASITE_DIAMOND_FRANCISCA_ITEM;
    public static Item CINCINNASITE_DIAMOND_JAVELIN_ITEM;

    // Better End
    public static Item TERMINITE_FRANCISCA_ITEM;
    public static Item TERMINITE_JAVELIN_ITEM;
    public static Item AETERNIUM_FRANCISCA_ITEM;
    public static Item AETERNIUM_JAVELIN_ITEM;
    public static Item THALLASIUM_FRANCISCA_ITEM;
    public static Item THALLASIUM_JAVELIN_ITEM;

    // Mythic Metals
    public static Item ADAMANTITE_FRANCISCA_ITEM;
    public static Item ADAMANTITE_JAVELIN_ITEM;

    // BYG
    public static Item PENDORITE_FRANCISCA_ITEM;
    public static Item PENDORITE_JAVELIN_ITEM;

    // Additional Additions
    public static Item ROSE_GOLD_FRANCISCA_ITEM;
    public static Item ROSE_GOLD_JAVELIN_ITEM;
    public static Item GILDED_NETHERITE_FRANCISCA_ITEM;
    public static Item GILDED_NETHERITE_JAVELIN_ITEM;

    // Paradise Lost
    public static Item OLVITE_FRANCISCA_ITEM;
    public static Item OLVITE_JAVELIN_ITEM;

    // Mythic Upgrades
    public static Item RUBY_NETHERITE_FRANCISCA_ITEM;
    public static Item RUBY_NETHERITE_JAVELIN_ITEM;
    public static Item SAPPHIRE_NETHERITE_FRANCISCA_ITEM;
    public static Item SAPPHIRE_NETHERITE_JAVELIN_ITEM;

    // Deeper and Darker
    public static Item WARDEN_FRANCISCA_ITEM;
    public static Item WARDEN_JAVELIN_ITEM;

    // EarlyStage
    public static Item STEEL_FRANCISCA_ITEM;
    public static Item STEEL_JAVELIN_ITEM;
    public static Item DAMASCUS_STEEL_FRANCISCA_ITEM;
    public static Item DAMASCUS_STEEL_JAVELIN_ITEM;

    // Oblivion
    public static Item FIERY_NETHERITE_FRANCISCA_ITEM;
    public static Item FIERY_NETHERITE_JAVELIN_ITEM;
    public static Item SCARLET_FRANCISCA_ITEM;
    public static Item SCARLET_JAVELIN_ITEM;
    public static Item SOLARITE_FRANCISCA_ITEM;
    public static Item SOLARITE_JAVELIN_ITEM;

    public static void loadItems() {
        // TODO: Better End (BCLib) compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (118 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatItems.java.
        // TODO: Better Nether (BCLib) compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (159 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatItems.java.
        // TODO: DragonLoot compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (54 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatItems.java.
        if (isMythicMetalsLoaded) {
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("adamantite_small_axe"),
                            new SmallAxeItem(MythicToolMaterials.ADAMANTITE,
                                    new Item.Properties().attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 5, -2.9f).withModifierAdded(Attributes.ATTACK_KNOCKBACK,
                                                    new AttributeModifier(SmallAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_long_sword"),
                    new LongSwordItem(MythicToolMaterials.ADAMANTITE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 6, -3.0f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(LongSwordItem.ATTACK_BONUS_MODIFIER_ID, 0.8f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_dagger"),
                    new DaggerItem(MythicToolMaterials.ADAMANTITE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 2, -2.0f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(DaggerItem.ATTACK_BONUS_MODIFIER_ID, -1.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_big_axe"),
                    new BigAxeItem(MythicToolMaterials.ADAMANTITE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 6, -3.4f).withModifierAdded(Attributes.SWEEPING_DAMAGE_RATIO,
                                            new AttributeModifier(BigAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_lance"),
                    new LanceItem(MythicToolMaterials.ADAMANTITE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 3, -3.2f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(LanceItem.ATTACK_BONUS_MODIFIER_ID, 1.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_healing_staff"), new HealingStaffItem(MythicToolMaterials.ADAMANTITE, 4,
                    new Item.Properties().attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 1, -3.3f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_mace"),
                    new MaceItem(MythicToolMaterials.ADAMANTITE, 2,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 4, -2.8f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(LongSwordItem.ATTACK_BONUS_MODIFIER_ID, -0.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ADAMANTITE_FRANCISCA_ITEM = new FranciscaItem(MythicToolMaterials.ADAMANTITE, () -> CompatEntities.ADAMANTITE_FRANCISCA,
                    new Item.Properties().attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 1, -2.6f)));
            ADAMANTITE_JAVELIN_ITEM = new JavelinItem(MythicToolMaterials.ADAMANTITE, () -> CompatEntities.ADAMANTITE_JAVELIN,
                    new Item.Properties().attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 1, -2.6f)).fireResistant());
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_francisca"), ADAMANTITE_FRANCISCA_ITEM);
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_javelin"), ADAMANTITE_JAVELIN_ITEM);
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_ninjato"),
                    new NinjatoItem(MythicToolMaterials.ADAMANTITE, new Item.Properties().attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 2, -2.1f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_sickle"),
                    new SickleItem(MythicToolMaterials.ADAMANTITE, new Item.Properties().attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 2, -2.2f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("adamantite_rapier"), new RapierItem(MythicToolMaterials.ADAMANTITE, 3,
                    new Item.Properties().attributes(SwordItem.createAttributes(MythicToolMaterials.ADAMANTITE, 2, -2.0f)).fireResistant()));

            ItemInit.MATERIAL_STRINGS.add("adamantite");
        }
        if (isParadiseLostLoaded) {
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_small_axe"),
                    new SmallAxeItem(ParadiseLostToolMaterials.OLVITE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 5, -2.9f).withModifierAdded(Attributes.ATTACK_KNOCKBACK,
                                            new AttributeModifier(SmallAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_long_sword"),
                    new LongSwordItem(ParadiseLostToolMaterials.OLVITE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 6, -3.0f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(LongSwordItem.ATTACK_BONUS_MODIFIER_ID, 0.8f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_dagger"),
                    new DaggerItem(ParadiseLostToolMaterials.OLVITE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 2, -2.0f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(DaggerItem.ATTACK_BONUS_MODIFIER_ID, -1.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_big_axe"),
                    new BigAxeItem(ParadiseLostToolMaterials.OLVITE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 6, -3.4f).withModifierAdded(Attributes.SWEEPING_DAMAGE_RATIO,
                                            new AttributeModifier(BigAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_lance"),
                    new LanceItem(ParadiseLostToolMaterials.OLVITE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 3, -3.2f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(LanceItem.ATTACK_BONUS_MODIFIER_ID, 1.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_healing_staff"), new HealingStaffItem(ParadiseLostToolMaterials.OLVITE, 4,
                    new Item.Properties().attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 1, -3.3f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_mace"),
                    new MaceItem(ParadiseLostToolMaterials.OLVITE, 1,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 4, -2.8f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(LongSwordItem.ATTACK_BONUS_MODIFIER_ID, -0.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            OLVITE_FRANCISCA_ITEM = new FranciscaItem(ParadiseLostToolMaterials.OLVITE, () -> CompatEntities.OLVITE_FRANCISCA,
                    new Item.Properties().attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 1, -2.6f)));
            OLVITE_JAVELIN_ITEM = new JavelinItem(ParadiseLostToolMaterials.OLVITE, () -> CompatEntities.OLVITE_JAVELIN,
                    new Item.Properties().attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 1, -2.6f)).fireResistant());
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_francisca"), OLVITE_FRANCISCA_ITEM);
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_javelin"), OLVITE_JAVELIN_ITEM);
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_ninjato"), new NinjatoItem(ParadiseLostToolMaterials.OLVITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 2, -2.1f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_sickle"), new SickleItem(ParadiseLostToolMaterials.OLVITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 2, -2.2f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("olvite_rapier"), new RapierItem(ParadiseLostToolMaterials.OLVITE, 2,
                    new Item.Properties().attributes(SwordItem.createAttributes(ParadiseLostToolMaterials.OLVITE, 2, -2.0f)).fireResistant()));

            ItemInit.MATERIAL_STRINGS.add("olvite");
        }
        // TODO: Oh The Biomes You'll Go compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (54 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatItems.java.
        // TODO: Additional Additions compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (106 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatItems.java.
        if (isMythicUpgradesLoaded) {
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("ruby_netherite_small_axe"),
                            new SmallAxeItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY,
                                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 5, -2.9f).withModifierAdded(Attributes.ATTACK_KNOCKBACK,
                                                    new AttributeModifier(SmallAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("ruby_netherite_long_sword"),
                            new LongSwordItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY,
                                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 6, -3.0f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(LongSwordItem.ATTACK_BONUS_MODIFIER_ID, 0.8f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("ruby_netherite_dagger"),
                            new DaggerItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY,
                                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 2, -2.0f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(DaggerItem.ATTACK_BONUS_MODIFIER_ID, -1.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("ruby_netherite_big_axe"),
                            new BigAxeItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY,
                                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 6, -3.4f).withModifierAdded(Attributes.SWEEPING_DAMAGE_RATIO,
                                                    new AttributeModifier(BigAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("ruby_netherite_lance"),
                            new LanceItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY,
                                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 3, -3.2f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(LanceItem.ATTACK_BONUS_MODIFIER_ID, 1.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("ruby_netherite_healing_staff"),
                    new HealingStaffItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 5, new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 1, -3.3f)).fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("ruby_netherite_mace"),
                            new MaceItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 3,
                                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 4, -2.8f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(LongSwordItem.ATTACK_BONUS_MODIFIER_ID, -0.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            RUBY_NETHERITE_FRANCISCA_ITEM = new FranciscaItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, () -> CompatEntities.RUBY_NETHERITE_FRANCISCA,
                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 1, -2.6f)));
            RUBY_NETHERITE_JAVELIN_ITEM = new JavelinItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, () -> CompatEntities.RUBY_NETHERITE_JAVELIN,
                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 1, -2.6f)).fireResistant());
            ItemInit.ITEMS.put(MedievalMain.identifierOf("ruby_netherite_francisca"), RUBY_NETHERITE_FRANCISCA_ITEM);
            ItemInit.ITEMS.put(MedievalMain.identifierOf("ruby_netherite_javelin"), RUBY_NETHERITE_JAVELIN_ITEM);
            ItemInit.ITEMS.put(MedievalMain.identifierOf("ruby_netherite_ninjato"),
                    new NinjatoItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 2, -2.1f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("ruby_netherite_sickle"),
                    new SickleItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 2, -2.2f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("ruby_netherite_rapier"),
                    new RapierItem(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 3, new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.RUBY, 2, -2.0f)).fireResistant()));

            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("sapphire_netherite_small_axe"),
                            new SmallAxeItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE,
                                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 5, -2.9f).withModifierAdded(Attributes.ATTACK_KNOCKBACK,
                                                    new AttributeModifier(SmallAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("sapphire_netherite_long_sword"),
                    new LongSwordItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 6, -3.0f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(LongSwordItem.ATTACK_BONUS_MODIFIER_ID, 0.8f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("sapphire_netherite_dagger"),
                    new DaggerItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 2, -2.0f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(DaggerItem.ATTACK_BONUS_MODIFIER_ID, -1.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("sapphire_netherite_big_axe"),
                            new BigAxeItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE,
                                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 6, -3.4f).withModifierAdded(Attributes.SWEEPING_DAMAGE_RATIO,
                                                    new AttributeModifier(BigAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("sapphire_netherite_lance"),
                    new LanceItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 3, -3.2f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(LanceItem.ATTACK_BONUS_MODIFIER_ID, 1.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("sapphire_netherite_healing_staff"),
                    new HealingStaffItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 5, new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 1, -3.3f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("sapphire_netherite_mace"),
                    new MaceItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 3,
                            new Item.Properties()
                                    .attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 4, -2.8f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(LongSwordItem.ATTACK_BONUS_MODIFIER_ID, -0.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                    .fireResistant()));
            SAPPHIRE_NETHERITE_FRANCISCA_ITEM = new FranciscaItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, () -> CompatEntities.SAPPHIRE_NETHERITE_FRANCISCA,
                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 1, -2.6f)));
            SAPPHIRE_NETHERITE_JAVELIN_ITEM = new JavelinItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, () -> CompatEntities.SAPPHIRE_NETHERITE_JAVELIN,
                    new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 1, -2.6f)).fireResistant());
            ItemInit.ITEMS.put(MedievalMain.identifierOf("sapphire_netherite_francisca"), SAPPHIRE_NETHERITE_FRANCISCA_ITEM);
            ItemInit.ITEMS.put(MedievalMain.identifierOf("sapphire_netherite_javelin"), SAPPHIRE_NETHERITE_JAVELIN_ITEM);
            ItemInit.ITEMS.put(MedievalMain.identifierOf("sapphire_netherite_ninjato"),
                    new NinjatoItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 2, -2.1f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("sapphire_netherite_sickle"),
                    new SickleItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 2, -2.2f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("sapphire_netherite_rapier"),
                    new RapierItem(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 3, new Item.Properties().attributes(SwordItem.createAttributes(net.trique.mythicupgrades.item.MythicToolMaterials.SAPPHIRE, 2, -2.0f)).fireResistant()));

            ItemInit.MATERIAL_STRINGS.add("ruby_netherite");
            ItemInit.MATERIAL_STRINGS.add("sapphire_netherite");
        }
        if (isDeeperDarkerLoaded) {
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("warden_small_axe"),
                            new SmallAxeItem(DDTiers.WARDEN,
                                    new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 5, -2.9f).withModifierAdded(Attributes.ATTACK_KNOCKBACK,
                                                    new AttributeModifier(SmallAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("warden_long_sword"),
                            new LongSwordItem(DDTiers.WARDEN,
                                    new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 6, -3.0f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(LongSwordItem.ATTACK_BONUS_MODIFIER_ID, 0.8f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("warden_dagger"),
                            new DaggerItem(DDTiers.WARDEN,
                                    new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 2, -2.0f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(DaggerItem.ATTACK_BONUS_MODIFIER_ID, -1.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("warden_big_axe"),
                            new BigAxeItem(DDTiers.WARDEN,
                                    new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 6, -3.4f).withModifierAdded(Attributes.SWEEPING_DAMAGE_RATIO,
                                                    new AttributeModifier(BigAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("warden_lance"),
                            new LanceItem(DDTiers.WARDEN,
                                    new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 3, -3.2f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(LanceItem.ATTACK_BONUS_MODIFIER_ID, 1.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("warden_healing_staff"),
                    new HealingStaffItem(DDTiers.WARDEN, 5, new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 1, -3.3f)).fireResistant()));
            ItemInit.ITEMS
                    .put(MedievalMain.identifierOf("warden_mace"),
                            new MaceItem(DDTiers.WARDEN, 3,
                                    new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 4, -2.8f).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(LongSwordItem.ATTACK_BONUS_MODIFIER_ID, -0.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))
                                            .fireResistant()));
            WARDEN_FRANCISCA_ITEM =
                    new FranciscaItem(DDTiers.WARDEN, () -> CompatEntities.WARDEN_FRANCISCA, new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 1, -2.6f)));
            WARDEN_JAVELIN_ITEM = new JavelinItem(DDTiers.WARDEN, () -> CompatEntities.WARDEN_JAVELIN,
                    new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 1, -2.6f)).fireResistant());
            ItemInit.ITEMS.put(MedievalMain.identifierOf("warden_francisca"), WARDEN_FRANCISCA_ITEM);
            ItemInit.ITEMS.put(MedievalMain.identifierOf("warden_javelin"), WARDEN_JAVELIN_ITEM);
            ItemInit.ITEMS.put(MedievalMain.identifierOf("warden_ninjato"),
                    new NinjatoItem(DDTiers.WARDEN, new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 2, -2.1f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("warden_sickle"),
                    new SickleItem(DDTiers.WARDEN, new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 2, -2.2f)).fireResistant()));
            ItemInit.ITEMS.put(MedievalMain.identifierOf("warden_rapier"),
                    new RapierItem(DDTiers.WARDEN, 3, new Item.Properties().attributes(SwordItem.createAttributes(DDTiers.WARDEN, 2, -2.0f)).fireResistant()));

            ItemInit.MATERIAL_STRINGS.add("warden");
        }

        // TODO: EarlyStage compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (107 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatItems.java.

        // TODO: Oblivion compat excluded: the mod has no usable NeoForge 1.21.1 build.
        // Original block (160 lines): _excluded/compat-unavailable/net/medievalweapons/compat/CompatItems.java.

    }
}