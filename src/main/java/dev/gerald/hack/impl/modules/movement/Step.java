//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.movement;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;

public class Step extends Module
{
    public NumSetting stepHeight;
    
    public Step() {
        super("Step", Module.Category.MOVEMENT, "Steps up blocks.");
        this.stepHeight = (NumSetting)this.register((Setting)new NumSetting("Height", 1.5f, 0.0f, 2.0f));
    }
    
    public void onEnable() {
        this.mc.player.stepHeight = this.stepHeight.getValue();
    }
    
    public void onDisable() {
        this.mc.player.stepHeight = 0.5f;
    }
}
