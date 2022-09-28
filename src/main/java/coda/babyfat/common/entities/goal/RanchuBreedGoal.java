package coda.babyfat.common.entities.goal;

import coda.babyfat.common.entities.Ranchu;
import coda.babyfat.registry.BFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class RanchuBreedGoal extends Goal {
	private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreLineOfSight();
	protected final Ranchu animal;
	private final Class<? extends Ranchu> mateClass;
	protected final Level world;
	protected Ranchu targetMate;
	private int spawnBabyDelay;
	private final double moveSpeed;

	public RanchuBreedGoal(Ranchu animal, double speedIn) {
		this(animal, speedIn, animal.getClass());
	}

	public RanchuBreedGoal(Ranchu animal, double moveSpeed, Class<? extends Ranchu> mateClass) {
		this.animal = animal;
		this.world = animal.level;
		this.mateClass = mateClass;
		this.moveSpeed = moveSpeed;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	public boolean canUse() {
		List<? extends Ranchu> list = animal.level.getNearbyEntities(this.mateClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(8.0D));

		assert !this.animal.isBaby();

		if (!this.animal.isInLove() || this.animal.isBaby()) {
			return false;
		} else if (list.size() <= 6 && getNearbyWaterLettuce() != null) {
			this.targetMate = this.getNearbyMate();
			return this.targetMate != null;
		} else {
			return false;
		}
	}

	public boolean canContinueToUse() {
		return this.targetMate.isAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
	}

	public void stop() {
		this.targetMate = null;
		this.spawnBabyDelay = 0;
	}

	public void tick() {
		this.animal.getLookControl().setLookAt(this.targetMate, 10.0F, (float) this.animal.getMaxHeadXRot());
		this.animal.getNavigation().moveTo(this.targetMate, this.moveSpeed);
		++this.spawnBabyDelay;
		if (this.spawnBabyDelay >= 60 && this.animal.distanceToSqr(this.targetMate) < 9.0D) {
			this.spawnBaby();
		}

	}

	@Nullable
	private Ranchu getNearbyMate() {
		List<? extends Ranchu> list = this.world.getNearbyEntities(this.mateClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(20.0D));
		double d0 = Double.MAX_VALUE;
		Ranchu animalentity = null;

		for (Ranchu ranchu : list) {
			if (this.animal.canMate(ranchu) && this.animal.distanceToSqr(ranchu) < d0 && !ranchu.isBaby()) {
				animalentity = ranchu;
				d0 = this.animal.distanceToSqr(ranchu);
			}
		}

		return animalentity;
	}

	@Nullable
	private BlockPos getNearbyWaterLettuce() {

		BlockPos blockPos = null;
		if(this.animal.tickCount % 10 == 0) {
			for (BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(this.animal.getX() - 10.0D), Mth.floor(this.animal.getY() - 10.0D), Mth.floor(this.animal.getZ() - 10.0D), Mth.floor(this.animal.getX() + 10.0D), Mth.floor(this.animal.getY() + 10.0D), Mth.floor(this.animal.getZ() + 10.0D))) {
				if (this.animal.level.getBlockState(blockpos1).is(BFBlocks.WATER_LETTUCE.get())) {
					blockPos = blockpos1;
					break;
				}
			}
		}

		return blockPos;
	}

	protected void spawnBaby() {
		this.animal.spawnChildFromBreeding((ServerLevel) this.world, this.targetMate);
	}
}