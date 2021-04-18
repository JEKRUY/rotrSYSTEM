package net.jekruy.rotr.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class StartMGame extends GuiScreenROTR {
	
	private String nameMGame;	
	
	public StartMGame(String nameMGameIn)
    {
		this.nameMGame = nameMGameIn;
    }
	
	@Override
	public void initGui() {
		super.initGui();
		int centerLeft = this.width / 2;
		this.buttonList.clear();
		this.buttonList.add(new GuiButtonROTR(503, centerLeft - 105, this.height - 30, 100, 14, true, true, false, I18n.format("menu.mgame.start"), 0xBFFFFFFF));
		this.buttonList.add(new GuiButtonROTR(504, centerLeft + 5, this.height - 30, 100, 14, true, true, false, I18n.format("menu.goto") + " RHub", 0xBFFFFFFF));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("rotr:textures/backkk.png"));
		Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		
	}
	
	@Override
	protected void actionPerformed(GuiButtonROTR button) throws IOException {
		if (button.id == 0) {
			{
				
			}
		}
	}
	
}
