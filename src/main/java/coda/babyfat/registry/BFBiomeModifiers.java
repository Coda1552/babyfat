package coda.babyfat.registry;

import coda.babyfat.BabyFat;
import coda.babyfat.level.biome.BFBiomeModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BFBiomeModifiers {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, BabyFat.MOD_ID);

	public static final RegistryObject<Codec<BFBiomeModifier>> BF_BIOME_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("bf_biome_modifier", () -> Codec.unit(BFBiomeModifier.INSTANCE));

}
