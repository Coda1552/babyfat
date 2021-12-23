package coda.babyfat.init;

import coda.babyfat.BabyFat;
import coda.babyfat.entities.BallEntity;
import coda.babyfat.entities.RanchuEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BFEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, BabyFat.MOD_ID);

    public static final RegistryObject<EntityType<RanchuEntity>> RANCHU = create("ranchu", EntityType.Builder.of(RanchuEntity::new, EntityClassification.WATER_CREATURE).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<BallEntity>> BALL = create("ball", EntityType.Builder.of(BallEntity::new, EntityClassification.MISC).sized(0.45F, 0.45F));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(BabyFat.MOD_ID + "." + name));
    }
}
