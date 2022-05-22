package com.lechos22j.bosniamod.item;

import com.lechos22j.bosniamod.BosniaMod;
import com.lechos22j.bosniamod.statuseffect.CorruptionStatusEffect;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CorruptedItem extends Item {
    public static CorruptedItem CORRUPTED_ITEM = new CorruptedItem(new FabricItemSettings().group(BosniaMod.BOSNIA_ITEM_GROUP));
    public CorruptedItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if(entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(CorruptionStatusEffect.CORRUPTION_STATUS_EFFECT, 100), entity);
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1), entity);
        }
    }
}
