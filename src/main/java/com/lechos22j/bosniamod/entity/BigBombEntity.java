package com.lechos22j.bosniamod.entity;

import com.lechos22j.bosniamod.item.HandBombItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class BigBombEntity extends ThrownItemEntity {
    public static final EntityType<BigBombEntity> BIG_BOMB_ENTITY_TYPE =
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC,
            (EntityType<BigBombEntity> type, World world) -> new BigBombEntity(type, world)
        )
            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
            .trackRangeBlocks(4)
            .trackedUpdateRate(10)
            .build();

    public BigBombEntity(EntityType<? extends BigBombEntity> type, World world) {
        super(type, world);
    }
    public BigBombEntity(World world, double x, double y, double z) {
        super(BIG_BOMB_ENTITY_TYPE, x, y, z, world);
    }
    public BigBombEntity(World world, LivingEntity livingEntity) {
        super(BIG_BOMB_ENTITY_TYPE, livingEntity, world);
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
            world.createExplosion(this, getX(), getY(), getZ(), 32.0F, Explosion.DestructionType.DESTROY);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return HandBombItem.HAND_BOMB_ITEM;
    }
}
