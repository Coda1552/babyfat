package codyhuh.babyfat.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import java.util.Collections;

public class RanchuModel<T extends Entity> extends AgeableListModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart tailBase;
	private final ModelPart tailFins;
	private final ModelPart rightPectoralFin;
	private final ModelPart leftPectoralFin;
	private final ModelPart rightPelvicFin;
	private final ModelPart leftPelvicFin;
	private final ModelPart rightTailFin;
	private final ModelPart leftTailFin;

	public RanchuModel(ModelPart base) {
		this.root = base.getChild("root");
		this.head = root.getChild("head");
		this.tailBase = root.getChild("tailBase");
		this.tailFins = tailBase.getChild("tailFins");
		this.rightPectoralFin = root.getChild("rightPectoralFin");
		this.leftPectoralFin = root.getChild("leftPectoralFin");
		this.rightPelvicFin = root.getChild("rightPelvicFin");
		this.leftPelvicFin = root.getChild("leftPelvicFin");
		this.rightTailFin = tailFins.getChild("rightTailFin");
		this.leftTailFin = tailFins.getChild("leftTailFin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 21.0F, -0.5F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-2.5F, -3.25F, -2.75F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 18).addBox(-2.0F, 0.75F, -1.75F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, -2.75F));

		PartDefinition rightPectoralFin = root.addOrReplaceChild("rightPectoralFin", CubeListBuilder.create().texOffs(13, 12).addBox(-2.0F, 0.0F, -1.5F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 1.0F, -1.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leftPectoralFin = root.addOrReplaceChild("leftPectoralFin", CubeListBuilder.create().texOffs(13, 12).mirror().addBox(0.0F, 0.0F, -1.5F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 1.0F, -1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition leftPelvicFin = root.addOrReplaceChild("leftPelvicFin", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, 3.5F, -1.0F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition cube_r1 = leftPelvicFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 13).mirror().addBox(-1.0F, 0.0F, -1.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.0F, 0.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition rightPelvicFin = root.addOrReplaceChild("rightPelvicFin", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, 3.5F, -1.0F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cube_r2 = rightPelvicFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 13).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition tailBase = root.addOrReplaceChild("tailBase", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 2.5F, -0.1309F, 0.0F, 0.0F));

		PartDefinition tailFins = tailBase.addOrReplaceChild("tailFins", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition rightTailFin = tailFins.addOrReplaceChild("rightTailFin", CubeListBuilder.create(), PartPose.offset(-0.4681F, -0.5F, -0.0048F));

		PartDefinition cube_r3 = rightTailFin.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(2, 12).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2181F, 0.0F, 0.0048F, 0.0F, -0.0436F, 0.0F));

		PartDefinition leftTailFin = tailFins.addOrReplaceChild("leftTailFin", CubeListBuilder.create(), PartPose.offset(0.4681F, -0.5F, -0.0048F));

		PartDefinition cube_r4 = leftTailFin.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(2, 12).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2181F, 0.0F, 0.0048F, 0.0F, 0.0436F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 1.5f;
		float degree = 0.8f;
		if (entity.isInWater()) {
			this.root.xRot = headPitch * ((float) Math.PI / 180F);
			this.root.yRot = netHeadYaw * ((float) Math.PI / 180F);
			this.root.yRot += Mth.cos(limbSwing * speed * 0.5F) * degree * 0.5F * limbSwingAmount;
			this.rightPectoralFin.zRot = Mth.cos(-2.0F + limbSwing * speed * 0.5F) * degree * -1.5F * limbSwingAmount - 1.0F;
			this.leftPectoralFin.zRot = Mth.cos(-2.0F + limbSwing * speed * 0.5F) * degree * 1.5F * limbSwingAmount + 1.0F;
			this.tailBase.yRot = Mth.cos(-1.0F + limbSwing * speed * 0.5F) * degree * limbSwingAmount;
			this.rightTailFin.yRot = Mth.cos(-1.5F + limbSwing * speed * 0.5F) * degree * limbSwingAmount;;
			this.leftTailFin.yRot = Mth.cos(-1.5F + limbSwing * speed * 0.5F) * degree * limbSwingAmount;;
		}
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return Collections.emptyList();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.root);
	}

}