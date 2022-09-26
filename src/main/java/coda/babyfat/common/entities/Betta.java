package coda.babyfat.common.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class Betta extends AbstractSchoolingFish {
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Ranchu.class, EntityDataSerializers.INT);

	public Betta(EntityType<? extends Betta> p_27461_, Level p_27462_) {
		super(p_27461_, p_27462_);
	}

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", getVariant());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		setVariant(compound.getInt("Variant"));
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {

		if(random.nextBoolean()) {
			setVariant(0);
		}else {
			setVariant(100);
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

	}

	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.COD_FLOP;
	}

	@Override
	public ItemStack getBucketItemStack() {
		return null;
	}
}
