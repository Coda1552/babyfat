package coda.babyfat.common.items;

import coda.babyfat.common.entities.Ranchu;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class RanchuBucketItem extends BucketItem {
    private final Supplier<? extends EntityType<?>> fishTypeSupplier;

    public RanchuBucketItem(java.util.function.Supplier<? extends EntityType<?>> fishTypeIn, java.util.function.Supplier<? extends Fluid> fluid, Item.Properties builder) {
        super(fluid, builder);
        this.fishTypeSupplier = fishTypeIn;
    }

    public void checkExtraContent(Level worldIn, ItemStack p_203792_2_, BlockPos pos) {
        if (worldIn instanceof ServerLevel) {
            this.placeFish((ServerLevel)worldIn, p_203792_2_, pos);
        }
    }

    protected void playEmptySound(@Nullable Player player, LevelAccessor worldIn, BlockPos pos) {
        worldIn.playSound(player, pos, SoundEvents.BUCKET_EMPTY_FISH, SoundSource.NEUTRAL, 1.0F, 1.0F);
    }

    private void placeFish(ServerLevel worldIn, ItemStack stack, BlockPos pos) {
        Entity entity = this.fishTypeSupplier.get().spawn(worldIn, stack, (Player)null, pos, MobSpawnType.BUCKET, true, false);
        if (entity != null) {
            ((Ranchu)entity).setFromBucket(true);
        }
    }
}

