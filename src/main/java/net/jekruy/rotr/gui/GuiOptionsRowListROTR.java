package net.jekruy.rotr.gui;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings.Options;

public class GuiOptionsRowListROTR extends GuiListExtendedROTR
{
    private final List<GuiOptionsRowListROTR.Row> options = Lists.<GuiOptionsRowListROTR.Row>newArrayList();

    public GuiOptionsRowListROTR(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotheightIn, GameSettings.Options... options)
    {
        super(mcIn, widthIn, heightIn, topIn, bottomIn, slotheightIn);
        this.centerListVertically = false;
        
        boolean settingrow;
        for (int i = 0; i < options.length; i += 1)
        {
        	if (i == 0) {
        		GuiButtonROTR guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, null);
                GuiLabelROTR label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0xB3FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, null, I18n.format("setting.categories.main")));
                GameSettings.Options gamesettings$options = options[i];
                guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, gamesettings$options);
                label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0x80FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, gamesettings$options, null));
                continue;
        	}
        	if (i == 3) {
        		GuiButtonROTR guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, null);
                GuiLabelROTR label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0xB3FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, null, I18n.format("setting.categories.display")));
                GameSettings.Options gamesettings$options = options[i];
                guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, gamesettings$options);
                label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0x80FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, gamesettings$options, null));
                continue;
        	}
        	if (i == 6) {
        		GuiButtonROTR guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, null);
                GuiLabelROTR label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0xB3FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, null, I18n.format("setting.categories.detals")));
                GameSettings.Options gamesettings$options = options[i];
                guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, gamesettings$options);
                label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0x80FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, gamesettings$options, null));
                continue;
        	}
        	if (i == 8) {
        		GuiButtonROTR guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, null);
                GuiLabelROTR label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0xB3FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, null, I18n.format("setting.categories.effect")));
                GameSettings.Options gamesettings$options = options[i];
                guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, gamesettings$options);
                label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0x80FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, gamesettings$options, null));
                continue;
        	}
        	if (i == 12) {
        		GuiButtonROTR guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, null);
                GuiLabelROTR label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0xB3FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, null, I18n.format("setting.categories.other")));
                GameSettings.Options gamesettings$options = options[i];
                guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, gamesettings$options);
                label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0x80FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, gamesettings$options, null));
                continue;
        	}
        	if (i == options.length - 1) {
        		GameSettings.Options gamesettings$options = options[i];
                GuiButtonROTR guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, gamesettings$options);
                GuiLabelROTR label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0x80FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, gamesettings$options, null));
        		guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, null);
                label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0x80FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, null, "code5"));
                continue;
        	} else {
        		GameSettings.Options gamesettings$options = options[i];
                GuiButtonROTR guibutton = this.createButton(mcIn, widthIn / 2 - 155, 0, gamesettings$options);
                GuiLabelROTR label = new GuiLabelROTR(mc.fontRenderer, i, widthIn / 2 - 155, 0, 100, 10, 0x80FFFFFF);
                this.options.add(new GuiOptionsRowListROTR.Row(guibutton, label, gamesettings$options, null));
                continue;
        	}
        }
    }

    private GuiButtonROTR createButton(Minecraft mcIn, int x, int y, GameSettings.Options options)
    {
        if (options == null)
        {
            return null;
        }
        else
        {
            int i = options.getOrdinal();
            return (GuiButtonROTR)(options.isFloat() ? new GuiOptionSliderROTR(i, x, y, options) : new GuiOptionButtonROTR(i, x, y, options, mcIn.gameSettings.getKeyBinding(options).replace(I18n.format(options.getTranslation()) + ": ", "")));
        }
    }

    /**
     * Gets the IGuiListEntry object for the given index
     */
    public GuiOptionsRowListROTR.Row getListEntry(int index)
    {
        return this.options.get(index);
    }

    protected int getSize()
    {
        return this.options.size();
    }

    /**
     * Gets the width of the list
     */
    public int getListWidth()
    {
        return 315;
    }

    protected int getScrollBarX()
    {
        return super.getScrollBarX() + 32;
    }

    public static class Row implements GuiListExtendedROTR.IGuiListEntry
    {
        private final Minecraft client = Minecraft.getMinecraft();
        private final GuiButtonROTR button;
		private Options text;
		private final GuiLabelROTR label;
		private String category;

        public Row(GuiButtonROTR buttonIn, GuiLabelROTR labelIn, Options gamesettings$options, String categoryIn)
        {
            this.label = labelIn;
			this.button = buttonIn;
            this.text = gamesettings$options;
            this.category = categoryIn;
        }

        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
        {
        	y = y + 5;
        	if (this.category != null) {
        		if (this.category.contains("code5")) {
        			
            	} else {
                    this.label.drawString(Minecraft.getMinecraft().fontRenderer, this.category, x + 5, y + 2, 0xB3FFFFFF);
            	}
        	} else {
            	this.button.y = y;
                this.label.y = y;
                Gui.drawRect(x, y, x + 310, y + 14, 0x30000000);
                this.button.drawButton(this.client, mouseX, mouseY, partialTicks);
                this.label.drawString(Minecraft.getMinecraft().fontRenderer, I18n.format(this.text.getTranslation()), x + 5, y + 3, 0x80FFFFFF);
        	}
        }

        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
            if (this.button.mousePressed(this.client, mouseX, mouseY))
            {
                if (this.button instanceof GuiOptionButtonROTR)
                {
                    this.client.gameSettings.setOptionValue(((GuiOptionButtonROTR)this.button).getOption(), 1);
                    this.button.displayString = this.client.gameSettings.getKeyBinding(GameSettings.Options.byOrdinal(this.button.id));
                }

                return true;
            }
            else
            {
                return false;
            }
        }

        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            if (this.button != null)
            {
                this.button.mouseReleased(x, y);
            }
        }

        public void updatePosition(int slotIndex, int x, int y, float partialTicks)
        {
        }
    }
}