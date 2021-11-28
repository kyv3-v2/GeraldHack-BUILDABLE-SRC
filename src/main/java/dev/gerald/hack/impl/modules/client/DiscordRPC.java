//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.client;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.util.*;

public class DiscordRPC extends Module
{
    public DiscordRPC() {
        super("DiscordRPC", Module.Category.CLIENT, "RPC for the client.");
        this.setNeedsKeybindSetting(false);
    }
    
    public void onEnable() {
        if (this.nullCheck()) {
            this.toggle();
        }
        MessageUtil.sendClientMessage("Starting DiscordRPC...");
        DiscordUtil.start();
    }
    
    public void onDisable() {
        MessageUtil.sendClientMessage("Stopping DiscordRPC...");
        DiscordUtil.stop();
    }
}
