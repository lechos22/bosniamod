package com.lechos22j.bosniamod.statuseffect;

import com.lechos22j.bosniamod.BosniaMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StatusEffectInitializer {
    public static void init(){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(BosniaMod.MOD_ID, "corruption"), CorruptionStatusEffect.CORRUPTION_STATUS_EFFECT);
    }
}
