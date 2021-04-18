package net.jekruy.rotr.gui;

import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.lang3.ArrayUtils;

public class GuiKeyBindingListROTR extends GuiListExtended
{
    private final GuiOptionROTR controlsScreen;
    private final Minecraft mc;
    private final GuiListExtended.IGuiListEntry[] listEntries;
    private int maxListLabelWidth;

    public GuiKeyBindingListROTR(GuiOptionROTR controls, Minecraft mcIn)
    {
        super(mcIn, controls.width + 45, controls.height, 92, controls.height - 34, 14);
        this.controlsScreen = controls;
        this.mc = mcIn;
        KeyBinding[] akeybinding = (KeyBinding[])ArrayUtils.clone(mcIn.gameSettings.keyBindings);
        this.listEntries = new GuiListExtended.IGuiListEntry[akeybinding.length + KeyBinding.getKeybinds().size()];
        Arrays.sort((Object[])akeybinding);
        int i = 0;
        String s = null;

        for (KeyBinding keybinding : akeybinding)
        {
            String s1 = keybinding.getKeyCategory();

            if (!s1.equals(s))
            {
                s = s1;
                this.listEntries[i++] = new GuiKeyBindingListROTR.CategoryEntry(s1);
            }

            int j = mcIn.fontRenderer.getStringWidth(I18n.format(keybinding.getKeyDescription()));

            if (j > this.maxListLabelWidth)
            {
                this.maxListLabelWidth = j;
            }

            this.listEntries[i++] = new GuiKeyBindingListROTR.KeyEntry(keybinding);
        }
    }

    protected int getSize()
    {
        return this.listEntries.length;
    }

    /**
     * Gets the IGuiListEntry object for the given index
     */
    public GuiListExtended.IGuiListEntry getListEntry(int index)
    {
        return this.listEntries[index];
    }

    protected int getScrollBarX()
    {
        return super.getScrollBarX() + 15;
    }

    /**
     * Gets the width of the list
     */
    public int getListWidth()
    {
        return super.getListWidth() + 32;
    }

    public class CategoryEntry implements GuiListExtended.IGuiListEntry
    {
        private final String labelText;
        private final int labelWidth;

        public CategoryEntry(String name)
        {
            this.labelText = I18n.format(name);
            this.labelWidth = GuiKeyBindingListROTR.this.mc.fontRenderer.getStringWidth(this.labelText);
        }

        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
        {
        	GuiKeyBindingListROTR.this.mc.fontRenderer.drawString(this.labelText, (mc.displayWidth / 2) - this.labelWidth / 2, y + slotHeight - GuiKeyBindingListROTR.this.mc.fontRenderer.FONT_HEIGHT - 1, 16777215);
        }

        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
            return false;
        }

        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
        }

        public void updatePosition(int slotIndex, int x, int y, float partialTicks)
        {
        }
    }

    public class KeyEntry implements GuiListExtended.IGuiListEntry
    {
        private final KeyBinding keybinding;
        private final String keyDesc;
        private final GuiButtonROTR btnChangeKeyBinding;
        private final GuiButtonROTR btnReset;

        private KeyEntry(KeyBinding name)
        {
            this.keybinding = name;
            this.keyDesc = I18n.format(name.getKeyDescription());
            this.btnChangeKeyBinding = new GuiButtonROTR(0, 0, 0, 75, 14, true, true, false, I18n.format(name.getKeyDescription()), 0x80FFFFFF);
            this.btnReset = new GuiButtonROTR(0, 0, 0, 50, 14, true, true, false, I18n.format("controls.reset"), 0x80FFFFFF);
        }

        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
        {
            boolean flag = GuiKeyBindingListROTR.this.controlsScreen.buttonId == this.keybinding;
            GuiKeyBindingListROTR.this.mc.fontRenderer.drawString(this.keyDesc, x + 90 - GuiKeyBindingListROTR.this.maxListLabelWidth, y + slotHeight / 2 - GuiKeyBindingListROTR.this.mc.fontRenderer.FONT_HEIGHT / 2, 16777215);
            this.btnReset.x = x + 190;
            this.btnReset.y = y;
            this.btnReset.enabled = this.keybinding.getKeyCode() != this.keybinding.getKeyCodeDefault();
            this.btnReset.drawButton(GuiKeyBindingListROTR.this.mc, mouseX, mouseY, partialTicks);
            this.btnChangeKeyBinding.x = x + 105;
            this.btnChangeKeyBinding.y = y;
            this.btnChangeKeyBinding.displayString = GameSettings.getKeyDisplayString(this.keybinding.getKeyCode());
            boolean flag1 = false;

            if (this.keybinding.getKeyCode() != 0)
            {
                for (KeyBinding keybinding : GuiKeyBindingListROTR.this.mc.gameSettings.keyBindings)
                {
                    if (keybinding != this.keybinding && keybinding.getKeyCode() == this.keybinding.getKeyCode())
                    {
                        flag1 = true;
                        break;
                    }
                }
            }

            if (flag)
            {
                this.btnChangeKeyBinding.displayString = TextFormatting.WHITE + "> " + TextFormatting.YELLOW + this.btnChangeKeyBinding.displayString + TextFormatting.WHITE + " <";
            }
            else if (flag1)
            {
                this.btnChangeKeyBinding.displayString = TextFormatting.RED + this.btnChangeKeyBinding.displayString;
            }

            this.btnChangeKeyBinding.drawButton(GuiKeyBindingListROTR.this.mc, mouseX, mouseY, partialTicks);
        }

        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
            if (this.btnChangeKeyBinding.mousePressed(GuiKeyBindingListROTR.this.mc, mouseX, mouseY))
            {
                GuiKeyBindingListROTR.this.controlsScreen.buttonId = this.keybinding;
                return true;
            }
            else if (this.btnReset.mousePressed(GuiKeyBindingListROTR.this.mc, mouseX, mouseY))
            {
                GuiKeyBindingListROTR.this.mc.gameSettings.setOptionKeyBinding(this.keybinding, this.keybinding.getKeyCodeDefault());
                KeyBinding.resetKeyBindingArrayAndHash();
                return true;
            }
            else
            {
                return false;
            }
        }

        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            this.btnChangeKeyBinding.mouseReleased(x, y);
            this.btnReset.mouseReleased(x, y);
        }

        public void updatePosition(int slotIndex, int x, int y, float partialTicks)
        {
        }
    }
}
