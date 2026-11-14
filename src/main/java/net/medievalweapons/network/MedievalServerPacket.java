package net.medievalweapons.network;

import net.medievalweapons.access.PlayerAccess;
import net.medievalweapons.network.packet.AttackPacket;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class MedievalServerPacket {

    // Fabric: PayloadTypeRegistry.playC2S().register(...) + ServerPlayNetworking.registerGlobalReceiver(...) in onInitialize().
    // NeoForge: payloads are registered on both sides through RegisterPayloadHandlersEvent (mod bus).
    public static void init(IEventBus modBus) {
        modBus.addListener(RegisterPayloadHandlersEvent.class, MedievalServerPacket::registerPayloads);
    }

    private static void registerPayloads(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToServer(AttackPacket.PACKET_ID, AttackPacket.PACKET_CODEC, (payload, context) -> {
            int entityId = payload.entityId();
            ServerPlayer player = (ServerPlayer) context.player();
            player.server.execute(() -> {
                player.resetLastActionTime();
                if (player.level().getEntity(entityId) != null) {
                    ((PlayerAccess) player).doOffhandAttack(player.level().getEntity(entityId));
                }
            });
        });
    }

}
