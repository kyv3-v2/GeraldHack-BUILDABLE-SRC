//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.player;

import dev.gerald.hack.api.module.*;

public class Suicide extends Module
{
    public Suicide() {
        super("Suicide", Module.Category.PLAYER, "Suicide is badass.");
    }
    
    public void onEnable() {
        this.mc.player.sendChatMessage("/kill");
        this.toggle();
    }
}
