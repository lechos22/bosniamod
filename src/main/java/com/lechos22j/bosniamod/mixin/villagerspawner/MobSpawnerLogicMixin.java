package com.lechos22j.bosniamod.mixin.villagerspawner;

import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.world.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin extends MobSpawnerLogic {
    @Accessor
    public abstract int setMinSpawnDelay(int _new);
    @Accessor
    public abstract int setMaxSpawnDelay(int _new);
    public static MobSpawnerLogicMixin getMe(MobSpawnerLogic me){
        return (MobSpawnerLogicMixin) me;
    }
}
