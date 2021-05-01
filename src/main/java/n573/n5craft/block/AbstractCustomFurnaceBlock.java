package n573.n5craft.block;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class AbstractCustomFurnaceBlock extends AbstractFurnaceBlock {

	protected AbstractCustomFurnaceBlock(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
		// TODO Auto-generated method stub
		
	}

}
