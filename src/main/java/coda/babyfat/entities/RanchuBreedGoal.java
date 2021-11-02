package coda.babyfat.entities;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class RanchuBreedGoal extends Goal {
    private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();
    protected final RanchuEntity animal;
    private final Class<? extends RanchuEntity> mateClass;
    protected final World world;
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
        List<RanchuEntity> list = animal.level.getNearbyEntities(this.mateClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(8.0D));

        assert !this.animal.isBaby();

        if (!this.animal.isInLove() || this.animal.isBaby()) {
            return false;
        }
        else if (list.size() < 4) {
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
        List<RanchuEntity> list = this.world.getNearbyEntities(this.mateClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(20.0D));
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
        this.animal.spawnChildFromBreeding((ServerWorld)this.world, this.targetMate);
    }
}