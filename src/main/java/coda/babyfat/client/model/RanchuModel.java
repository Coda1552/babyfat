package coda.babyfat.client.model;

import coda.babyfat.entities.RanchuEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class RanchuModel<T extends Entity> extends AgeableModel<RanchuEntity> {
    public ModelRenderer body;
    public ModelRenderer tailbase;
    public ModelRenderer headtop;
    public ModelRenderer pectoralfinright;
    public ModelRenderer pectoralfinleft;
    public ModelRenderer tailfinright;
    public ModelRenderer tailfinleft;
    public ModelRenderer headbottom;

    public RanchuModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.pectoralfinright = new ModelRenderer(this, 13, 12);
        this.pectoralfinright.setPos(-2.5F, 3.0F, -2.0F);
        this.pectoralfinright.addBox(-1.5F, 0.0F, -0.5F, 2.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(pectoralfinright, 0.0F, 0.0F, -1.3962634015954636F);
        this.headbottom = new ModelRenderer(this, 0, 18);
        this.headbottom.setPos(0.0F, 2.5F, 0.0F);
        this.headbottom.addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.tailfinright = new ModelRenderer(this, 23, 22);
        this.tailfinright.mirror = true;
        this.tailfinright.setPos(-0.5F, 1.0F, 1.0F);
        this.tailfinright.addBox(0.0F, 0.0F, -3.0F, 9.0F, 0.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tailfinright, 0.0F, -1.5707963267948966F, 1.5707963267948966F);
        this.pectoralfinleft = new ModelRenderer(this, 17, 12);
        this.pectoralfinleft.setPos(2.5F, 3.0F, -2.0F);
        this.pectoralfinleft.addBox(-0.5F, 0.0F, -0.5F, 2.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(pectoralfinleft, 0.0F, 0.0F, 1.3962634015954636F);
        this.tailbase = new ModelRenderer(this, 20, 0);
        this.tailbase.setPos(0.0F, -1.5F, 3.0F);
        this.tailbase.addBox(-2.0F, -1.0F, -1.5F, 4.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.headtop = new ModelRenderer(this, 0, 11);
        this.headtop.setPos(0.0F, -1.0F, -2.5F);
        this.headtop.addBox(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.tailfinleft = new ModelRenderer(this, 4, 22);
        this.tailfinleft.setPos(0.5F, 1.0F, 1.0F);
        this.tailfinleft.addBox(0.0F, 0.0F, -3.0F, 9.0F, 0.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tailfinleft, 0.0F, -1.5707963267948966F, 1.5707963267948966F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 21.0F, 0.0F);
        this.body.addBox(-2.5F, -3.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.pectoralfinright);
        this.headtop.addChild(this.headbottom);
        this.tailbase.addChild(this.tailfinright);
        this.body.addChild(this.pectoralfinleft);
        this.body.addChild(this.tailbase);
        this.body.addChild(this.headtop);
        this.tailbase.addChild(this.tailfinleft);
    }

    @Override
    public void setupAnim(RanchuEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.5f;
        float degree = 0.8f;
        if (entityIn.isInWater()) {
            this.body.xRot = headPitch * ((float)Math.PI / 180F);
            this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
            this.tailbase.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.5F) * degree * 1.5F * limbSwingAmount;
            this.body.yRot += MathHelper.cos(limbSwing * speed * 0.5F) * degree * 0.5F * limbSwingAmount;
            this.tailfinright.xRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.5F) * degree * 1.5F * limbSwingAmount;
            this.tailfinleft.xRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.5F) * degree * 1.5F * limbSwingAmount;
            this.pectoralfinright.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.5F) * degree * 2.0F * limbSwingAmount;
            this.pectoralfinleft.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.5F) * degree * -2.0F * limbSwingAmount;
        }
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return Collections.emptyList();
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(body);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
