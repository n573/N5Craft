package n573.n5craft.tileentity;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CustomFurnaceTileEntity extends AbstractCustomFurnaceTileEntity {

	protected CustomFurnaceTileEntity(TileEntityType<?> tileTypeIn,
			IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
		super(tileTypeIn, recipeTypeIn);
		// TODO Auto-generated constructor stub
	}

	@Override
    @Nonnull
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("block.brickfurnace.brick_furnace");
    }

    @Override
    @Nonnull
    protected Container createMenu(int id, @Nonnull PlayerInventory player) {
        return new FurnaceContainer(id, player, this, this.furnaceData);
    }
	
}
