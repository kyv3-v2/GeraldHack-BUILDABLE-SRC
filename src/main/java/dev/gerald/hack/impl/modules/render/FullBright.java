//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.render;

import dev.gerald.hack.api.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FullBright extends Module
{
    float originalGamma;
    
    public FullBright() {
        super("FullBright", Module.Category.RENDER, "LET THERE BE LIGHT!");
    }
    
    public void onEnable() {
        this.originalGamma = this.mc.gameSettings.gammaSetting;
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent event) {
        this.mc.gameSettings.gammaSetting = 100.0f;
    }
    
    public void onDisable() {
        this.mc.gameSettings.gammaSetting = this.originalGamma;
    }
}
