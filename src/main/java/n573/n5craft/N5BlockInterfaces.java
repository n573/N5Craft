package n573.n5craft;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootContext.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

public class N5BlockInterfaces {
	public interface BlockstateProvider
	{
		BlockState getState();

		void setState(BlockState newState);
	}
	public interface ITileDrop extends IReadOnPlacement
	{
		List<ItemStack> getTileDrops(LootContext context);

		default ItemStack getPickBlock(@Nullable PlayerEntity player, BlockState state, RayTraceResult rayRes)
		{
			//TODO make this work properly on the client side
			TileEntity tile = (TileEntity)this;
			if(tile.getWorld().isRemote)
				return new ItemStack(state.getBlock());
			ServerWorld world = (ServerWorld)tile.getWorld();
			return getTileDrops(
					new Builder(world)
							.withNullableParameter(LootParameters.TOOL, ItemStack.EMPTY)
							.withNullableParameter(LootParameters.BLOCK_STATE, world.getBlockState(tile.getPos()))
							.withNullableParameter(LootParameters.field_237457_g_, Vector3d.copyCentered(tile.getPos()))
							.build(LootParameterSets.BLOCK)
			).get(0);
		}
	}
	
	public interface IReadOnPlacement
	{
		void readOnPlacement(@Nullable LivingEntity placer, ItemStack stack);
	}
}
