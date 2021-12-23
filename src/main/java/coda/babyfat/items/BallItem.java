package coda.babyfat.items;

import coda.babyfat.entities.BallEntity;
import coda.babyfat.init.BFEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class BallItem extends Item {

    public BallItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();

        if (player != null) {
            ItemStack itemstack = player.getItemInHand(context.getHand());

            World world = context.getLevel();
            if (!world.isClientSide) {
                BallEntity entity = new BallEntity(BFEntities.BALL.get(), world);
                if (!player.abilities.instabuild) {
                    itemstack.shrink(1);
                }
                world.addFreshEntity(entity);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            return ActionResultType.SUCCESS;
        }

        return super.useOn(context);
    }
}