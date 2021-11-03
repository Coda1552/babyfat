package coda.babyfat.client.renderer;

import coda.babyfat.BabyFat;
import coda.babyfat.client.model.RanchuModel;
import coda.babyfat.entities.RanchuEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class RanchuRenderer extends MobRenderer<RanchuEntity, RanchuModel<RanchuEntity>> {
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[RanchuEntity.MAX_VARIANTS];

    public RanchuRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RanchuModel<>(), 0.35F);
    }

    @Override
    public ResourceLocation getTextureLocation(RanchuEntity entity) {
        int variant = entity.getVariant();
        ResourceLocation loc = new ResourceLocation(BabyFat.MOD_ID, "textures/entity/ranchu_" + variant + ".png");
        return TEXTURES[variant] = loc;
    }

    @Override
    protected void setupRotations(RanchuEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater() && entityLiving.isAddedToWorld()) {
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}
