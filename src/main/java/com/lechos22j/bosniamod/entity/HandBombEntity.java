package com.lechos22j.bosniamod.entity;

import com.lechos22j.bosniamod.BosniaMod;
import com.lechos22j.bosniamod.item.HandBombItem;
import com.lechos22j.bosniamod.renderer.HandBombRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class HandBombEntity extends ThrownItemEntity {
    public static final EntityType<HandBombEntity> HAND_BOMB_ENTITY_TYPE;

    public TntEntity getTntEntity() {
        return tntEntity;
    }

    private final TntEntity tntEntity;
    public HandBombEntity(EntityType<? extends HandBombEntity> type, World world) {
        super(type, world);
        tntEntity = new TntEntity(EntityType.TNT, world);
        tntEntity.setFuse(Integer.MAX_VALUE);
        world.spawnEntity(tntEntity);
        tntEntity.startRiding(this);
    }
    public HandBombEntity(World world, double x, double y, double z) {
        super(HAND_BOMB_ENTITY_TYPE, x, y, z, world);
        tntEntity = new TntEntity(EntityType.TNT, world);
        tntEntity.setFuse(Integer.MAX_VALUE);
        world.spawnEntity(tntEntity);
        tntEntity.startRiding(this);
    }
    public HandBombEntity(World world, LivingEntity livingEntity) {
        super(HAND_BOMB_ENTITY_TYPE, livingEntity, world);
        tntEntity = new TntEntity(EntityType.TNT, world);
        tntEntity.setFuse(Integer.MAX_VALUE);
        world.spawnEntity(tntEntity);
        tntEntity.startRiding(this);
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
            world.createExplosion(this, getX(), getY(), getZ(), 2.0F, Explosion.DestructionType.BREAK);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return HandBombItem.HAND_BOMB_ITEM;
    }
    static {
        HAND_BOMB_ENTITY_TYPE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(BosniaMod.MOD_ID,"hand_bomb"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType<HandBombEntity> type, World world) -> new HandBombEntity(type, world))
                .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                .trackRangeBlocks(4)
                .trackedUpdateRate(10)
                .build()
        );
    }
    public static void init() {
        EntityRendererRegistry.register(HAND_BOMB_ENTITY_TYPE, HandBombRenderer::new);
    }
}
