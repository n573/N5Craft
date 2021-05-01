package n573.n5craft.lists;

import n573.n5craft.N5Craft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum ArmorMaterialList implements IArmorMaterial {

	n5craft("grateful", 400, new int[] {8,10,9,7}, 25, ItemList.grateful_ingot, "entity.ender_dragon.growl", 4.0f);
	
	private static final int[] max_damage_array = new int[]{13,15,16,11};
	private String name, equipSound;
	private int durability, enchatability;
	private int[] damageReductionAmounts;
	private Item repairItem;
	private float toughness;
	
	private ArmorMaterialList(String name, int durability, int[] damageReductionAmounts, int enchantability, Item repairItem, String equipSound, float toughness) {
		this.name = name;
		this.equipSound = equipSound;
		this.durability = durability;
		this.enchatability = enchantability;
		this.repairItem = repairItem;
		this.damageReductionAmounts = damageReductionAmounts;
		this.toughness = toughness;
	}

	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		return this.max_damage_array[slotIn.getIndex()] * this.durability;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return this.damageReductionAmounts[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		return this.enchatability;
	}

	@Override
	public SoundEvent getSoundEvent() {
		return new SoundEvent(new ResourceLocation(equipSound));
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(this.repairItem);
	}

	@Override
	public String getName() {
		return N5Craft.modid + ":" + this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
