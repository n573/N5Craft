package n573.n5craft.lists;

import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodList extends Foods {
	//public static final Food CRACK = (new Food.Builder()).hunger(4).saturation(1.2F).effect(new EffectInstance(Effects.REGENERATION, 400, 1), 1.0F).effect(new EffectInstance(Effects.RESISTANCE, 6000, 0), 1.0F).effect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0), 1.0F).effect(new EffectInstance(Effects.ABSORPTION, 2400, 3), 1.0F).setAlwaysEdible().build();
	public static final Food CRACK = (new Food.Builder()).hunger(4).saturation(1.2F).effect(new EffectInstance(Effects.REGENERATION, 400, 1), 1.0F).effect(new EffectInstance(Effects.SATURATION, 6000, 2), 1.0F).effect(new EffectInstance(Effects.SPEED, 6000, 3), 1.0F).setAlwaysEdible().build();
	
}
