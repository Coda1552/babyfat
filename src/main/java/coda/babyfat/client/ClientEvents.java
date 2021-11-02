package coda.babyfat.client;

import coda.babyfat.client.renderer.RanchuRenderer;
import coda.babyfat.init.BFEntities;
import coda.babyfat.items.BFSpawnEggItem;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientEvents {

    public static void init() {
         RenderingRegistry.registerEntityRenderingHandler(BFEntities.RANCHU.get(), RanchuRenderer::new);
    }

    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
         ItemColors handler = event.getItemColors();
         IItemColor eggColor = (stack, tintIndex) -> ((BFSpawnEggItem) stack.getItem()).getColor(tintIndex);
         for (BFSpawnEggItem e : BFSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}
