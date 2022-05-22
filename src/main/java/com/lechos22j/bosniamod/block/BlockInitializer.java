package com.lechos22j.bosniamod.block;

import com.lechos22j.bosniamod.BosniaMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInitializer {
    public static void init(){
        Registry.register(Registry.BLOCK, new Identifier(BosniaMod.MOD_ID, "corrupted_block"), CorruptedBlock.CORRUPTED_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(BosniaMod.MOD_ID, "corrupted_block"), CorruptedBlock.CORRUPTED_BLOCK_ITEM);
        Registry.register(Registry.BLOCK, new Identifier(BosniaMod.MOD_ID, "corrupting_block"), CorruptingBlock.CORRUPTING_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(BosniaMod.MOD_ID, "corrupting_block"), CorruptingBlock.CORRUPTING_BLOCK_ITEM);
    }
}
