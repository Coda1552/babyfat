package codyhuh.babyfat.client;

import codyhuh.babyfat.BabyFat;
import codyhuh.babyfat.client.model.RanchuModel;
import codyhuh.babyfat.client.renderer.RanchuRenderer;
import codyhuh.babyfat.registry.BFBlocks;
import codyhuh.babyfat.registry.BFEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BabyFat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.RANCHU, RanchuModel::createBodyLayer);
	}


	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BFEntities.RANCHU.get(), RanchuRenderer::new);
	}
}
