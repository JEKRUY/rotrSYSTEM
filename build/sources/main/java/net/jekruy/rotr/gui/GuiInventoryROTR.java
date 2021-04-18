package net.jekruy.rotr.gui;

import java.io.IOException;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.jekruy.rotr.util.TDW;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;

public class GuiInventoryROTR extends InventoryEffectRendererROTR implements IRecipeShownListener
{
	TDW tdw = new TDW(DimensionManager.getWorld(0));
	double posM;
	double posWW;
	double posA;
	int posW;
	int h;
	int w;
	int i;
	int j;
	String Date;
	
    /** The old x position of the mouse pointer */
    private float oldMouseX;

    /** The old y position of the mouse pointer */
    private float oldMouseY;
    //private GuiButtonImage recipeButton;
    private boolean buttonClicked;

    public GuiInventoryROTR(EntityPlayer player)
    {
        super(player.inventoryContainer);
        this.allowUserInput = true;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        if (this.mc.playerController.isInCreativeMode())
        {
            this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.player));
        }
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleareWd beforehand.
     */
    public void initGui()
    {
    	this.guiTop = this.height/2;
    	posM = this.height / 10;
		posWW = this.height;		
        if (this.mc.playerController.isInCreativeMode())
        {
            this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.player));
        }
        else
        {
            super.initGui();
        }

        //this.recipeButton = new GuiButtonImage(10, this.guiLeft + 104, this.height / 2 - 22, 20, 18, 178, 0, 19, INVENTORY_BACKGROUND);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    	
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    	this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
    	//h = this.height / 2;
    	//w = this.width / 2;
		if (posWW > 0) {
			posWW = posWW - posM;
			posM = posM / (1.11);
			posW = (int)posWW;
		}
		
		w = this.width - 464;
		h = this.height - 246;
		
		GL11.glPushMatrix();
		
		GL11.glEnable (GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		
		this.mc.getTextureManager().bindTexture(new ResourceLocation("rotr:textures/gui/inventory928.png"));
		Gui.drawModalRectWithCustomSizedTexture(w, h, 0, 0, 928, 492, 928, 492);
		
		i = (this.width / 2) - 232;
		j = (this.height / 2) - 123;
		//
		this.drawStringSizeFull("GO TO RHOME", i + 12, j + 17, 2.0F, 0xFF51F6F3);
		this.drawStringSizeFull("GO TO RHUB", i + 12, j + 29, 2.0F, 0xFF51F6F3);
		this.drawStringSizeFull("GO TO STORE", i + 12, j + 42, 2.0F, 0xFF51F6F3);
		//
		this.drawStringSizeFull(mc.player.getDisplayNameString(), i + 232 - fontRenderer.getStringWidth(mc.player.getDisplayNameString())/2, j + 41, 2.0F, 0xFF51F6F3);
		this.drawStringSizeFull((int)mc.player.getHealth()*5 + "%", (int)(i + 154)/2, (int)(j + 91)/2, 4.0F, 0xFF21818C);
		this.drawStringSizeFull(mc.player.getFoodStats().getFoodLevel()*5 + "%", (int)(i + 300 - fontRenderer.getStringWidth(mc.player.getFoodStats().getFoodLevel()*5 + "%"))/2, (int)(j + 91)/2, 4.0F, 0xFF21818C);
		//
		this.drawStringSizeFull(tdw.GetTime(), (int)(i + 19)/2, (int)(j + 128)/2, 4.0F, 0xFF51F6F3);
		Date = tdw.GetWeek() + ", " + tdw.GetMonth() + ", " + tdw.GetDay() +  ", " + tdw.GetYear();
		this.drawStringSizeFull(Date, i + 69 - (mc.fontRenderer.getStringWidth(Date)) / 2, j + 147, 2.0F, 0xFF51F6F3);
		//
		if (tdw.GetWeather() == 0) {
			this.drawStringSizeFull("Sun", i + 409, j + 147, 2.0F, 0xFF51F6F3);
			this.mc.getTextureManager().bindTexture(new ResourceLocation("rotr:textures/gui/weather/sun.png"));
			Gui.drawModalRectWithCustomSizedTexture(w + 669, h + 244, 0, 0, 154, 86, 154, 86);
		} else {
			this.drawStringSizeFull("Rain", i + 409, j + 147, 2.0F, 0xFF51F6F3);
			this.mc.getTextureManager().bindTexture(new ResourceLocation("rotr:textures/gui/weather/thunder.png"));
			Gui.drawModalRectWithCustomSizedTexture(w + 669, h + 244, 0, 0, 154, 86, 154, 86);
		}
		this.drawStringSizeFull("10C", (int)(i + 413)/2, (int)(j + 130)/2, 4.0F, 0xFF51F6F3);
		//
		this.drawString(fontRenderer, " ", i, j, 0xFFFFFFFF);
		
		GL11.glPopMatrix();
		
		//mc.fontRenderer.drawString("GO TO RHOME", i + 15, j + 20 + posW, 0xBF5371635);
		
		//this.drawString(fontRendererIn, text, x, y, color);
		
		
		//mc.fontRenderer.drawString(" ", i + 10, j + 20, 0xFFFFFFFF);
        //drawEntityOnScreen(i + 85 + posW, j + 100, 50, this.mc.player);
    }

    /**
     * Draws an entity on the screen looking toward the cursor.
     */
    public static void drawEntityOnScreen(int posX, int posY, int scale, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.color(0.2F, 1.0F, 1.0F, 1.0F);
        //GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(10 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(0 / 40.0F)) * 20.0F;
        ent.rotationYaw = (float)Math.atan((double)(0 / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float)Math.atan((double)(10 / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        GlStateManager.enableColorMaterial();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    /**
     * Test if the 2D point is in a rectangle (relative to the GUI). Args : rectX, rectY, rectWidth, rectHeight, pointX,
     * pointY
     */

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
    	super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /**
     * Called when a mouse button is released.
     */
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (this.buttonClicked)
        {
            this.buttonClicked = false;
        }
        else
        {
            super.mouseReleased(mouseX, mouseY, state);
        }
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButtonROTR button) throws IOException
    {
    	if (button.id == 0) 
    	{
				this.mc.displayGuiScreen(new GuiCraftingROTR(mc.player.inventory, mc.world));
		}
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */

    /**
     * Called when the mouse is clicked over a slot or outside the gui.
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    	super.keyTyped(typedChar, keyCode);
    }

    /**
     * Called when the mouse is clicked over a slot or outside the gui.
     */
    protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type)
    {
        super.handleMouseClick(slotIn, slotId, mouseButton, type);
    }


    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

	@Override
	public void recipesUpdated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GuiRecipeBook func_194310_f() {
		// TODO Auto-generated method stub
		return null;
	}
}
