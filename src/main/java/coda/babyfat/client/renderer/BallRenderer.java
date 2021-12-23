package coda.babyfat.client.renderer;

import coda.babyfat.BabyFat;
import coda.babyfat.client.model.BallModel;
import coda.babyfat.entities.BallEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BallRenderer extends LivingRenderer<BallEntity, BallModel<BallEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(BabyFat.MOD_ID, "textures/entity/ball");

    public BallRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BallModel<>(), 0.35F);
    }

    @Override
    public ResourceLocation getTextureLocation(BallEntity p_110775_1_) {
        return TEXTURE;
    }
}
