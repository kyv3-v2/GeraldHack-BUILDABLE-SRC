//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.commands;

import dev.gerald.hack.api.command.*;
import com.mojang.realmsclient.gui.*;
import dev.gerald.hack.api.util.*;
import dev.gerald.hack.*;
import dev.gerald.hack.api.module.*;
import java.util.*;

public class ModuleList extends Command
{
    public ModuleList() {
        super("modulelist", "Lists all modules in the client.");
    }
    
    public void execute(final String[] var1) {
        MessageUtil.sendRawMessage(ChatFormatting.GRAY + "Modules:");
        for (final Module module : Gerald.INSTANCE.moduleManager.getModules()) {
            MessageUtil.sendRawMessage((module.isEnabled() ? ChatFormatting.GREEN : ChatFormatting.RED) + "[" + module.getName() + "]");
        }
    }
}
