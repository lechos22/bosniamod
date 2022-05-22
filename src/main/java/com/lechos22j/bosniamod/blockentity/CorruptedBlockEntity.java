package com.lechos22j.bosniamod.blockentity;

import com.lechos22j.bosniamod.BosniaMod;
import com.lechos22j.bosniamod.block.CorruptedBlock;
import com.lechos22j.bosniamod.block.CorruptingBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class CorruptedBlockEntity extends BlockEntity {
    public static final BlockEntityType<CorruptedBlockEntity> CORRUPTED_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
        .create(CorruptedBlockEntity::new, CorruptedBlock.CORRUPTED_BLOCK)
        .build();
    private long age = 0;
    private int generation = 0;
    public CorruptedBlockEntity(BlockPos pos, BlockState state) {
        super(CORRUPTED_BLOCK_ENTITY_TYPE, pos, state);
    }

    private void rebreed(BlockPos pos){
        assert world != null;
        if(!world.getBlockState(pos).isAir() && world.getBlockState(pos).getBlock() != CorruptedBlock.CORRUPTED_BLOCK && world.getBlockState(pos).getBlock() != CorruptingBlock.CORRUPTING_BLOCK){
            world.setBlockState(pos, CorruptedBlock.CORRUPTED_BLOCK.getDefaultState());
            world.getBlockEntity(pos, CORRUPTED_BLOCK_ENTITY_TYPE).ifPresent(entity -> {
                entity.generation = generation + 1;
            });
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, CorruptedBlockEntity blockEntity) {
        if(blockEntity.age++ >= 100) {
            world.breakBlock(pos, false);
        }
        else if(blockEntity.age == 50 && blockEntity.generation < 5) {
            blockEntity.rebreed(pos.down());
            blockEntity.rebreed(pos.up());
            blockEntity.rebreed(pos.north());
            blockEntity.rebreed(pos.south());
            blockEntity.rebreed(pos.east());
            blockEntity.rebreed(pos.west());
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putLong("age", age);
        nbt.putInt("generation", generation);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        age = nbt.getLong("age");
        generation = nbt.getInt("generation");
        super.readNbt(nbt);
    }
}
