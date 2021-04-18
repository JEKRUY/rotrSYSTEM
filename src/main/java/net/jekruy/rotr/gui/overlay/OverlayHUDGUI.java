package net.jekruy.rotr.gui.overlay;

import org.lwjgl.opengl.GL11;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;

import net.jekruy.rotr.ElementsROTR;
import net.jekruy.rotr.keybind.Keybinds;
import net.minecraft.client.gui.Gui;

@ElementsROTR.ModElement.Tag
public class OverlayHUDGUI extends ElementsROTR.ModElement {
	
	static boolean variable = true;
	
	public OverlayHUDGUI(ElementsROTR instance) {
		super(instance, 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new GUIRenderEventClass());
	}
	public static class GUIRenderEventClass {
		
		float pos;
		boolean frame = false;
		ResourceLocation noisea = new ResourceLocation("rotr:textures/noise/a.png");
		ResourceLocation noiseb = new ResourceLocation("rotr:textures/noise/b.png");
		
		@SubscribeEvent(priority = EventPriority.NORMAL)
		@SideOnly(Side.CLIENT)
		public void eventHandler(RenderGameOverlayEvent event) {
			
			if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
				int posX = (event.getResolution().getScaledWidth()) / 2;
				int posY = (event.getResolution().getScaledHeight()) / 2;
				int posXX = (event.getResolution().getScaledWidth());
				int posYY = (event.getResolution().getScaledHeight());
				String Coord;
				String LevelName;
				Minecraft mc = Minecraft.getMinecraft();
				int Coins = 000100;
				String CoinsText = "COINS ";
				int scale = 1;
				//EntityPlayer entity = Minecraft.getMinecraft().player;
				if ((true)) {
					if (Minecraft.isGuiEnabled()) {
						
						GL11.glPushMatrix();
						GlStateManager.disableDepth();
						GlStateManager.depthMask(false);
						GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
						GlStateManager.depthMask(true);
						GlStateManager.enableDepth();
						GlStateManager.enableAlpha();
						GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
						GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
						mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/rpaneloverlay.png"));
						Gui.drawModalRectWithCustomSizedTexture(0, posY - 100, 0, 0, 55, 200, 55, 200);
						mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/lpaneloverlay.png"));
						Gui.drawModalRectWithCustomSizedTexture(posXX - 55, posY - 100, 0, 0, 55, 200, 55, 200);
						mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/tpaneloverlay969.png"));
						Gui.drawModalRectWithCustomSizedTexture(posX - 238, 0, 0, 0, 477, 45, 477, 45);
						mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/bpaneloverlay1077.png"));
						Gui.drawModalRectWithCustomSizedTexture(posX - 270, posYY - 38, 0, 0, 540, 38, 540, 38);
						GL11.glPopMatrix();
						
						//Coordinate
						Coord = ("X: " + mc.player.getPosition().getX() + " | Z: " + mc.player.getPosition().getZ());
						mc.fontRenderer.drawString(Coord, posX - mc.fontRenderer.getStringWidth(Coord)/2, 7, 0xBF46a147);
						
						//Level Name
						//LevelName = mc.getCurrentServerData().serverMOTD;
						LevelName = "TEST PLANET";
						mc.fontRenderer.drawString(LevelName, posX - mc.fontRenderer.getStringWidth(LevelName)/2, 15, 0xBF46a147);
						
						//Level Player
						mc.fontRenderer.drawString("LEVEL: 0000001", posX + 100, 7, 0xBF46a147);
						
						//RCoin
						mc.fontRenderer.drawString("RCOIN: 0000001", posX - 100, 7, 0xBF46a147);						
						mc.fontRenderer.drawString("", 0, 0, 0xFFFFFFFF);
						
						//Health
						mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/gui/hud/proc/" + (int)Math.ceil((mc.player.getHealth() / 2)) + ".png"));
						Gui.drawModalRectWithCustomSizedTexture(posX - 194, posYY - 24, 0, 0, 66, 16, 66, 16);
						
						//FoodStats
						mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/gui/hud/proc/" + (int)Math.ceil((mc.player.getFoodStats().getFoodLevel() / 2)) + "a.png"));
						Gui.drawModalRectWithCustomSizedTexture(posX + 128, posYY - 24, 0, 0, 66, 16, 66, 16);
						
						//Voice Chat
						if (Keybinds.speakvoice.isKeyDown() == true) {
							mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/microon.png"));
						}else {
							mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/microoff.png"));
						}
						Gui.drawModalRectWithCustomSizedTexture(posX + 215, posYY - 17, 0, 0, 12, 12, 12, 12);

						
						//mc.fontRenderer.drawString(String.valueOf(((int) mc.player.getHealth())*5), ((posX - 155)/2 - mc.fontRenderer.getStringWidth(String.valueOf(((int) mc.player.getHealth())*5))/2), (posYY - 24)/2, 0xBF46a147);
						
						GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
						
						//Noise!!
						GL11.glPushMatrix();
						GL11.glDisable(GL11.GL_ALPHA_TEST);
						GL11.glEnable(GL11.GL_BLEND);
						//OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
						GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
						if ((int)mc.player.getHealth()*5 < 26) {
							pos = (25 - ((float) mc.player.getHealth())*5)/50;
							GlStateManager.color(1.0F, 1.0F, 1.0F, pos);
							if (frame == true) {
								frame = false;
								mc.renderEngine.bindTexture(noisea);
							} else {
								frame = true;
								mc.renderEngine.bindTexture(noiseb);
							}
							Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, posXX, posYY, 64, 64);
						}
						GL11.glPopMatrix();
						
						GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					}
				}
			}
		}
	}
}
