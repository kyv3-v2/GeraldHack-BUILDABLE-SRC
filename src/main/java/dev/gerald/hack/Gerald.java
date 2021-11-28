//shittery removed by ligmaballz#6211
//made buildable by cash

package dev.gerald.hack;

import net.minecraftforge.fml.common.*;
import dev.gerald.hack.impl.modules.*;
import dev.gerald.hack.impl.commands.*;
import dev.gerald.hack.api.event.*;
import dev.gerald.hack.impl.gui.*;
import dev.gerald.hack.impl.gui.clickgui.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.common.*;
import org.lwjgl.opengl.*;
import dev.gerald.hack.api.util.*;

@Mod(modid = "gerald", name = "GeraldHack", version = "1.1")
public class Gerald
{
    public static final String CLIENT_ID = "gerald";
    public static final String CLIENT_NAME = "GeraldHack";
    public static final String CLIENT_VERSION = "1.1";
    public static String CLIENT_PREFIX;
    @Mod.Instance("gerald")
    public static Gerald INSTANCE;
    public ModuleManager moduleManager;
    public CommandManager commandManager;
    public EventListener eventListener;
    public HUD hud;
    public ClickGUI clickGUI;
    public ConfigManager configManager;
    
    @Mod.EventHandler
    public void init(final FMLPreInitializationEvent event) throws Exception {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.moduleManager = new ModuleManager();
        this.commandManager = new CommandManager();
        this.eventListener = new EventListener();
        this.clickGUI = new ClickGUI();
        this.hud = new HUD(this);
        this.configManager = new ConfigManager();
        Display.setTitle("GeraldHack v1.1");
        this.configManager.load();
        Runtime.getRuntime().addShutdownHook((Thread)new ShutdownHook());
    }
    
    public void setClientPrefix(final String key) {
        Gerald.CLIENT_PREFIX = key;
    }
    
    static {
        Gerald.CLIENT_PREFIX = "-";
    }
}
