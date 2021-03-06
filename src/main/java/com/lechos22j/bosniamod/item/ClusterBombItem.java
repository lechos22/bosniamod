package com.lechos22j.bosniamod.item;

import com.lechos22j.bosniamod.BosniaMod;
import com.lechos22j.bosniamod.entity.ClusterBombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ClusterBombItem extends Item {
    public static final ClusterBombItem CLUSTER_BOMB_ITEM =
        new ClusterBombItem(
            new Settings()
                .group(BosniaMod.BOSNIA_ITEM_GROUP)
        );

    public ClusterBombItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            ClusterBombEntity clusterBombEntity = new ClusterBombEntity(world, user);
            clusterBombEntity.setItem(itemStack);
            clusterBombEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            if(itemStack.getNbt() != null && itemStack.getNbt().contains("stage", NbtElement.INT_TYPE))
                clusterBombEntity.setStage(itemStack.getNbt().getInt("stage"));
            if(itemStack.getNbt() != null && itemStack.getNbt().contains("bomb_type", NbtElement.STRING_TYPE))
                clusterBombEntity.setBombType(Identifier.tryParse(itemStack.getNbt().getString("bomb_type")));
            world.spawnEntity(clusterBombEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
