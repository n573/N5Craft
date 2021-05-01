package n573.n5craft.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage {

	public CustomEnergyStorage(int capacity)
    {
        super(capacity, capacity, capacity, 0);
    }

    public CustomEnergyStorage(int capacity, int maxTransfer)
    {
        super(capacity, maxTransfer, maxTransfer, 0);
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract)
    {
        super(capacity, maxReceive, maxExtract, 0);
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy)
    {
    	super(capacity, maxReceive, maxExtract, energy);
    }
	
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        return super.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        return super.extractEnergy(maxExtract, simulate);
    }
    
    @Override
    public int getEnergyStored()
    {
        return super.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored()
    {
        return super.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract()
    {
        return super.canExtract();
    }

    @Override
    public boolean canReceive()
    {
        return super.canReceive();
    }
    
    //Custom part
    
    public void readFromNBT(CompoundNBT nbt) {
    	this.energy = nbt.getInt("Energy");
    	this.capacity = nbt.getInt("Capacity");
    	this.maxReceive = nbt.getInt("MaxRecieve");
    	this.maxExtract = nbt.getInt("MaxExtract");
    }
    
    public void writeToNBT(CompoundNBT nbt) {
    	nbt.putInt("Energy", this.energy);
    	nbt.putInt("Capacity", this.capacity);
    	nbt.putInt("MaxRecieve", this.maxReceive);
    	nbt.putInt("MaxExtract", this.maxExtract);
    }
    
}
