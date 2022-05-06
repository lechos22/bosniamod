package com.lechos22j.bosniamod.mixin;

import com.lechos22j.bosniamod.item.GasMaskItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at=@At("RETURN"), method="isAffectedBySplashPotions()Z", cancellable = true)
    private void isAffectedBySplashPotions(CallbackInfoReturnable<Boolean> cir) {
        if(cir.getReturnValue()) {
            if((Object) this instanceof LivingEntity livingEntity) {
                System.out.println("isAffectedBySplashPotions()");
                if(livingEntity.getEquippedStack(EquipmentSlot.HEAD).getItem() == GasMaskItem.GAS_MASK_ITEM) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
