package coda.babyfat.common.entities.goal;

import coda.babyfat.common.entities.RanchuEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class RanchuBreedGoal extends Goal {
    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreLineOfSight();
    protected final RanchuEntity animal;
    private final Class<? extends RanchuEntity> mateClass;
    protected final Level world;
    protected RanchuEntity targetMate;
    private int spawnBabyDelay;
    private final double moveSpeed;

    public RanchuBreedGoal(RanchuEntity animal, double speedIn) {
        this(animal, speedIn, animal.getClass());
    }

    public RanchuBreedGoal(RanchuEntity animal, double moveSpeed, Class<? extends RanchuEntity> mateClass) {
        this.animal = animal;
        this.world = animal.level;
        this.mateClass = mateClass;
        this.moveSpeed = moveSpeed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        List<? extends RanchuEntity> list = animal.level.getNearbyEntities(this.mateClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(8.0D));

        assert !this.animal.isBaby();

        if (!this.animal.isInLove() || this.animal.isBaby()) {
            return false;
        }
        else if (list.size() <= 6) {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null;
        }
        else {
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
        this.animal.getLookControl().setLookAt(this.targetMate, 10.0F, (float)this.animal.getMaxHeadXRot());
        this.animal.getNavigation().moveTo(this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;
        if (this.spawnBabyDelay >= 60 && this.animal.distanceToSqr(this.targetMate) < 9.0D) {
            this.spawnBaby();
        }

    }

    @Nullable
    private RanchuEntity getNearbyMate() {
        List<? extends RanchuEntity> list = this.world.getNearbyEntities(this.mateClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(20.0D));
        double d0 = Double.MAX_VALUE;
        RanchuEntity animalentity = null;

        for (RanchuEntity ranchu : list) {
            if (this.animal.canMate(ranchu) && this.animal.distanceToSqr(ranchu) < d0 && !ranchu.isBaby()) {
                animalentity = ranchu;
                d0 = this.animal.distanceToSqr(ranchu);
            }
        }

        return animalentity;
    }

    protected void spawnBaby() {
        this.animal.spawnChildFromBreeding((ServerLevel) this.world, this.targetMate);
    }
}