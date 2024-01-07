package codyhuh.babyfat.client;

import codyhuh.babyfat.BabyFat;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModModelLayers {
	public static final ModelLayerLocation RANCHU = new ModelLayerLocation(new ResourceLocation(BabyFat.MOD_ID, "ranchu"), "ranchu");
}
