package coda.babyfat.init;

import coda.babyfat.BabyFat;
import coda.babyfat.items.BFSpawnEggItem;
import coda.babyfat.items.RanchuBucketItem;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BFItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BabyFat.MOD_ID);

    public static final RegistryObject<Item> RANCHU = ITEMS.register("ranchu", () -> new Item(new Item.Properties().food(new Food.Builder().nutrition(1).saturationMod(0.2F).build()).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> RANCHU_BUCKET = ITEMS.register("ranchu_bucket", () -> new RanchuBucketItem(BFEntities.RANCHU::get, () -> Fluids.WATER, new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1)));
    public static final RegistryObject<Item> RANCHU_SPAWN_EGG = ITEMS.register("ranchu_spawn_egg", () -> new BFSpawnEggItem(BFEntities.RANCHU, 0x736036, 0xd1a965, new Item.Properties().tab(ItemGroup.TAB_MISC)));
}
