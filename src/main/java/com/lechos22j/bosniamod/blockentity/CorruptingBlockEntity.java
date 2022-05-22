package com.lechos22j.bosniamod.blockentity;

import com.lechos22j.bosniamod.block.CorruptedBlock;
import com.lechos22j.bosniamod.block.CorruptingBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CorruptingBlockEntity extends BlockEntity {
    public static final BlockEntityType<CorruptingBlockEntity> CORRUPTING_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
        .create(CorruptingBlockEntity::new, CorruptingBlock.CORRUPTING_BLOCK)
        .build();
    private long age = 0;
    public CorruptingBlockEntity(BlockPos pos, BlockState state) {
        super(CORRUPTING_BLOCK_ENTITY_TYPE, pos, state);
    }

    private void rebreed(BlockPos pos){
        assert world != null;
        if(!world.getBlockState(pos).isAir() && world.getBlockState(pos).getBlock() != CorruptedBlock.CORRUPTED_BLOCK && world.getBlockState(pos).getBlock() != CorruptingBlock.CORRUPTING_BLOCK){
            world.setBlockState(pos, CorruptedBlock.CORRUPTED_BLOCK.getDefaultState());
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, CorruptingBlockEntity blockEntity) {
        if(blockEntity.age++ % 50 == 49) {
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
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        age = nbt.getLong("age");
        super.readNbt(nbt);
    }
}
