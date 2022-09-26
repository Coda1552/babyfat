package coda.babyfat.client.renderer;

import coda.babyfat.BabyFat;
import coda.babyfat.client.ModModelLayers;
import coda.babyfat.client.model.RanchuModel;
import coda.babyfat.common.entities.Ranchu;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RanchuRenderer<T extends Ranchu> extends MobRenderer<T, RanchuModel<T>> {
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[Ranchu.MAX_VARIANTS];

    public RanchuRenderer(EntityRendererProvider.Context context) {
        super(context, new RanchuModel<>(context.bakeLayer(ModModelLayers.RANCHU)), 0.35F);
    }

    @Override
    public ResourceLocation getTextureLocation(Ranchu entity) {
        int variant = entity.getVariant() + 1;
        ResourceLocation loc = new ResourceLocation(BabyFat.MOD_ID, "textures/entity/ranchu_" + variant + ".png");
        return TEXTURES[variant] = loc;
    }

    @Override
    protected void setupRotations(T entity, PoseStack posestack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entity, posestack, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        posestack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entity.isInWater() && entity.isAddedToWorld()) {
            posestack.translate(0.1F, 0.1F, -0.1F);
            posestack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }

}
