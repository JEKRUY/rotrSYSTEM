package net.jekruy.rotr.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class GuiButtonROTR extends Gui
{
    protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation("rotr:textures/button.png");
    protected static final ResourceLocation TYPE_TEXTURES = new ResourceLocation("rotr:textures/buttonscroll.png");

    protected boolean texture;
    /** Button width in pixels */
    protected int width;

    /** Button height in pixels */
    protected int height;

    /** The x position of this control. */
    public int x;

    /** The y position of this control. */
    public int y;

    /** The string displayed on this control. */
    public String displayString;
    public int id;

    /** True if this control is enabled, false to disable. */
    public boolean enabled;

    /** Hides the button completely if false. */
    public boolean visible;
    protected boolean hovered;
	private boolean typeTexture;
	private int colorText;
	private boolean textRight;

    public GuiButtonROTR(int buttonId, int x, int y, boolean texture, String buttonText, boolean textRight, int colorTextIn)
    {
        this(buttonId, x, y, 200, 14, true, true, false, buttonText, 0x80FFFFFF);
    }

    public GuiButtonROTR(int buttonId, int x, int y, int widthIn, int heightIn, boolean textureIn, boolean typeTexture, boolean textRight, String buttonText, int colorTextIn)
    {
        this.width = 200;
        this.height = 14;
        this.enabled = true;
        this.visible = true;
        this.id = buttonId;
        this.x = x;
        this.y = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
        this.texture = textureIn;
        this.typeTexture = typeTexture;
        this.colorText = colorTextIn;
        this.textRight = textRight;
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean mouseOver)
    {
        int i = 1;

        if (!this.enabled)
        {
            i = 0;
        }
        else if (mouseOver)
        {
            i = 2;
        }

        return i;
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            if (this.texture) {
                if (this.typeTexture) {
                    mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
                } else {
                    mc.getTextureManager().bindTexture(TYPE_TEXTURES);
                }
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                this.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 14, this.width / 2, this.height);
                this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 14, this.width / 2, this.height);
            } else {
                //this.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(255, 0, 0).getRGB());
            }
            this.mouseDragged(mc, mouseX, mouseY);
            int j = this.colorText;

            if (!this.enabled)
            {
                j = 0x40FFFFFF;
            }
            else if (this.hovered)
            {
                j = 0xB3A3FFFB;
            }
            
            if (this.textRight) {
            	this.drawString(fontrenderer, this.displayString, this.x + this.width - fontrenderer.getStringWidth(this.displayString) - 5, this.y + (this.height - 8) / 2, j);
            } else {
            	this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
            }
        }
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY)
    {
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        return this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver()
    {
        return this.hovered;
    }

    public void drawButtonForegroundLayer(int mouseX, int mouseY)
    {
    }

    public void playPressSound(SoundHandler soundHandlerIn)
    {
        soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    public int getButtonWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }
}
