package com.lechos22j.bosniamod.item;

import com.lechos22j.bosniamod.BosniaMod;
import com.lechos22j.bosniamod.armormaterial.GasMaskArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GasMaskItem extends ArmorItem {
    public static GasMaskItem GAS_MASK_ITEM;
    public GasMaskItem(Settings settings) {
        super(GasMaskArmorMaterial.GAS_MASK_ARMOR_MATERIAL, EquipmentSlot.HEAD, settings);
    }

    static {
        GAS_MASK_ITEM = Registry.register(Registry.ITEM, new Identifier(BosniaMod.MOD_ID, "gas_mask"), new GasMaskItem(new Settings().group(BosniaMod.BOSNIA_ITEM_GROUP).maxCount(1)));
    }

    public static void init() {
    }
}
