package net.medievalweapons.init;

import net.medievalweapons.MedievalMain;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class ModelInit {

    // Fabric calls init() from onInitializeClient(). NeoForge: FMLClientSetupEvent (mod bus), on the main thread
    // (ItemProperties maps are not thread-safe).
    public static void init(IEventBus modBus) {
        modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork((Runnable) ModelInit::init));
    }

    // The original passes ClampedItemPropertyFunction lambdas to the (Fabric access-widened) clamped overloads, which are
    // private in NeoForge; the public overloads take ItemPropertyFunction, so the lambdas are typed as
    // ClampedItemPropertyFunction explicitly to keep the 0..1 clamping.
    public static void init() {
        ItemProperties.register(ItemInit.LONG_BOW_ITEM, ResourceLocation.parse("pull"), (ClampedItemPropertyFunction) (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 50.0F;
            }
        });
        ItemProperties.register(ItemInit.LONG_BOW_ITEM, ResourceLocation.parse("pulling"), (ClampedItemPropertyFunction) (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        });
        ItemProperties.register(ItemInit.RECURVE_BOW_ITEM, ResourceLocation.parse("pull"), (ClampedItemPropertyFunction) (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 16.0F;
            }
        });
        ItemProperties.register(ItemInit.RECURVE_BOW_ITEM, ResourceLocation.parse("pulling"), (ClampedItemPropertyFunction) (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        });
        ItemProperties.registerGeneric(MedievalMain.identifierOf("offhand"), (ClampedItemPropertyFunction) (stack, world, entity, seed) -> {
            return entity != null && !entity.getOffhandItem().isEmpty() ? 1.0F : 0.0F;
        });
        ItemProperties.registerGeneric(ResourceLocation.parse("throwing"), (ClampedItemPropertyFunction) (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0f : 0.0f;
        });
        ItemProperties.registerGeneric(MedievalMain.identifierOf("gui"), (ClampedItemPropertyFunction) (stack, world, entity, seed) -> {
            return seed == 0 ? 1.0f : 0.0f;
        });
    }

}
