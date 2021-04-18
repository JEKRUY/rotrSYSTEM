package net.jekruy.rotr.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiGOverROTR extends GuiScreenROTR {
	
	int guiLeft;
	int guiTop;
	String text = "Ebat ti lox";
	@Override
	public void initGui() {
		super.initGui();
		guiLeft = this.width / 2;
		guiTop = this.height / 2;
		this.buttonList.clear();
		this.buttonList.add(new GuiButtonROTR(0, guiLeft - 100, guiTop - 7, 200, 14, true, true, false, I18n.format("deathScreen.respawn"), 0x80FFFFFF));
		super.initGui();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		guiLeft = this.width / 2;
		guiTop = this.height / 2;
		this.drawString(mc.fontRenderer, text, guiLeft - (mc.fontRenderer.getStringWidth(text)), guiTop - 50, 0x80FFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	
	
	@Override
	protected void actionPerformed(GuiButtonROTR button) throws IOException {
		if (button.id == 0) {
			this.mc.player.respawnPlayer();
			mc.displayGuiScreen((GuiScreen)null);
		}
	}
}
