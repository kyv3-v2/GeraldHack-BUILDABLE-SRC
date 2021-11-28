//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.misc;

import dev.gerald.hack.api.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoWeather extends Module
{
    public NoWeather() {
        super("NoWeather", Module.Category.MISC, "Makes the weather do shit.");
        this.setNeedsKeybindSetting(false);
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event) {
        this.mc.world.setRainStrength(0.0f);
    }
}
