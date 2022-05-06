package com.lechos22j.bosniamod.entity;

import com.lechos22j.bosniamod.BosniaMod;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInitializer {
    public static void init(){
        Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BosniaMod.MOD_ID,"hand_bomb"),
            HandBombEntity.HAND_BOMB_ENTITY_TYPE
        );
        EntityRendererRegistry.register(HandBombEntity.HAND_BOMB_ENTITY_TYPE, FlyingItemEntityRenderer::new);
        Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BosniaMod.MOD_ID,"cluster_bomb"),
            ClusterBombEntity.CLUSTER_BOMB_ENTITY_TYPE
        );
        EntityRendererRegistry.register(ClusterBombEntity.CLUSTER_BOMB_ENTITY_TYPE, FlyingItemEntityRenderer::new);
    }
}
