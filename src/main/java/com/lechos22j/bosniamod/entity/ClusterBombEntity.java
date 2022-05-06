package com.lechos22j.bosniamod.entity;

import com.lechos22j.bosniamod.item.ClusterBombItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ClusterBombEntity extends ThrownItemEntity {
    public static final EntityType<ClusterBombEntity> CLUSTER_BOMB_ENTITY_TYPE =
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC,
            (EntityType<ClusterBombEntity> type, World world) -> new ClusterBombEntity(type, world)
        )
            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
            .trackRangeBlocks(4)
            .trackedUpdateRate(10)
            .build();

    public TntEntity getTntEntity() {
        return tntEntity;
    }

    private final TntEntity tntEntity;
    private int stage;
    public ClusterBombEntity(EntityType<? extends ClusterBombEntity> type, World world) {
        super(type, world);
        tntEntity = new TntEntity(EntityType.TNT, world);
        tntEntity.setFuse(Integer.MAX_VALUE);
        world.spawnEntity(tntEntity);
        tntEntity.startRiding(this);
        stage = 1;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        stage = nbt.getInt("stage");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound result = super.writeNbt(nbt);
        result.putInt("stage", stage);
        return result;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public ClusterBombEntity(World world, double x, double y, double z) {
        super(CLUSTER_BOMB_ENTITY_TYPE, x, y, z, world);
        tntEntity = new TntEntity(EntityType.TNT, world);
        tntEntity.setFuse(Integer.MAX_VALUE);
        world.spawnEntity(tntEntity);
        tntEntity.startRiding(this);
        stage = 1;
    }

    public ClusterBombEntity(World world, LivingEntity livingEntity) {
        super(CLUSTER_BOMB_ENTITY_TYPE, livingEntity, world);
        tntEntity = new TntEntity(EntityType.TNT, world);
        tntEntity.setFuse(Integer.MAX_VALUE);
        world.spawnEntity(tntEntity);
        tntEntity.startRiding(this);
        stage = 1;
    }
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!world.isClient) {
            world.sendEntityStatus(this, (byte)3);
            tntEntity.kill();
            explode();
            kill();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(getY() < -100) {
            if(!world.isClient) {
                world.sendEntityStatus(this, (byte)3);
                tntEntity.kill();
                explode();
                kill();
            }
        }

        if(!world.isClient && age >= 15) {
            for(double i = -2; i <= 2; i += 2) {
                for(double j = -2; j <= 2; j += 2) {
                    ProjectileEntity bomb;
                    if(stage > 0) {
                        bomb = new ClusterBombEntity(world, getX() + i, getY() - 1.0, getZ() + j);
                        ((ClusterBombEntity)bomb).setStage(stage - 1);
                    }
                    else
                        bomb = new HandBombEntity(world, getX() + i, getY() - 1.0, getZ() + j);
                    bomb.setVelocity(getVelocity().add(0.0, -0.2, 0.0));
                    world.spawnEntity(bomb);
                }
            }
            world.sendEntityStatus(this, (byte)3);
            tntEntity.kill();
            explode();
            kill();
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!world.isClient) {
            world.sendEntityStatus(this, (byte)3);
            tntEntity.kill();
            explode();
            kill();
        }
    }

    private void explode() {
        if (!world.isClient) {
            world.createExplosion(this, getX(), getY(), getZ(), 6.0F, Explosion.DestructionType.BREAK);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ClusterBombItem.CLUSTER_BOMB_ITEM;
    }
}
