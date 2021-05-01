package n573.n5craft;

import java.util.List;

import n573.n5craft.lists.ToolMaterialList;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.datafix.fixes.BlockStateFlatternEntities;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;

public class SpecialItem extends Item {

	public SpecialItem(Properties props) {
		super(props);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslationTextComponent("Right click to smite your enemies!"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		//return super.onItemRightClick(worldIn, playerIn, handIn);
		if(!playerIn.getCooldownTracker().hasCooldown(this)) {
			playerIn.addPotionEffect(new EffectInstance(Effects.GLOWING, 200, 5));
			LightningBoltEntity smite = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, worldIn);
			smite.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ()); //spawns on player
			worldIn.addEntity(smite);
//			ZombieEntity zomb = new ZombieEntity(worldIn);
//			zomb.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
//			worldIn.addEntity(zomb);
			playerIn.getCooldownTracker().setCooldown(this, 100);
		}
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
		//		worldIn.addEntity(new EntityStruckByLightningEvent(Entity., null))

	}

}
