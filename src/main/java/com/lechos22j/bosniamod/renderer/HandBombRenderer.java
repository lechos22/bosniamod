package com.lechos22j.bosniamod.renderer;

import com.lechos22j.bosniamod.entity.HandBombEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HandBombRenderer extends EntityRenderer<HandBombEntity> {
    private final TntEntityRenderer tntRenderer;
    public HandBombRenderer(EntityRendererFactory.Context context) {
        super(context);
        tntRenderer = new TntEntityRenderer(context);
    }

    @Override
    public Identifier getTexture(HandBombEntity entity) {
        return null;
    }

    @Override
    public void render(HandBombEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        matrices.push();
        matrices.scale(0.5f, 0.5f, 0.5f);
        tntRenderer.render(entity.getTntEntity(), yaw, tickDelta, matrices, vertexConsumers, light);
        matrices.pop();
    }
}
