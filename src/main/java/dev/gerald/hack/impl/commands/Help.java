//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.commands;

import dev.gerald.hack.api.command.*;
import com.mojang.realmsclient.gui.*;
import dev.gerald.hack.api.util.*;
import dev.gerald.hack.*;
import org.lwjgl.input.*;
import java.util.*;

public class Help extends Command
{
    public Help() {
        super("help", "Displays info and shit about the client.");
    }
    
    public void execute(final String[] var1) {
        MessageUtil.sendRawMessage(ChatFormatting.GREEN + "GeraldHack" + ChatFormatting.GRAY + "()" + ChatFormatting.RESET + " {");
        MessageUtil.sendRawMessage("    Creator: " + ChatFormatting.GREEN + "Guess?");
        MessageUtil.sendRawMessage("    Command Prefix: " + ChatFormatting.GREEN + Gerald.CLIENT_PREFIX);
        MessageUtil.sendRawMessage("    ClickGUI Bind: " + ChatFormatting.GREEN + Keyboard.getKeyName(Gerald.INSTANCE.moduleManager.getModuleByName("ClickGui").getKeybind()));
        MessageUtil.sendRawMessage("    " + ChatFormatting.GREEN + "Commands" + ChatFormatting.GRAY + "()" + ChatFormatting.RESET + " {");
        for (final Command command : Gerald.INSTANCE.commandManager.commands) {
            MessageUtil.sendRawMessage("        " + ChatFormatting.GREEN + command.getName() + ChatFormatting.RESET + ": " + command.getDescription());
        }
        MessageUtil.sendRawMessage("    }");
        MessageUtil.sendRawMessage("}");
    }
}
