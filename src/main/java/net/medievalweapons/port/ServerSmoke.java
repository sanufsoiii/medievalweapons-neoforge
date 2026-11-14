package net.medievalweapons.port;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.medievalweapons.MedievalMain;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dedicated-server self-check of the port (runServerSmoke, -Dmedievalweapons.port.serverSmoke=true only):
 * spawns one entity of every entity type of the mod next to the world spawn, ticks the server, round-trips every entity
 * through NBT, logs {@code [medievalweapons-port] SERVER_SMOKE_OK entities=<N>} and stops the server.
 * Not registered at all without the system property.
 */
public final class ServerSmoke {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedievalMain.MOD_ID);
    private static final int TICKS = 100;

    private static final List<Entity> SPAWNED = new ArrayList<>();
    private static int expected = 0;
    private static int startTick = -1;
    private static boolean done = false;

    private ServerSmoke() {
    }

    public static void register(IEventBus gameBus) {
        LOGGER.info("[medievalweapons-port] SERVER_SMOKE_ENABLED");
        gameBus.addListener(ServerStartedEvent.class, ServerSmoke::onServerStarted);
        gameBus.addListener(ServerTickEvent.Post.class, ServerSmoke::onServerTick);
    }

    private static void onServerStarted(ServerStartedEvent event) {
        MinecraftServer server = event.getServer();
        ServerLevel level = server.overworld();
        BlockPos spawn = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, level.getSharedSpawnPos());

        int items = 0;
        for (ResourceLocation id : BuiltInRegistries.ITEM.keySet()) {
            if (MedievalMain.MOD_ID.equals(id.getNamespace())) {
                items++;
            }
        }
        long recipes = server.getRecipeManager().getRecipeIds().filter(id -> MedievalMain.MOD_ID.equals(id.getNamespace())).count();
        LOGGER.info("[medievalweapons-port] SERVER_SMOKE_DATA items={} recipes={}", items, recipes);

        int index = 0;
        for (EntityType<?> type : BuiltInRegistries.ENTITY_TYPE) {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(type);
            if (!MedievalMain.MOD_ID.equals(id.getNamespace())) {
                continue;
            }
            expected++;
            Entity entity = type.create(level);
            if (entity == null) {
                LOGGER.error("[medievalweapons-port] SERVER_SMOKE_FAIL could not create {}", id);
                continue;
            }
            // Spread the entities on a small grid two blocks above the surface next to the spawn point.
            double x = spawn.getX() + 0.5D + (index % 6) * 2.0D;
            double z = spawn.getZ() + 0.5D + (index / 6) * 2.0D;
            entity.moveTo(x, spawn.getY() + 2.0D, z, 0.0F, 0.0F);
            if (level.addFreshEntity(entity)) {
                SPAWNED.add(entity);
                LOGGER.info("[medievalweapons-port] SERVER_SMOKE_SPAWNED {} ({})", id, entity.getClass().getSimpleName());
            } else {
                LOGGER.error("[medievalweapons-port] SERVER_SMOKE_FAIL could not add {}", id);
            }
            index++;
        }
        startTick = server.getTickCount();
        LOGGER.info("[medievalweapons-port] SERVER_SMOKE_STARTED types={} spawned={} at {}", expected, SPAWNED.size(), spawn);
    }

    private static void onServerTick(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();
        if (done || startTick < 0 || server.getTickCount() - startTick < TICKS) {
            return;
        }
        done = true;
        ServerLevel level = server.overworld();

        // Save/load round trip: exercises addAdditionalSaveData/readAdditionalSaveData of every spawned entity.
        int roundTrips = 0;
        for (Entity entity : SPAWNED) {
            CompoundTag tag = new CompoundTag();
            if (!entity.save(tag)) {
                // save() refuses removed entities (e.g. a projectile that despawned); saveWithoutId still exercises the data.
                tag = entity.saveWithoutId(new CompoundTag());
                tag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString());
            }
            Optional<Entity> copy = EntityType.create(tag, level);
            if (copy.isPresent() && copy.get().getType() == entity.getType()) {
                roundTrips++;
            } else {
                LOGGER.error("[medievalweapons-port] SERVER_SMOKE_FAIL NBT round trip of {}", BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()));
            }
        }
        long alive = SPAWNED.stream().filter(Entity::isAlive).count();
        LOGGER.info("[medievalweapons-port] SERVER_SMOKE_TICKED ticks={} alive={} nbtRoundTrips={}", TICKS, alive, roundTrips);

        if (expected > 0 && SPAWNED.size() == expected && roundTrips == expected) {
            LOGGER.info("[medievalweapons-port] SERVER_SMOKE_OK entities={}", SPAWNED.size());
        } else {
            LOGGER.error("[medievalweapons-port] SERVER_SMOKE_FAIL expected={} spawned={} nbtRoundTrips={}", expected, SPAWNED.size(), roundTrips);
        }
        SPAWNED.forEach(Entity::discard);
        server.halt(false);
    }
}
