package codyhuh.babyfat;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = BabyFat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BFConfig {
    public static boolean naturalBreeding;

    @SubscribeEvent
    public static void configLoad(ModConfigEvent.Reloading event) {
        try {
            IConfigSpec spec = event.getConfig().getSpec();
            if (spec == Common.SPEC) Common.reload();
        }
        catch (Throwable e) {
            BabyFat.LOGGER.error("Something went wrong updating the Baby Fat config, using previous or default values! {}", e.toString());
        }
    }

    public static class Common {
        public static final Common INSTANCE;
        public static final ForgeConfigSpec SPEC;

        static {
            Pair<Common, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Common::new);
            INSTANCE = pair.getLeft();
            SPEC = pair.getRight();
        }

        public final ForgeConfigSpec.BooleanValue naturalBreeding;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("General");
            naturalBreeding = builder.comment("Should Ranchus breed naturally when conditions are met?").define("natural_ranchu_breeding", true);
            builder.pop();
        }

        public static void reload() {
            BFConfig.naturalBreeding = INSTANCE.naturalBreeding.get();
        }
    }
}