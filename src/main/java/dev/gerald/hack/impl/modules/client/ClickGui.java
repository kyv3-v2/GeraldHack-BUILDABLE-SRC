//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.client;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import dev.gerald.hack.impl.gui.clickgui.*;
import net.minecraft.client.gui.*;

public class ClickGui extends Module
{
    public ColorSetting colorSetting;
    public BoolSetting categoryParent;
    public BoolSetting bold;
    public BoolSetting moduleCounter;
    public ModeSetting centerMode;
    
    public ClickGui() {
        super("ClickGui", Module.Category.CLIENT, "Renders shit");
        this.colorSetting = (ColorSetting)this.register((Setting)new ColorSetting("Color", 0, 255, 183, 255));
        this.categoryParent = (BoolSetting)this.register((Setting)new BoolSetting("Category", false, true));
        this.bold = (BoolSetting)this.register((Setting)new BoolSetting("Bold", true, false, () -> this.categoryParent.getValue()));
        this.moduleCounter = (BoolSetting)this.register((Setting)new BoolSetting("ModuleCounter", true, false, () -> this.categoryParent.getValue()));
        this.centerMode = (ModeSetting)this.register((Setting)new ModeSetting("CenterMode", (Enum)CenterMode.Center, () -> this.categoryParent.getValue()));
    }
    
    public void onEnable() {
        this.mc.displayGuiScreen((GuiScreen)new ClickGUI());
        this.toggle();
    }
    
    public enum CenterMode
    {
        Center, 
        Left, 
        Right;
    }
}
