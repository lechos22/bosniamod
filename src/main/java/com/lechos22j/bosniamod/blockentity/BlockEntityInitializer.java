package com.lechos22j.bosniamod.blockentity;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntityInitializer {
    public static void init() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("bosniamod", "corrupted_block"), CorruptedBlockEntity.CORRUPTED_BLOCK_ENTITY_TYPE);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("bosniamod", "corrupting_block"), CorruptingBlockEntity.CORRUPTING_BLOCK_ENTITY_TYPE);
    }
}
