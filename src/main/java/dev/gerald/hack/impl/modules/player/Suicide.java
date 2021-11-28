//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

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
