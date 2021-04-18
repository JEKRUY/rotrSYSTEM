package net.jekruy.rotr.chat;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.jekruy.rotr.gui.GuiButtonROTR;
import net.jekruy.rotr.gui.GuiLabelROTR;
import net.jekruy.rotr.gui.GuiScreenROTR;
import net.jekruy.rotr.gui.GuiTextFieldROTR;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiChatROTR extends GuiScreenROTR {
	
	private GuiTextFieldROTR text;
	private GuiLabelROTR label;
	int activeChat = -1;
	String nameChat = "";
	public Chat[] chatid = new Chat[11];
	
	@Override
	public void initGui() {
		guiLeft = (this.width) / 2 - 150;
		guiTop = (this.height) / 2 - 100;
		super.initGui();
		this.buttonList.clear();
		int id = 0;
		for(int c = 1; c < chatid.length;++c) {
			if (chatid[c] != null) {
				this.buttonList.add(new GuiButtonROTR(c, guiLeft, guiTop + 19 + 19 * id, 100, 14 , true, true, false, chatid[c].name, 0xBFFFFFFF));
				++id;
			}
		}
		if (activeChat != -1) {
			this.buttonList.add(new GuiButtonROTR(-3, guiLeft + 280, guiTop, 20, 10, false, true, false, "Clear", 0xBFFFFFFF));
		}
		this.buttonList.add(new GuiButtonROTR(-4, guiLeft + 90, guiTop, 10, 10, false, true, false, "+", 0xBFFFFFFF));
		this.label = new GuiLabelROTR(mc.fontRenderer, 0, guiLeft + 110, guiTop + 5, 280, 27, 0x80FFFFFF);
		this.text = new GuiTextFieldROTR(0, mc.fontRenderer, guiLeft + 110, guiTop + 186, 190, 14);
		this.text.setFocused(true);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		GL11.glPushMatrix ();
		GL11.glEnable (GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/chat.png"));
		Gui.drawModalRectWithCustomSizedTexture(guiLeft - 5, guiTop - 5, 0, 0, 310, 210, 310, 210);
		GL11.glPopMatrix ();
		//this.drawGradientRect(guiLeft - 5, guiTop - 5, guiLeft + 105, guiTop + 205, 0x30FFFFFF, 0x30FFFFFF);
		//this.drawGradientRect(guiLeft + 105, guiTop - 5, guiLeft + 305, guiTop + 205, 0x30FFFFFF, 0x30FFFFFF);
		this.text.drawTextBox();
		mc.fontRenderer.drawString(I18n.format("game.chatc"), guiLeft, guiTop, 0x80FFFFFF);
		this.label.drawLabel(mc, mouseX, mouseY);
		super.drawScreen(mouseX, mouseY, partialTicks);
		if (activeChat > -1) {
			mc.fontRenderer.drawString(chatid[activeChat].name, guiLeft + 110, guiTop, 0x80FFFFFF);
			for (int m = 0; m < chatid[activeChat].msg.length;++m) {
				if (chatid[activeChat] != null) {
					if (chatid[activeChat].msg[m] != null) {
						mc.renderEngine.bindTexture(new ResourceLocation("rotr:textures/msg.png"));
						Gui.drawModalRectWithCustomSizedTexture(guiLeft + 110, guiTop + 25 + 16 * m, 0, 0, 190, 14, 190, 14);
						this.drawString(mc.fontRenderer, chatid[activeChat].msg[m], guiLeft + 115, guiTop + 28 + 16 * m, 0x80FFFFFF);
					}
				}
			}
		}
	}
	
	
	
	int guiLeft = (this.width) / 2 - 150;
	int guiTop = (this.height) / 2 - 100;
	
	int n = 0;
	public void AddMessage(String username, String message) {
		for(int c = 1; c < chatid.length;++c) {
			if(chatid[c] != null) {
				System.out.println("ChatID = " + c);
				if (chatid[c].name.equals(username)) {
					System.out.println("Username = " + chatid[c].name);
					for(int cc = 0; cc < chatid[c].msg.length;++cc) {
						if (cc == 9) {
							chatid[c].msg[0] = chatid[c].msg[1];
							chatid[c].msg[1] = chatid[c].msg[2];
							chatid[c].msg[2] = chatid[c].msg[3];
							chatid[c].msg[3] = chatid[c].msg[4];
							chatid[c].msg[4] = chatid[c].msg[5];
							chatid[c].msg[5] = chatid[c].msg[6];
							chatid[c].msg[6] = chatid[c].msg[7];
							chatid[c].msg[7] = chatid[c].msg[8];
							chatid[c].msg[8] = chatid[c].msg[9];
							chatid[c].msg[9] = message;
							break;
						}else {
							if (chatid[c].msg[cc] == null) {
								chatid[c].msg[cc] = message;
								System.out.println("Message =" + chatid[c].msg[cc] + " on " + chatid[c].name + ". MsgID = " + cc);
								break;
							}
						}
					}
					break;
				}else {
					System.out.println("No, this chat is " + chatid[c].name + ". Need to " + username);
				}
			}else {
				chatid[c] = new Chat();
				chatid[c].name = username;
				chatid[c].msg[0] = message;
				System.out.println("Create chat " + chatid[c].name + " and send msg: " + chatid[c].msg[0] + " | ChatID = " + c);
				break;
			}
		}
		initGui();
	}
	
	public class Chat {
		String name;
		String[] msg = new String[10];
	}
	
	protected void mouseClicked(int x, int y, int btn) throws IOException {
        super.mouseClicked(x, y, btn);
        this.text.mouseClicked(x, y, btn);
    }
	
	protected void keyTyped(char par1, int keyCode) throws IOException
    {
        super.keyTyped(par1, keyCode);
        this.text.textboxKeyTyped(par1, keyCode);
        if (keyCode == Keyboard.KEY_RETURN) {
        	System.out.println("Send!");
			if (!this.text.getText().isEmpty()) {
				if(activeChat == -1) {
					sendChatMessage(this.text.getText());
					this.text.setText("");
				}else {
					Minecraft.getMinecraft().player.sendChatMessage("/msg " + chatid[activeChat].name + " " + this.text.getText());
					for(int cc = 0; cc < chatid[activeChat].msg.length;++cc) {
						if (cc == 9) {
							chatid[activeChat].msg[0] = chatid[activeChat].msg[1];
							chatid[activeChat].msg[1] = chatid[activeChat].msg[2];
							chatid[activeChat].msg[2] = chatid[activeChat].msg[3];
							chatid[activeChat].msg[3] = chatid[activeChat].msg[4];
							chatid[activeChat].msg[4] = chatid[activeChat].msg[5];
							chatid[activeChat].msg[5] = chatid[activeChat].msg[6];
							chatid[activeChat].msg[6] = chatid[activeChat].msg[7];
							chatid[activeChat].msg[7] = chatid[activeChat].msg[8];
							chatid[activeChat].msg[8] = chatid[activeChat].msg[9];
							chatid[activeChat].msg[9] = this.text.getText();
							break;
						}else {
							if (chatid[activeChat].msg[cc] == null) {
								chatid[activeChat].msg[cc] = this.text.getText();
								break;
							}
						}
					}
					nameChat = chatid[activeChat].name;
					this.text.setText("");
				}
			}
        }
    }
	
	@Override
	protected void actionPerformed(GuiButtonROTR button) throws IOException {
		if (button.id > 0) {
			{
				activeChat = button.id;
				initGui();
			}
		}		
		if (button.id == -5) {
			{
				
			}
		}
		if (button.id == -4) {
			mc.displayGuiScreen(new GuiCreateChatROTR(this));
		}
		if (button.id == -3) {
			chatid[activeChat] = null;
			activeChat = -1;
			initGui();
		}
	}
}
