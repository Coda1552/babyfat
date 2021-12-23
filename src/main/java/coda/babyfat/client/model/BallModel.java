package coda.babyfat.client.model;

import coda.babyfat.entities.BallEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class BallModel<B extends LivingEntity> extends EntityModel<BallEntity> {
	private final ModelRenderer root;
	private final ModelRenderer ball;

	public BallModel() {
		texWidth = 32;
		texHeight = 32;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 24.0F, 0.0F);
		

		ball = new ModelRenderer(this);
		ball.setPos(3.0F, 0.0F, -3.0F);
		root.addChild(ball);
		ball.texOffs(0, 0).addBox(-6.5F, -7.0F, -0.5F, 7.0F, 7.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(BallEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if (Entity.getHorizontalDistanceSqr(entity.getDeltaMovement()) > 1.0E-7D) {
			ball.xRot = limbSwing * ((float) Math.PI / 180);
			ball.yRot = limbSwing * ((float) Math.PI / 180);
			ball.zRot = limbSwing * ((float) Math.PI / 180);
		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		root.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}