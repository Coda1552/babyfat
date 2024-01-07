package codyhuh.babyfat.client.renderer;

import codyhuh.babyfat.BabyFat;
import codyhuh.babyfat.client.ModModelLayers;
import codyhuh.babyfat.client.model.RanchuModel;
import codyhuh.babyfat.common.entities.Ranchu;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RanchuRenderer<T extends Ranchu> extends MobRenderer<T, RanchuModel<T>> {
	private static final ResourceLocation[] TEXTURES = new ResourceLocation[Ranchu.MAX_VARIANTS];
	private static final ResourceLocation DEFAULT_TEXTURES = new ResourceLocation(BabyFat.MOD_ID, "textures/entity/ranchu/ranchu_1.png");

	public RanchuRenderer(EntityRendererProvider.Context context) {
		super(context, new RanchuModel<>(context.bakeLayer(ModModelLayers.RANCHU)), 0.35F);
	}

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		int variant = entity.getVariant() + 1;
		if (TEXTURES[variant - 1] == null) {
			ResourceLocation loc = new ResourceLocation(BabyFat.MOD_ID, "textures/entity/ranchu/ranchu_" + variant + ".png");
			if (!Minecraft.getInstance().getResourceManager().getResource(loc).isPresent()) {
				System.out.println("Found Unknown variant " + variant + ", using default");
				loc = DEFAULT_TEXTURES;
				return loc;
			}

			return TEXTURES[variant - 1] = loc;
		}

		return TEXTURES[variant - 1];
	}

	@Override
	protected void setupRotations(T entity, PoseStack posestack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(entity, posestack, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * Mth.sin(0.6F * ageInTicks);
		posestack.mulPose(Axis.YP.rotationDegrees(f));
		if (!entity.isInWater()) {
			posestack.translate(0.1F, 0.1F, -0.1F);
			posestack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

}
