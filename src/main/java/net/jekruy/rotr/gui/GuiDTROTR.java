package net.jekruy.rotr.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiDTROTR extends GuiScreen
{

    public void initGui()
    {
        this.buttonList.clear();
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawBackground(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("rotr:textures/backk.png"));
		Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("rotr:textures/controller255.png"));
		Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 96, this.height / 2 - 64, 0, 0, 193, 128, 193, 128);
        this.drawCenteredString(this.fontRenderer, I18n.format("multiplayer.downloadingTerrain"), this.width / 2, this.height / 2 + 90, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
