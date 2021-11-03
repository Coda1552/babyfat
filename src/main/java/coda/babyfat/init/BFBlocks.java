package coda.babyfat.init;

import coda.babyfat.BabyFat;
import coda.babyfat.blocks.WaterLettuceBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BFBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BabyFat.MOD_ID);

    public static final RegistryObject<Block> WATER_LETTUCE = BLOCKS.register("water_lettuce", () -> new WaterLettuceBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.LILY_PAD).noOcclusion()));
}
