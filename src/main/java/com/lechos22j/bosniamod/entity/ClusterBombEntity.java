package com.lechos22j.bosniamod.entity;

import com.lechos22j.bosniamod.item.ClusterBombItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
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

    private int stage;
    private EntityType<?> bombType = HandBombEntity.HAND_BOMB_ENTITY_TYPE;
    public ClusterBombEntity(EntityType<? extends ClusterBombEntity> type, World world) {
        super(type, world);
        stage = 1;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        stage = nbt.getInt("stage");
        setBombType(Identifier.tryParse(nbt.getString("bomb_type")));
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound result = super.writeNbt(nbt);
        result.putInt("stage", stage);
        result.putString("bomb_type", Registry.ENTITY_TYPE.getId(bombType).toString());
        return result;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public EntityType<?> getBombType(){
        return bombType;
    }

    public void setBombType(EntityType<?> bombType){
        this.bombType = bombType;
    }

    public void setBombType(Identifier bombType){
        try {
            this.bombType = Registry.ENTITY_TYPE.get(bombType);
        } catch (ClassCastException exception){
            exception.printStackTrace();
        }
    }

    public ClusterBombEntity(World world, double x, double y, double z) {
        super(CLUSTER_BOMB_ENTITY_TYPE, x, y, z, world);
        stage = 1;
    }

    public ClusterBombEntity(World world, LivingEntity livingEntity) {
        super(CLUSTER_BOMB_ENTITY_TYPE, livingEntity, world);
        stage = 1;
    }
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!world.isClient) {
            world.sendEntityStatus(this, (byte)3);
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
                explode();
                kill();
            }
        }

        if(!world.isClient && age >= 15) {
            for(double i = -2; i <= 2; i += 2) {
                for(double j = -2; j <= 2; j += 2) {
                    ProjectileEntity bomb = null;
                    if(stage > 0) {
                        bomb = new ClusterBombEntity(world, getX() + i, getY() - 1.0, getZ() + j);
                        ((ClusterBombEntity)bomb).stage = stage - 1;
                        ((ClusterBombEntity)bomb).bombType = bombType;
                    }
                    else {
                        if(bombType != null) {
                            if(bombType.create(world) instanceof ProjectileEntity tmp) {
                                bomb = tmp;
                                bomb.setPos(getX() + i, getY() - 1.0, getZ() + j);
                            }
                        }
                    }
                    if(bomb != null) {
                        bomb.setOwner(getOwner());
                        bomb.setVelocity(getVelocity().add(0.0, -0.2, 0.0));
                        world.spawnEntity(bomb);
                    }
                }
            }
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
            world.createExplosion(this, getX(), getY(), getZ(), 6.0F, Explosion.DestructionType.BREAK);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ClusterBombItem.CLUSTER_BOMB_ITEM;
    }
}
