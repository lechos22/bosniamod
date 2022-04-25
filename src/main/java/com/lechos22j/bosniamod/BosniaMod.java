package com.lechos22j.bosniamod;

import com.lechos22j.bosniamod.entity.HandBombEntity;
import com.lechos22j.bosniamod.item.HandBombItem;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BosniaMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("bosniamod");
	public static final String MOD_ID = "bosniamod";

	static {
		LOGGER.info("BosniaMod loaded");
	}

	@Override
	public void onInitialize() {
		HandBombItem.init();
		HandBombEntity.init();
		LOGGER.info("BosniaMod initialized");
	}
}
