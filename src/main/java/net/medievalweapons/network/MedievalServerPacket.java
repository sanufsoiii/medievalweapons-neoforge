package net.medievalweapons.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.medievalweapons.access.PlayerAccess;
import net.medievalweapons.network.packet.AttackPacket;

public class MedievalServerPacket {

    public static void init() {
        PayloadTypeRegistry.playC2S().register(AttackPacket.PACKET_ID, AttackPacket.PACKET_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(AttackPacket.PACKET_ID, (payload, context) -> {
            int entityId = payload.entityId();
            context.player().server.execute(() -> {
                context.player().resetLastActionTime();
                if (context.player().level().getEntity(entityId) != null) {
                    ((PlayerAccess) context.player()).doOffhandAttack(context.player().level().getEntity(entityId));
                }
            });
        });
    }

}
