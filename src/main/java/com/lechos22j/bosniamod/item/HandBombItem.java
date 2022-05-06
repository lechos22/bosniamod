package com.lechos22j.bosniamod.item;

import com.lechos22j.bosniamod.BosniaMod;
import com.lechos22j.bosniamod.entity.HandBombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HandBombItem extends Item {
    public static final HandBombItem HAND_BOMB_ITEM =
        new HandBombItem(
            new Settings()
                .group(BosniaMod.BOSNIA_ITEM_GROUP)
        );
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
}
