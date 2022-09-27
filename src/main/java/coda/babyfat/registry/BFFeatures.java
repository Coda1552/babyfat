package coda.babyfat.registry;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class BFFeatures {
	public static ConfiguredFeature<?, ?> WATER_LETTUCE;

	public static void registerFeatures() {
		//WATER_LETTUCE = Registry.register(WorldGen.CONFIGURED_FEATURE, "water_lettuce", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BFBlocks.WATER_LETTUCE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(4));
	}
}