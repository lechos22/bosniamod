package com.lechos22j.bosniamod.mixin.villagerspawner;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobSpawnerBlockEntity.class)
public abstract class MobSpawnerBlockEntityMixin extends MobSpawnerBlockEntity{
    @Accessor
    public abstract MobSpawnerLogic getLogic();

    public MobSpawnerBlockEntityMixin(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public static MobSpawnerBlockEntityMixin getMe(MobSpawnerBlockEntity me){
        return (MobSpawnerBlockEntityMixin) me;
    }
}
