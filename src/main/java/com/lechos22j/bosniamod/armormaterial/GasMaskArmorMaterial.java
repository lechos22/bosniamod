package com.lechos22j.bosniamod.armormaterial;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class GasMaskArmorMaterial implements ArmorMaterial {
    public static final ArmorMaterial GAS_MASK_ARMOR_MATERIAL;

    @Override
    public int getDurability(EquipmentSlot slot) {
        if (slot == EquipmentSlot.HEAD) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        if (slot == EquipmentSlot.HEAD) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.LEATHER);
    }

    @Override
    public String getName() {
        return "gas_mask";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }

    static {
        GAS_MASK_ARMOR_MATERIAL = new GasMaskArmorMaterial();
    }

    public static void init() {}
}
