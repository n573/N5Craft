package n573.n5craft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import n573.n5craft.lists.ArmorMaterialList;
import n573.n5craft.lists.BlockList;
import n573.n5craft.lists.FoodList;
import n573.n5craft.lists.ItemList;
import n573.n5craft.lists.ToolMaterialList;
import n573.n5craft.world.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("n5craft")
public class N5Craft {
	
	public static N5Craft instance;
	public static final String modid = "n5craft";
	private static final Logger logger = LogManager.getLogger(modid);
	
	public static final ItemGroup n5craft = new N5ItemGroup();
	
	public N5Craft() 
	{	
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		logger.info("Setup method registered.");
	}
	
private void clientRegistries(final FMLClientSetupEvent event) {
	logger.info("clientRegistries method registered.");
	}
	
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public static class RegistryEvents {
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) 
	{
		event.getRegistry().registerAll(
		//Items
				ItemList.grateful_ingot = new Item(new Item.Properties().group(n5craft)).setRegistryName(location("grateful_ingot")),
		
		//Blocks
				//ItemList.grateful_block = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.grateful_block.getRegistryName())
				ItemList.grateful_block = new BlockItem(BlockList.grateful_block, new Item.Properties().group(n5craft)).setRegistryName(BlockList.grateful_block.getRegistryName()),
				ItemList.grateful_ore = new BlockItem(BlockList.grateful_ore, new Item.Properties().group(n5craft)).setRegistryName(BlockList.grateful_ore.getRegistryName()),
				ItemList.grateful_nether_ore = new BlockItem(BlockList.grateful_nether_ore, new Item.Properties().group(n5craft)).setRegistryName(BlockList.grateful_nether_ore.getRegistryName()),
				ItemList.grateful_end_ore = new BlockItem(BlockList.grateful_end_ore, new Item.Properties().group(n5craft)).setRegistryName(BlockList.grateful_end_ore.getRegistryName()),
		//Tools
				ItemList.grateful_axe = new AxeItem(ToolMaterialList.n5craft, -1.0f, 6.0f, new Item.Properties().group(n5craft)).setRegistryName(location("grateful_axe")),
				ItemList.grateful_pickaxe = new PickaxeItem(ToolMaterialList.n5craft, -1, 6.0f, new Item.Properties().group(n5craft)).setRegistryName(location("grateful_pickaxe")),
				ItemList.grateful_hoe = new HoeItem(ToolMaterialList.n5craft, 2, 6.0f, new Item.Properties().group(n5craft)).setRegistryName(location("grateful_hoe")),	
				ItemList.grateful_shovel = new ShovelItem(ToolMaterialList.n5craft, -1.0f, 6.0f, new Item.Properties().group(n5craft)).setRegistryName(location("grateful_shovel")),
				ItemList.grateful_sword = new SwordItem(ToolMaterialList.n5craft, 0, 6.0f, new Item.Properties().group(n5craft)).setRegistryName(location("grateful_sword")),
				ItemList.smite_hammer = new SpecialItem(new Item.Properties().group(n5craft)).setRegistryName(location("smite_hammer")),
		//Armor
				ItemList.grateful_helmet = new ArmorItem(ArmorMaterialList.n5craft, EquipmentSlotType.HEAD, new Item.Properties().group(n5craft)).setRegistryName(location("grateful_helmet")),
				ItemList.grateful_chestplate = new ArmorItem(ArmorMaterialList.n5craft, EquipmentSlotType.CHEST, new Item.Properties().group(n5craft)).setRegistryName(location("grateful_chestplate")),
				ItemList.grateful_leggings = new ArmorItem(ArmorMaterialList.n5craft, EquipmentSlotType.LEGS, new Item.Properties().group(n5craft)).setRegistryName(location("grateful_leggings")),
				ItemList.grateful_boots = new ArmorItem(ArmorMaterialList.n5craft, EquipmentSlotType.FEET, new Item.Properties().group(n5craft)).setRegistryName(location("grateful_boots")),

		//Food
//				ItemList.crack = new Item(new Item.Properties().group(n5craft).food(new Food.Builder().effect(new EffectInstance(Effects.SPEED), 2000).fastToEat().hunger(2).saturation(0.2f).setAlwaysEdible().build())).setRegistryName(location("crack"))
				ItemList.crack = new Item(new Item.Properties().group(n5craft).food(FoodList.CRACK)).setRegistryName("crack"),
				
				ItemList.custom_furnace = new BlockItem(BlockList.custom_furnace, new Item.Properties().group(n5craft)).setRegistryName(BlockList.custom_furnace.getRegistryName())
				
				);
		logger.info("Items registered.");
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) 
	{
		event.getRegistry().registerAll(
				BlockList.grateful_block = new Block(Block.Properties.create(Material.IRON).sound(SoundType.BAMBOO).harvestLevel(1).hardnessAndResistance(2.0f, 3.0f)).setRegistryName(location("grateful_block")),
				BlockList.grateful_ore = new Block(Block.Properties.create(Material.ROCK).sound(SoundType.METAL).harvestLevel(1).hardnessAndResistance(2.0f, 3.0f)).setRegistryName(location("grateful_ore")),
				BlockList.grateful_nether_ore = new Block(Block.Properties.create(Material.ROCK).sound(SoundType.METAL).harvestLevel(1).hardnessAndResistance(2.0f, 3.0f)).setRegistryName(location("grateful_nether_ore")),
				BlockList.grateful_end_ore = new Block(Block.Properties.create(Material.ROCK).sound(SoundType.METAL).harvestLevel(1).hardnessAndResistance(2.0f, 3.0f)).setRegistryName(location("grateful_end_ore")),
				BlockList.custom_furnace = new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).harvestLevel(1)).setRegistryName("custom_furnace")
		);
		logger.info("Blocks registered.");
	}
	
	private static ResourceLocation location(String name) {
		return new ResourceLocation(modid,name);
	}
	
}

}
