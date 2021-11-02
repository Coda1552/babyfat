package coda.babyfat.items;

import coda.babyfat.entities.RanchuEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class RanchuBucketItem extends BucketItem {
    private final Supplier<? extends EntityType<?>> fishTypeSupplier;

    public RanchuBucketItem(java.util.function.Supplier<? extends EntityType<?>> fishTypeIn, java.util.function.Supplier<? extends Fluid> fluid, Item.Properties builder) {
        super(fluid, builder);
        this.fishTypeSupplier = fishTypeIn;
    }

    public void checkExtraContent(World worldIn, ItemStack p_203792_2_, BlockPos pos) {
        if (worldIn instanceof ServerWorld) {
            this.placeFish((ServerWorld)worldIn, p_203792_2_, pos);
        }
    }

    protected void playEmptySound(@Nullable PlayerEntity player, IWorld worldIn, BlockPos pos) {
        worldIn.playSound(player, pos, SoundEvents.BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }

    private void placeFish(ServerWorld worldIn, ItemStack stack, BlockPos pos) {
        Entity entity = this.fishTypeSupplier.get().spawn(worldIn, stack, (PlayerEntity)null, pos, SpawnReason.BUCKET, true, false);
        if (entity != null) {
            ((RanchuEntity)entity).setFromBucket(true);
        }
    }
}

