package net.jekruy.rotr.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class rotrPortalBlock extends Block {
	
	public rotrPortalBlock (String name, Material material) {
		super(Material.GLASS);
		setRegistryName(name);
	    setUnlocalizedName("block.rotrportal");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setHardness(3);
		setResistance(3);
		lightValue = 15;
		setSoundType(SoundType.METAL);
	}	
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer(){
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	public boolean isFullCube(IBlockState state){
		return false;
	}
}
