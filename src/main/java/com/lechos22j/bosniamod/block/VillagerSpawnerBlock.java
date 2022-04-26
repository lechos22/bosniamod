package com.lechos22j.bosniamod.block;

import com.lechos22j.bosniamod.blockentity.VillagerSpawnerBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class VillagerSpawnerBlock extends SpawnerBlock {
    public static VillagerSpawnerBlock VILLAGER_SPAWNER_BLOCK;
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VillagerSpawnerBlockEntity(pos, state);
    }
    public VillagerSpawnerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    static {
        VILLAGER_SPAWNER_BLOCK = Registry.register(Registry.BLOCK, new Identifier("bosniamod", "villager_spawner"), new VillagerSpawnerBlock(Settings.copy(Blocks.SPAWNER)));
    }
    public static void init(){}
}
