package coda.babyfat.client;

import coda.babyfat.BabyFat;
import coda.babyfat.client.model.RanchuModel;
import coda.babyfat.client.renderer.RanchuRenderer;
import coda.babyfat.registry.BFEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BabyFat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

	public static void init() {
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
