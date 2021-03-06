package com.lechos22j.bosniamod.block;

import com.lechos22j.bosniamod.BosniaMod;
import com.lechos22j.bosniamod.blockentity.CorruptedBlockEntity;
import com.lechos22j.bosniamod.statuseffect.CorruptionStatusEffect;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CorruptedBlock extends BlockWithEntity {
    public static final CorruptedBlock CORRUPTED_BLOCK = new CorruptedBlock(
        FabricBlockSettings.of(Material.WOOL)
            .strength(1.5f, 6.0f)
            .luminance(6)
            .nonOpaque()
            .velocityMultiplier(0.4f)
    );
    public static final BlockItem CORRUPTED_BLOCK_ITEM = new BlockItem(CORRUPTED_BLOCK, new FabricItemSettings().group(BosniaMod.BOSNIA_ITEM_GROUP));
    public CorruptedBlock(Settings settings) {
        super(settings);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type){
        return checkType(type, CorruptedBlockEntity.CORRUPTED_BLOCK_ENTITY_TYPE, CorruptedBlockEntity::tick);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CorruptedBlockEntity(pos, state);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if(entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(CorruptionStatusEffect.CORRUPTION_STATUS_EFFECT, 200));
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1), entity);
        }
    }
}
