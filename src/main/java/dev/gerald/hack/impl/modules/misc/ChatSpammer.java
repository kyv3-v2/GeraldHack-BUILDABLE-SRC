//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.misc;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.util.*;
import dev.gerald.hack.api.setting.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.apache.commons.lang3.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ChatSpammer extends Module
{
    public NumSetting delay;
    public BoolSetting randomLetters;
    public TimerUtil timer;
    IntegerUtil messageCounter;
    
    public ChatSpammer() {
        super("ChatSpammer", Module.Category.MISC, "Very basic chat spammer.");
        this.delay = (NumSetting)this.register((Setting)new NumSetting("Delay", 2.0f, 0.0f, 5.0f));
        this.randomLetters = (BoolSetting)this.register((Setting)new BoolSetting("RandomLetters", true, false));
        this.timer = new TimerUtil();
        this.messageCounter = new IntegerUtil(0);
    }
    
    public String getMetaData() {
        return (this.messageCounter.getValue() != 0) ? ("[" + ChatFormatting.GRAY + this.messageCounter.getValue() + ChatFormatting.RESET + "]") : "";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.PlayerTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (this.timer.passedMs((long)this.delay.getValue() * 1000L)) {
            this.mc.player.sendChatMessage("Gerald(Hack) on top!" + (this.randomLetters.getValue() ? (" | " + RandomStringUtils.random(6, true, true)) : ""));
            this.messageCounter.increase(1);
            this.timer.reset();
        }
    }
    
    public void onDisable() {
        this.messageCounter.reset();
    }
}
