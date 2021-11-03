package coda.babyfat;

import coda.babyfat.client.ClientEvents;
import coda.babyfat.entities.RanchuEntity;
import coda.babyfat.init.BFBlocks;
import coda.babyfat.init.BFEntities;
import coda.babyfat.init.BFFeatures;
import coda.babyfat.init.BFItems;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Features;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Random;

@Mod(BabyFat.MOD_ID)
public class BabyFat {
    public static final String MOD_ID = "babyfat";

    public BabyFat() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::registerClient);
        bus.addListener(this::registerEntityAttributes);
        bus.addListener(this::registerCommon);
        bus.addListener(this::registerFeatures);
        forgeBus.addListener(this::onRanchuBreed);
        forgeBus.addListener(this::onBiomeLoading);

        BFItems.ITEMS.register(bus);
        BFEntities.ENTITIES.register(bus);
        BFBlocks.BLOCKS.register(bus);
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
         event.put(BFEntities.RANCHU.get(), RanchuEntity.createAttributes().build());
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        EntitySpawnPlacementRegistry.register(BFEntities.RANCHU.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RanchuEntity::checkFishSpawnRules);

        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(BFItems.WATER_LETTUCE.get(), 0.65F);
        });
    }

    private void registerFeatures(FMLCommonSetupEvent event) {
        event.enqueueWork(BFFeatures::registerFeatures);
    }

    private void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.RIVER) {
            event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(BFEntities.RANCHU.get(), 1, 1, 1));
        }
        if (event.getName().getPath().equals("savanna")) {
            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BFFeatures.WATER_LETTUCE);
        }
    }

    private void onRanchuBreed(BabyEntitySpawnEvent event) {
        if (event.getParentA() instanceof RanchuEntity && event.getParentB() instanceof RanchuEntity) {
            RanchuEntity ranchuA = (RanchuEntity) event.getParentA();
            RanchuEntity ranchuB = (RanchuEntity) event.getParentB();
            RanchuEntity child = (RanchuEntity) event.getChild();
            Random rand = ranchuA.getRandom();

            // Feral + Feral
            if (ranchuA.getVariant() <= 2 && ranchuB.getVariant() <= 2) {
                if (rand.nextFloat() < 0.15) {
                    child.setVariant(rand.nextInt(RanchuEntity.MAX_VARIANTS - 3) + 3);
                }
                else {
                    child.setVariant(rand.nextInt(3) + 1);
                }
            }

            // Fancy + Fancy
            else if (ranchuA.getVariant() > 2 && ranchuB.getVariant() > 2) {
                child.setVariant(rand.nextInt(RanchuEntity.MAX_VARIANTS - 3) + 3);
            }

            // Feral + Fancy
            else if (ranchuA.getVariant() <= 2 || ranchuB.getVariant() <= 2 && ranchuA.getVariant() > 2 || ranchuB.getVariant() > 2) {
                if (rand.nextBoolean()) {
                    child.setVariant(rand.nextInt(RanchuEntity.MAX_VARIANTS - 3) + 3);
                }
                else {
                    child.setVariant(rand.nextInt(3) + 1);
                }
            }

            child.copyPosition(ranchuA);
            child.setBaby(true);
            ranchuA.getCommandSenderWorld().addFreshEntity(child);
        }
    }

    private void registerClient(FMLClientSetupEvent event) {
        ClientEvents.init();
    }
}
