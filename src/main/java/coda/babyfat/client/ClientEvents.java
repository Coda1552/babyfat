package coda.babyfat.client;

import coda.babyfat.BabyFat;
import coda.babyfat.client.model.RanchuModel;
import coda.babyfat.client.renderer.RanchuRenderer;
import coda.babyfat.registry.BFBlocks;
import coda.babyfat.registry.BFEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BabyFat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

	public static void init() {
		Minecraft.getInstance().getBlockColors().register((p_92621_, p_92622_, p_92623_, p_92624_) -> {
			return p_92622_ != null && p_92623_ != null ? BiomeColors.getAverageGrassColor(p_92622_, p_92623_) : GrassColor.get(0.5D, 1.0D);
		}, BFBlocks.WATER_LETTUCE.get());
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.RANCHU, RanchuModel::createBodyLayer);
	}


	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BFEntities.RANCHU.get(), RanchuRenderer::new);
	}
}
