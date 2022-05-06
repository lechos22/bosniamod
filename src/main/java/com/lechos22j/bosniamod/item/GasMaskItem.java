package com.lechos22j.bosniamod.item;

import com.lechos22j.bosniamod.BosniaMod;
import com.lechos22j.bosniamod.armormaterial.GasMaskArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class GasMaskItem extends ArmorItem {
    public static final GasMaskItem GAS_MASK_ITEM =
        new GasMaskItem(
            new Settings()
                .group(BosniaMod.BOSNIA_ITEM_GROUP)
                .maxCount(1)
        );
    public GasMaskItem(Settings settings) {
        super(GasMaskArmorMaterial.GAS_MASK_ARMOR_MATERIAL, EquipmentSlot.HEAD, settings);
    }
}
