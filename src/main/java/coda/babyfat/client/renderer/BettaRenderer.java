package coda.babyfat.client.renderer;

import coda.babyfat.BabyFat;
import coda.babyfat.client.ModModelLayers;
import coda.babyfat.client.model.BettaModel;
import coda.babyfat.common.entities.Betta;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BettaRenderer extends MobRenderer<Betta, BettaModel<Betta>> {
	public BettaRenderer(EntityRendererProvider.Context p_173954_) {
		super(p_173954_, new BettaModel<>(p_173954_.bakeLayer(ModModelLayers.BETTA)), 0.3F);
	}

	@Override
	public ResourceLocation getTextureLocation(Betta entity) {
		int variant = entity.getVariant() + 1;
		ResourceLocation loc = new ResourceLocation(BabyFat.MOD_ID, "textures/entity/betta_" + variant + ".png");
		return loc;
	}
	protected void setupRotations(Betta p_114017_, PoseStack p_114018_, float p_114019_, float p_114020_, float p_114021_) {
		super.setupRotations(p_114017_, p_114018_, p_114019_, p_114020_, p_114021_);
		float f = 4.3F * Mth.sin(0.6F * p_114019_);
		p_114018_.mulPose(Vector3f.YP.rotationDegrees(f));
		if (!p_114017_.isInWater()) {
			p_114018_.translate((double)0.1F, (double)0.1F, (double)-0.1F);
			p_114018_.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}

	}
}