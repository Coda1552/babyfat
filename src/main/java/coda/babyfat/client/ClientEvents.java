package coda.babyfat.client;

import coda.babyfat.BabyFat;
import coda.babyfat.client.model.RanchuModel;
import coda.babyfat.client.renderer.RanchuRenderer;
import coda.babyfat.registry.BFBlocks;
import coda.babyfat.registry.BFEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BabyFat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

	public static void init() {
		setRenderLayer(BFBlocks.WATER_LETTUCE.get(), RenderType.cutout());
	}

	private static void setRenderLayer(Block block, RenderType type) {
		ItemBlockRenderTypes.setRenderLayer(block, type::equals);
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
