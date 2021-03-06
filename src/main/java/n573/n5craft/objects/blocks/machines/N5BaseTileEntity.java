package n573.n5craft.objects.blocks.machines;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import n573.n5craft.N5BlockInterfaces.BlockstateProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class N5BaseTileEntity extends TileEntity implements BlockstateProvider {

	private BlockState overrideBlockState = null;
	
	public N5BaseTileEntity(TileEntityType<? extends TileEntity> type) {
		super(type);
	}

	@Override
	public BlockState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setState(BlockState newState) {
		// TODO Auto-generated method stub
		
	}
	
//	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
//	public static final BooleanProperty BURNING = BooleanProperty.create("burning");
	
	@Override
	public void read(BlockState stateIn, CompoundNBT nbtIn)
	{
		super.read(stateIn, nbtIn);
		this.readCustomNBT(nbtIn, false);
	}

	public abstract void readCustomNBT(CompoundNBT nbt, boolean descPacket);

	@Override
	public CompoundNBT write(CompoundNBT nbt)
	{
		super.write(nbt);
		this.writeCustomNBT(nbt, false);
		return nbt;
	}

	public abstract void writeCustomNBT(CompoundNBT nbt, boolean descPacket);

	@Override
	public SUpdateTileEntityPacket getUpdatePacket()
	{
		CompoundNBT nbttagcompound = new CompoundNBT();
		this.writeCustomNBT(nbttagcompound, true);
		return new SUpdateTileEntityPacket(this.pos, 3, nbttagcompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
	{
		this.readCustomNBT(pkt.getNbtCompound(), true);
	}

	@Override
	public CompoundNBT getUpdateTag()
	{
		CompoundNBT nbt = super.getUpdateTag();
		writeCustomNBT(nbt, true);
		return nbt;
	}
	
	public void receiveMessageFromClient(CompoundNBT message)
	{
	}

	public void receiveMessageFromServer(CompoundNBT message)
	{
	}

	public void onEntityCollision(World world, Entity entity)
	{
	}

	@Override
	public boolean receiveClientEvent(int id, int type)
	{
		if(id==0||id==255)
		{
			markContainingBlockForUpdate(null);
			return true;
		}
		else if(id==254)
		{
			BlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			return true;
		}
		return super.receiveClientEvent(id, type);
	}

	public void markContainingBlockForUpdate(@Nullable BlockState newState)
	{
		if(this.world!=null)
			markBlockForUpdate(getPos(), newState);
	}

	public void markBlockForUpdate(BlockPos pos, @Nullable BlockState newState)
	{
		BlockState state = world.getBlockState(pos);
		if(newState==null)
			newState = state;
		world.notifyBlockUpdate(pos, state, newState, 3);
		world.notifyNeighborsOfStateChange(pos, newState.getBlock());
	}

	private final Set<LazyOptional<?>> caps = new HashSet<>();
	private final Map<Direction, LazyOptional<IEnergyStorage>> energyCaps = new HashMap<>();

	protected <T> LazyOptional<T> registerConstantCap(T val)
	{
		return registerCap(() -> val);
	}

	protected <T> LazyOptional<T> registerCap(NonNullSupplier<T> cap)
	{
		return registerCap(LazyOptional.of(cap));
	}

	protected <T> LazyOptional<T> registerCap(LazyOptional<T> cap)
	{
		caps.add(cap);
		return cap;
	}

	protected <T> void unregisterCap(LazyOptional<T> cap)
	{
		cap.invalidate();
		caps.remove(cap);
	}

//	@Nonnull
//	@Override
//	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
//	{
//		if(cap==CapabilityEnergy.ENERGY&&this instanceof EnergyHelper.IIEInternalFluxConnector)
//		{
//			if(!energyCaps.containsKey(side))
//			{
//				IEForgeEnergyWrapper wrapper = ((EnergyHelper.IIEInternalFluxConnector)this).getCapabilityWrapper(side);
//				if(wrapper!=null)
//					energyCaps.put(side, registerConstantCap(wrapper));
//				else
//					energyCaps.put(side, LazyOptional.empty());
//			}
//			return energyCaps
//					.get(side)
//					.cast();
//		}
//		return super.getCapability(cap, side);
//	}

//	@Override
//	public double getMaxRenderDistanceSquared()
//	{
//		double increase = IEClientConfig.increasedTileRenderdistance.get();
//		return super.getMaxRenderDistanceSquared()*
//				increase*increase;
//	}

	@Override
	public void remove()
	{
		super.remove();
		for(LazyOptional<?> cap : caps)
			if(cap.isPresent())
				cap.invalidate();
		caps.clear();
	}

	@Nonnull
	public World getWorldNonnull()
	{
		return Objects.requireNonNull(super.getWorld());
	}

	protected void checkLight()
	{
		checkLight(pos);
	}

	protected void checkLight(BlockPos pos)
	{
		getWorldNonnull().getPendingBlockTicks().scheduleTick(pos, getBlockState().getBlock(), 4);
	}

	public void setOverrideState(BlockState state)
	{
		overrideBlockState = state;
	}

	@Override
	public BlockState getBlockState()
	{
		if(overrideBlockState!=null)
			return overrideBlockState;
		else
			return super.getBlockState();
	}

	@Override
	public void updateContainingBlockInfo()
	{
		BlockState old = getBlockState();
		super.updateContainingBlockInfo();
		BlockState newState = getBlockState();
		if(old!=null&&
				newState!=null&&
				getType().isValidBlock(old.getBlock())&&
				!getType().isValidBlock(newState.getBlock()))
			setOverrideState(old);
	}

//	@Nonnull
//	@Override
//	public IModelData getModelData()
//	{
//		IModelData base = super.getModelData();
//		if(this instanceof IPropertyPassthrough)
//			return CombinedModelData.combine(
//					base, new SinglePropertyModelData<>(this, Model.TILEENTITY_PASSTHROUGH)
//			);
//		else
//			return base;
//	}

	@Override
	public void setWorldAndPos(World world, BlockPos pos)
	{
		super.setWorldAndPos(world, pos);
	}

	protected void onNeighborBlockChange(BlockPos otherPos)
	{
		BlockPos delta = otherPos.subtract(pos);
		Direction side = Direction.getFacingFromVector(delta.getX(), delta.getY(), delta.getZ());
		Preconditions.checkNotNull(side);
	}

	

}
