//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.client;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.impl.events.*;
import com.mojang.realmsclient.gui.*;
import dev.gerald.hack.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Messages extends Module
{
    public Messages() {
        super("Messages", Module.Category.CLIENT, "Sends messages and shit.");
        this.setNeedsKeybindSetting(false);
    }
    
    @SubscribeEvent
    public void onModuleEnable(final ModuleToggleEvent.Enable event) {
        MessageUtil.sendRawMessage(ChatFormatting.BOLD + event.getModule().getName() + ChatFormatting.RESET + ChatFormatting.GREEN + " ENABLED!");
    }
    
    @SubscribeEvent
    public void onModuleDisable(final ModuleToggleEvent.Disable event) {
        MessageUtil.sendRawMessage(ChatFormatting.BOLD + event.getModule().getName() + ChatFormatting.RESET + ChatFormatting.RED + " DISABLED!");
    }
}
