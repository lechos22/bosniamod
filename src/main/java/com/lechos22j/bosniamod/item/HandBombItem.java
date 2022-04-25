package com.lechos22j.bosniamod.item;

import com.lechos22j.bosniamod.BosniaMod;
import com.lechos22j.bosniamod.entity.HandBombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class HandBombItem extends Item {
    public static final HandBombItem HAND_BOMB_ITEM;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            HandBombEntity handBombEntity = new HandBombEntity(world, user);
            handBombEntity.setItem(itemStack);
            handBombEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(handBombEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
    public HandBombItem(Settings settings) {
        super(settings);
    }
    static {
        HAND_BOMB_ITEM = Registry.register(
            Registry.ITEM,
            new Identifier(BosniaMod.MOD_ID, "hand_bomb"),
            new HandBombItem(
                new Settings()
                    .group(ItemGroup.COMBAT)
            )
        );
    }
    public static void init() {
    }
}
