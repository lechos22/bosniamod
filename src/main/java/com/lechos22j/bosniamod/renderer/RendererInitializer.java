package com.lechos22j.bosniamod.renderer;

import com.lechos22j.bosniamod.entity.ClusterBombEntity;
import com.lechos22j.bosniamod.entity.HandBombEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class RendererInitializer {
    public static void init(){
        EntityRendererRegistry.register(HandBombEntity.HAND_BOMB_ENTITY_TYPE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ClusterBombEntity.CLUSTER_BOMB_ENTITY_TYPE, FlyingItemEntityRenderer::new);
    }
}
