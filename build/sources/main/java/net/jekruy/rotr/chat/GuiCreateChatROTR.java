package net.jekruy.rotr.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.jekruy.rotr.gui.GuiButtonROTR;
import net.jekruy.rotr.gui.GuiScreenROTR;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiCreateChatROTR extends GuiScreenROTR {

	int guiLeft;
	int guiTop;
	private GuiChatROTR currentscreen;
	NetHandlerPlayClient connection;
	List<NetworkPlayerInfo> playerInfo;
	
	public GuiCreateChatROTR(GuiChatROTR currentscreenIn)
    {
    	this.currentscreen = currentscreenIn;
    }
	
	@Override
	public void initGui() {
		guiLeft = (this.width) / 2 - 150;
		guiTop = (this.height) / 2 - 100;
		super.initGui();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		guiLeft = (this.width) / 2 - 150;
		guiTop = (this.height) / 2 - 100;
		connection = Minecraft.getMinecraft().player.connection;
		GL11.glPushMatrix ();
		GL11.glEnable (GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    playerInfo = new ArrayList<NetworkPlayerInfo>(connection.getPlayerInfoMap());
	    mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/cchat.png"));
	    GL11.glPopMatrix ();
		Gui.drawModalRectWithCustomSizedTexture(guiLeft - 5, guiTop - 5, 0, 0, 310, 210, 310, 210);
		this.drawString(mc.fontRenderer, I18n.format("game.cchat"), guiLeft + 10, guiTop + 5, 0x80FFFFFF);
		this.buttonList.clear();
		int id = 0;
		for(int i = 0; i < playerInfo.size();++i) {
			if (!playerInfo.get(i).getGameProfile().getName().equals(mc.player.getName())) {
				mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/msg.png"));
				Gui.drawModalRectWithCustomSizedTexture(guiLeft + 5, guiTop + 24 + 16 * id, 0, 0, 290, 14, 290, 14);
				this.drawString(mc.fontRenderer, playerInfo.get(i).getGameProfile().getName(), guiLeft + 10, guiTop + 27 + 16 * id, 0xBFFFFFFF);
				this.drawString(mc.fontRenderer, "+", guiLeft + 280, guiTop + 27 + 16 * id, 0x80FFFFFF);
				this.buttonList.add(new GuiButtonROTR(i, guiLeft + 270, guiTop + 24 + 16 * id, 20, 14, true, true, false, "", 0xBFFFFFFF));
				++id;
			}
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButtonROTR button) throws IOException {
		if (button.id > -1) {
			{
				for(int c = 1; c < currentscreen.chatid.length;++c) {
					if (currentscreen.chatid[c] != null) {
						System.out.println("Chat no null" + c);
						if (currentscreen.chatid[c].name.equals(playerInfo.get(button.id).getGameProfile().getName())) {
							System.out.println("Chat is it" + playerInfo.get(button.id).getGameProfile().getName());
							currentscreen.activeChat = c;
							mc.displayGuiScreen(currentscreen);
							break;
						}
					}else {
						System.out.println("Chat null" + c);
						currentscreen.chatid[c] = currentscreen.new Chat();
						currentscreen.chatid[c].name = playerInfo.get(button.id).getGameProfile().getName();
						currentscreen.chatid[c].msg[0] = "You have opened a new dialogue";
						currentscreen.activeChat = c;
						currentscreen.initGui();
						mc.displayGuiScreen(currentscreen);
						break;
					}
				}
			}
		}		
	}

}
