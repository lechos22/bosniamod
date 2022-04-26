package com.lechos22j.bosniamod;

import com.lechos22j.bosniamod.entity.HandBombEntity;
import com.lechos22j.bosniamod.item.HandBombItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BosniaMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("bosniamod");
	public static final String MOD_ID = "bosniamod";
	public static final ItemGroup BOSNIA_ITEM_GROUP = FabricItemGroupBuilder
		.create(new Identifier("bosniamod", "all"))
		.icon(HandBombItem.HAND_BOMB_ITEM::getDefaultStack)
		.appendItems(stacks -> {
			stacks.add(HandBombItem.HAND_BOMB_ITEM.getDefaultStack());
			ItemStack villagerSpawner = Items.SPAWNER.getDefaultStack();
			NbtCompound nbt = villagerSpawner.getNbt();
			//spawner{BlockEntityTag:{SpawnData:{entity:{id:"minecraft:villager",CustomName:'{"text":"Bosniac"}'}}}}
			nbt.getCompound("BlockEntityTag").getCompound("SpawnData").getCompound("entity").putString("id", "minecraft:villager");
			nbt.getCompound("BlockEntityTag").getCompound("SpawnData").getCompound("entity").putString("CustomName", "{\"text\":\"Bosniac\"}");
			stacks.add(villagerSpawner);
		})
		.build();

	static {
		LOGGER.info("BosniaMod loaded");
	}

	@Override
	public void onInitialize() {
		HandBombItem.init();
		HandBombEntity.init();
		// TODO: Cluster Bomb
		LOGGER.info("BosniaMod initialized");
	}
}
