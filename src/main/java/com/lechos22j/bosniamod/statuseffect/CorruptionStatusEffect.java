package com.lechos22j.bosniamod.statuseffect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CorruptionStatusEffect extends StatusEffect {
    public static final CorruptionStatusEffect CORRUPTION_STATUS_EFFECT = new CorruptionStatusEffect();
    public CorruptionStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0xD04060);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(DamageSource.MAGIC, 1.0f * (amplifier + 1));
    }
}
