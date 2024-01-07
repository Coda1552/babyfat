package codyhuh.babyfat.registry;

import codyhuh.babyfat.BabyFat;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BFTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BabyFat.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BF_TAB = TABS.register("babyfat_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + BabyFat.MOD_ID))
                    .icon(BFItems.RANCHU.get()::getDefaultInstance)
                    .displayItems((displayParams, output) -> {
                        for (var item : BFItems.ITEMS.getEntries()) {
                            output.accept(item.get());
                        }
                    })
                    .build()
    );
}