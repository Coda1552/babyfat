package coda.babyfat.init;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

public class BFFeatures {
    public static ConfiguredFeature<?, ?> WATER_LETTUCE;

    public static void registerFeatures() {
         WATER_LETTUCE = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "water_lettuce", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BFBlocks.WATER_LETTUCE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(4));
    }
}