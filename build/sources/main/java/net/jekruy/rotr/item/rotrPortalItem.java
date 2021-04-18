package net.jekruy.rotr.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class rotrPortalItem extends Item {

	public rotrPortalItem(String name)  
    {
		setUnlocalizedName(name); 
		setRegistryName(name);  
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHasSubtypes(true);
	}
	
}
