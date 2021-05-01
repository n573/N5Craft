package n573.n5craft;

import n573.n5craft.lists.ItemList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class N5ItemGroup extends ItemGroup {
	public N5ItemGroup() {
		super("n5craft");
	}
	
	@Override
	public ItemStack createIcon() {
		return new ItemStack(ItemList.grateful_ingot);
	}
	
}
