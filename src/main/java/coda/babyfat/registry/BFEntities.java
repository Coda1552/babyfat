package coda.babyfat.registry;

import coda.babyfat.BabyFat;
import coda.babyfat.common.entities.Ranchu;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BFEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BabyFat.MOD_ID);

	public static final RegistryObject<EntityType<Ranchu>> RANCHU = create("ranchu", EntityType.Builder.of(Ranchu::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.5F));

	private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
		return ENTITIES.register(name, () -> builder.build(BabyFat.MOD_ID + "." + name));
	}
}
