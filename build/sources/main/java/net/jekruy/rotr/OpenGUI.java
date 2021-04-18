package net.jekruy.rotr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.jekruy.rotr.gui.GuiPauseROTR;
import net.jekruy.rotr.gui.GuiScreenROTR;
import net.jekruy.rotr.gui.StartMGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

import net.jekruy.rotr.chat.GuiChatROTR;
import net.jekruy.rotr.gui.GuiCraftingROTR;
import net.jekruy.rotr.gui.GuiDTROTR;
import net.jekruy.rotr.gui.GuiGOverROTR;
import net.jekruy.rotr.gui.GuiInventoryROTR;
import net.jekruy.rotr.gui.GuiMainMenuROTR;

public class OpenGUI{
	
	public static boolean Title = false;    
    Minecraft mc = Minecraft.getMinecraft();
    GuiScreenROTR MineMenu = new GuiMainMenuROTR();
    private GuiChatROTR ChatROTR = new GuiChatROTR();
    String message;
    String username;
        
	@SubscribeEvent
	public void onGuiOpenEvent(GuiOpenEvent event)
	{
		if (event.getGui() instanceof GuiGameOver) {
			event.setGui(new GuiGOverROTR());
		}
		if (event.getGui() instanceof GuiDownloadTerrain) {
			event.setGui(new GuiDTROTR());
		}
		if (event.getGui() instanceof GuiChat) {
			event.setGui(ChatROTR);
		}
		if (event.getGui() instanceof GuiIngameMenu) {
			event.setGui(new GuiPauseROTR());
		}
		if (event.getGui() instanceof GuiMultiplayer) {
			event.setGui(new GuiMainMenuROTR());
		}
		if (event.getGui() instanceof GuiMainMenu) {
			event.setGui(MineMenu);
			if (Title == false) {
				Title = true;
				SettingGame();
			}
		}
		if (event.getGui() instanceof GuiInventory) {
			event.setGui(new GuiInventoryROTR(Minecraft.getMinecraft().player));
		}
		if (event.getGui() instanceof GuiCrafting) {
			//event.setGui(new GuiCraftingROTR(mc.player.inventory, mc.world));
			event.setGui(new StartMGame("control"));
		}
		
	}
	
	@SubscribeEvent
	public void onClientChatEvent(ClientChatReceivedEvent event)
	{
		if (event.getMessage().getUnformattedText().contains(I18n.format("game.chatr"))) {
			message = (event.getMessage().getUnformattedText());
			username = message.split(" ")[0];
			ChatROTR.AddMessage(username, message.replace(message.split(" ")[0], "").replace(" "+I18n.format("game.chatr")+": ", ""));
		}
	}
	
	public void SettingGame() {
		Display.setTitle("Replace of the Reality");
		File icon = Paths.get("%appdata%\\Replace of the Reality\\updates\\Client\\resources\\rotr\\icon.png").toFile();
		try {
			Display.setIcon(new ByteBuffer[]{ loadIcon(icon)});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static ByteBuffer loadIcon(final File icon2) throws IOException {
        final BufferedImage icon = ImageIO.read(icon2);
 
        final int[] rgb = icon.getRGB(0, 0, icon.getWidth(), icon.getHeight(), null, 0, icon.getWidth());
 
        final ByteBuffer buffer = ByteBuffer.allocate(4 * rgb.length);
        for (int color : rgb) {
            buffer.putInt(color << 8 | ((color >> 24) & 0xFF));
        }
        buffer.flip();
        return buffer;
    }
}
