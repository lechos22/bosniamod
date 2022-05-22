package com.lechos22j.bosniamod.block;

import com.lechos22j.bosniamod.BosniaMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;

public class CorruptedBlock extends Block {
    public static final CorruptedBlock CORRUPTED_BLOCK = new CorruptedBlock(
        FabricBlockSettings.of(Material.BARRIER)
            .strength(1.5f, 6.0f)
            .luminance(6)
            .nonOpaque()
            .dropsNothing()
            .velocityMultiplier(0.4f)
    );
    public static final BlockItem CORRUPTED_BLOCK_ITEM = new BlockItem(CORRUPTED_BLOCK, new FabricItemSettings().group(BosniaMod.BOSNIA_ITEM_GROUP));
    public CorruptedBlock(Settings settings) {
        super(settings);
    }
}
