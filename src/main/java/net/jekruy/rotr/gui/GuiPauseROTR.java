package net.jekruy.rotr.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.resources.I18n;

public class GuiPauseROTR extends GuiScreenROTR {
	EntityPlayer Player = Minecraft.getMinecraft().player;
	Minecraft mc = Minecraft.getMinecraft();
	double posM;
	double posHH;
	int posH;
	//public static GuiPause instance;
	
	@Override
	public void initGui() {
		super.initGui();
		posM = this.height / 10;
		posHH = this.height;
		int guiLeft = (this.width - 300) / 2;
		int guiTop = (this.height - 135) / 2;
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButtonROTR(0, guiLeft + 0, guiTop + 65 + posH, 300, 14, true, true, false, I18n.format("menu.Continue"), 0xBFFFFFFF));
		this.buttonList.add(new GuiButtonROTR(1, guiLeft + 0, guiTop + 90 + posH, 148, 14, true, true, false, I18n.format("menu.AvatarMenu"), 0xBFFFFFFF));
		this.buttonList.add(new GuiButtonROTR(2, guiLeft + 152, guiTop + 90 + posH, 148, 14, true, true, false, I18n.format("menu.Map"), 0xBFFFFFFF));
		this.buttonList.add(new GuiButtonROTR(3, guiLeft + 0, guiTop + 115 + posH, 148, 14, true, true, false, I18n.format("menu.Settings"), 0xBFFFFFFF));
		this.buttonList.add(new GuiButtonROTR(4, guiLeft + 152, guiTop + 115 + posH, 148, 14, true, true, false, I18n.format("menu.Mainmenu"), 0xBFFFFFFF));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int guiLeft = (this.width - 300) / 2;
		int guiTop = (this.height - 135) / 2;
		GL11.glPushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		if (posHH > 0) {
			posHH = posHH - posM;
			posM = posM / (1.1);
			posH = (int)posHH;
			this.buttonList.clear();
			this.buttonList.add(new GuiButtonROTR(0, guiLeft + 0, guiTop + 65 + posH, 300, 14, true, true, false, I18n.format("menu.Continue"), 0xBFFFFFFF));
			this.buttonList.add(new GuiButtonROTR(1, guiLeft + 0, guiTop + 90 + posH, 148, 14, true, true, false, I18n.format("menu.AvatarMenu"), 0xBFFFFFFF));
			this.buttonList.add(new GuiButtonROTR(2, guiLeft + 152, guiTop + 90 + posH, 148, 14, true, true, false, I18n.format("menu.Map"), 0xBFFFFFFF));
			this.buttonList.add(new GuiButtonROTR(3, guiLeft + 0, guiTop + 115 + posH, 148, 14, true, true, false, I18n.format("menu.Settings"), 0xBFFFFFFF));
			this.buttonList.add(new GuiButtonROTR(4, guiLeft + 152, guiTop + 115 + posH, 148, 14, true, true, false, I18n.format("menu.Mainmenu"), 0xBFFFFFFF));
		}
		GL11.glPopMatrix();
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		//this.renderHoveredToolTip(mouseX, mouseY);
		String Username = new String(Minecraft.getMinecraft().player.getName());
		boolean isServer = Player.world.isRemote;
		// this.drawRect(0, 0, 352, 60, 0x6BCCCCCC);
		String Planet = new String("OFFLINE");
		if (!Player.world.isRemote) {
			Planet = new String(Minecraft.getMinecraft().getCurrentServerData().serverName);
		}
		this.mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/avataranel.png"));
		Gui.drawModalRectWithCustomSizedTexture(guiLeft, guiTop + posH, 0, 0, 300, 60, 300, 60);
		this.fontRenderer.drawString("Planet: " + Planet, guiLeft + 60, guiTop+ 32 + posH, 0x80FFFFFF);
		// GL11.glScalef(2, 2, 2);
		this.fontRenderer.drawString(Username, guiLeft + 60, guiTop + 22 + posH, -1);
	}

	@Override
	protected void actionPerformed(GuiButtonROTR button) throws IOException {
		if (button.id == 0) {
			{
				this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
			}
		}
		if (button.id == 1) {
			{
				
			}
		}
		if (button.id == 2) {
			{
				
			}
		}
		if (button.id == 3) {
			{
				this.mc.displayGuiScreen(new GuiOptionROTR(this, mc.gameSettings));
			}
		}
		if (button.id == 4) {
			{
				this.mc.world.sendQuittingDisconnectingPacket();
                this.mc.loadWorld(null);
				Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
				
			}
		}
				
	}
}	