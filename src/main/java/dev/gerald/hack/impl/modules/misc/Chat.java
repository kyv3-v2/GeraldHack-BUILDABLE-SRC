//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.misc;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import java.text.*;
import java.util.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.text.*;

public class Chat extends Module
{
    public BoolSetting suffix;
    public BoolSetting timeStamp;
    
    public Chat() {
        super("Chat", Module.Category.MISC, "Changes chat shit.");
        this.suffix = (BoolSetting)this.register((Setting)new BoolSetting("Suffix", true, false));
        this.timeStamp = (BoolSetting)this.register((Setting)new BoolSetting("Timestamp", true, false));
    }
    
    @SubscribeEvent
    public void onChat(final ClientChatEvent event) {
        if (event.getOriginalMessage().startsWith("/") || event.getOriginalMessage().startsWith("!")) {
            return;
        }
        if (this.suffix.getValue()) {
            event.setMessage(event.getOriginalMessage() + " \u0262\u1d07\u0280\u1d00\u029f\u1d05\u029c\u1d00\u1d04\u1d0b");
        }
    }
    
    @SubscribeEvent
    public void onChatReceived(final ClientChatReceivedEvent event) {
        final SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        final String timeString = time.format(new Date());
        if (this.timeStamp.getValue()) {
            event.setMessage(new TextComponentString(ChatFormatting.GRAY + "<" + ChatFormatting.GREEN + timeString + ChatFormatting.GRAY + "> " + ChatFormatting.RESET).appendSibling(event.getMessage()));
        }
    }
}
