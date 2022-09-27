package coda.babyfat.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

public class WaterLettuceBlock extends BushBlock {
	protected static final VoxelShape AABB = Block.box(4.0D, -2.0D, 4.0D, 12.0D, 4.0D, 12.0D);

	public WaterLettuceBlock(BlockBehaviour.Properties p_i48297_1_) {
		super(p_i48297_1_);
	}

	public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
		Vec3 vec3 = p_220053_1_.getOffset(p_220053_2_, p_220053_3_);
		return AABB.move(vec3.x, vec3.y, vec3.z);
	}

	protected boolean mayPlaceOn(BlockState p_200014_1_, BlockGetter p_200014_2_, BlockPos p_200014_3_) {
		FluidState fluidstate = p_200014_2_.getFluidState(p_200014_3_);
		FluidState fluidstate1 = p_200014_2_.getFluidState(p_200014_3_.above());
		return (fluidstate.getType() == Fluids.WATER || p_200014_1_.getMaterial() == Material.ICE) && fluidstate1.getType() == Fluids.EMPTY;
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.WATER;
	}

	public BlockBehaviour.OffsetType getOffsetType() {
		return BlockBehaviour.OffsetType.XZ;
	}
}