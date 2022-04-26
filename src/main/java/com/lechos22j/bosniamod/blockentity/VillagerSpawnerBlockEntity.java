package com.lechos22j.bosniamod.blockentity;

import com.lechos22j.bosniamod.BosniaMod;
import com.lechos22j.bosniamod.mixin.villagerspawner.MobSpawnerBlockEntityMixin;
import com.lechos22j.bosniamod.mixin.villagerspawner.MobSpawnerLogicMixin;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerEntry;
import net.minecraft.world.MobSpawnerLogic;

public class VillagerSpawnerBlockEntity extends MobSpawnerBlockEntity {
    public VillagerSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        // TODO: change spawned entity
        MobSpawnerLogic logic = MobSpawnerBlockEntityMixin.getMe(this).getLogic();
        MobSpawnerLogicMixin.getMe(logic).setMinSpawnDelay(100);
        MobSpawnerLogicMixin.getMe(logic).setMaxSpawnDelay(400);
        MobSpawnerEntry mobSpawnerEntry = new MobSpawnerEntry();
        mobSpawnerEntry.getNbt().putString("id", "minecraft:villager");
        logic.setSpawnEntry(world, pos, mobSpawnerEntry);
    }
}
