package coda.babyfat.registry;

import coda.babyfat.BabyFat;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.worldSurfaceSquaredWithCount;

public class BFFeatures {
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WATER_LETTUCE = FeatureUtils.register(BabyFat.MOD_ID + ":water_lettuce", Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BFBlocks.WATER_LETTUCE.get())))));
	public static final Holder<PlacedFeature> PATCH_WATER_LETTUCE = PlacementUtils.register(BabyFat.MOD_ID + ":patch_water_lettuce", WATER_LETTUCE, worldSurfaceSquaredWithCount(4));


	public static void registerFeatures() {
		//
	}
}