package net.jekruy.rotr.keybind;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Keybinds
{
    public static KeyBinding speakvoice;
    public static KeyBinding sendmessage;
 
    public static void register()
    {
    	speakvoice = new KeyBinding(I18n.format("key.voicechat"), Keyboard.KEY_V, I18n.format("key.categories.rotr"));
        ClientRegistry.registerKeyBinding(speakvoice);
        sendmessage = new KeyBinding(I18n.format("key.send"), Keyboard.KEY_NUMPADENTER, I18n.format("key.categories.rotr"));
        ClientRegistry.registerKeyBinding(sendmessage);
    }
}
