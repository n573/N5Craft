package n573.n5craft.client.gui.screen.inventory;

import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AbstractCustomFurnaceScreen extends AbstractFurnaceScreen<AbstractFurnaceContainer>{

	public AbstractCustomFurnaceScreen(AbstractFurnaceContainer screenContainer, AbstractRecipeBookGui recipeGuiIn,
			PlayerInventory inv, ITextComponent titleIn, ResourceLocation guiTextureIn) {
		super(screenContainer, recipeGuiIn, inv, titleIn, guiTextureIn);
		// TODO Auto-generated constructor stub
	}

}
