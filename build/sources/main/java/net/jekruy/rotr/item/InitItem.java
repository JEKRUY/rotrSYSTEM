package net.jekruy.rotr.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InitItem {
	
	public static Item rotrportal = new rotrPortalItem("rotrportal");

    public static void register()
    {	
    	setRegister(rotrportal);

    }

    @SideOnly(Side.CLIENT)
    public static void registerRender()
    {	
    	setRender(rotrportal);

    }

    private static void setRegister(Item item)
    {
        ForgeRegistries.ITEMS.registerAll(item);
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Item item)
    {
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
