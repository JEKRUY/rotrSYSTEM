package net.jekruy.rotr.gui;

import org.lwjgl.input.Keyboard;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.SoundEvents;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class GuiOptionROTR extends GuiScreenROTR {
	
	int ScreenID = 1;
	
	EntityPlayer Player = Minecraft.getMinecraft().player;
	Minecraft mc = Minecraft.getMinecraft();
	
	private final GameSettings game_settings;
	private GuiListExtendedROTR optionsRowList;
	private GuiScreen currentscreen;
	private GuiOptionButtonROTR narratorButton;
	private String offDisplayString;
	public KeyBinding buttonId;
    public long time;
    private GuiKeyBindingListROTR keyBindingList;
    
    public static final GameSettings.Options[] VIDEO_OPTIONS = new GameSettings.Options[] { GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.GRAPHICS, GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.GUI_SCALE, GameSettings.Options.ATTACK_INDICATOR, GameSettings.Options.USE_FULLSCREEN, GameSettings.Options.PARTICLES, GameSettings.Options.MIPMAP_LEVELS, GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.ENTITY_SHADOWS, GameSettings.Options.RENDER_CLOUDS, GameSettings.Options.GAMMA, GameSettings.Options.VIEW_BOBBING, GameSettings.Options.USE_VBO, GameSettings.Options.ENABLE_VSYNC, GameSettings.Options.ANAGLYPH};
    private static final GameSettings.Options[] CHAT_OPTIONS = new GameSettings.Options[] {GameSettings.Options.CHAT_VISIBILITY, GameSettings.Options.CHAT_COLOR, GameSettings.Options.CHAT_LINKS, GameSettings.Options.CHAT_OPACITY, GameSettings.Options.CHAT_LINKS_PROMPT, GameSettings.Options.CHAT_SCALE, GameSettings.Options.CHAT_HEIGHT_FOCUSED, GameSettings.Options.CHAT_HEIGHT_UNFOCUSED, GameSettings.Options.CHAT_WIDTH, GameSettings.Options.REDUCED_DEBUG_INFO, GameSettings.Options.NARRATOR};
    private static final GameSettings.Options[] OPTIONS_ARR = new GameSettings.Options[] {GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY, GameSettings.Options.TOUCHSCREEN, GameSettings.Options.AUTO_JUMP};
    //private static final qualitysettingsof.enumOptions PERFOM_OPTIONS;
    //private static final GameSettings.Options[] PERFOM_OPTIONS = new GameSettings.Options[] { GameSettings.Options.SMOOTH_FPS, GameSettings.Options.SMOOTH_WORLD, GameSettings.Options.FAST_RENDER, GameSettings.Options.FAST_MATH, GameSettings.Options.CHUNK_UPDATES, GameSettings.Options.CHUNK_UPDATES_DYNAMIC, GameSettings.Options.RENDER_REGIONS, GameSettings.Options.LAZY_CHUNK_LOADING, GameSettings.Options.SMART_ANIMATIONS };
    
    public GuiOptionROTR(GuiScreen currentscreenIn, GameSettings gameSettingsIn)
    {
    	this.game_settings = gameSettingsIn;
    	this.currentscreen = currentscreenIn;
    	
    }

	@Override
	public void initGui() {
		super.initGui();
		int centerLeft = this.width / 2;
		this.buttonList.clear();
		this.buttonList.add(new GuiButtonROTR(500, centerLeft - 260, 10, 100, 14, true, true, false, I18n.format("menu.graphic"), 0xBFFFFFFF));
		this.buttonList.add(new GuiButtonROTR(501, centerLeft - 155, 10, 100, 14, true, true, false, I18n.format("menu.Music"), 0xBFFFFFFF));
		this.buttonList.add(new GuiButtonROTR(502, centerLeft - 50, 10, 100, 14, true, true, false, I18n.format("menu.Control"), 0xBFFFFFFF));
		this.buttonList.add(new GuiButtonROTR(503, centerLeft + 55, 10, 100, 14, true, true, false, I18n.format("menu.chatsettings"), 0xBFFFFFFF));
		this.buttonList.add(new GuiButtonROTR(504, centerLeft + 160, 10, 100, 14, true, true, false, I18n.format("menu.performance"), 0xBFFFFFFF));
		this.buttonList.add(new GuiButtonROTR(505, centerLeft - 50, this.height - 24, 100, 14, true, true, false, I18n.format("menu.Done"), 0xBFFFFFFF));
		if (ScreenID == 1) { //Option Main
			Keyboard.enableRepeatEvents(true);
			if (OpenGlHelper.vboSupported)
	        {
	            this.optionsRowList = new GuiOptionsRowListROTR(this.mc, this.width, this.height, 34, this.height - 34, 19, VIDEO_OPTIONS);
	        }
	        else
	        {
	            GameSettings.Options[] agamesettings$options = new GameSettings.Options[VIDEO_OPTIONS.length - 1];
	            int i = 0;

	            for (GameSettings.Options gamesettings$options : VIDEO_OPTIONS)
	            {
	                if (gamesettings$options == GameSettings.Options.USE_VBO)
	                {
	                    break;
	                }

	                agamesettings$options[i] = gamesettings$options;
	                ++i;
	            }

	            this.optionsRowList = new GuiOptionsRowListROTR(this.mc, this.width, this.height, 34, this.height - 34 , 19, agamesettings$options);
	        }
		}
		if (ScreenID == 2) { //Option Sound
			int n = 19;
			this.buttonList.add(new GuiOptionROTR.ButtonSound(SoundCategory.MASTER.ordinal(), this.width / 2 - 155, 44, SoundCategory.MASTER, true));
	        this.buttonList.add(new GuiOptionROTR.ButtonSound(SoundCategory.MUSIC.ordinal(), this.width / 2 - 155, 44 + n * 1, SoundCategory.MUSIC, true));
	        this.buttonList.add(new GuiOptionROTR.ButtonSound(SoundCategory.BLOCKS.ordinal(), this.width / 2 - 155, 44 + n * 2, SoundCategory.BLOCKS, true));
	        this.buttonList.add(new GuiOptionROTR.ButtonSound(SoundCategory.WEATHER.ordinal(), this.width / 2 - 155, 44 + n * 3, SoundCategory.WEATHER, true));
	        this.buttonList.add(new GuiOptionROTR.ButtonSound(SoundCategory.NEUTRAL.ordinal(), this.width / 2 - 155, 44 + n * 4, SoundCategory.NEUTRAL, true));
	        this.buttonList.add(new GuiOptionROTR.ButtonSound(SoundCategory.HOSTILE.ordinal(), this.width / 2 - 155, 44 + n * 5, SoundCategory.HOSTILE, true));
	        this.buttonList.add(new GuiOptionROTR.ButtonSound(SoundCategory.PLAYERS.ordinal(), this.width / 2 - 155, 44 + n * 6, SoundCategory.PLAYERS, true));
	        this.buttonList.add(new GuiOptionROTR.ButtonSound(SoundCategory.RECORDS.ordinal(), this.width / 2 - 155, 44 + n * 7, SoundCategory.RECORDS, true));
	        this.buttonList.add(new GuiOptionROTR.ButtonSound(SoundCategory.VOICE.ordinal(), this.width / 2 - 155, 44 + n * 8, SoundCategory.VOICE, true));
		}
		if (ScreenID == 3) { //Option Control
			Keyboard.enableRepeatEvents(true);
			this.keyBindingList = new GuiKeyBindingListROTR(this, mc);
			int i = 0;

	        for (GameSettings.Options gamesettings$options : OPTIONS_ARR)
	        {
	            if (gamesettings$options.isFloat())
	            {
	                this.buttonList.add(new GuiOptionSliderROTR(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, 44 + 19 * (i >> 1), gamesettings$options));
	            }
	            else
	            {
	                this.buttonList.add(new GuiOptionButtonROTR(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, 44 + 19 * (i >> 1), gamesettings$options, this.game_settings.getKeyBinding(gamesettings$options)));
	            }

	            ++i;
	        }
		}
		if (ScreenID == 4) { //Option Chat
			int i = 0;
	        for (GameSettings.Options gamesettings$options : CHAT_OPTIONS)
	        {
	            if (gamesettings$options.isFloat())
	            {
	                this.buttonList.add(new GuiOptionSliderROTR(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, 44 + 19 * (i >> 1), gamesettings$options));
	            }
	            else
	            {
	                GuiOptionButtonROTR guioptionbutton = new GuiOptionButtonROTR(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, 44 + 19 * (i >> 1), gamesettings$options, this.game_settings.getKeyBinding(gamesettings$options));
	                this.buttonList.add(guioptionbutton);

	                if (gamesettings$options == GameSettings.Options.NARRATOR)
	                {
	                    this.narratorButton = guioptionbutton;
	                    guioptionbutton.enabled = NarratorChatListener.INSTANCE.isActive();
	                }
	            }
	            ++i;
	        }
		}
		if (ScreenID == 5) { //Option Performance
			//for (int i = 0; i < PERFOM_OPTIONS.length; i++) {
			//      GameSettings.Options enumoptions = PERFOM_OPTIONS[i];
			//      int x = this.width / 2 - 155 + i % 2 * 160;
			//      int y = this.height / 6 + 21 * i / 2 - 12;
			//      if (!enumoptions.isFloat()) {
			//        this.buttonList.add(new GuiOptionButtonROTR(enumoptions.getOrdinal(), x, y, enumoptions, this.game_settings.getKeyBinding(enumoptions)));
			///     } else {
			 //       this.buttonList.add(new GuiOptionSliderROTR(enumoptions.getOrdinal(), x, y, enumoptions));
			 //     } 
			 //   } 
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		if (mc.world == null) {
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("rotr:textures/backk.png"));
			Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		}
		if (ScreenID == 1) { //Option Main
			super.drawScreen(mouseX, mouseY, partialTicks);
			this.optionsRowList.drawScreen(mouseX, mouseY, partialTicks);
		}
		if (ScreenID == 2) {
			super.drawScreen(mouseX, mouseY, partialTicks);
		}
		if (ScreenID == 3) {
			super.drawScreen(mouseX, mouseY, partialTicks);
			this.keyBindingList.drawScreen(mouseX, mouseY, partialTicks);
	        for (KeyBinding keybinding : this.game_settings.keyBindings)
	        {
	            if (keybinding.getKeyCode() != keybinding.getKeyCodeDefault())
	            {
	                break;
	            }
	        }
		}
		if (ScreenID == 4) {
			super.drawScreen(mouseX, mouseY, partialTicks);
		}
		if (ScreenID == 5) { //Option Skin
			super.drawScreen(mouseX, mouseY, partialTicks);
		}
	}
	public void handleMouseInput() throws IOException
	{
		super.handleMouseInput();
		if (ScreenID == 1) {
	        this.optionsRowList.handleMouseInput();
		}
		if (ScreenID == 3) {
			this.keyBindingList.handleMouseInput();
		}
    }
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
		if (ScreenID == 1) {
			super.mouseClicked(mouseX, mouseY, mouseButton);
			int i = this.game_settings.guiScale;
	        this.optionsRowList.mouseClicked(mouseX, mouseY, mouseButton);

	        if (this.game_settings.guiScale != i)
	        {
	            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
	            int j = scaledresolution.getScaledWidth();
	            int k = scaledresolution.getScaledHeight();
	            this.setWorldAndResolution(this.mc, j, k);
	        }
		}
		if (ScreenID == 2) {
			super.mouseClicked(mouseX, mouseY, mouseButton);
    	}
		if (ScreenID == 3) {
			if (this.buttonId != null)
	        {
	            this.game_settings.setOptionKeyBinding(this.buttonId, -100 + mouseButton);
	            this.buttonId = null;
	            KeyBinding.resetKeyBindingArrayAndHash();
	        }
	        else if (mouseButton != 0 || !this.keyBindingList.mouseClicked(mouseX, mouseY, mouseButton))
	        {
	            super.mouseClicked(mouseX, mouseY, mouseButton);
	        }
		}
		if (ScreenID == 4) {
			super.mouseClicked(mouseX, mouseY, mouseButton);
    	}
		if (ScreenID == 5) {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}
    }
	private String getMessage(EnumPlayerModelParts playerModelParts)
    {
        String s;

        if (this.mc.gameSettings.getModelParts().contains(playerModelParts))
        {
            s = I18n.format("options.on");
        }
        else
        {
            s = I18n.format("options.off");
        }

        return playerModelParts.getName().getFormattedText() + ": " + s;
    }
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
    	if (ScreenID == 1) {
    		super.mouseReleased(mouseX, mouseY, state);
    		int i = this.game_settings.guiScale;
            this.optionsRowList.mouseReleased(mouseX, mouseY, state);

            if (this.game_settings.guiScale != i)
            {
                ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                int j = scaledresolution.getScaledWidth();
                int k = scaledresolution.getScaledHeight();
                this.setWorldAndResolution(this.mc, j, k);
            }
    	}
    	if (ScreenID == 2) {
    		super.mouseReleased(mouseX, mouseY, state);
    	}
    	if (ScreenID == 3) {
    		if (state != 0 || !this.keyBindingList.mouseReleased(mouseX, mouseY, state))
            {
                super.mouseReleased(mouseX, mouseY, state);
            }
    	}
    	if (ScreenID == 4) {
    		super.mouseReleased(mouseX, mouseY, state);
    	}
    	if (ScreenID == 5) {
    		super.mouseReleased(mouseX, mouseY, state);
    	}
    }
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    	if (ScreenID == 1) {
    		if (keyCode == 1)
            {
                this.mc.gameSettings.saveOptions();
            }
            super.keyTyped(typedChar, keyCode);
    	}
    	if (ScreenID == 2) {
    		if (keyCode == 1)
            {
                this.mc.gameSettings.saveOptions();
            }
            super.keyTyped(typedChar, keyCode);
    	}
        if (ScreenID == 3) {
        	if (this.buttonId != null)
            {
                if (keyCode == 1)
                {
                    this.game_settings.setOptionKeyBinding(this.buttonId, 0);
                }
                else if (keyCode != 0)
                {
                    this.game_settings.setOptionKeyBinding(this.buttonId, keyCode);
                }
                else if (typedChar > 0)
                {
                    this.game_settings.setOptionKeyBinding(this.buttonId, typedChar + 256);
                }

                this.buttonId = null;
                this.time = Minecraft.getSystemTime();
                KeyBinding.resetKeyBindingArrayAndHash();
            }
            else
            {
                super.keyTyped(typedChar, keyCode);
            }
        }
        if (ScreenID == 4) {
    		if (keyCode == 1)
            {
                this.mc.gameSettings.saveOptions();
            }
            super.keyTyped(typedChar, keyCode);
    	}
        if (ScreenID == 5) {
    		if (keyCode == 1)
            {
                this.mc.gameSettings.saveOptions();
            }
            super.keyTyped(typedChar, keyCode);
    	}
    }
	
	@Override
	protected void actionPerformed(GuiButtonROTR button) throws IOException {
		if (ScreenID == 2) {
			if (button.id == 201)
	        {
	            this.mc.gameSettings.setOptionValue(GameSettings.Options.SHOW_SUBTITLES, 1);
	            button.displayString = this.mc.gameSettings.getKeyBinding(GameSettings.Options.SHOW_SUBTITLES);
	            this.mc.gameSettings.saveOptions();
	        }
		}
		if (ScreenID == 4) {
			if (button.id < 100 && button instanceof GuiOptionButtonROTR)
	        {
	            this.game_settings.setOptionValue(((GuiOptionButtonROTR)button).getOption(), 1);
	            button.displayString = this.game_settings.getKeyBinding(GameSettings.Options.byOrdinal(button.id));
	        }
		}
		if (ScreenID == 5) {
			if (button.id < 200 && button instanceof GuiOptionButtonROTR) {
			      this.game_settings.setOptionValue(((GuiOptionButtonROTR)button).getOption(), 1);
			      button.displayString = this.game_settings.getKeyBinding(GameSettings.Options.byOrdinal(button.id));
			    } 
		}
		if (button.id == 505) {
			{
				mc.displayGuiScreen(currentscreen);
			}
		}
		if (button.id == 500) {
			{
				ScreenID = 1;
				initGui();
			}
		}
		if (button.id == 501) {
			{
				ScreenID = 2;
				initGui();
			}
		}
		if (button.id == 502) {
			{
				ScreenID = 3;
				initGui();
			}
		}
		if (button.id == 503) {
			{
				ScreenID = 4;
				initGui();
			}
		}
		if (button.id == 504) {
			{
				ScreenID = 5;
				initGui();
			}
		}			
	}
	class ButtonSound extends GuiButtonROTR
    {
        private final SoundCategory category;
        private final String categoryName;
        public float volume = 1.0F;
        public boolean pressed;

        public ButtonSound(int buttonId, int x, int y, SoundCategory categoryIn, boolean master)
        {
            super(buttonId, x, y, master ? 310 : 150, 14, true, true, false, "", 0x80FFFFFF);
            this.category = categoryIn;
            this.categoryName = I18n.format("soundCategory." + categoryIn.getName());
            this.displayString = this.categoryName + ": " + GuiOptionROTR.this.getDisplayString(categoryIn);
            this.volume = GuiOptionROTR.this.game_settings.getSoundLevel(categoryIn);
        }

        protected int getHoverState(boolean mouseOver)
        {
            return 0;
        }

        protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
        {
            if (this.visible)
            {
                if (this.pressed)
                {
                    this.volume = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
                    this.volume = MathHelper.clamp(this.volume, 0.0F, 1.0F);
                    mc.gameSettings.setSoundLevel(this.category, this.volume);
                    mc.gameSettings.saveOptions();
                    this.displayString = this.categoryName + ": " + GuiOptionROTR.this.getDisplayString(this.category);
                }

                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.drawTexturedModalRect(this.x + (int)(this.volume * (float)(this.width - 8)), this.y, 0, 66, 4, 14);
                this.drawTexturedModalRect(this.x + (int)(this.volume * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 14);
            }
        }

        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
        {
            if (super.mousePressed(mc, mouseX, mouseY))
            {
                this.volume = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
                this.volume = MathHelper.clamp(this.volume, 0.0F, 1.0F);
                mc.gameSettings.setSoundLevel(this.category, this.volume);
                mc.gameSettings.saveOptions();
                this.displayString = this.categoryName + ": " + GuiOptionROTR.this.getDisplayString(this.category);
                this.pressed = true;
                return true;
            }
            else
            {
                return false;
            }
        }

        public void playPressSound(SoundHandler soundHandlerIn)
        {
        }

        public void mouseReleased(int mouseX, int mouseY)
        {
            if (this.pressed)
            {
            	GuiOptionROTR.this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            }

            this.pressed = false;
        }
    }
	protected String getDisplayString(SoundCategory category)
    {
        float f = this.game_settings.getSoundLevel(category);
        return f == 0.0F ? this.offDisplayString : (int)(f * 100.0F) + "%";
    }
	public void updateNarratorButton()
    {
        this.narratorButton.displayString = this.game_settings.getKeyBinding(GameSettings.Options.byOrdinal(this.narratorButton.id));
    }
	class ButtonPart extends GuiButtonROTR
    {
        private final EnumPlayerModelParts playerModelParts;

        private ButtonPart(int p_i45514_2_, int p_i45514_3_, int p_i45514_4_, int p_i45514_5_, int p_i45514_6_, EnumPlayerModelParts playerModelParts)
        {
            super(p_i45514_2_, p_i45514_3_, p_i45514_4_, p_i45514_5_, p_i45514_6_, true, true, false, GuiOptionROTR.this.getMessage(playerModelParts), 0x80FFFFFF);
            this.playerModelParts = playerModelParts;
        }
    }
}