package net.jekruy.rotr.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.renderer.GlStateManager;

public class GuiMainMenuROTR extends GuiScreenROTR {
	
	EntityPlayer Player = Minecraft.getMinecraft().player;
	Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void initGui() {
		super.initGui();
		int guiLeft = this.width / 2;
		int guiTop = this.height / 2;
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButtonROTR(0, guiLeft - 57, guiTop - 57, 115, 115, false, true, false, I18n.format(""), 0x80FFFFFF));
		this.buttonList.add(new GuiButtonROTR(2, guiLeft + 70, guiTop - 33, 62, 62, false, true, false, I18n.format(""), 0x80FFFFFF));
		this.buttonList.add(new GuiButtonROTR(1, guiLeft - 132, guiTop - 33, 62, 62, false, true, false, I18n.format(""), 0x80FFFFFF));
		this.buttonList.add(new GuiButtonROTR(3, guiLeft + 138, guiTop - 21, 42, 42, false, true, false, I18n.format(""), 0x80FFFFFF));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int guiLeft = this.width / 2;
		int guiTop = this.height / 2;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		this.drawDefaultBackground();
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("rotr:textures/background.png"));
		Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		this.mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/mainmenu588.png"));
		Gui.drawModalRectWithCustomSizedTexture(guiLeft - 262, guiTop - 150, 0, 0, 525, 300, 525, 300);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButtonROTR button) throws IOException {
		if (button.id == 0) {
			{
				mc.displayGuiScreen(new GuiConnectingROTR(this, mc, "135.181.54.135", 25565));
			}
		}
		if (button.id == 1) {
			{
				mc.displayGuiScreen(new GuiWorldSelection(this));
			}
		}
		if (button.id == 2) {
			{
				mc.displayGuiScreen(new GuiOptionROTR(this, mc.gameSettings));
				System.out.println("TEST");				
			}
		}
		if (button.id == 3) {
			{
				mc.shutdown();
			}
		}
				
	}
}	