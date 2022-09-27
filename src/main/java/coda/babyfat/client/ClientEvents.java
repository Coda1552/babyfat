package coda.babyfat.client;

import coda.babyfat.client.model.RanchuModel;
import coda.babyfat.client.renderer.RanchuRenderer;
import coda.babyfat.registry.BFEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
