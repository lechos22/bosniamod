package com.lechos22j.bosniamod.entity;

import com.lechos22j.bosniamod.BosniaMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInitializer {
    public static void init(){
        Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BosniaMod.MOD_ID,"hand_bomb"),
            HandBombEntity.HAND_BOMB_ENTITY_TYPE
        );
        Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BosniaMod.MOD_ID,"cluster_bomb"),
            ClusterBombEntity.CLUSTER_BOMB_ENTITY_TYPE
        );
    }
}
