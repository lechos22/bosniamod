package com.lechos22j.bosniamod;

import com.lechos22j.bosniamod.entity.EntityInitializer;
import com.lechos22j.bosniamod.entity.FireBombEntity;
import com.lechos22j.bosniamod.item.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
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
			stacks.add(FireBombItem.FIRE_BOMB_ITEM.getDefaultStack());
			stacks.add(BigBombItem.BIG_BOMB_ITEM.getDefaultStack());
			stacks.add(ClusterBombItem.CLUSTER_BOMB_ITEM.getDefaultStack());
			{
				ItemStack clusterBomb = ClusterBombItem.CLUSTER_BOMB_ITEM.getDefaultStack();
				NbtCompound nbt = clusterBomb.getNbt();
				if (nbt == null)
					nbt = new NbtCompound();
				nbt.put("display", new NbtCompound() {{
					putString("Name", "{\"translate\":\"item.bosniamod.cluster_bomb_fire\"}");
				}});
				nbt.putString("bomb_type", FireBombEntity.FIRE_BOMB_ENTITY_TYPE.toString());
				clusterBomb.setNbt(nbt);
				stacks.add(clusterBomb);
			}
			{
				ItemStack clusterBomb = ClusterBombItem.CLUSTER_BOMB_ITEM.getDefaultStack();
				NbtCompound nbt = clusterBomb.getNbt();
				if (nbt == null)
					nbt = new NbtCompound();
				nbt.put("display", new NbtCompound() {{
					putString("Name", "{\"translate\":\"item.bosniamod.cluster_bomb_big\"}");
				}});
				nbt.putString("bomb_type", BigBombItem.BIG_BOMB_ITEM.toString());
				clusterBomb.setNbt(nbt);
				stacks.add(clusterBomb);
			}
			stacks.add(GasMaskItem.GAS_MASK_ITEM.getDefaultStack());
			{
				ItemStack villagerSpawner = Items.SPAWNER.getDefaultStack();
				NbtCompound nbt = villagerSpawner.getNbt();
				if (nbt == null)
					nbt = new NbtCompound();
				nbt.put("display", new NbtCompound() {{
					putString("Name", "{\"translate\":\"block.bosniamod.bosnian_spawner\"}");
				}});
				nbt.put("BlockEntityTag", new NbtCompound() {{
					put("SpawnData", new NbtCompound() {{
						put("entity", new NbtCompound() {{
							putString("id", "minecraft:villager");
							putString("CustomName", "{\"translate\":\"entity.bosniamod.bosnian\"}");
							putBoolean("CustomNameVisible", true);
						}});
					}});
				}});
				villagerSpawner.setNbt(nbt);
				stacks.add(villagerSpawner);
			}
			{
				ItemStack funnierVillagerSpawner = Items.SPAWNER.getDefaultStack();
				NbtCompound funnierNbt = funnierVillagerSpawner.getNbt();
				if (funnierNbt == null)
					funnierNbt = new NbtCompound();
				funnierNbt.put("display", new NbtCompound() {{
					putString("Name", "{\"translate\":\"block.bosniamod.funnier_bosnian_spawner\"}");
				}});
				funnierNbt.put("BlockEntityTag", new NbtCompound() {{
					put("SpawnData", new NbtCompound() {{
						put("entity", new NbtCompound() {{
							putString("id", "minecraft:villager");
							putString("CustomName", "{\"translate\":\"entity.bosniamod.bosnian\"}");
							putBoolean("CustomNameVisible", true);
							putLong("Age", -23553);
						}});
					}});
				}});
				funnierVillagerSpawner.setNbt(funnierNbt);
				stacks.add(funnierVillagerSpawner);
			}
		})
		.build();

	@Override
	public void onInitialize() {
		ItemInitializer.init();
		EntityInitializer.init();
		LOGGER.info("BosniaMod initialized");
	}
}
