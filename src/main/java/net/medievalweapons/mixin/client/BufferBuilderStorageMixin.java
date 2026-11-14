package net.medievalweapons.mixin.client;

import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.medievalweapons.init.RenderInit;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderBuffers.class)
public class BufferBuilderStorageMixin {

    @Inject(method = "put", at = @At("HEAD"))
    private static void assignBufferBuilder(Object2ObjectLinkedOpenHashMap<RenderType, ByteBufferBuilder> builderStorage, RenderType layer, CallbackInfo info) {
        for (RenderType customLayer : RenderInit.CUSTOM_GLINT.values()) {
            builderStorage.put(customLayer, new ByteBufferBuilder(256));
        }
    }
}
