package com.lechos22j.bosniamod.entity;

import com.lechos22j.bosniamod.item.HandBombItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class HandBombEntity extends ThrownItemEntity {
    public static final EntityType<HandBombEntity> HAND_BOMB_ENTITY_TYPE =
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC,
            (EntityType<HandBombEntity> type, World world) -> new HandBombEntity(type, world)
        )
            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
            .trackRangeBlocks(4)
            .trackedUpdateRate(10)
            .build();

    public HandBombEntity(EntityType<? extends HandBombEntity> type, World world) {
        super(type, world);
    }
    public HandBombEntity(World world, double x, double y, double z) {
        super(HAND_BOMB_ENTITY_TYPE, x, y, z, world);
    }
    public HandBombEntity(World world, LivingEntity livingEntity) {
        super(HAND_BOMB_ENTITY_TYPE, livingEntity, world);
    }
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!world.isClient) {
            world.sendEntityStatus(this, (byte)3);
            explode();
            kill();
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!world.isClient) {
            world.sendEntityStatus(this, (byte)3);
            explode();
            kill();
        }
    }

    private void explode() {
        if (!world.isClient) {
            world.createExplosion(this, getX(), getY(), getZ(), 4.0F, Explosion.DestructionType.BREAK);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return HandBombItem.HAND_BOMB_ITEM;
    }
}
