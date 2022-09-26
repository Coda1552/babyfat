package coda.babyfat.client.model;// Made with Blockbench 4.4.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BettaModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart betta;
	private final ModelPart backfin;

	public BettaModel(ModelPart root) {
		this.betta = root.getChild("betta");
		this.backfin = this.betta.getChild("backfin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition betta = partdefinition.addOrReplaceChild("betta", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

		PartDefinition body = betta.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.0F, -4.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(0.0F, -6.0F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, -5).addBox(0.0F, 0.0F, -3.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition finleft = body.addOrReplaceChild("finleft", CubeListBuilder.create().texOffs(11, 9).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, -1.0F));

		PartDefinition finright = body.addOrReplaceChild("finright", CubeListBuilder.create().texOffs(11, 9).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, -1.0F));

		PartDefinition backfin = body.addOrReplaceChild("backfin", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = backfin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, -4.5F, -2.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, 0.0F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		if (!entity.isInWater()) {
			f = 1.5F;
		}
		this.backfin.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);
	}
	@Override
	public ModelPart root() {
		return this.betta;
	}
}