package n573.n5craft.tileentity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;

public class AbstractCustomFurnaceTileEntity extends AbstractFurnaceTileEntity {

	protected AbstractCustomFurnaceTileEntity(TileEntityType<?> tileTypeIn,
			IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
		super(tileTypeIn, recipeTypeIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ITextComponent getDefaultName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		// TODO Auto-generated method stub
		return null;
	}

}
