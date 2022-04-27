package com.lechos22j.bosniamod.renderer;

import com.lechos22j.bosniamod.entity.ClusterBombEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ClusterBombRenderer extends EntityRenderer<ClusterBombEntity> {
    public ClusterBombRenderer(EntityRendererFactory.Context context) {
        // TODO: model and texture
        super(context);
    }

    @Override
    public Identifier getTexture(ClusterBombEntity entity) {
        return null;
    }

    @Override
    public void render(ClusterBombEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
