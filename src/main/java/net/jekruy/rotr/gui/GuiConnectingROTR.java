package net.jekruy.rotr.gui;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.CPacketLoginStart;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class GuiConnectingROTR extends GuiScreenROTR
{
    private static final AtomicInteger CONNECTION_ID = new AtomicInteger(0);
    private static final Logger LOGGER = LogManager.getLogger();
    private NetworkManager networkManager;
    private boolean cancel;
    private final GuiScreenROTR previousGuiScreen;

    public GuiConnectingROTR(GuiScreenROTR parent, Minecraft mcIn, ServerData serverDataIn)
    {
        this.mc = mcIn;
        this.previousGuiScreen = parent;
        ServerAddress serveraddress = ServerAddress.fromString(serverDataIn.serverIP);
        mcIn.loadWorld((WorldClient)null);
        mcIn.setServerData(serverDataIn);
        this.connect(serveraddress.getIP(), serveraddress.getPort());
    }

    public GuiConnectingROTR(GuiScreenROTR parent, Minecraft mcIn, String hostName, int port)
    {
        this.mc = mcIn;
        this.previousGuiScreen = parent;
        mcIn.loadWorld((WorldClient)null);
        this.connect(hostName, port);
    }
    
    @Override
    public void initGui()
    {
        this.buttonList.clear();
        this.buttonList.add(new GuiButtonROTR(0, this.width / 2 - 50, this.height / 2 + 110, 100, 14, true, true, false, I18n.format("gui.cancel"), 0x80FFFFFF));
    } 
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("rotr:textures/backk.png"));
		Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("rotr:textures/controller255.png"));
		Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 96, this.height / 2 - 64, 0, 0, 193, 128, 193, 128);

        if (this.networkManager == null)
        {
            this.drawCenteredString(this.fontRenderer, I18n.format("menu.Connect"), this.width / 2, this.height / 2 + 90, 16777215);
        }
        else
        {
            this.drawCenteredString(this.fontRenderer, I18n.format("connect.authorizing"), this.width / 2, this.height / 2 + 90, 16777215);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void connect(final String ip, final int port)
    {
        LOGGER.info("Connecting to {}, {}", ip, Integer.valueOf(port));
        (new Thread("Server Connector #" + CONNECTION_ID.incrementAndGet())
        {
            public void run()
            {
                InetAddress inetaddress = null;

                try
                {
                    if (GuiConnectingROTR.this.cancel)
                    {
                        return;
                    }

                    inetaddress = InetAddress.getByName(ip);
                    GuiConnectingROTR.this.networkManager = NetworkManager.createNetworkManagerAndConnect(inetaddress, port, GuiConnectingROTR.this.mc.gameSettings.isUsingNativeTransport());
                    GuiConnectingROTR.this.networkManager.setNetHandler(new NetHandlerLoginClient(GuiConnectingROTR.this.networkManager, GuiConnectingROTR.this.mc, GuiConnectingROTR.this.previousGuiScreen));
                    GuiConnectingROTR.this.networkManager.sendPacket(new C00Handshake(ip, port, EnumConnectionState.LOGIN));
                    GuiConnectingROTR.this.networkManager.sendPacket(new CPacketLoginStart(GuiConnectingROTR.this.mc.getSession().getProfile()));
                }
                catch (UnknownHostException unknownhostexception)
                {
                    if (GuiConnectingROTR.this.cancel)
                    {
                        return;
                    }

                    GuiConnectingROTR.LOGGER.error("Couldn't connect to server", (Throwable)unknownhostexception);
                    GuiConnectingROTR.this.mc.displayGuiScreen(new GuiDisconnected(GuiConnectingROTR.this.previousGuiScreen, "connect.failed", new TextComponentTranslation("disconnect.genericReason", new Object[] {"Unknown host"})));
                }
                catch (Exception exception)
                {
                    if (GuiConnectingROTR.this.cancel)
                    {
                        return;
                    }

                    GuiConnectingROTR.LOGGER.error("Couldn't connect to server", (Throwable)exception);
                    String s = exception.toString();

                    if (inetaddress != null)
                    {
                        String s1 = inetaddress + ":" + port;
                        s = s.replaceAll(s1, "");
                    }

                    GuiConnectingROTR.this.mc.displayGuiScreen(new GuiDisconnected(GuiConnectingROTR.this.previousGuiScreen, "connect.failed", new TextComponentTranslation("disconnect.genericReason", new Object[] {s})));
                }
            }
        }).start();
    }

    public void updateScreen()
    {
        if (this.networkManager != null)
        {
            if (this.networkManager.isChannelOpen())
            {
                this.networkManager.processReceivedPackets();
            }
            else
            {
                //this.networkManager.handlerRemoved(); .handleDisconnection();
            }
        }
    }
    
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    }

    protected void actionPerformed(GuiButtonROTR button) throws IOException
    {
        if (button.id == 0)
        {
            this.cancel = true;

            if (this.networkManager != null)
            {
                this.networkManager.closeChannel(new TextComponentString("Aborted"));
            }

            this.mc.displayGuiScreen(this.previousGuiScreen);
        }
    }
}
