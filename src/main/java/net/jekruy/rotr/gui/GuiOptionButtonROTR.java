package net.jekruy.rotr.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class GuiOptionButtonROTR extends GuiButtonROTR
{
    private final GameSettings.Options enumOptions;

    public GuiOptionButtonROTR(int buttonID, int x, int y, String buttonText)
    {
        this(buttonID, x, y, (GameSettings.Options)null, buttonText);
    }

    public GuiOptionButtonROTR(int buttonID, int x, int y, GameSettings.Options options, String buttonText)
    {
        super(buttonID, x + 300 - Minecraft.getMinecraft().fontRenderer.getStringWidth(buttonText), y, Minecraft.getMinecraft().fontRenderer.getStringWidth(buttonText) + 10, 14, true, false, false, buttonText, 0x80FFFFFF);
        this.enumOptions = options;
    }

    public GameSettings.Options getOption()
    {
        return this.enumOptions;
    }
}
