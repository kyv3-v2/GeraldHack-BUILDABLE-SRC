//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.misc;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.util.*;
import dev.gerald.hack.api.setting.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Donda extends Module
{
    public NumSetting delay;
    public TimerUtil timer;
    IntegerUtil dondaCounter;
    
    public Donda() {
        super("Donda", Module.Category.MISC, "Donda...");
        this.delay = (NumSetting)this.register((Setting)new NumSetting("Delay", 2.0f, 0.0f, 5.0f));
        this.timer = new TimerUtil();
        this.dondaCounter = new IntegerUtil(0);
    }
    
    public String getMetaData() {
        return (this.dondaCounter.getValue() != 0) ? ("[" + ChatFormatting.GRAY + this.dondaCounter.getValue() + ChatFormatting.RESET + "]") : "";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.PlayerTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (this.timer.passedMs((long)this.delay.getValue() * 1000L)) {
            this.mc.player.sendChatMessage(">Donda");
            this.dondaCounter.increase(1);
            this.timer.reset();
        }
    }
    
    public void onDisable() {
        this.dondaCounter.reset();
    }
}
