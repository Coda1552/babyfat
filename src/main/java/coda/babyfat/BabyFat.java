package coda.babyfat;

import coda.babyfat.client.ClientEvents;
import coda.babyfat.common.entities.Ranchu;
import coda.babyfat.registry.BFBiomeModifiers;
import coda.babyfat.registry.BFBlocks;
import coda.babyfat.registry.BFEntities;
import coda.babyfat.registry.BFFeatures;
import coda.babyfat.registry.BFItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

@Mod(BabyFat.MOD_ID)
public class BabyFat {
	public static final String MOD_ID = "babyfat";
	public static final List<Runnable> CALLBACKS = new ArrayList<>();


	public BabyFat() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		bus.addListener(this::registerClient);
		bus.addListener(this::registerEntityAttributes);
		bus.addListener(this::registerCommon);
		bus.addListener(this::registerFeatures);
		forgeBus.addListener(this::onRanchuBreed);

		BFBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(bus);
		BFItems.ITEMS.register(bus);
		BFEntities.ENTITIES.register(bus);
		BFBlocks.BLOCKS.register(bus);
	}

	private void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(BFEntities.RANCHU.get(), Ranchu.createAttributes().build());
	}

	private void registerCommon(FMLCommonSetupEvent event) {
		SpawnPlacements.register(BFEntities.RANCHU.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Ranchu::checkFishSpawnRules);


		event.enqueueWork(() -> {
			ComposterBlock.COMPOSTABLES.put(BFItems.WATER_LETTUCE.get(), 0.65F);
		});
	}

	private void registerFeatures(FMLCommonSetupEvent event) {
		event.enqueueWork(BFFeatures::registerFeatures);
	}

	private void onRanchuBreed(BabyEntitySpawnEvent event) {
		if (event.getParentA() instanceof Ranchu && event.getParentB() instanceof Ranchu) {
			Ranchu ranchuA = (Ranchu) event.getParentA();
			Ranchu ranchuB = (Ranchu) event.getParentB();
			Ranchu child = (Ranchu) event.getChild();
			RandomSource rand = ranchuA.getRandom();

			// Feral + Feral
			if (ranchuA.getVariant() <= 2 && ranchuB.getVariant() <= 2) {
				if (rand.nextFloat() < 0.15) {
					child.setVariant(rand.nextInt(Ranchu.MAX_VARIANTS - 3) + 3);
				} else {
					child.setVariant(rand.nextInt(3) + 1);
				}
			}

			// Fancy + Fancy
			else if (ranchuA.getVariant() > 2 && ranchuB.getVariant() > 2) {
				child.setVariant(rand.nextInt(Ranchu.MAX_VARIANTS - 3) + 3);
			}

			// Feral + Fancy
			else if (ranchuA.getVariant() <= 2 || ranchuB.getVariant() <= 2 && ranchuA.getVariant() > 2 || ranchuB.getVariant() > 2) {
				if (rand.nextBoolean()) {
					child.setVariant(rand.nextInt(Ranchu.MAX_VARIANTS - 3) + 3);
				} else {
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

	public static final CreativeModeTab BABY_FAT = new CreativeModeTab(MOD_ID) {
		@Override
		public ItemStack makeIcon() {
			return BFItems.RANCHU.get().getDefaultInstance();
		}
	};


}
