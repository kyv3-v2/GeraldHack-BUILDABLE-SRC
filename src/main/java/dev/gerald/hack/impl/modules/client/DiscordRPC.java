//Sponsored by pingbypasser#5926

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
