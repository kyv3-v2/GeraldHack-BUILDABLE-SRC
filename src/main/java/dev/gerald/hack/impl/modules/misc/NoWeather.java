//Sponsored by pingbypasser#5926

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
