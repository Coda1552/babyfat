package codyhuh.babyfat.common.items;

import codyhuh.babyfat.BabyFat;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import java.util.function.Supplier;

public class RanchuBucketItem extends MobBucketItem {

	public RanchuBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Item.Properties builder) {
		super(entityType, fluid, () -> SoundEvents.BUCKET_EMPTY_FISH, builder);
		DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> BabyFat.CALLBACKS.add(() -> ItemProperties.register(this, new ResourceLocation(BabyFat.MOD_ID, "variant"), (stack, world, player, i) -> stack.hasTag() ? stack.getTag().getInt("Variant") : 0)));
	}

}

