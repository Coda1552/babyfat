package coda.babyfat.common.items;

import coda.babyfat.BabyFat;
import coda.babyfat.common.entities.Ranchu;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class RanchuBucketItem extends MobBucketItem {

	public RanchuBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Item item,
							boolean hasTooltip, Item.Properties builder) {
		super(entityType, fluid, () -> SoundEvents.BUCKET_EMPTY_FISH, builder);
		DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> BabyFat.CALLBACKS.add(() ->
				ItemProperties.register(this, new ResourceLocation(BabyFat.MOD_ID, "variant"),
						(stack, world, player, i) -> stack.hasTag() ? stack.getTag().getInt("Variant") : 0)));
		ItemProperties.register(this, new ResourceLocation(BabyFat.MOD_ID, "age"),
				(stack, world, player, i) -> stack.hasTag() ? stack.getTag().getInt("Age") : 1);
	}

}

