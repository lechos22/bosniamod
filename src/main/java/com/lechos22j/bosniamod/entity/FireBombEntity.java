package com.lechos22j.bosniamod.entity;

import com.lechos22j.bosniamod.item.FireBombItem;
import com.lechos22j.bosniamod.item.HandBombItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

public class FireBombEntity extends ThrownItemEntity {
    public static final EntityType<FireBombEntity> FIRE_BOMB_ENTITY_TYPE =
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC,
            (EntityType<FireBombEntity> type, World world) -> new FireBombEntity(type, world)
        )
            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
            .trackRangeBlocks(4)
            .trackedUpdateRate(10)
            .build();

    public FireBombEntity(EntityType<? extends FireBombEntity> type, World world) {
        super(type, world);
    }
    public FireBombEntity(World world, double x, double y, double z) {
        super(FIRE_BOMB_ENTITY_TYPE, x, y, z, world);
    }
    public FireBombEntity(World world, LivingEntity livingEntity) {
        super(FIRE_BOMB_ENTITY_TYPE, livingEntity, world);
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
            LivingEntity owner = (this.getOwner() instanceof LivingEntity tmp) ? tmp : null;
            world.createExplosion(this, DamageSource.explosion(owner), new ExplosionBehavior(), getX(), getY(), getZ(), 4.0F, true, Explosion.DestructionType.NONE);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return FireBombItem.FIRE_BOMB_ITEM;
    }
}
