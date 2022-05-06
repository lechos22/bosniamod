package com.lechos22j.bosniamod.item;

import com.lechos22j.bosniamod.BosniaMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInitializer {
    public static void init() {
        Registry.register(
            Registry.ITEM,
            new Identifier(BosniaMod.MOD_ID, "cluster_bomb"),
            ClusterBombItem.CLUSTER_BOMB_ITEM
        );
        Registry.register(
            Registry.ITEM,
            new Identifier(BosniaMod.MOD_ID, "gas_mask"),
            GasMaskItem.GAS_MASK_ITEM
        );
        Registry.register(
            Registry.ITEM,
            new Identifier(BosniaMod.MOD_ID, "hand_bomb"),
            HandBombItem.HAND_BOMB_ITEM
        );
        Registry.register(
            Registry.ITEM,
            new Identifier(BosniaMod.MOD_ID, "big_bomb"),
            BigBombItem.BIG_BOMB_ITEM
        );
    }
}
