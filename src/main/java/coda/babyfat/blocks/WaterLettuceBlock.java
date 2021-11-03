package coda.babyfat.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.PlantType;

public class WaterLettuceBlock extends BushBlock {
   protected static final VoxelShape AABB = Block.box(4.0D, -2.0D, 4.0D, 12.0D, 4.0D, 12.0D);

   public WaterLettuceBlock(AbstractBlock.Properties p_i48297_1_) {
      super(p_i48297_1_);
   }

   public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
      return AABB;
   }

   protected boolean mayPlaceOn(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
      FluidState fluidstate = p_200014_2_.getFluidState(p_200014_3_);
      FluidState fluidstate1 = p_200014_2_.getFluidState(p_200014_3_.above());
      return (fluidstate.getType() == Fluids.WATER || p_200014_1_.getMaterial() == Material.ICE) && fluidstate1.getType() == Fluids.EMPTY;
   }

   @Override
   public PlantType getPlantType(IBlockReader world, BlockPos pos) {
      return PlantType.WATER;
   }
}